package com.parser.service;

import com.parser.domain.Sentence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Takes the input file name from the command line argument and reads the contents of the file
 */
@Service
public class FileParser {
    private final Logger logger = LogManager.getLogger(FileParser.class);
    private final DocumentWriter documentWriter;
    private final SentencesToWordsService sentencesToWordsService;

    public FileParser(DocumentWriter documentWriter, SentencesToWordsService sentencesToWordsService) {
        this.documentWriter = documentWriter;
        this.sentencesToWordsService = sentencesToWordsService;
    }

    public void parseInputFile(String[] arguments) {
        String line;
        StringBuilder paragraph = new StringBuilder();

        String inputFile = sentencesToWordsService.pathFormatter(arguments[0]);
        String outputFile = sentencesToWordsService.pathFormatter(arguments[1]);
        String option = arguments[2];

        logger.info("Loading {} file", inputFile);
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            logger.info("Parsing {} file", inputFile);
            documentWriter.createDocument(option, outputFile);
            while ((line = br.readLine()) != null) {
                if (!line.endsWith(".")) {
                    paragraph.append(line);
                    continue;
                }
                paragraph.append(line).append(" ");
                Sentence sentenceObj = sentencesToWordsService.splitAndSort(paragraph);
                paragraph.setLength(0);
                documentWriter.writeToDocument(outputFile, option, sentenceObj);
            }

            documentWriter.closeDocument(option, outputFile);

        } catch (OutOfMemoryError ex) {
            logger.error("Input file size is huge {0}", ex);
        } catch (IOException ex) {
            logger.error("File not found! {0}", ex);
        }
    }
}