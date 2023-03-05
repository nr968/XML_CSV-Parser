package com.parser;

import com.parser.service.FileParser;
import com.parser.utility.ArgumentsCheck;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Entry Point for the application
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.parser")
public class XmlCsvParserApplication implements ApplicationRunner {

    private final FileParser fileParser;
    private final ArgumentsCheck argumentsCheck;

    public XmlCsvParserApplication(FileParser fileParser, ArgumentsCheck argumentsCheck) {
        this.fileParser = fileParser;
        this.argumentsCheck = argumentsCheck;
    }

    public static void main(String[] args) {
        SpringApplication.run(XmlCsvParserApplication.class, args);
    }

    public void run(ApplicationArguments arguments) {
        if (null == argumentsCheck.validateArgument(arguments.getSourceArgs())) {
            System.exit(1);
        } else {
            fileParser.parseInputFile(argumentsCheck.validateArgument(arguments.getSourceArgs()));
        }
    }
}