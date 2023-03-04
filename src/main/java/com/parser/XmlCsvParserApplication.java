package com.parser;

import com.parser.service.FileParser;
import com.parser.utility.ArgumentsCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.parser")
public class XmlCsvParserApplication implements ApplicationRunner{

	@Autowired
	private FileParser fileParser;
	@Autowired
	private ArgumentsCheck argumentsCheck;

	public static void main(String[] args) {
		SpringApplication.run(XmlCsvParserApplication.class, args);
	}

	public void run(ApplicationArguments arguments){
		if(argumentsCheck.validateArgument(arguments.getSourceArgs()) == null){
			System.exit(1);
		}else {
			fileParser.parseInputFile(argumentsCheck.validateArgument(arguments.getSourceArgs()));
		}
	}

}
