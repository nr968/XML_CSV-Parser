package com.parser;

import com.parser.utility.SentencesToWords;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SentenceToWordsTest {

    @Test
    public void testSortWords() {
        SentencesToWords sentencesToWords = new SentencesToWords();

        StringBuilder paragraph = new StringBuilder("What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者! I couldn't understand a word,perhaps because Chinese   isn't my mother tongue. I was just standing there watching Mr. Young marching around - he  was    furious.   Why was he directing  his  anger at me? Little did I know \tabout \tthat.");

        List<String[]> expected = new LinkedList<>();
        expected.add(new String[]{"he", "shocking", "shouted", "was", "What", "你这肮脏的掠夺者", "停在那儿"});
        expected.add(new String[]{"a", "because", "Chinese", "couldn't", "I", "isn't", "mother", "my", "perhaps", "tongue", "understand", "word"});
        expected.add(new String[]{"around", "furious", "he", "I", "just", "marching", "Mr.", "standing", "there", "was", "was", "watching", "Young"});
        expected.add(new String[]{"anger", "at", "directing", "he", "his", "me", "was", "Why"});
        expected.add(new String[]{"about", "did", "I", "know", "Little", "that"});

        String[] sentences = sentencesToWords.splitParagraph(paragraph);
        int i = 0;
        for (String sentence : sentences) {
            String[] actualWords = sentencesToWords.splitSentence(sentence);
            String[] expectedWords = expected.get(i);
            int j = 0;
            for (String actualWord : actualWords) {
                Assert.assertEquals(expectedWords[j], actualWord);
                j++;
            }
            i++;
        }
    }
}