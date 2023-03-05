package com.parser.writer;

import com.parser.utility.SentencesToWords;
import com.parser.utility.WordChecks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The sorted words are passed to CSVWriter class which writes to temp CSV file
 */
@Component
public class CSVWriter {
    private static final Logger logger = LogManager.getLogger(CSVWriter.class);

    private final CSVHeaderWriter csvHeaderWriter;
    private final SentencesToWords sentencesToWords;
    private String tempOutputFile;
    private long i = 1;

    public CSVWriter(CSVHeaderWriter csvHeaderWriter, SentencesToWords sentencesToWords) {
        this.csvHeaderWriter = csvHeaderWriter;
        this.sentencesToWords = sentencesToWords;
    }

    public void createCSVDocument(String outputFile) {
        File tempFile = new File(outputFile);
        tempOutputFile = outputFile.replaceAll(tempFile.getName(), "temp.csv");
        try (BufferedWriter ignored = new BufferedWriter(new FileWriter(tempOutputFile))) {
            logger.info("Writing to CSV document");
        } catch (IOException ex) {
            logger.error("Error creating CSV document", ex);
        }
    }

    public void CSVConverter(List<String[]> words) {
        WordChecks wordChecks = new WordChecks();
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(tempOutputFile, true))) {
            for (String[] wordsArray : words) {
                csvWriter.write("Sentence " + i);
                for (String word : wordsArray) {
                    word = wordChecks.csvSpecialCharacterCheck(word);
                    csvWriter.write(", " + word);
                }
                i++;
                csvWriter.newLine();
            }
        } catch (IOException e) {
            logger.error("An Exception occurred while writing to CSV document", e);
        }
    }

    public void closeCSVDocument(String outputFile) {
        try {
            csvHeaderWriter.addCSVHeader(sentencesToWords.getMaxWordCount(), outputFile);
            logger.info("CSV conversion completed");
            logger.info("Data written to {} successfully!", new File(outputFile).getAbsolutePath() + ".csv");
        } catch (IOException ex) {
            logger.error("Error in closing CSV document" + ex);
        }
    }
}