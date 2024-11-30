package use_case.search;

import java.util.List;

/**
 * Output data for search operations.
 */
public class SearchOutputData {
    private final List<String> results;

    /**
     * Constructs a SearchOutputData instance with the specified results.
     *
     * @param results the list of search results
     */
    public SearchOutputData(List<String> results) {
        this.results = results;
    }

    /**
     * Returns the search results.
     *
     * @return the list of search results
     */
    public List<String> getResults() {
        return results;
    }
}

