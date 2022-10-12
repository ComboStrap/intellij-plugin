package com.combostrap.intellij.markups.wiki;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class WikiFile extends PsiFileBase {

    public WikiFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, WikiLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return WikiFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Wiki File";
    }

}
