package com.parser.domain;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Sentence {

    private final List<String[]> wordsList = new LinkedList<>();

    public List<String[]> getWordsList() {
        return wordsList;
    }
}
