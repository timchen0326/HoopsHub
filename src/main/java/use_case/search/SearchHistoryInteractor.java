package use_case.search;

import java.time.LocalDateTime;
import java.util.List;

import data_access.SearchHistoryDataAccessObject;
import entity.SearchHistoryRecord;

/**
 * Interactor for managing search history.
 */
public class SearchHistoryInteractor {
    private final SearchHistoryDataAccessObject dataAccess;

    /**
     * Constructs a SearchHistoryInteractor with the given data access object.
     *
     * @param dataAccess the data access object for saving and retrieving search history
     */
    public SearchHistoryInteractor(SearchHistoryDataAccessObject dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * Adds a search query to the history.
     *
     * @param query the search query to add; must not be null
     */
    public void addSearchQuery(String query) {
        final SearchHistoryRecord searchRecord = new SearchHistoryRecord(query, LocalDateTime.now());
        dataAccess.saveSearchHistory(searchRecord);
    }

    /**
     * Retrieves the search history.
     *
     * @return a list of search history records
     */
    public List<SearchHistoryRecord> getSearchHistory() {
        return dataAccess.getSearchHistory();
    }
}
