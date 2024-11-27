package entity;

import java.time.LocalDateTime;

/**
 * Represents a record in the search history.
 */
public class SearchHistoryRecord {
    private final String query;
    private final LocalDateTime timestamp;

    /**
     * Constructs a SearchHistoryRecord with the specified query and timestamp.
     *
     * @param query     the search query; must not be null
     * @param timestamp the timestamp of the search; must not be null
     */
    public SearchHistoryRecord(String query, LocalDateTime timestamp) {
        this.query = query;
        this.timestamp = timestamp;
    }

    /**
     * Gets the search query associated with this record.
     *
     * @return the search query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Gets the timestamp associated with this record.
     *
     * @return the timestamp of the search
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Extracts the result ("Win" or "Lose") based on the query content.
     * This assumes that the query contains "Win" or "Lose" as a keyword.
     *
     * @return "Win" if the query contains the word "win" (case insensitive),
     *         "Lose" if it contains the word "lose", or "Unknown" otherwise
     */
    public String getResult() {
        if (query.toLowerCase().contains("win")) {
            return "Win";
        }
        if (query.toLowerCase().contains("lose")) {
            return "Lose";
        }
        return "Unknown";
    }

    /**
     * Returns a string representation of the search history record.
     *
     * @return a string containing the query and timestamp
     */
    @Override
    public String toString() {
        return "Search: " + query + " | Time: " + timestamp;
    }
}
