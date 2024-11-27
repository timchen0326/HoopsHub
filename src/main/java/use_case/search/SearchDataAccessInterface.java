package use_case.search;

import java.util.List;

import entity.SearchResult;

/**
 * SearchDataAccessInterface defines the contract for fetching search data
 * based on a given username.
 */
public interface SearchDataAccessInterface {

    /**
     * Fetches a list of search results for the specified username.
     *
     * @param username the username to search for; must not be null
     * @return a list of search results, or an empty list if no results are found
     */
    List<SearchResult> fetchData(String username);
}
