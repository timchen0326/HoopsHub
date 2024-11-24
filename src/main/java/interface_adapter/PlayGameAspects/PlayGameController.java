package interface_adapter.PlayGameAspects;

import use_case.playgame.FetchPlayerStatisticsInputBoundary;
import use_case.playgame.FetchPlayerYearsUseCase;
import use_case.playgame.FetchPlayerStatsUseCase;
import use_case.playgame.GetAverageStatUseCase;

import java.util.List;

public class PlayGameController {
    private final FetchPlayerYearsController fetchPlayerYearsController;
    private final FetchPlayerStatsController fetchPlayerStatsController;
    private final GetAverageStatController getAverageStatController;

    public PlayGameController(FetchPlayerStatisticsInputBoundary interactor) {
        FetchPlayerYearsUseCase fetchPlayerYearsUseCase = new FetchPlayerYearsUseCase(interactor);
        FetchPlayerStatsUseCase fetchPlayerStatsUseCase = new FetchPlayerStatsUseCase(interactor);
        GetAverageStatUseCase getAverageStatUseCase = new GetAverageStatUseCase(interactor);

        this.fetchPlayerYearsController = new FetchPlayerYearsController(fetchPlayerYearsUseCase);
        this.fetchPlayerStatsController = new FetchPlayerStatsController(fetchPlayerStatsUseCase);
        this.getAverageStatController = new GetAverageStatController(getAverageStatUseCase);
    }

    public List<String> getAvailableYears(String playerName) {
        return fetchPlayerYearsController.getAvailableYears(playerName);
    }

    public String fetchPlayerStatisticsByYear(String playerName, String year) {
        return fetchPlayerStatsController.fetchPlayerStatisticsByYear(playerName, year);
    }

    public double getAverageStatistic(String statType, String playerName, String year) {
        return getAverageStatController.getAverageStatistic(statType, playerName, year);
    }
}