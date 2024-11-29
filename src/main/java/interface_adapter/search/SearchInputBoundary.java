package interface_adapter.search;

/**
 * Input boundary interface for executing a search operation.
 */
public interface SearchInputBoundary {

    /**
     * Executes a search based on the provided username.
     *
     * @param username the username to search for
     */
    void executeSearch(String username);
}
