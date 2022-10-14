package com.combostrap.intellij.markups;

import com.combostrap.intellij.markups.wiki.WikiParserDefinition;
import com.intellij.testFramework.ParsingTestCase;

/**
 * https://plugins.jetbrains.com/docs/intellij/parsing-test.html#define-a-parsing-test
 */
public class MarkupParserTest extends ParsingTestCase {

    /**
     * This is a test to run
     * Note that 'Base' is the name of the searched file
     *
     * This function will load the `Base.wiki` file
     * from the {@link #getTestDataPath() test data path}
     */
    public void testBase() {
        doTest(true);
    }

    public MarkupParserTest() {
        /**
         * @param dataPath is the base above the `TestDataPath` directory
         * @param fileExt is the extension file
         */
        super("", "wiki", new WikiParserDefinition());
    }

    /**
     * @return path to test data file directory relative to root of this module.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/testData";
    }


}
