package interface_adapter.PlayGameAspects;

import entity.PlayerStatistic;
import use_case.playgame.FetchPlayerYearsUseCase;
import use_case.playgame.FetchPlayerStatsUseCase;
import use_case.playgame.GetAverageStatUseCase;

import java.util.List;

public class PlayGameController {
    private final FetchPlayerYearsController fetchPlayerYearsController;
    private final FetchPlayerStatsController fetchPlayerStatsController;
    private final GetAverageStatController getAverageStatController;

    public PlayGameController(FetchPlayerYearsUseCase fetchPlayerYearsUseCase,
                              FetchPlayerStatsUseCase fetchPlayerStatsUseCase,
                              GetAverageStatUseCase getAverageStatUseCase) {
        this.fetchPlayerYearsController = new FetchPlayerYearsController(fetchPlayerYearsUseCase);
        this.fetchPlayerStatsController = new FetchPlayerStatsController(fetchPlayerStatsUseCase);
        this.getAverageStatController = new GetAverageStatController(getAverageStatUseCase);
    }

    public List<String> getAvailableYears(String playerName) {
        return fetchPlayerYearsController.getAvailableYears(playerName);
    }

    public PlayerStatistic fetchPlayerStatisticsByYear(String playerName, String year) {
        return fetchPlayerStatsController.fetchPlayerStatisticsByYear(playerName, year);
    }

    public double getAverageStatistic(String statType, String playerName, String year) {
        return getAverageStatController.getAverageStatistic(statType, playerName, year);
    }
}