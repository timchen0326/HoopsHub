package interface_adapter.play_game_aspects;

import java.util.List;

import entity.PlayerStatistic;
import use_case.playgame.FetchPlayerStatsUseCase;
import use_case.playgame.FetchPlayerYearsUseCase;
import use_case.playgame.GetAverageStatUseCase;

/**
 * Controller for managing player statistics and available years.
 * Acts as an intermediary between the user interface and the use case layer.
 */
public class PlayGameController {
    private final FetchPlayerYearsController fetchPlayerYearsController;
    private final FetchPlayerStatsController fetchPlayerStatsController;
    private final GetAverageStatController getAverageStatController;

    /**
     * Constructs a PlayGameController with the given use cases.
     *
     * @param fetchPlayerYearsUseCase the use case for fetching available years for a player
     * @param fetchPlayerStatsUseCase the use case for fetching player statistics
     * @param getAverageStatUseCase the use case for fetching average statistics
     */
    public PlayGameController(FetchPlayerYearsUseCase fetchPlayerYearsUseCase,
                              FetchPlayerStatsUseCase fetchPlayerStatsUseCase,
                              GetAverageStatUseCase getAverageStatUseCase) {
        this.fetchPlayerYearsController = new FetchPlayerYearsController(fetchPlayerYearsUseCase);
        this.fetchPlayerStatsController = new FetchPlayerStatsController(fetchPlayerStatsUseCase);
        this.getAverageStatController = new GetAverageStatController(getAverageStatUseCase);
    }

    /**
     * Retrieves the available years for the specified player.
     *
     * @param playerName the name of the player
     * @return a list of available years for the player
     */
    public List<String> getAvailableYears(String playerName) {
        return fetchPlayerYearsController.getAvailableYears(playerName);
    }

    /**
     * Fetches the statistics for the specified player and year.
     *
     * @param playerName the name of the player
     * @param year the year for which to fetch statistics
     * @return the player's statistics for the specified year
     */
    public PlayerStatistic fetchPlayerStatisticsByYear(String playerName, String year) {
        return fetchPlayerStatsController.fetchPlayerStatisticsByYear(playerName, year);
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
        return getAverageStatController.getAverageStatistic(statType, playerName, year);
    }
}
