package interface_adapter;

import use_case.play_game.FetchPlayerStatisticsInputBoundary;

public class PlayGameController {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public PlayGameController(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public String fetchPlayerStatistics(String playerName) {
        return interactor.fetchPlayerStatistics(playerName);
    }
}