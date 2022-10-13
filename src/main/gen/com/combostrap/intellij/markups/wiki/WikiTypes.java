// This is a generated file. Not intended for manual editing.
package com.combostrap.intellij.markups.wiki;

import com.combostrap.intellij.markups.MarkupToken;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.combostrap.intellij.markups.wiki.psi.impl.*;

public interface WikiTypes {

  IElementType PROPERTY = new WikiElement("PROPERTY");

  IElementType COMMENT = new MarkupToken("COMMENT");
  IElementType CRLF = new MarkupToken("CRLF");
  IElementType KEY = new MarkupToken("KEY");
  IElementType SEPARATOR = new MarkupToken("SEPARATOR");
  IElementType VALUE = new MarkupToken("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new WikiPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
