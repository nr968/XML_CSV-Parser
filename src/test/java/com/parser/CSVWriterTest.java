package com.parser;

import com.parser.writer.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSVWriterTest {

    private final Logger logger = LogManager.getLogger(CSVWriterTest.class);

    @Test
    public void testCSVConverter() {
        List<String[]> words = new LinkedList<>();
        words.add(new String[]{"a", "because", "Chinese", "couldn't", "I", "isn't", "mother", "my", "perhaps",
                "tongue",
                "understand", "word"});
        String expected = "Sentence 1, a, because, Chinese, couldn't, I, isn't, mother, my, perhaps, tongue, " +
                "understand, word";
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.createCSVDocument("test_csvConverter.csv");
        try{
            csvWriter.CSVConverter(words);
            String actual;
            BufferedReader reader = new BufferedReader(new FileReader("temp.csv"));
            while ((actual = reader.readLine()) != null) {
                Assert.assertEquals(expected, actual);
            }
        }catch (IOException ex){
            logger.error("An exception occurred while reading the file {0}",ex);
        }
    }
}