package de.exxcellent.challenge.processors;

import de.exxcellent.challenge.models.WeatherDataEntry;
import java.util.List;

/**
 * This class processes weather data and provides methods to analyze it
 */
public class WeatherDataProcessor {

    private List<WeatherDataEntry> weatherDataEntryList;

    /**
     * Creates a new processor with the given list of weather data entries.
     *
     * @param weatherDataEntryList the list of entries to use
     */
    public WeatherDataProcessor(List<WeatherDataEntry> weatherDataEntryList) {
        this.weatherDataEntryList = weatherDataEntryList;
    }

    /**
     * Sets or replaces the list of weather data entries.
     *
     * @param weatherDataEntryList the new list of entries
     */
    public void setWeatherDataEntryList(List<WeatherDataEntry> weatherDataEntryList) {
        this.weatherDataEntryList = weatherDataEntryList;
    }

    /**
     * Finds the day with the lowest temperature spread.
     * If more than one entry has the same difference, the first one is returned.
     *
     * @return the day with the lowest temperature spread
     * @throws IllegalStateException if the list is not set or empty
     */
    public int getDayWithLowestTempSpread() {
        if (weatherDataEntryList == null || weatherDataEntryList.isEmpty()) {
            throw new IllegalStateException("Weather data entries are not set or empty.");
        }
        int lowestSpread = Integer.MAX_VALUE;
        int dayWithLowestSpread = -1;
        for (WeatherDataEntry data : weatherDataEntryList) {
            int spread = data.getMaxTemp() - data.getMinTemp();
            if (spread < lowestSpread) {
                lowestSpread = spread;
                dayWithLowestSpread = data.getDay();
            }
        }
        return dayWithLowestSpread;
    }
}
