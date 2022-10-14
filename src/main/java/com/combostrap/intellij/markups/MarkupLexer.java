package com.combostrap.intellij.markups;

import com.combostrap.intellij.markups.wiki.WikiToken;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import com.nobigsoftware.dfalex.DfaBuilder;
import com.nobigsoftware.dfalex.DfaState;
import com.nobigsoftware.dfalex.Pattern;
import com.nobigsoftware.dfalex.StringMatcher;
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

    private static final int WIKI_HEADING = 1;

    private static final DfaState<Integer> dfa;

    static {
        DfaBuilder<Integer> builder = new DfaBuilder<>();
        builder.addPattern(Pattern.regex("={1,6}"), WIKI_HEADING);

        dfa = builder.build(null);
    }

    private StringMatcher matcher;
    private Integer state;


    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {

        this.buffer = buffer;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.initialState = initialState;


        this.matcher = new StringMatcher(String.valueOf(buffer));
        this.matcher.setPositions(startOffset, endOffset, Integer.MAX_VALUE);

    }

    @Override
    public int getState() {

        return this.state;

    }

    /**
     * Returns the token at the current position of the lexer or {@code null} if lexing is finished.
     *
     * This function is called first (to get the document element i assume), then {@link #advance()}
     * See {@link com.intellij.lang.impl.TokenSequence#performLexing(CharSequence, Lexer)}
     *
     * @return the current token.
     */
    @SuppressWarnings("UnstableApiUsage")
    @Override
    public @Nullable IElementType getTokenType() {

        if(this.state==null){
            return null;
        }
        switch (state){
            case WIKI_HEADING:
                return new WikiToken("HEADING");
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }

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


        return this.endOffset;
    }

    /**
     * Advances the lexer to the next token.
     */
    @Override
    public void advance() {

        this.state = matcher.findNext(dfa);


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
