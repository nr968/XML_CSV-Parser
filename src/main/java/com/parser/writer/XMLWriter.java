package com.parser.writer;

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
 * The sorted words are passed to XMLWriter class which writes to XML file
 */
@Component
public class XMLWriter {
    private final Logger logger = LogManager.getLogger(XMLWriter.class);
    private final WordChecks wordChecks;

    public XMLWriter(WordChecks wordChecks) {
        this.wordChecks = wordChecks;
    }

    public void createXMLDocument(String writerFile) {
        try (BufferedWriter xmlWriter = new BufferedWriter(new FileWriter(writerFile))) {
            logger.info("Writing to XML document");
            xmlWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            xmlWriter.newLine();
            xmlWriter.write("<text>");
            xmlWriter.newLine();
        } catch (IOException ex) {
            logger.error("Error in creating output file {0}", ex);
        }
    }

    public void XMLConverter(String outputFile, List<String[]> words) {
        try (BufferedWriter xmlWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            for (String[] wordsArray : words) {
                xmlWriter.write("<sentence>");
                for (String word : wordsArray) {
                    word = wordChecks.xmlSpecialCharacterCheck(word);
                    xmlWriter.write("<word>" + word + "</word>");
                }
                xmlWriter.write("</sentence>");
                xmlWriter.newLine();
            }
        } catch (IOException ex) {
            logger.error("An Exception occurred while writing to XML Document {0}", ex);
        }
    }

    public void closeXMLDocument(String outputFile) {
        try (BufferedWriter xmlWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            xmlWriter.write("</text>");
            logger.info("XML conversion completed");
            logger.info("Data written to {} successfully!", new File(outputFile).getAbsolutePath());
        } catch (IOException ex) {
            logger.error("Error closing document" + ex);
        }
    }
}