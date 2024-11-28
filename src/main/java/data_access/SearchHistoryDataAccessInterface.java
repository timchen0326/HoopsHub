package data_access;

import java.util.List;

import entity.SearchHistoryRecord;

/**
 * Defines the interface for accessing search history data.
 */
public interface SearchHistoryDataAccessInterface {

    /**
     * Saves a search history record.
     *
     * @param searchRecord the search history record to save
     */
    void saveSearchHistory(SearchHistoryRecord searchRecord);

    /**
     * Retrieves all search history records.
     *
     * @return a list of all search history records
     */
    List<SearchHistoryRecord> getSearchHistory();
}
