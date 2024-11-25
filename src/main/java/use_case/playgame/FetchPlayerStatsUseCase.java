package use_case.playgame;

import entity.PlayerStatistic;

public class FetchPlayerStatsUseCase {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public FetchPlayerStatsUseCase(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public PlayerStatistic execute(String playerName, String year) {
        return interactor.fetchPlayerStatisticsByYear(playerName, Integer.parseInt(year));
    }
}
