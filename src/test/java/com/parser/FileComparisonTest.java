package com.parser;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class FileComparisonTest {

    @Test
    public void xmlFileComparison() throws IOException, URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("small_expected.xml");
        assert resourceUrl != null;
        byte[] expectedFileBytes = Files.readAllBytes(Paths.get(resourceUrl.toURI()));
        String expectedFileContents = new String(expectedFileBytes);

        resourceUrl = getClass().getClassLoader().getResource("small_test.xml");
        assert resourceUrl != null;
        byte[] actualFileBytes = Files.readAllBytes(Paths.get(resourceUrl.toURI()));
        String actualFileContents = new String(actualFileBytes);

        assertEquals("The contents of the output file do not match the expected file", expectedFileContents,
                actualFileContents);
    }

    @Test
    public void csvFileComparison() throws IOException, URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("small_expected.csv");
        assert resourceUrl != null;
        byte[] expectedFileBytes = Files.readAllBytes(Paths.get(resourceUrl.toURI()));
        String expectedFileContents = new String(expectedFileBytes);

        resourceUrl = getClass().getClassLoader().getResource("small_test.csv");
        assert resourceUrl != null;
        byte[] actualFileBytes = Files.readAllBytes(Paths.get(resourceUrl.toURI()));
        String actualFileContents = new String(actualFileBytes);

        assertEquals("The contents of the output file do not match the expected file", expectedFileContents,
                actualFileContents);
    }
}