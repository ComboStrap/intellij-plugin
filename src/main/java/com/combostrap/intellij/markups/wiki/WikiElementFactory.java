package com.combostrap.intellij.markups.wiki;

import com.combostrap.intellij.markups.wiki.psi.WikiProperty;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;

public class WikiElementFactory {


    public static WikiProperty createProperty(Project project, String name) {
        final WikiFile file = createFile(project, name);
        return (WikiProperty) file.getFirstChild();
    }

    public static WikiFile createFile(Project project, String text) {
        String name = "dummy.simple";
        return (WikiFile) PsiFileFactory.getInstance(project).createFileFromText(name, WikiFileType.INSTANCE, text);
    }

    public static WikiProperty createProperty(Project project, String name, String value) {
        final WikiFile file = createFile(project, name + " = " + value);
        return (WikiProperty) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final WikiFile file = createFile(project, "\n");
        return file.getFirstChild();
    }


}
