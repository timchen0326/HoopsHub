package interface_adapter.PlayGameAspects;

public interface PlayerStatisticsGateway {
    /**
     * Fetches raw player data as a JSON string for the given player name.
     *
     * @param playerName The name of the player.
     * @return A JSON string representing the player's statistics.
     * @throws Exception If the data fetching fails.
     */
    String fetchPlayerData(String playerName) throws Exception;
}
