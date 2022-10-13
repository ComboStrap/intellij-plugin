package com.combostrap.intellij.markups.wiki;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class WikiNamedElementImpl extends ASTWrapperPsiElement implements WikiNamedElement {

    public WikiNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

}
