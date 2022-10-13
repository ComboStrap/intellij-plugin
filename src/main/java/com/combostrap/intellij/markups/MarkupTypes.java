// This is a generated file. Not intended for manual editing.
package com.combostrap.intellij.markups;

import com.combostrap.intellij.markups.wiki.WikiElement;
import com.combostrap.intellij.markups.wiki.psi.impl.WikiPropertyImpl;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface MarkupTypes {

  IElementType PROPERTY = new WikiElement("PROPERTY");




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
