package de.exxcellent.challenge.processors;

import de.exxcellent.challenge.models.FootballDataEntry;

import java.util.List;

public class FootballDataProcessor {

    private List<FootballDataEntry> footballDataEntries;

    public FootballDataProcessor(List<FootballDataEntry> footballDatumEntries) {
        this.footballDataEntries = footballDatumEntries;
    }

    public void setFootballDataEntries(List<FootballDataEntry> footballDatumEntries) {
        this.footballDataEntries = footballDatumEntries;
    }

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
