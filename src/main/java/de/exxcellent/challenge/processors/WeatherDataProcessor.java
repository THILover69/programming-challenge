package de.exxcellent.challenge.processors;

import de.exxcellent.challenge.models.WeatherDataEntry;
import java.util.List;


public class WeatherDataProcessor {

    private List<WeatherDataEntry> weatherDataEntryList;


    public WeatherDataProcessor(List<WeatherDataEntry> weatherDataEntryList) {
        this.weatherDataEntryList = weatherDataEntryList;
    }


    public void setWeatherDataEntryList(List<WeatherDataEntry> weatherDataEntryList) {
        this.weatherDataEntryList = weatherDataEntryList;
    }


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
