package com.parser;

import com.parser.utility.ArgumentsCheck;
import org.junit.Assert;
import org.junit.Test;

public class ArgumentsCheckTest {

    @Test
    public void testUsageMessage() {
        ArgumentsCheck argumentsCheck = new ArgumentsCheck();
        String expected = "<-i> <path/inputFile.extension> <-o> <path/outputFile>";
        String actual = argumentsCheck.getUsageMessage();

        Assert.assertEquals(expected, actual);
    }
}