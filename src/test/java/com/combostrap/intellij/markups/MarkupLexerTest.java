package com.combostrap.intellij.markups;

import com.combostrap.intellij.markups.wiki.WikiLexer;
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

        WikiLexer wikiLexer = new WikiLexer(null);
        Path path = Paths.get("src","test","testData","Base.wiki");
        if(!Files.exists(path)){
            throw new FileNotFoundException(path.toAbsolutePath().toString());
        }

        CharSequence result = Files.readString(path);
        wikiLexer.reset(result,0,result.length(),0);
        List<IElementType> tokens = new ArrayList<>();
        List<CharSequence> texts = new ArrayList<>();
        while (true){
            IElementType token = wikiLexer.advance();
            if(token==null){
                break;
            }
            tokens.add(token);
            CharSequence text = wikiLexer.yytext();
            texts.add(text);
        }

    }

    /**
     * http://mtimmerm.github.io/dfalex/
     *
     * Regex: Expression:
     * https://github.com/mtimmerm/dfalex/blob/master/src/com/nobigsoftware/dfalex/RegexParser.java
     */
    public void testDfaLex() {
        DfaState<Integer> dfa;
        {
            DfaBuilder<Integer> builder = new DfaBuilder<>();
            builder.addPattern(Pattern.regex("cc"), 1);
            builder.addPattern(Pattern.regex("aa"), 2);
            dfa = builder.build(null);
        }
        StringMatcher matcher = new StringMatcher("bbbbbaabbbbabbbba");
        while(true) {
            Integer result = matcher.findNext(dfa);
            if(result==null){
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
