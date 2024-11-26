package data_access;

import java.util.List;
import java.util.stream.Collectors;

import entity.PlayerStatistic;
import entity.PlayerStatisticFactory;
import interface_adapter.play_game_aspects.PlayerStatisticsRepository;

/**
 * Implementation of the PlayerStatisticsRepository interface.
 * This class fetches player statistics data from an API.
 */
public class PlayerStatisticsRepositoryImpl implements PlayerStatisticsRepository {
    private final PlayerStatisticsApi api;

    /**
     * Constructs a new PlayerStatisticsRepositoryImpl.
     * Initializes the PlayerStatisticsApi instance.
     */
    public PlayerStatisticsRepositoryImpl() {
        this.api = new PlayerStatisticsApi();
    }

    /**
     * Fetches all statistics for a given player.
     *
     * @param playerName the name of the player whose statistics are to be fetched
     * @return a list of PlayerStatistic objects containing the player's statistics
     * @throws RuntimeException if an error occurs while fetching the data
     */
    @Override
    public List<PlayerStatistic> fetchAllStatisticsForPlayer(String playerName) {
        try {
            final String rawData = api.fetchPlayerData(playerName);
            return PlayerStatisticFactory.fromJson(rawData);
        }
        catch (Exception ex) {
            throw new RuntimeException("Error fetching all statistics for player: " + playerName, ex);
        }
    }

    /**
     * Fetches statistics for a given player for a specific year.
     *
     * @param playerName the name of the player whose statistics are to be fetched
     * @param year the year for which the statistics are to be fetched
     * @return a PlayerStatistic object containing the player's, or null if no matching year is found
     * @throws RuntimeException if an error occurs while fetching the data
     */

    @Override
    public PlayerStatistic fetchStatsForPlayerByYear(String playerName, int year) {
        try {
            final List<PlayerStatistic> stats = fetchAllStatisticsForPlayer(playerName);
            return stats.stream()
                    .filter(stat -> stat.getSeason() == year)
                    .findFirst()
                    .orElse(null);
        }
        catch (Exception ex) {
            throw new RuntimeException("Error fetching statistics for player by year: "
                    + playerName + ", year: " + year, ex);
        }
    }

    /**
     * Fetches the available years for which statistics are available for a given player.
     *
     * @param playerName the name of the player whose available years are to be fetched
     * @return a list of integers representing the available years
     * @throws RuntimeException if an error occurs while fetching the data
     */
    @Override
    public List<Integer> fetchAvailableYearsForPlayer(String playerName) {
        try {
            final List<PlayerStatistic> stats = fetchAllStatisticsForPlayer(playerName);
            return stats.stream()
                    .map(PlayerStatistic::getSeason)
                    .collect(Collectors.toList());
        }
        catch (Exception ex) {
            throw new RuntimeException("Error fetching available years for player: " + playerName, ex);
        }
    }
}
