package de.exxcellent.challenge.processors;

import de.exxcellent.challenge.models.FootballDataEntry;
import de.exxcellent.challenge.parser.csv.CsvParser;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FootballDataProcessorTest {

    @Test
    void getTeamWithSmallestGoalDifference(){
        String filePath = "src/test/resources/de/exxcellent/challenge/football_test.csv";
        List<FootballDataEntry> footballDataEntryList = new CsvParser().parse(filePath, FootballDataEntry.class);
        FootballDataProcessor processor = new FootballDataProcessor(footballDataEntryList);
        String result = processor.getTeamWithSmallestGoalDifference();
        assertEquals("Newcastle", result);
    }

    @Test
    void shouldThrowExceptionWhenFootballDataIsEmpty() {
        FootballDataProcessor processorWithEmptyList = new FootballDataProcessor(Collections.emptyList());

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                processorWithEmptyList::getTeamWithSmallestGoalDifference
        );
        assertEquals("Football data entries are not set or empty.", exception.getMessage());
    }
}