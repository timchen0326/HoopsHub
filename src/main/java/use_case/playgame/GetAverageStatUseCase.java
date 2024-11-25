package use_case.playgame;

public class GetAverageStatUseCase {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public GetAverageStatUseCase(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public double execute(String statType, String playerName, String year) {
        return interactor.getAverageStat(playerName, year, statType);
    }
}