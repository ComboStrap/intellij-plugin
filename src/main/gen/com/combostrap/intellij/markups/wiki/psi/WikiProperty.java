// This is a generated file. Not intended for manual editing.
package com.combostrap.intellij.markups.wiki.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.combostrap.intellij.markups.wiki.WikiNamedElement;
import com.intellij.navigation.ItemPresentation;

public interface WikiProperty extends WikiNamedElement {

  String getKey();

  String getValue();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

  ItemPresentation getPresentation();

}
