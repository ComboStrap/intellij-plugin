package com.combostrap.intellij.markups;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * https://plugins.jetbrains.com/docs/intellij/implementing-lexer.html#lexer-state
 *
 * The lexical analyzer defines how the contents of a file are broken into tokens
 *
 * An essential requirement for a syntax highlighting lexer is that its state must be represented by
 * a single integer number returned from `Lexer.getState()`.
 *
 * That state will be passed to the `Lexer.start()` method, along with the start offset of the fragment to process,
 * when lexing is resumed from the middle of a file.
 *
 * Lexers used in other contexts can always return 0 from getState().
 */
public class MarkupLexer extends Lexer {

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {

    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public @Nullable IElementType getTokenType() {
        return null;
    }

    @Override
    public int getTokenStart() {
        return 0;
    }

    @Override
    public int getTokenEnd() {
        return 0;
    }

    @Override
    public void advance() {

    }

    @Override
    public @NotNull LexerPosition getCurrentPosition() {
        return null;
    }

    @Override
    public void restore(@NotNull LexerPosition position) {

    }

    @Override
    public @NotNull CharSequence getBufferSequence() {
        return null;
    }

    @Override
    public int getBufferEnd() {
        return 0;
    }

}
