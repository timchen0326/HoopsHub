package use_case.playgame;

/**
 * Use case for fetching the average statistic for a player.
 * Acts as an intermediary between the controller and the interactor.
 */
public class GetAverageStatUseCase {
    private final FetchPlayerStatisticsInputBoundary interactor;

    /**
     * Constructs a GetAverageStatUseCase with the given interactor.
     *
     * @param interactor the interactor for fetching player statistics
     */
    public GetAverageStatUseCase(FetchPlayerStatisticsInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the use case to fetch the average statistic for the specified player, year, and statistic type.
     *
     * @param statType the type of statistic to fetch
     * @param playerName the name of the player
     * @param year the year for which to fetch the statistic
     * @return the average statistic value
     */
    public double execute(String statType, String playerName, String year) {
        return interactor.getAverageStat(playerName, year, statType);
    }
}
