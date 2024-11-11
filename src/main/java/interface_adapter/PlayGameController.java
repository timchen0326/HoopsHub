package interface_adapter;

import use_case.note.FetchPlayerStatisticsInputBoundary;

import java.util.List;

public class PlayGameController {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public PlayGameController(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public String fetchPlayerStatistics(String playerName) {
        return interactor.fetchPlayerStatistics(playerName);
    }

    public List<String> getAvailableYears(String playerName) {
        return interactor.getAvailableYears(playerName);
    }

    public String fetchPlayerStatisticsByYear(String playerName, String year) {
        return interactor.fetchPlayerStatisticsByYear(playerName, Integer.parseInt(year));
    }
    
}
