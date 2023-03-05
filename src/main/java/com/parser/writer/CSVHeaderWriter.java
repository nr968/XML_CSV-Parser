package com.parser.writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Creates the final output file, adds header and copies the contents of the temp Csv file to final output file
 */
@Component
public class CSVHeaderWriter {

    private static final Logger logger = LogManager.getLogger(CSVHeaderWriter.class);
    private String[] csvHeader;

    private void createHeader(int wordCount) {
        csvHeader = new String[wordCount + 1];
        Arrays.setAll(csvHeader, i -> " Word " + i);
        csvHeader[0] = "";
    }

    private void writeHeaderToOutputFile(String outputFile) {
        try (OutputStream os = Files.newOutputStream(Paths.get(outputFile + ".csv"))) {
            String header = String.join(",", csvHeader).trim();
            byte[] headerToByte = header.getBytes();
            os.write(headerToByte);
            os.write(System.getProperty("line.separator").getBytes());
        } catch (IOException ex) {
            logger.error("An Exception occurred while writing Header to CSV document {0}", ex);
        }
    }

    public void addCSVHeader(int wordCount, String outputFile) throws IOException {
        File tempFile = new File(outputFile);
        String tempOutputFile = outputFile.replaceAll(tempFile.getName(), "temp.csv");
        File srcFile = new File(tempOutputFile);
        File destFile = new File(outputFile + ".csv");
        createHeader(wordCount);
        try (InputStream is = Files.newInputStream(srcFile.toPath()); OutputStream os = new FileOutputStream(destFile, true)) {
            writeHeaderToOutputFile(outputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            Files.delete(new File(tempOutputFile).toPath());
        } catch (IOException ex) {
            logger.error("An Exception occurred while writing CSV document {0}", ex);
        }
    }
}