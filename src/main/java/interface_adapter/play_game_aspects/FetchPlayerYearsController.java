package interface_adapter.play_game_aspects;

import java.util.List;

import use_case.playgame.FetchPlayerYearsUseCase;

/**
 * Controller for fetching available years for a player.
 * Acts as an intermediary between the user interface and the use case layer.
 */
public class FetchPlayerYearsController {
    private final FetchPlayerYearsUseCase fetchPlayerYearsUseCase;

    /**
     * Constructs a FetchPlayerYearsController with the given use case.
     *
     * @param fetchPlayerYearsUseCase the use case for fetching available years for a player
     */
    public FetchPlayerYearsController(FetchPlayerYearsUseCase fetchPlayerYearsUseCase) {
        this.fetchPlayerYearsUseCase = fetchPlayerYearsUseCase;
    }

    /**
     * Retrieves the available years for the specified player.
     *
     * @param playerName the name of the player
     * @return a list of available years for the player
     */
    public List<String> getAvailableYears(String playerName) {
        return fetchPlayerYearsUseCase.execute(playerName);
    }
}
