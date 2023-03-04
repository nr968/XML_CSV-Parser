package com.parser.utility;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ArgumentsCheck {
    private final Logger logger = LogManager.getLogger(ArgumentsCheck.class);
    private final String usageMessage = System.getProperty("os.name").toLowerCase().contains("mac") ? "<-i> " +
            "<path/inputFile.extension> <-o> <path/outputFile>" : "<-i> <path\\inputFile.extension> <-o> " +
            "<path\\outputFile>";

    private void printUsage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar -Xmx32m jar-name.jar " + usageMessage + " -c conversionOption (xml_csv, xml, csv)", options);
    }
    public String[] validateArgument(String[] args) {
        Options options = new Options();
        Option input = new Option("i", "inputFile", true, "Path to the inputFile");
        options.addOption(input);
        Option output = new Option("o", "outputFile", true, "path to the outputFile");
        options.addOption(output);
        Option convertOption = new Option("c", "convertOption", true, "Conversion format");
        options.addOption(convertOption);
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i") && cmd.hasOption("o") && cmd.hasOption("c")) {
                String formatOption = cmd.getOptionValue("c");
                if (formatOption.equalsIgnoreCase("xml_csv") || formatOption.equalsIgnoreCase("xml") || formatOption.equalsIgnoreCase("csv")) {
                    return new String[]{cmd.getOptionValue("i"), cmd.getOptionValue("o"), formatOption};
                } else {
                    logger.error("{} is not a valid formatOption", formatOption);
                    printUsage(options);
                    return null;
                }
            } else {
                printUsage(options);
                return null;
            }
        } catch (ParseException ex) {
            logger.error("Error in parsing arguments {0}", ex);
        }
        return null;
    }

    public String getUsageMessage() {
        return usageMessage;
    }
}
