package use_case.playgame;

import entity.PlayerStatistic;

/**
 * Use case for fetching player statistics by year.
 * Acts as an intermediary between the controller and the interactor.
 */
public class FetchPlayerStatsUseCase {
    private final FetchPlayerStatisticsInputBoundary interactor;

    public FetchPlayerStatsUseCase(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the use case to fetch player statistics for the specified year.
     *
     * @param playerName the name of the player
     * @param year the year for which to fetch statistics
     * @return the player's statistics for the specified year
     */
    public PlayerStatistic execute(String playerName, String year) {
        return interactor.fetchPlayerStatisticsByYear(playerName, Integer.parseInt(year));
    }
}
