package use_case.search;

import java.time.LocalDateTime;

/**
 * Contains the input data for the search history use case.
 */
public class SearchHistoryInputData {

    private final String query;
    private final LocalDateTime timestamp;

    public SearchHistoryInputData(String query, LocalDateTime timestamp) {
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
