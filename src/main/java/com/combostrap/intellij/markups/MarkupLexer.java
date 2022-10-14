package com.combostrap.intellij.markups;

import com.combostrap.intellij.markups.wiki.WikiToken;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import com.nobigsoftware.dfalex.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * https://plugins.jetbrains.com/docs/intellij/implementing-lexer.html#lexer-state
 * <p>
 * The lexical analyzer defines how the contents of a file are broken into tokens
 * <p>
 * An essential requirement for a syntax highlighting lexer is that its state must be represented by
 * a single integer number returned from `Lexer.getState()`.
 * <p>
 * That state will be passed to the `Lexer.start()` method, along with the start offset of the fragment to process,
 * when lexing is resumed from the middle of a file.
 * <p>
 * Lexers used in other contexts can always return 0 from getState().
 */
public class MarkupLexer extends LexerBase {

    private CharSequence buffer;
    private int startOffset;
    private int endOffset;

    /**
     * State is an integer that represents
     * a matched token expressions
     */
    private int initialState;

    private static final int WIKI_HEADING_1 = 1;
    private static final int WIKI_HEADING_2 = 2;
    private static final int WIKI_HEADING_3 = 3;
    private static final int WIKI_HEADING_4 = 4;
    private static final int EOL = 5;
    private static final int TEXT = 6;
    private static final int WS = 7;

    private static final DfaState<Integer> dfa;


    static {
        DfaBuilder<Integer> builder = new DfaBuilder<>();
        builder.addPattern(Pattern.regex("======"), WIKI_HEADING_1);
        builder.addPattern(Pattern.regex("====="), WIKI_HEADING_2);
        builder.addPattern(Pattern.regex("===="), WIKI_HEADING_3);
        builder.addPattern(Pattern.regex("==="), WIKI_HEADING_4);
        builder.addPattern(Pattern.regex("\r|\n|\r\n"), EOL);
        builder.addPattern(Pattern.regex("[^\r\n]*"), TEXT);
        //  builder.addPattern(Pattern.regex("[ \t\f]"), WS);
        DfaAmbiguityResolver<Integer> dfaAmbiguityResolver = integers -> integers
                .stream().max(Integer::compare)
                .orElse(null);
        dfa = builder.build(dfaAmbiguityResolver);

    }

    private StringMatcher matcher;
    private Integer state;
    private WikiToken actualToken = null;


    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {

        this.buffer = buffer;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.initialState = initialState;


        this.matcher = new StringMatcher(String.valueOf(buffer));
        //this.matcher.setPositions(startOffset, endOffset, Integer.MAX_VALUE);

    }

    /**
     * The lexical state is an extra state to create the lexical rules.
     * <p>
     * In a lexer grammar, in addition to regular expression matches,
     * you  can use lexical states to refine a specification.
     * <p>
     * They act like a condition for a regular expression (called also a start condition)
     * <p>
     * <p>
     * <p>
     * In [JFlex](https://jflex.de/manual.html#ExampleLexRules) for instance:
     * * if the scanner is in lexical state FOO, only expressions that are preceded by the start condition <FOO> can be matched
     * * the lexical state YYINITIAL is the state in which the lexer begins scanning.
     * * if a regular expression has no start conditions it is matched in all lexical states.
     * * a start condition of a regular expression can contain more than one lexical state. It is then matched when the lexer is in any of these lexical states.
     * * the state is advertised with `yybegin(state)`
     * <p>
     * For Xml, for instance: `yybegin(CDATA)`
     * https://github.com/JetBrains/intellij-community/blob/master/xml/xml-psi-impl/src/com/intellij/lexer/_XmlLexer.flex
     */
    @Override
    public int getState() {

        return this.state;

    }

    /**
     * Returns the token at the current position of the lexer or {@code null} if lexing is finished.
     * <p>
     * This function is called first (to get the document element i assume), then {@link #advance()}
     * See {@link com.intellij.lang.impl.TokenSequence#performLexing(CharSequence, Lexer)}
     *
     * @return the current token.
     */
    @SuppressWarnings("UnstableApiUsage")
    @Override
    public @Nullable IElementType getTokenType() {

        if (this.actualToken == null) this.advance();
        return this.actualToken;

    }

    /**
     * Returns the start offset of the current token.
     *
     * @return the current token start offset.
     */
    @Override
    public int getTokenStart() {

        return matcher.getLastMatchStart();

    }

    /**
     * Returns the end offset of the current token.
     *
     * @return the current token end offset.
     */
    @Override
    public int getTokenEnd() {

        return this.matcher.getLastMatchEnd();
    }

    /**
     * Advances the lexer to the next token.
     */
    @Override
    public void advance() {

        this.state = matcher.findNext(dfa);
        if (state == null) {
            this.actualToken = null;
            return;
        }
        switch (state) {
            case WIKI_HEADING_1:
            case WIKI_HEADING_2:
            case WIKI_HEADING_3:
            case WIKI_HEADING_4:
                this.actualToken = new WikiToken("HEADING");
                break;
            case EOL:
                this.actualToken = new WikiToken("EOL");
                break;
            case TEXT:
                this.actualToken = new WikiToken("TEXT");
                break;
            case WS:
                this.actualToken = new WikiToken("WHITESPACE");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }

    }


    /**
     * Returns the buffer sequence over which the lexer is running. This method should return the
     * same buffer instance which was passed to the {@code start()} method.
     *
     * @return the lexer buffer.
     */
    @Override
    public @NotNull CharSequence getBufferSequence() {

        return this.buffer;

    }

    /**
     * Returns the offset at which the lexer will stop lexing. This method should return
     * the length of the buffer or the value passed in the {@code endOffset} parameter
     * to the {@code start()} method.
     *
     * @return the lexing end offset
     */
    @Override
    public int getBufferEnd() {

        return this.endOffset;

    }

}
