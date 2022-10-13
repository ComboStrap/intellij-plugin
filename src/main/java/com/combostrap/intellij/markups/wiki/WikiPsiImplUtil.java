package com.combostrap.intellij.markups.wiki;

import com.combostrap.intellij.markups.MarkupIcons;
import com.combostrap.intellij.markups.wiki.psi.WikiProperty;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import javax.annotation.Nullable;
import javax.swing.*;

/**
 * No idea
 * https://plugins.jetbrains.com/docs/intellij/psi-helper-and-utilities.html
 */
public class WikiPsiImplUtil {

    public static String getKey(WikiProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(WikiTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(WikiProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(WikiTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(WikiProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(WikiProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(WikiTypes.KEY);
        if (keyNode != null) {
            WikiProperty property = WikiElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(WikiProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(WikiTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final WikiProperty element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Override
            public Icon getIcon(boolean unused) {
                return MarkupIcons.WIKI;
            }
        };
    }

}
