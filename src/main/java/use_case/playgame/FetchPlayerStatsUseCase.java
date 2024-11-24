package use_case.playgame;

public class FetchPlayerStatsUseCase {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public FetchPlayerStatsUseCase(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public String execute(String playerName, String year) {
        return interactor.fetchPlayerStatisticsByYear(playerName, Integer.parseInt(year));
    }
}