package use_case.search;

/**
 * Input boundary interface for executing a search operation.
 */
public interface SearchInputBoundary {

    /**
     * Executes a search based on the provided username.
     *
     * @param username the username to search for; must not be null
     * @return the search result as a string; may return an empty string if no results are found
     */
    String executeSearch(String username);
}
