package de.exxcellent.challenge.processors;

import de.exxcellent.challenge.models.FootballDataEntry;
import java.util.List;

/**
 * This class processes football data and provides methods to analyze it
 */
public class FootballDataProcessor {

    private List<FootballDataEntry> footballDataEntries;

    /**
     * Creates a new processor with the given list of football data entries.
     *
     * @param footballDataEntries the list of entries to use
     */
    public FootballDataProcessor(List<FootballDataEntry> footballDataEntries) {
        this.footballDataEntries = footballDataEntries;
    }

    /**
     * Sets or replaces the list of football data entries.
     *
     * @param footballDataEntries the new list of entries
     */
    public void setFootballDataEntries(List<FootballDataEntry> footballDataEntries) {
        this.footballDataEntries = footballDataEntries;
    }

    /**
     * Finds the team with the smallest absolute goal difference.
     * If more than one team has the same difference, the first one is returned.
     *
     * @return the name of the team with the smallest goal difference
     * @throws IllegalStateException if the list is not set or empty
     */
    public String getTeamWithSmallestGoalDifference() {
        if (footballDataEntries == null || footballDataEntries.isEmpty()) {
            throw new IllegalStateException("Football data entries are not set or empty.");
        }
        String closestTeam = null;
        int smallestGoalDifference = Integer.MAX_VALUE;

        for (FootballDataEntry data : footballDataEntries) {
            int goalDifference = Math.abs(data.getGoals() - data.getGoalsAllowed());
            if (goalDifference < smallestGoalDifference) {
                smallestGoalDifference = goalDifference;
                closestTeam = data.getTeam();
            }
        }
        return closestTeam;
    }
}
