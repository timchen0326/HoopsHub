package use_case.playgame;

import java.util.List;
import java.util.stream.Collectors;

import entity.PlayerStatistic;
import interface_adapter.play_game_aspects.PlayerStatisticsRepository;

public class FetchPlayerStatisticsInteractor implements FetchPlayerStatisticsInputBoundary {
    private final PlayerStatisticsRepository repository;
    private final FetchPlayerStatisticsOutputBoundary outputBoundary;

    public FetchPlayerStatisticsInteractor(PlayerStatisticsRepository repository, FetchPlayerStatisticsOutputBoundary outputBoundary) {
        this.repository = repository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public List<PlayerStatistic> fetchPlayerStatistics(String playerName) {
        try {
            List<PlayerStatistic> statistics = repository.fetchAllStatisticsForPlayer(playerName);
            FetchPlayerStatisticsResponseModel responseModel = new FetchPlayerStatisticsResponseModel(playerName, statistics);
            outputBoundary.presentPlayerStatistics(responseModel);
            return statistics;
        } catch (Exception e) {
            outputBoundary.presentError(e.getMessage());
            return null;
        }
    }

    @Override
    public PlayerStatistic fetchPlayerStatisticsByYear(String playerName, int year) {
        try {
            PlayerStatistic statistic = repository.fetchStatsForPlayerByYear(playerName, year);
            if (statistic == null) {
                outputBoundary.presentError("No data available for year " + year);
                return null;
            }
            return statistic;
        } catch (Exception e) {
            outputBoundary.presentError(e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> getAvailableYears(String playerName) {
        try {
            List<Integer> years = repository.fetchAvailableYearsForPlayer(playerName);
            List<String> yearStrings = years.stream().map(String::valueOf).collect(Collectors.toList());
            outputBoundary.presentAvailableYears(yearStrings);
            return yearStrings;
        } catch (Exception e) {
            outputBoundary.presentError(e.getMessage());
            return null;
        }
    }

    @Override
    public double getAverageStat(String playerName, String year, String statType) {
        try {
            PlayerStatistic stat = repository.fetchStatsForPlayerByYear(playerName, Integer.parseInt(year));
            if (stat == null) {
                throw new IllegalArgumentException("No data available for year " + year);
            }

            double average = calculateAverage(stat, statType);
            outputBoundary.presentAverageStat(average, playerName, year, statType);
            return average;
        } catch (Exception e) {
            outputBoundary.presentError(e.getMessage());
            return -1;
        }
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