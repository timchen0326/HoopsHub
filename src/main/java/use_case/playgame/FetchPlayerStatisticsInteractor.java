package use_case.playgame;

import java.util.List;
import java.util.stream.Collectors;

import entity.PlayerStatistic;
import interface_adapter.play_game_aspects.PlayerStatisticsRepository;

/**
 * Interactor class for fetching player statistics.
 * Implements the FetchPlayerStatisticsInputBoundary interface.
 */
public class FetchPlayerStatisticsInteractor implements FetchPlayerStatisticsInputBoundary {
    private static final String DATABASE_ERROR_MESSAGE = "Database error";
    private final PlayerStatisticsRepository repository;
    private final FetchPlayerStatisticsOutputBoundary outputBoundary;
    /**
     * Constructs a FetchPlayerStatisticsInteractor with the given repository and output boundary.
     *
     * @param repository the repository for fetching player statistics
     * @param outputBoundary the output boundary for presenting errors
     */

    public FetchPlayerStatisticsInteractor(PlayerStatisticsRepository repository,
                                           FetchPlayerStatisticsOutputBoundary outputBoundary) {
        this.repository = repository;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Fetches all statistics for a given player.
     *
     * @param playerName the name of the player
     * @return a list of PlayerStatistic objects, or null if an error occurs
     */

    @Override
    public List<PlayerStatistic> fetchPlayerStatistics(String playerName) {
        List<PlayerStatistic> result = null;
        try {
            result = repository.fetchAllStatisticsForPlayer(playerName);
        }
        catch (RuntimeException ex) {
            outputBoundary.presentError(DATABASE_ERROR_MESSAGE);
        }
        return result;
    }

    /**
     * Fetches statistics for a given player and year.
     *
     * @param playerName the name of the player
     * @param year the year of the statistics
     * @return a PlayerStatistic object, or null if an error occurs
     */
    @Override
    public PlayerStatistic fetchPlayerStatisticsByYear(String playerName, int year) {
        PlayerStatistic result = null;
        try {
            result = repository.fetchStatsForPlayerByYear(playerName, year);
        }
        catch (RuntimeException ex) {
            outputBoundary.presentError(DATABASE_ERROR_MESSAGE);
        }
        return result;
    }

    /**
     * Fetches available years for a given player.
     *
     * @param playerName the name of the player
     * @return a list of years as strings, or null if an error occurs
     */
    @Override
    public List<String> getAvailableYears(String playerName) {
        List<String> result = null;
        try {
            final List<Integer> years = repository.fetchAvailableYearsForPlayer(playerName);
            result = years.stream().map(String::valueOf).collect(Collectors.toList());
        }
        catch (RuntimeException ex) {
            outputBoundary.presentError(DATABASE_ERROR_MESSAGE);
        }
        return result;
    }

    /**
     * Calculates the average statistic for a given player, year, and statistic type.
     *
     * @param playerName the name of the player
     * @param year the year of the statistics
     * @param statType the type of statistic to calculate
     * @return the average value of the specified statistic
     * @throws IllegalArgumentException if the year or statistic type is invalid
     */
    @Override
    public double getAverageStat(String playerName, String year, String statType) {
        final PlayerStatistic stat = repository.fetchStatsForPlayerByYear(playerName, Integer.parseInt(year));
        if (stat == null) {
            throw new IllegalArgumentException("Invalid year");
        }

        double result = 0.0;

        switch (statType) {
            case "Average Points":
                result = stat.getPoints() / stat.getGamesPlayed();
                break;
            case "Average Rebounds":
                result = stat.getTotalRebounds() / stat.getGamesPlayed();
                break;
            case "Average Assists":
                result = stat.getAssists() / stat.getGamesPlayed();
                break;
            default:
                throw new IllegalArgumentException("Invalid stat type");
        }

        return result;
    }
}
