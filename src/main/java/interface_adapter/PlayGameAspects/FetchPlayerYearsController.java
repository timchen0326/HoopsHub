package interface_adapter.PlayGameAspects;

import use_case.playgame.FetchPlayerYearsUseCase;

import java.util.List;

public class FetchPlayerYearsController {
    private final FetchPlayerYearsUseCase useCase;

    public FetchPlayerYearsController(FetchPlayerYearsUseCase useCase) {
        this.useCase = useCase;
    }

    public List<String> getAvailableYears(String playerName) {
        return useCase.execute(playerName);
    }
}