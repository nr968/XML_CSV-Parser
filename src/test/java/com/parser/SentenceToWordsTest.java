package com.parser;

import com.parser.domain.Sentence;
import com.parser.utility.SentencesToWords;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SentenceToWordsTest {

    private final SentencesToWords sentencesToWords = new SentencesToWords();

    @Test
    public void testMacOsPathFormatter() {
        String expected = "path/to/the/files";
        String path = "path\\to\\the\\files";
        String actual = sentencesToWords.pathFormatter(path);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortWords() {
        StringBuilder paragraph = new StringBuilder("What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者! I couldn't understand a word,perhaps because Chinese   isn't my mother tongue. I was just standing there watching Mr. Young marching around - he  was    furious.   Why was he directing  his  anger at me? Little did I know \tabout \tthat.");
        List<String[]> expected = new LinkedList<>();
        expected.add(new String[]{"he", "shocking", "shouted", "was", "What", "你这肮脏的掠夺者", "停在那儿"});
        expected.add(new String[]{"a", "because", "Chinese", "couldn't", "I", "isn't", "mother", "my","perhaps",
                "tongue", "understand", "word"});
        expected.add(new String[]{"around", "furious", "he", "I", "just", "marching", "Mr.", "standing", "there", "was", "was", "watching", "Young"});
        expected.add(new String[]{"anger", "at", "directing", "he", "his", "me", "was", "Why"});
        expected.add(new String[]{"about", "did", "I", "know", "Little", "that"});
        Sentence sentenceObj = sentencesToWords.sortWords(paragraph);
        List<String[]> actual = sentenceObj.getWordsList();
        int i=0;
        for (String[] expectedWords : expected){
            String[] actualWords = actual.get(i);
            int j=0;
            for (String word:expectedWords) {
                Assert.assertEquals(word, actualWords[j]);
                j++;
            }
            i++;
        }
    }
}