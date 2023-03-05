package com.parser.utility;

import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * The contents read in the FileParser class is passed here and this class splits the lines to sentences, sentences to words and sorts the words
 */
@Component
public class SentencesToWords {
    private static int maxWordCount = 0;

    public int getMaxWordCount() {
        return maxWordCount;
    }

    public String[] splitParagraph(StringBuilder paragraph) {
        return Stream.of(paragraph.toString().split("(?<!Mr|Mrs)[?!.]\\s+")).toArray(String[]::new);
    }

    public String[] splitSentence(String sentence) {
        String[] sortedWords = Stream.of(sentence.split("(?<!Mr|Mrs)[\\s():.!,-]+")).sorted((s1, s2) -> {
            int result = s1.compareToIgnoreCase(s2);
            if (result == 0) {
                return s2.compareTo(s1);
            }
            return result;
        }).toArray(String[]::new);
        maxWordCount = Math.max(sortedWords.length, maxWordCount);
        return sortedWords;
    }
}