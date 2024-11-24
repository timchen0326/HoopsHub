package data_access;

import entity.SearchHistoryRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles storing and retrieving search history records.
 */
public class SearchHistoryDataAccessObject {
    private final List<SearchHistoryRecord> history = new ArrayList<>();

    /**
     * Saves a search history record.
     */
    public void saveSearchHistory(SearchHistoryRecord record) {
        history.add(record);
    }

    /**
     * Retrieves all search history records.
     */
    public List<SearchHistoryRecord> getSearchHistory() {
        return new ArrayList<>(history); // Return a copy to prevent external modification
    }
}
