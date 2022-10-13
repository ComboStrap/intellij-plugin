// This is a generated file. Not intended for manual editing.
package com.combostrap.intellij.markups.wiki.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.combostrap.intellij.markups.wiki.WikiTypes.*;
import com.combostrap.intellij.markups.wiki.WikiNamedElementImpl;
import com.combostrap.intellij.markups.wiki.psi.*;
import com.combostrap.intellij.markups.wiki.WikiPsiImplUtil;
import com.intellij.navigation.ItemPresentation;

public class WikiPropertyImpl extends WikiNamedElementImpl implements WikiProperty {

  public WikiPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull WikiVisitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof WikiVisitor) accept((WikiVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getKey() {
    return WikiPsiImplUtil.getKey(this);
  }

  @Override
  public String getValue() {
    return WikiPsiImplUtil.getValue(this);
  }

  @Override
  public String getName() {
    return WikiPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return WikiPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return WikiPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public ItemPresentation getPresentation() {
    return WikiPsiImplUtil.getPresentation(this);
  }

}
