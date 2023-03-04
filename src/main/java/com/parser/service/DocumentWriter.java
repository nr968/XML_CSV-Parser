package com.parser.service;

import com.parser.writer.CSVWriter;
import com.parser.domain.Sentence;
import com.parser.writer.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DocumentWriter {
    @Autowired
    private XMLWriter xmlWriter;
    @Autowired
    private CSVWriter csvWriter;

    public void createDocument(String option, String outputFile) {
        switch (option) {
            case "xml_csv":
                xmlWriter.createXMLDocument(outputFile + ".xml");
                csvWriter.createCSVDocument(outputFile + ".csv");
                break;
            case "xml":
                xmlWriter.createXMLDocument(outputFile + ".xml");
                break;
            case "csv":
                csvWriter.createCSVDocument(outputFile + ".csv");
                break;
        }
    }

    public void writeToDocument(String outputFile, String option, Sentence sentenceObj) {
        switch (option) {
            case "xml_csv":
                xmlWriter.XMLConverter(outputFile + ".xml", sentenceObj.getWordsList());
                csvWriter.CSVConverter(sentenceObj.getWordsList());
                break;
            case "xml":
                xmlWriter.XMLConverter(outputFile + ".xml", sentenceObj.getWordsList());
                break;
            case "csv":
                csvWriter.CSVConverter(sentenceObj.getWordsList());
                break;
        }
    }

    public void closeDocument(String option, String outputFile) throws IOException {
        switch (option) {
            case "xml_csv":
                xmlWriter.closeXMLDocument(outputFile + ".xml");
                csvWriter.closeCSVDocument(outputFile);
                break;
            case "xml":
                xmlWriter.closeXMLDocument(outputFile + ".xml");
                break;
            case "csv":
                csvWriter.closeCSVDocument(outputFile);
                break;
        }
    }
}
