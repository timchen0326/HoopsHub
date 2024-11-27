package use_case.playgame;

import java.util.List;

/**
 * Use case for fetching available years for a player.
 * Acts as an intermediary between the controller and the interactor.
 */
public class FetchPlayerYearsUseCase {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public FetchPlayerYearsUseCase(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the use case to fetch available years for the specified player.
     *
     * @param playerName the name of the player
     * @return a list of available years for the player
     */
    public List<String> execute(String playerName) {
        return interactor.getAvailableYears(playerName);
    }
}
