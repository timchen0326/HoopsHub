package interface_adapter.PlayGameAspects;

import use_case.playgame.FetchPlayerYearsUseCase;

import java.util.List;

public class FetchPlayerYearsController {
    private final FetchPlayerYearsUseCase fetchPlayerYearsUseCase;

    public FetchPlayerYearsController(FetchPlayerYearsUseCase fetchPlayerYearsUseCase) {
        this.fetchPlayerYearsUseCase = fetchPlayerYearsUseCase;
    }

    public List<String> getAvailableYears(String playerName) {
        return fetchPlayerYearsUseCase.execute(playerName);
    }
}