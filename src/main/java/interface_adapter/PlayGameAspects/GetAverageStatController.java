package interface_adapter.PlayGameAspects;

import use_case.playgame.GetAverageStatUseCase;

public class GetAverageStatController {
    private final GetAverageStatUseCase useCase;

    public GetAverageStatController(GetAverageStatUseCase useCase) {
        this.useCase = useCase;
    }

    public double getAverageStatistic(String statType, String playerName, String year) {
        return useCase.execute(statType, playerName, year);
    }
}