package use_case.search;

import java.util.List;

/**
 * Defines the boundary for returning output data related to search history.
 */
public interface SearchHistoryOutputBoundary {

    /**
     * Presents the search history records.
     *
     * @param history the list of search history records
     */
    void presentSearchHistory(List<SearchHistoryOutputData> history);
}
