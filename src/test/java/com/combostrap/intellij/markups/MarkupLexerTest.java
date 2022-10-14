package com.combostrap.intellij.markups;

import com.combostrap.intellij.markups.wiki.WikiLexer;
import com.intellij.lang.impl.TokenSequence;
import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.psi.tree.IElementType;
import com.nobigsoftware.dfalex.DfaBuilder;
import com.nobigsoftware.dfalex.DfaState;
import com.nobigsoftware.dfalex.Pattern;
import com.nobigsoftware.dfalex.StringMatcher;
import junit.framework.TestCase;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MarkupLexerTest extends TestCase {


    public void testRegexp() throws IOException {

        MarkupLexer myLexer = new MarkupLexer();
        Path path = Paths.get("src", "test", "testData", "Base.wiki");
        if (!Files.exists(path)) {
            throw new FileNotFoundException(path.toAbsolutePath().toString());
        }
        CharSequence result = Files.readString(path);

        /**
         * Code from {@link TokenSequence#Builder}
         */
        myLexer.start(result);
        int i = 0;
        List<Integer> myLexStarts = new ArrayList<>();
        List<String> myLexCaptures = new ArrayList<>();
        List<IElementType> myLexTypes = new ArrayList<>();
        while (true) {
            IElementType type = myLexer.getTokenType();
            if (type == null) break;

            if (i % 20 == 0) ProgressIndicatorProvider.checkCanceled();

            int tokenStart = myLexer.getTokenStart();
            int tokenEnd = myLexer.getTokenEnd();
            myLexStarts.add(tokenStart);
            myLexTypes.add(type);
            String tokenText = myLexer.getTokenText();
            myLexCaptures.add(tokenText);
            i++;
            myLexer.advance();
        }
        myLexStarts.add(result.length());

        /**
         * Test
         */
        assertEquals(3, myLexTypes.size());


    }

    /**
     * http://mtimmerm.github.io/dfalex/
     * <p>
     * Regex: Expression:
     * https://github.com/mtimmerm/dfalex/blob/master/src/com/nobigsoftware/dfalex/RegexParser.java
     */
    public void testDfaLex() {
        DfaState<Integer> dfa;
        {
            DfaBuilder<Integer> builder = new DfaBuilder<>();
            builder.addPattern(Pattern.regex("=|==|===|====|=====|======"), 1);
            builder.addPattern(Pattern.regex("aa"), 2);
            dfa = builder.build(null);
        }
        StringMatcher matcher = new StringMatcher("== Hallo ===");
        while (true) {
            Integer result = matcher.findNext(dfa);
            if (result == null) {
                break;
            }
            Assert.assertEquals((Integer) 2, result);
            String lastMatch = matcher.getLastMatch();
            Assert.assertEquals("aa", lastMatch);
            int start = matcher.getLastMatchStart();
            Assert.assertEquals(6, start);
            int end = matcher.getLastMatchEnd();
            Assert.assertEquals(8, end);
        }

    }
}
