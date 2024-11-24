package interface_adapter.PlayGameAspects;

import use_case.playgame.FetchPlayerStatsUseCase;

public class FetchPlayerStatsController {
    private final FetchPlayerStatsUseCase useCase;

    public FetchPlayerStatsController(FetchPlayerStatsUseCase useCase) {
        this.useCase = useCase;
    }

    public String fetchPlayerStatisticsByYear(String playerName, String year) {
        return useCase.execute(playerName, year);
    }
}