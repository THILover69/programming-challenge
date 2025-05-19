package de.exxcellent.challenge.parser.csv;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;
import de.exxcellent.challenge.parser.Parser;
import de.exxcellent.challenge.parser.ParsingException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser implementation for reading and validating CSV files using OpenCSV.
 */
public class CsvParser implements Parser {

    /**
     * Parses a CSV file and maps each row to an object of the specified type.
     * Also validates that all required headers as defined by @CsvBindByName are present.
     *
     * @param filePath      The path to the CSV file.
     * @param classToParse  The class type to which each row should be mapped.
     * @param <T>           The type of the resulting object list.
     * @return A list of parsed objects.
     * @throws ParsingException If the file is not found, the header is invalid, or parsing fails.
     */
    public <T> List<T> parse(String filePath, Class<T> classToParse) throws ParsingException {
        List<String> actualHeaders = this.extractHeader(filePath);
        if (!this.checkRelevantHeaders(actualHeaders, classToParse))
        {
            throw new ParsingException("The CSV file does not contain the required headers");
        }
        try {
            return new CsvToBeanBuilder<T>(new FileReader(filePath))
                    .withType(classToParse)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            throw new ParsingException("File not found: " + filePath, e);
        } catch (IllegalStateException e) {
            throw new ParsingException("Error parsing CSV file: " + filePath, e);
        } catch (Exception e) {
            throw new ParsingException("Unexpected Error while parsing the CSV file: " + filePath, e);
        }
    }

    /**
     * Extracts the header line from the CSV file.
     *
     * @param filePath The path to the CSV file.
     * @return A list of header names.
     * @throws ParsingException If the file is not found or an error occurs while reading it.
     */
    private List<String> extractHeader(String filePath) throws ParsingException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] headerLine = reader.readNext();
            if (headerLine == null || headerLine.length == 0) {
                throw new ParsingException("CSV file has no header line: " + filePath);
            }
            List<String> headers = new ArrayList<>();
            for (String col : headerLine) {
                headers.add(col.trim());
            }
            return headers;
        } catch (FileNotFoundException e) {
            throw new ParsingException("File not found: " + filePath, e);
        } catch (Exception e) {
            throw new ParsingException("Error while extracting Header of the CSV file: " + filePath, e);
        }
    }

    /**
     * Extracts the list of relevant header names defined by @CsvBindByName in the given class.
     *
     * @param classToParse The class containing the annotated fields.
     * @param <T>          The type of the class
     * @return A list of expected header names.
     */
    private <T> List<String> getRelevantHeaders(Class<T> classToParse) {
        List<String> headers = new ArrayList<>();
        for (Field field : classToParse.getDeclaredFields()) {
            if (field.isAnnotationPresent(CsvBindByName.class)) {
                CsvBindByName annotation = field.getAnnotation(CsvBindByName.class);
                String column = annotation.column();
                headers.add(column);
            }
        }
        return headers;
    }

    /**
     * Compares the actual headers from the CSV file with the expected headers
     * defined in the class using @CsvBindByName.
     *
     * @param actualHeaders The list of header names read from the CSV file.
     * @param classToParse  The target class for mapping.
     * @param <T>           The type of the class
     * @return {@code true} if all expected headers are present; {@code false} otherwise.
     */
    private <T> boolean checkRelevantHeaders(List<String> actualHeaders, Class<T> classToParse) {
        List<String> expectedHeaders = getRelevantHeaders(classToParse);
        return actualHeaders.containsAll(expectedHeaders);
    }
}
