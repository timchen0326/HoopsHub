package use_case.playgame;

import java.util.List;

import entity.PlayerStatistic;

/**
 * Input boundary interface for fetching player statistics.
 * Defines the methods required for retrieving player statistics.
 */
public interface FetchPlayerStatisticsInputBoundary {

    /**
     * Fetches all statistics for the specified player.
     *
     * @param playerName the name of the player
     * @return a list of all statistics for the player
     */
    List<PlayerStatistic> fetchPlayerStatistics(String playerName);

    /**
     * Fetches the statistics for the specified player and year.
     *
     * @param playerName the name of the player
     * @param year the year for which to fetch statistics
     * @return the player's statistics for the specified year
     */
    PlayerStatistic fetchPlayerStatisticsByYear(String playerName, int year);

    /**
     * Fetches the available years for the specified player.
     *
     * @param playerName the name of the player
     * @return a list of available years for the player
     */
    List<String> getAvailableYears(String playerName);

    /**
     * Retrieves the average statistic for the specified player, year, and statistic type.
     *
     * @param playerName the name of the player
     * @param year the year for which to fetch the statistic
     * @param statType the type of statistic to fetch
     * @return the average statistic value
     */
    double getAverageStat(String playerName, String year, String statType);
}
