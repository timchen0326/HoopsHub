package use_case.search;

import java.time.LocalDateTime;

/**
 * Encapsulates the output data for the search history.
 */
public class SearchHistoryOutputData {

    private final String query;
    private final LocalDateTime timestamp;

    public SearchHistoryOutputData(String query, LocalDateTime timestamp) {
        this.query = query;
        this.timestamp = timestamp;
    }

    public String getQuery() {
        return query;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
