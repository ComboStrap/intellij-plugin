package com.combostrap.intellij.markups;

import com.combostrap.intellij.markups.wiki.WikiLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * https://plugins.jetbrains.com/docs/intellij/grammar-and-parser.html#define-a-token-type
 */
public class MarkupToken extends IElementType {

    public MarkupToken(@NonNls @NotNull String debugName) {
        super(debugName, WikiLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "WikiTokenType." + super.toString();
    }
}
