package interface_adapter.play_game_aspects;

import entity.PlayerStatistic;
import use_case.playgame.FetchPlayerStatsUseCase;

/**
 * Controller for fetching player statistics.
 * Acts as an intermediary between the user interface and the use case layer.
 */
public class FetchPlayerStatsController {
    private final FetchPlayerStatsUseCase fetchPlayerStatsUseCase;

    /**
     * Constructs a FetchPlayerStatsController with the given use case.
     *
     * @param fetchPlayerStatsUseCase the use case for fetching player statistics
     */
    public FetchPlayerStatsController(FetchPlayerStatsUseCase fetchPlayerStatsUseCase) {
        this.fetchPlayerStatsUseCase = fetchPlayerStatsUseCase;
    }

    /**
     * Fetches the statistics of a player for a specific year.
     *
     * @param playerName the name of the player
     * @param year       the year for which statistics are fetched
     * @return the player's statistics for the specified year
     */
    public PlayerStatistic fetchPlayerStatisticsByYear(String playerName, String year) {
        return fetchPlayerStatsUseCase.execute(playerName, year);
    }
}
