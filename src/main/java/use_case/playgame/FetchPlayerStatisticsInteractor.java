package use_case.playgame;

import entity.PlayerStatistic;
import interface_adapter.PlayGameAspects.PlayerStatisticsRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Integer> years = repository.fetchAvailableYearsForPlayer(playerName);
        return years.stream().map(String::valueOf).collect(Collectors.toList());
    }

    @Override
    public double getAverageStat(String playerName, String year, String statType) {
        PlayerStatistic stat = repository.fetchStatsForPlayerByYear(playerName, Integer.parseInt(year));
        if (stat == null) {
            throw new IllegalArgumentException("No data available for year " + year);
        }

        return calculateAverage(stat, statType);
    }

    private double calculateAverage(PlayerStatistic stat, String statType) {
        switch (statType) {
            case "Average Rebounds":
                return (double) stat.getTotalRebounds() / stat.getGamesPlayed();
            case "Average Points":
                return (double) stat.getPoints() / stat.getGamesPlayed();
            case "Average Assists":
                return (double) stat.getAssists() / stat.getGamesPlayed();
            default:
                throw new IllegalArgumentException("Invalid statistic type.");
        }
    }
}
