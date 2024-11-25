package interface_adapter.PlayGameAspects;

import use_case.playgame.FetchPlayerStatsUseCase;
import entity.PlayerStatistic;

public class FetchPlayerStatsController {
    private final FetchPlayerStatsUseCase fetchPlayerStatsUseCase;

    public FetchPlayerStatsController(FetchPlayerStatsUseCase fetchPlayerStatsUseCase) {
        this.fetchPlayerStatsUseCase = fetchPlayerStatsUseCase;
    }

    public PlayerStatistic fetchPlayerStatisticsByYear(String playerName, String year) {
        return fetchPlayerStatsUseCase.execute(playerName, year);
    }
}