package interface_adapter.play_game_aspects;

import java.util.List;

import use_case.playgame.FetchPlayerStatisticsOutputBoundary;
import use_case.playgame.FetchPlayerStatisticsResponseModel;

/**
 * Presenter class for fetching player statistics.
 * Implements the FetchPlayerStatisticsOutputBoundary interface.
 */
public class FetchPlayerStatisticsPresenter implements FetchPlayerStatisticsOutputBoundary {

    /**
     * Presents the player statistics.
     *
     * @param responseModel the response model containing player statistics
     */
    @Override
    public void presentPlayerStatistics(FetchPlayerStatisticsResponseModel responseModel) {
        System.out.println("Statistics for " + responseModel.getPlayerName(
        ) + ": " + responseModel.getPlayerStatistics());
    }

    /**
     * Presents the available years for a player.
     *
     * @param years a list of available years as strings
     */
    @Override
    public void presentAvailableYears(List<String> years) {
        System.out.println("Available years: " + String.join(", ", years));
    }

    /**
     * Presents the average statistic for a player.
     *
     * @param average the average value of the statistic
     * @param playerName the name of the player
     * @param year the year of the statistic
     * @param statType the type of statistic
     */
    @Override
    public void presentAverageStat(double average, String playerName, String year, String statType) {
        System.out.println("Average " + statType + " for " + playerName + " in " + year + ": " + average);
    }

    /**
     * Presents an error message.
     *
     * @param message the error message
     */
    @Override
    public void presentError(String message) {
        System.err.println("Error: " + message);
    }
}

