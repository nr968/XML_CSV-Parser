package com.parser.service;

import com.parser.domain.Sentence;
import com.parser.utility.SentencesToWords;
import org.springframework.stereotype.Service;

/**
 * Service layer for SentenceToWords class
 */
@Service
public class SentencesToWordsService {

    private final SentencesToWords sentencesToWords;

    private final Sentence sentenceObj;

    public SentencesToWordsService(SentencesToWords sentencesToWords, Sentence sentenceObj) {
        this.sentencesToWords = sentencesToWords;
        this.sentenceObj = sentenceObj;
    }

    public Sentence splitAndSort(StringBuilder paragraph) {
        sentenceObj.getWordsList().clear();
        String[] sentences = sentencesToWords.splitParagraph(paragraph);
        for (String sentence : sentences) {
            String[] sortedWords = sentencesToWords.splitSentence(sentence);
            sentenceObj.getWordsList().add(sortedWords);
        }
        return sentenceObj;
    }

    public String pathFormatter(String path) {
        return System.getProperty("os.name").toLowerCase().contains("mac") ? path.replace('\\', '/') : path.replace('/', '\\');
    }
}