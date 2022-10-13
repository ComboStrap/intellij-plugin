// This is a generated file. Not intended for manual editing.
package com.combostrap.intellij.markups.wiki;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.combostrap.intellij.markups.wiki.psi.impl.*;

public interface WikiTypes {

  IElementType PROPERTY = new WikiElement("PROPERTY");

  IElementType COMMENT = new WikiToken("COMMENT");
  IElementType CRLF = new WikiToken("CRLF");
  IElementType KEY = new WikiToken("KEY");
  IElementType SEPARATOR = new WikiToken("SEPARATOR");
  IElementType VALUE = new WikiToken("VALUE");

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
