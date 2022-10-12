package com.combostrap.intellij.markups.wiki;

import com.combostrap.intellij.markups.MarkupIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class WikiFileType extends LanguageFileType {

    public static final WikiFileType INSTANCE = new WikiFileType();

    protected WikiFileType() {
        super(WikiLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Wiki";
    }

    @Override
    public @NotNull String getDescription() {
        return "Dokuwiki";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "wiki";
    }

    @Override
    public @Nullable Icon getIcon() {
        return MarkupIcons.WIKI;
    }


}
