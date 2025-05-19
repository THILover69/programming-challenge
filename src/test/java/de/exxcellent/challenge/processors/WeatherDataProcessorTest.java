package de.exxcellent.challenge.processors;

import de.exxcellent.challenge.models.WeatherDataEntry;
import de.exxcellent.challenge.parser.csv.CsvParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataProcessorTest {

    @Test
    void getDayWithLowestTempSpread() {
        String weatherDataFilePath = "src/test/resources/de/exxcellent/challenge/weather_test.csv";
        List<WeatherDataEntry> weatherDataEntryList = new CsvParser().parse(weatherDataFilePath, WeatherDataEntry.class);

        WeatherDataProcessor processor = new WeatherDataProcessor(weatherDataEntryList);
        int result = processor.getDayWithLowestTempSpread();
        assertEquals(2, result);
    }
}