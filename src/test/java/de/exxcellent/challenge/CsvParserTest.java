package de.exxcellent.challenge;

import de.exxcellent.challenge.models.WeatherDataEntry;
import de.exxcellent.challenge.parser.csv.CsvParser;
import de.exxcellent.challenge.parser.ParsingException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

    @Test
    void parse() {
        CsvParser csvParser = new CsvParser();
        String filePath = "src/main/resources/de/exxcellent/challenge/weather.csv";
        try {
           List<WeatherDataEntry> records = csvParser.parse(filePath, WeatherDataEntry.class);
            for (WeatherDataEntry record : records) {
                System.out.println("Day: " + record.getDay() + ", Max Temp: " + record.getMaxTemp() + ", Min Temp: " + record.getMinTemp());
            }
            assertEquals(30, records.size());
            assertEquals(1, records.get(0).getDay());
            assertEquals(30, records.get(29).getDay());
            assertEquals(88, records.get(0).getMaxTemp());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void parseWithInvalidFilePath() {
        CsvParser csvParser = new CsvParser();
        String invalidFilePath = "invalid/path/to/file.csv";
        Exception exception = assertThrows(ParsingException.class, () -> {
            csvParser.parse(invalidFilePath, WeatherDataEntry.class);
        });
        String expMessage = "File not found: " + invalidFilePath;
        String actMessage = exception.getMessage();
        assertTrue(actMessage.contains(expMessage));
    }

    @Test
    void parseWithInvalidHeader() {
        CsvParser csvParser = new CsvParser();
        String filePath = "src/test/resources/de/exxcellent/challenge/football_test_wrong_headers.csv";
        Exception exception = assertThrows(ParsingException.class, () -> {
            csvParser.parse(filePath, WeatherDataEntry.class);
        });
        String expMessage = "The CSV file does not contain the required headers";
        String actMessage = exception.getMessage();
        assertTrue(actMessage.contains(expMessage));
    }
}