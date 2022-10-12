package com.combostrap.intellij.markups;

import com.intellij.lang.Language;

public class WikiLanguage extends Language {

    public static final WikiLanguage INSTANCE = new WikiLanguage();

    protected WikiLanguage() {
        super("Wiki");
    }

}
