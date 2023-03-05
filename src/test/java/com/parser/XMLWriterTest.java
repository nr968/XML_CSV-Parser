package com.parser;

import com.parser.utility.WordChecks;
import com.parser.writer.XMLWriter;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class XMLWriterTest extends TestCase {

    private final Logger logger = LogManager.getLogger(XMLWriterTest.class);

    public void testXMLConverter() {
        WordChecks wordChecks = new WordChecks();
        List<String[]> words = new LinkedList<>();
        words.add(new String[]{"a", "because", "Chinese", "couldn't", "I", "isn't", "mother", "my", "perhaps", "tongue", "understand", "word"});
        String expected = "<sentence><word>a</word><word>because</word><word>Chinese</word><word>couldn&apos;t</word><word>I</word><word>isn&apos;t</word><word>mother</word><word>my</word><word>perhaps</word><word>tongue</word><word>understand</word><word>word</word></sentence>";
        XMLWriter xmlWriter = new XMLWriter(wordChecks);
        try {
            xmlWriter.XMLConverter("test_xmlConverter.xml", words);
            BufferedReader reader = new BufferedReader(new FileReader("test_xmlConverter.xml"));
            String actual;
            while ((actual = reader.readLine()) != null) {
                assertEquals(expected, actual);
            }
            reader.close();
        } catch (IOException ex) {
            logger.error("An exception occurred while reading the file {0}", ex);
        }
    }

    @After
    public void cleanUp() throws IOException {
        Files.delete(new File("test_xmlConverter.xml").toPath());
    }
}