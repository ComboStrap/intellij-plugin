// This is a generated file. Not intended for manual editing.
package com.combostrap.intellij.markups.wiki.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.combostrap.intellij.markups.wiki.WikiNamedElement;

public class WikiVisitor extends PsiElementVisitor {

  public void visitProperty(@NotNull WikiProperty o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull WikiNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
