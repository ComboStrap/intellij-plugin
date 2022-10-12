package com.combostrap.intellij.markups.wiki;

import com.intellij.lexer.FlexAdapter;

/**
 * https://plugins.jetbrains.com/docs/intellij/lexer-and-parser-definition.html#define-a-lexer-adapter
 */
public class WikiLexerAdapter extends FlexAdapter {

    public WikiLexerAdapter() {
        super(new WikiLexer(null));
    }
}
