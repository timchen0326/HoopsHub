package entity;

import java.time.LocalDateTime;

/**
 * Represents a record in the search history.
 */
public class SearchHistoryRecord {
    private final String query;
    private final LocalDateTime timestamp;

    public SearchHistoryRecord(String query, LocalDateTime timestamp) {
        this.query = query;
        this.timestamp = timestamp;
    }

    public String getQuery() {
        return query;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Extracts the result ("Win" or "Lose") based on the query content.
     * This assumes that the query contains "Win" or "Lose" as a keyword.
     */
    public String getResult() {
        if (query.toLowerCase().contains("win")) {
            return "Win";
        } else if (query.toLowerCase().contains("lose")) {
            return "Lose";
        } else {
            return "Unknown"; // Fallback if neither "Win" nor "Lose" is present
        }
    }

    @Override
    public String toString() {
        return "Search: " + query + " | Time: " + timestamp;
    }
}
