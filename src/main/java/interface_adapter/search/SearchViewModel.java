package interface_adapter.search;

import java.util.List;

import entity.SearchResult;

/**
 * ViewModel for formatting search results into a displayable string.
 */
public class SearchViewModel {

    /**
     * Formats the search results into a displayable string.
     *
     * @param results the list of search results; must not be null
     * @return a formatted string representation of the search results, or "No results found." if the list is empty
     */
    public String formatResults(List<SearchResult> results) {
        if (results.isEmpty()) {
            return "No results found.";
        }
        final StringBuilder builder = new StringBuilder();
        for (SearchResult result : results) {
            builder.append(result.toString()).append("\n\n");
        }
        return builder.toString();
    }
}
