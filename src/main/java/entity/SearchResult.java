package entity;

public class SearchResult {
    private String username;
    private String userId;
    private int wins;
    private int losses;

    public SearchResult(String username, String userId, int wins, int losses) {
        this.username = username;
        this.userId = userId;
        this.wins = wins;
        this.losses = losses;
    }

    public String getUsername() {
        return username;
    }

    public String getUserId() {
        return userId;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\nUserID: " + userId + "\nWins: " + wins + "\nLosses: " + losses;
    }
}
