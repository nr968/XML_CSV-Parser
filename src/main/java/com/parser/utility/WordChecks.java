package com.parser.utility;

import org.springframework.stereotype.Component;

/**
 * Checks the sorted words for special characters, tabs and cleans up the words
 */
@Component
public class WordChecks {

    public String xmlSpecialCharacterCheck(String wordToCheck) {
        wordToCheck = wordToCheck.replaceAll("&", "&amp;");
        wordToCheck = wordToCheck.replaceAll("\"", "&qout;");
        wordToCheck = wordToCheck.replaceAll("'", "&apos;");
        wordToCheck = wordToCheck.replaceAll("’", "&apos;");
        wordToCheck = wordToCheck.replaceAll("<", "&lt;");
        wordToCheck = wordToCheck.replaceAll(">", "&gt;");
        return wordToCheck;
    }

    public String csvSpecialCharacterCheck(String wordToCheck) {
        return wordToCheck.replaceAll("’", "'");
    }
}