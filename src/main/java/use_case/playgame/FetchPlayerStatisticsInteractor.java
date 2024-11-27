package use_case.playgame;

import java.util.List;
import java.util.stream.Collectors;

import entity.PlayerStatistic;
import interface_adapter.play_game_aspects.PlayerStatisticsRepository;

/**
 * Interactor for fetching player statistics.
 * Implements the input boundary interface to handle the use case logic.
 */
public class FetchPlayerStatisticsInteractor implements FetchPlayerStatisticsInputBoundary {
    private final PlayerStatisticsRepository repository;

    public FetchPlayerStatisticsInteractor(PlayerStatisticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PlayerStatistic> fetchPlayerStatistics(String playerName) {
        return repository.fetchAllStatisticsForPlayer(playerName);
    }

    @Override
    public PlayerStatistic fetchPlayerStatisticsByYear(String playerName, int year) {
        return repository.fetchStatsForPlayerByYear(playerName, year);
    }

    @Override
    public List<String> getAvailableYears(String playerName) {
        final List<Integer> years = repository.fetchAvailableYearsForPlayer(playerName);
        return years.stream().map(String::valueOf).collect(Collectors.toList());
    }

    @Override
    public double getAverageStat(String playerName, String year, String statType) {
        final PlayerStatistic stat = repository.fetchStatsForPlayerByYear(playerName, Integer.parseInt(year));
        if (stat == null) {
            throw new IllegalArgumentException("No data available for year " + year);
        }

        return calculateAverage(stat, statType);
    }

    private double calculateAverage(PlayerStatistic stat, String statType) {
        final double result;
        switch (statType) {
            case "Average Rebounds":
                result = (double) stat.getTotalRebounds() / stat.getGamesPlayed();
                break;
            case "Average Points":
                result = (double) stat.getPoints() / stat.getGamesPlayed();
                break;
            case "Average Assists":
                result = (double) stat.getAssists() / stat.getGamesPlayed();
                break;
            default:
                throw new IllegalArgumentException("Invalid statistic type.");
        }
        return result;
    }
}
