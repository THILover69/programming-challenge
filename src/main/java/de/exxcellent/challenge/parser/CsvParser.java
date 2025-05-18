package de.exxcellent.challenge.parser;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

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

    private <T> boolean checkRelevantHeaders(List<String> actualHeaders, Class<T> classToParse) {
        List<String> expectedHeaders = getRelevantHeaders(classToParse);
        return actualHeaders.containsAll(expectedHeaders);
    }
}
