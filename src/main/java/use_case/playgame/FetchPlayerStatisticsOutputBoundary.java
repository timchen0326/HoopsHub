package use_case.playgame;

import java.util.List;

/**
 * Output boundary interface for presenting player statistics.
 * Defines methods for sending responses to the presenter.
 */
public interface FetchPlayerStatisticsOutputBoundary {

    /**
     * Presents the player statistics.
     *
     * @param responseModel the response model containing player statistics
     */
    void presentPlayerStatistics(FetchPlayerStatisticsResponseModel responseModel);

    /**
     * Presents the available years for a player.
     *
     * @param years a list of available years as strings
     */
    void presentAvailableYears(List<String> years);

    /**
     * Presents the average statistic for a player.
     *
     * @param average the average value of the statistic
     * @param playerName the name of the player
     * @param year the year of the statistic
     * @param statType the type of statistic
     */
    void presentAverageStat(double average, String playerName, String year, String statType);

    /**
     * Presents an error message.
     *
     * @param message the error message
     */
    void presentError(String message);
}
