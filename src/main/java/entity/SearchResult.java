package entity;

/**
 * Represents a search result containing user information such as username, userId, wins, and losses.
 */
public class SearchResult {

    private final String username;
    private final String userId;
    private final int wins;
    private final int losses;

    /**
     * Constructs a SearchResult with the specified details.
     *
     * @param username the username of the user; must not be null
     * @param userId   the user ID of the user; must not be null
     * @param wins     the number of wins associated with the user
     * @param losses   the number of losses associated with the user
     */
    public SearchResult(String username, String userId, int wins, int losses) {
        this.username = username;
        this.userId = userId;
        this.wins = wins;
        this.losses = losses;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user ID of the user.
     *
     * @return the user ID of the user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the number of wins associated with the user.
     *
     * @return the number of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Gets the number of losses associated with the user.
     *
     * @return the number of losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Returns a string representation of the search result.
     *
     * @return a string containing the user's details
     */
    @Override
    public String toString() {
        return "Username: " + username + "\nUserID: " + userId + "\nWins: " + wins + "\nLosses: " + losses;
    }
}

