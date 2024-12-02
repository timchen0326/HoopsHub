package use_case.search;

/**
 * Defines the boundary for processing input data related to search history.
 */
public interface SearchHistoryInputBoundary {

    /**
     * Adds a new search query to the history.
     *
     * @param inputData the data for the search query to add
     */
    void addSearchHistory(SearchHistoryInputData inputData);
}
