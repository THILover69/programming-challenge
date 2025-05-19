package de.exxcellent.challenge.models;

import com.opencsv.bean.CsvBindByName;

/**
 * Represents a single row of weather data.
 */
public class WeatherDataEntry {

    @CsvBindByName(column = "Day")
    private int day;

    @CsvBindByName(column = "MxT")
    private int maxTemp;

    @CsvBindByName(column = "MnT")
    private int minTemp;

    /**
     * Constructor to manually create a weather data entry.
     *
     * @param day     the day of the month
     * @param maxTemp the maximum temperature on that day
     * @param minTemp the minimum temperature on that day
     */
    public WeatherDataEntry(int day, int maxTemp, int minTemp) {
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    /**
     * Default constructor. Needed for openCSV.
     */
    public WeatherDataEntry() {}

    public int getDay() {
        return day;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }
}