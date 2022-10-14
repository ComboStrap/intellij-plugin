package com.combostrap.intellij.markups.wiki;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * https://plugins.jetbrains.com/docs/intellij/grammar-and-parser.html#define-a-token-type
 */
public class WikiToken extends IElementType {

    public WikiToken(@NonNls @NotNull String debugName) {
        super(debugName, WikiLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "WikiTokenType." + super.toString();
    }
}
