package com.combostrap.intellij.markups.wiki;

import com.combostrap.intellij.markups.MarkupIcons;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 *
 * https://plugins.jetbrains.com/docs/intellij/syntax-highlighter-and-color-settings-page.html#define-a-color-settings-page
 */
public class WikiSyntaxHighlighterColorSettings implements ColorSettingsPage {

    /**
     * It is supported to group related attributes
     * like operators or braces by separating the nodes with //, e.g.:
     *
     * Operator//Plus
     * Operator//Minus
     * ...
     *
     */
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Key", WikiSyntaxHighlighter.KEY),
            new AttributesDescriptor("Separator", WikiSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Value", WikiSyntaxHighlighter.VALUE),
            new AttributesDescriptor("Bad value", WikiSyntaxHighlighter.BAD_CHARACTER)
    };

    @Override
    public @Nullable Icon getIcon() {
        return MarkupIcons.WIKI;
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter() {
        return new WikiSyntaxHighlighter();
    }

    @Override
    public @NonNls @NotNull String getDemoText() {
        return "# You are reading the \".properties\" entry.\n" +
                "! The exclamation mark can also mark text as comments.\n" +
                "website = https://en.wikipedia.org/\n" +
                "language = English\n" +
                "# The backslash below tells the application to continue reading\n" +
                "# the value onto the next line.\n" +
                "message = Welcome to \\\n" +
                "          Wikipedia!\n" +
                "# Add spaces to the key\n" +
                "key\\ with\\ spaces = This is the value that could be looked up with the key \"key with spaces\".\n" +
                "# Unicode\n" +
                "tab : \\u0009";
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Override
    public @NotNull String getDisplayName() {
        return "Wiki";
    }

}
