package com.parser;

import com.parser.utility.WordChecks;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordChecksTest {

    WordChecks wordChecks = new WordChecks();

    @Test
    public void testXmlSpecialCharacterCheck() {
        String[] words={"couldn't","you’d","sun&moon"};
        String[] expectedWords = {"couldn&apos;t","you&apos;d","sun&amp;moon"};
        String[] actualWords = {wordChecks.xmlSpecialCharacterCheck(words[0]),
                wordChecks.xmlSpecialCharacterCheck(words[1]), wordChecks.xmlSpecialCharacterCheck(words[2])};
        int i=0;
        for(String expectedWord:expectedWords){
            assertEquals(expectedWord,actualWords[i]);
            i++;
        }
    }

    @Test
    public void testCsvSpecialCharacterCheck() {
        String words="you’d";
        String expected = "you'd";
        String actual = wordChecks.csvSpecialCharacterCheck(words);
        assertEquals(expected,actual);
    }
}