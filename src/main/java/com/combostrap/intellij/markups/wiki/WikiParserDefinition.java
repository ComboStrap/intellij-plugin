package com.combostrap.intellij.markups.wiki;

import com.combostrap.intellij.markups.wiki.parser.WikiParser;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class WikiParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(WikiLanguage.INSTANCE);


    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new WikiLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new WikiParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return WikiTokenSets.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return WikiTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new WikiFile(viewProvider);
    }
}
