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

    @Override
    public String toString() {
        return "Search: " + query + " | Time: " + timestamp;
    }
}
