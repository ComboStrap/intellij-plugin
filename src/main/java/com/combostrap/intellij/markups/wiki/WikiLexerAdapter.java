package com.combostrap.intellij.markups.wiki;

import com.intellij.lexer.FlexAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * https://plugins.jetbrains.com/docs/intellij/lexer-and-parser-definition.html#define-a-lexer-adapter
 */
public class WikiLexerAdapter extends FlexAdapter {

    public WikiLexerAdapter() {
        super(new WikiLexer(null));
    }

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        super.start(buffer, startOffset, endOffset, initialState);
    }

    @Override
    public void advance() {
        super.advance();
    }
}
