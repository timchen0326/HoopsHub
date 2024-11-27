package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.SearchHistoryRecord;

/**
 * Handles storing and retrieving search history records.
 */
public class SearchHistoryDataAccessObject {
    private final List<SearchHistoryRecord> history = new ArrayList<>();

    /**
     * Saves a search history record.
     *
     * @param searchRecord the search history record to save; must not be null
     */
    public void saveSearchHistory(SearchHistoryRecord searchRecord) {
        history.add(searchRecord);
    }

    /**
     * Retrieves all search history records.
     *
     * @return a list of all search history records
     */
    public List<SearchHistoryRecord> getSearchHistory() {
        return new ArrayList<>(history);
    }
}
