package com.combostrap.intellij.markups.wiki;

import com.intellij.psi.tree.TokenSet;

/**
 * Define all sets of related token types
 */
public interface WikiTokenSets {

    TokenSet IDENTIFIERS = TokenSet.create(WikiTypes.KEY);

    TokenSet COMMENTS = TokenSet.create(WikiTypes.COMMENT);

}
