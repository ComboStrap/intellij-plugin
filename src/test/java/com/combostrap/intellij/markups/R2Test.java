package com.combostrap.intellij.markups;

import com.combostrap.intellij.markups.wiki.WikiTypes;
import com.google.re2j.Matcher;
import com.google.re2j.Pattern;
import com.intellij.psi.tree.IElementType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class R2Test {

    @Test
    public void base() {
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        m.appendTail(sb);
        System.out.println(sb);
    }

    @Test
    public void base2() {
        Pattern p = Pattern.compile("(cat)|(dog)");

        // https://javadoc.io/static/com.google.re2j/re2j/1.3/com/google/re2j/Matcher.html
        String input = "one cat two dogs in the yard";
        Matcher m = p.matcher(input);
        StringBuilder sr = new StringBuilder();

        int previousPosition = 0;
        List<IElementType> wikiTokens = new ArrayList<>();
        while (m.find()) {

            StringBuffer beforeMatchString = new StringBuffer();
            m.appendReplacement(beforeMatchString, "");
            m.appendReplacement(sr, "");
            // m.appendTail(st);
            int startPosition = m.start();
            if (startPosition > previousPosition) {
                wikiTokens.add(WikiTypes.TEXT);
            }
            String group = m.group();
            switch (group) {
                case "cat":
                    wikiTokens.add(WikiTypes.CAT);
                    break;
                case "dog":
                    wikiTokens.add(WikiTypes.DOG);
                    break;
            }
            int endPosition = m.end();

        }
        if (m.end() != input.length()) {
            wikiTokens.add(WikiTypes.TEXT);
        }
        Assert.assertEquals(5, wikiTokens.size());

    }
}
