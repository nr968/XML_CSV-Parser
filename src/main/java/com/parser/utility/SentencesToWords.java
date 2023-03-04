package com.parser.utility;

import com.parser.domain.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class SentencesToWords {
    private static int maxWordCount = 0;

    @Autowired
    private Sentence sentenceObj;

    public int getMaxWordCount() {
        return maxWordCount;
    }

    public String pathFormatter(String path) {
        return System.getProperty("os.name").toLowerCase().contains("mac") ? path.replace('\\', '/') : path.replace('/',
                '\\');
    }

    private String[] splitParagraph(StringBuilder paragraph) {
        return Stream.of(paragraph.toString()
                        .split("(?<!Mr|Mrs)[?!.]\\s+"))
                .toArray(String[]::new);
    }

    private String[] splitSentence(String sentence) {
        return Stream.of(sentence.split("(?<!Mr|Mrs)[\\s():.!,-]+"))
                .sorted((s1, s2) -> {
                    int result = s1.compareToIgnoreCase(s2);
                    if (result == 0) {
                        return s2.compareTo(s1);
                    }
                    return result;
                })
                .toArray(String[]::new);
    }

    public Sentence sortWords(StringBuilder paragraph) {
        sentenceObj.getWordsList().clear();
        String[] sentences = splitParagraph(paragraph);
        for (String sentence : sentences) {
            String[] wordSplit = splitSentence(sentence);
            maxWordCount = Math.max(wordSplit.length, maxWordCount);
            sentenceObj.getWordsList().add(wordSplit);
        }
        return sentenceObj;
    }
}
