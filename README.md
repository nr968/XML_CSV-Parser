# XML_CSV-Parser

The application converts a given input file into XML formatted file and CSV formatter file

# Pre-requisite to Build and Run

- JDK and JRE - 1.8 or higher
- Maven - 3.8.1 or higher

# Build and Run

1. Clone the repo to your local or download the project and import into your IDE
2. Build the project and generate JAR file by running the command - **mvn clean package**
3. Once build is successful, the JAR file will be available under "src/target" folder.
4. Run the jar using below command  
   **java -jar -Xmx32m jar-name.jar -i path/inputFile.extension -o path/output_file -c format(s) to convert (xml_csv, xml, csv)**
   (-Xmx32m specifies JVM to run with max heap memory of 32 megabytes)

5. Example :
- On MacOS : java -jar -Xmx32m XmlCsvParser-1.0-SNAPSHOT-jar-with-dependencies.jar -i /Users/usr/inputFiles/small.in -o /Users/usr/outputFiles/small xml_csv

- On Windows : java -jar -Xmx32m XmlCsvParser-1.0-SNAPSHOT-jar-with-dependencies.jar -i "C:\Users\username\inputFiles\small.in" -o "C:\Users\username\outputFiles\small" xml_csv

- On completion the output files will be generated in the mentioned outputFiles folder as small.xml and small.csv

# Class descriptions

- XmlCsvParserApplication.class : Entry Point for the application
- ArgumentsCheck.class : Checks for the Arguments passed in the Command Line
- FileParser.class : Takes the input file name from the command line argument and reads the contents of the file
- TextTransform.class : The contents read in the FileParser class is passed here and this class splits the lines to sentences, sentences to words and sorts the words
- Sentence.class : Model class which stores the sorted words
- DocumentWriter.class : Calls XmlWriter and CsvWriter class methods 
- WordChecks.class : Checks the sorted words for special characters, tabs and cleans up the words 
- XMLWriter.class : The sorted words are passed to XMLWriter class which writes to XML file
- CSVWriter.class : The sorted words are passed to CSVWriter class which writes to temp CSV file
- CSVHeaderWriter.class : Creates the final output file, adds header and copies the contents of the temp Csv file to final output file 
