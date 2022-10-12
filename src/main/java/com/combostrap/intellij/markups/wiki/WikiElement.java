package com.combostrap.intellij.markups.wiki;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class WikiElement extends IElementType {

    public WikiElement(@NotNull @NonNls String debugName) {
        super(debugName, WikiLanguage.INSTANCE);
    }

}
