package interface_adapter;

import use_case.playgame.FetchPlayerStatisticsInputBoundary;

import java.util.List;
import java.util.stream.Collectors;

public class PlayGameController {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public PlayGameController(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public List<String> getAvailableYears(String playerName) {
        // Convert List<Integer> to List<String>
        return interactor.getAvailableYears(playerName)
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public String fetchPlayerStatisticsByYear(String playerName, String year) {
        return interactor.fetchPlayerStatisticsByYear(playerName, Integer.parseInt(year));
    }

    public double getAverageStatistic(String statType, String playerName, String year) {
        return interactor.getAverageStat(playerName, year, statType);
    }
}