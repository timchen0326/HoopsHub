package use_case.playgame;

import java.util.List;

public class FetchPlayerYearsUseCase {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public FetchPlayerYearsUseCase(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public List<String> execute(String playerName) {
        return interactor.getAvailableYears(playerName);
    }
}