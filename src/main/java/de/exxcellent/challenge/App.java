package de.exxcellent.challenge;

import de.exxcellent.challenge.parser.ParsingException;
import de.exxcellent.challenge.models.FootballDataEntry;
import de.exxcellent.challenge.models.WeatherDataEntry;
import de.exxcellent.challenge.parser.Parser;
import de.exxcellent.challenge.parser.ParserFactory;
import de.exxcellent.challenge.parser.ParserType;
import de.exxcellent.challenge.processors.FootballDataProcessor;
import de.exxcellent.challenge.processors.WeatherDataProcessor;

import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {
        String weatherDataFilePath = "src/main/resources/de/exxcellent/challenge/weather.csv";
        String footballDataFilePath = "src/main/resources/de/exxcellent/challenge/football.csv";
        Parser parser = ParserFactory.getParser(ParserType.CSV);
        try {
            List<WeatherDataEntry> weatherDataEntryList = parser.parse(weatherDataFilePath, WeatherDataEntry.class);
            WeatherDataProcessor weatherDataProcessor = new WeatherDataProcessor(weatherDataEntryList);
            int dayWithLowestTempSpread = weatherDataProcessor.getDayWithLowestTempSpread();
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithLowestTempSpread);
        } catch (ParsingException e) {
            System.err.println("Weather data file not found: " + e.getMessage());
            return;
        }

        try {
            List<FootballDataEntry> footballDataEntryList = parser.parse(footballDataFilePath, FootballDataEntry.class);
            FootballDataProcessor footballDataProcessor = new FootballDataProcessor(footballDataEntryList);
            String teamWithWorstGoalDifference = footballDataProcessor.getTeamWithSmallestGoalDifference();
            System.out.printf("Team with smallest goal spread       : %s%n", teamWithWorstGoalDifference);

        } catch (ParsingException e) {
            System.err.println("Football data file not found: " + e.getMessage());
        }
    }
}
