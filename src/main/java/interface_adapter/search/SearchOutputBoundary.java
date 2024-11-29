package interface_adapter.search;

/**
 * Output boundary interface for presenting search results.
 */
public interface SearchOutputBoundary {

    /**
     * Presents the search results.
     *
     * @param outputData the output data containing search results
     */
    void presentResults(SearchOutputData outputData);
}
