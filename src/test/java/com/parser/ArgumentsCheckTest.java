package com.parser;

import com.parser.utility.ArgumentsCheck;
import org.junit.Assert;
import org.junit.Test;

public class ArgumentsCheckTest {
    private final ArgumentsCheck argumentsCheck = new ArgumentsCheck();

    @Test
    public void testUsageMessage() {
        String expected = System.getProperty("os.name").toLowerCase().contains("mac") ? "<-i> " +
                "<path/inputFile.extension> <-o> <path/outputFile>" : "<-i> <path\\inputFile.extension> <-o> " +
                "<path\\outputFile>";
        String actual = argumentsCheck.getUsageMessage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testValidateArgument() {
        String[] arguments = new String[]{"-i", "InputFile", "-o", "OutputFile", "-c", "xml_csv"};
        String[] expected = new String[]{"InputFile", "OutputFile", "xml_csv"};
        String[] actual = argumentsCheck.validateArgument(arguments);
        int i = 0;
        for (String expectedArg : expected) {
            Assert.assertEquals(expectedArg, actual[i++]);
        }
    }
}