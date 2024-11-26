package interface_adapter.play_game_aspects;

import use_case.playgame.GetAverageStatUseCase;

/**
 * Controller for fetching the average statistic of a player for a specific year.
 * Acts as an intermediary between the user interface and the use case layer.
 */
public class GetAverageStatController {
    private final GetAverageStatUseCase useCase;

    /**
     * Constructs a GetAverageStatController with the given use case.
     *
     * @param useCase the use case for fetching the average statistic
     */
    public GetAverageStatController(GetAverageStatUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Retrieves the average statistic for the specified player and year.
     *
     * @param statType the type of statistic to fetch
     * @param playerName the name of the player
     * @param year the year for which to fetch the statistic
     * @return the average statistic value
     */
    public double getAverageStatistic(String statType, String playerName, String year) {
        return useCase.execute(statType, playerName, year);
    }
}
