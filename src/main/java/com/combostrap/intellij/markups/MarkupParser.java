package com.combostrap.intellij.markups;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 * https://plugins.jetbrains.com/docs/intellij/implementing-parser-and-psi.html#parser-implementation
 *
 * The parser works by setting pairs of markers (PsiBuilder.Marker instances)
 * within the stream of tokens received from the lexer.
 * Each pair of markers defines the range of lexer tokens
 * for a single node in the AST tree.
 * If a pair of markers is nested in another pair
 * (starts after its start and ends before its end),
 * it becomes the outer pair's child node.
 *
 * The element type for the marker pair and for the AST node created
 * from it is specified when the end marker is set, which is done
 * by making the call to PsiBuilder.Marker.done().
 * Also, it is possible to drop a start marker before its end marker has been set.
 * The drop() method drops only a single start marker without affecting any markers added after it,
 * and the rollbackTo() method drops the start marker and all markers added
 * after it and reverts the lexer position to the start marker. These methods can be used to implement lookahead when parsing.
 *
 * For re-using existing ANTLRv4 grammars, see antlr4-intellij-adaptor library.
 */
public class MarkupParser implements PsiParser {

    /**
     *
     * First, an abstract syntax tree (AST) is built, defining the structure of the program. AST nodes are created internally by the IDE and are represented by instances of the ASTNode class. Each AST node has an associated element type IElementType instance, and the element types are defined by the language plugin. The AST tree's top-level node for a file needs to have a special element type,
     * which extends the IFileElementType class.
     *
     * The AST nodes have a direct mapping to text ranges in the underlying document. The bottom-most nodes of the AST match individual tokens returned by the lexer, and higher-level nodes match multiple-token fragments. Operations performed on nodes of the AST tree, such as inserting, removing, reordering nodes,
     * and so on, are immediately reflected as changes to the underlying document's text.
     *
     * Second, a PSI, or Program Structure Interface, tree is built on top of the AST, adding semantics and methods for manipulating specific language constructs. Nodes of the PSI tree are represented by classes implementing the PsiElement interface and are created by the language plugin in the ParserDefinition.createElement() method. The top-level node of the PSI tree for a file needs to implement the PsiFile interface and is created in the ParserDefinition.createFile() method.
     *
     *
     * Parses the contents of the specified PSI builder and returns an AST tree with the
     * specified type of root element. The PSI builder contents is the entire file
     * or (if chameleon tokens are used) the text of a chameleon token which needs to
     * be reparsed.
     *
     * @param root    the type of the root element in the AST tree extends the IFileElementType class.
     * @param builder the builder which is used to retrieve the original file tokens and build the AST tree.
     * @return the root of the resulting AST tree.
     *
     *
     * Let op ! PSIbuilder
     * https://plugins.jetbrains.com/docs/intellij/implementing-parser-and-psi.html#whitespace-and-comments
     * An essential feature of PsiBuilder is its handling of whitespace and comments.
     * The types of tokens which are treated as whitespace or comments are defined by getWhitespaceTokens()
     * and getCommentTokens() in ParserDefinition.
     * PsiBuilder automatically omits whitespace and comment tokens from the stream of tokens
     * it passes to PsiParser and adjusts the token ranges of AST nodes
     * so that leading and trailing whitespace tokens are not included in the node.
     */
    @Override
    public @NotNull ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {

        builder.advanceLexer();
        /**
         * The parser must process all tokens returned by the lexer up to the end of the stream,
         * in other words, until PsiBuilder.getTokenType() returns null,
         * even if the tokens are not valid according to the language syntax.
         */
        @Nullable IElementType token = builder.getTokenType();

        return builder.getTreeBuilt();

    }



}
