package use_case.search;

import data_access.SearchHistoryDataAccessObject;
import entity.SearchHistoryRecord;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interactor for managing search history.
 */
public class SearchHistoryInteractor {
    private final SearchHistoryDataAccessObject dataAccess;

    public SearchHistoryInteractor(SearchHistoryDataAccessObject dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * Adds a search query to the history.
     */
    public void addSearchQuery(String query) {
        SearchHistoryRecord record = new SearchHistoryRecord(query, LocalDateTime.now());
        dataAccess.saveSearchHistory(record);
    }

    /**
     * Retrieves the search history.
     */
    public List<SearchHistoryRecord> getSearchHistory() {
        return dataAccess.getSearchHistory();
    }
}
