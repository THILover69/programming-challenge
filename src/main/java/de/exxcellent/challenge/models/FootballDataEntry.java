package de.exxcellent.challenge.models;

import com.opencsv.bean.CsvBindByName;

/**
 * Represents a single row of football data.
 */
public class FootballDataEntry {

    @CsvBindByName(column = "Team")
    private String team;

    @CsvBindByName(column = "Goals")
    private int goals;

    @CsvBindByName(column = "Goals Allowed")
    private int goalsAllowed;

    /**
     * Constructor for manually creating a football data entry.
     *
     * @param team         name of the football team
     * @param goals        number of goals scored
     * @param goalsAllowed number of goals allowed
     */
    public FootballDataEntry(String team, int goals, int goalsAllowed) {
        this.team = team;
        this.goals = goals;
        this.goalsAllowed = goalsAllowed;
    }

    /**
     * Default constructor. Needed for openCSV.
     */
    public FootballDataEntry() {
    }

    public String getTeam() {
        return team;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoalsAllowed() {
        return goalsAllowed;
    }
}
