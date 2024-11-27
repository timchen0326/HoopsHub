package interface_adapter.play_game_aspects;

import java.util.List;

import entity.PlayerStatistic;

/**
 * Repository interface for fetching player statistics.
 * Provides methods to retrieve statistics for a player.
 */
public interface PlayerStatisticsRepository {

    /**
     * Fetches all statistics for the specified player.
     *
     * @param playerName the name of the player
     * @return a list of all statistics for the player
     */
    List<PlayerStatistic> fetchAllStatisticsForPlayer(String playerName);

    /**
     * Fetches the statistics for the specified player and year.
     *
     * @param playerName the name of the player
     * @param year the year for which to fetch statistics
     * @return the player's statistics for the specified year
     */
    PlayerStatistic fetchStatsForPlayerByYear(String playerName, int year);

    /**
     * Fetches the available years for the specified player.
     *
     * @param playerName the name of the player
     * @return a list of available years for the player
     */
    List<Integer> fetchAvailableYearsForPlayer(String playerName);
}
