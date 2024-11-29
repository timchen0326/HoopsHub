package use_case.search;

import interface_adapter.search.SearchRequestModel;

/**
 * Input boundary interface for executing a search operation.
 */
public interface SearchInputBoundary {

    void executeSearch(SearchRequestModel requestModel);

    /**
     * Executes a search based on the provided username.
     *
     * @param username the username to search for; must not be null
     * @return the search result as a string; may return an empty string if no results are found
     */
    String executeSearch(String username);
}
