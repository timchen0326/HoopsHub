package interface_adapter.search;

import java.util.List;

/**
 * ViewModel for formatting search results into a displayable string.
 * Implements the SearchOutputBoundary interface.
 */
public class SearchViewModel implements SearchOutputBoundary {

    private String formattedResults;

    /**
     * Processes the output data and formats the results for display.
     *
     * @param outputData the output data containing search results
     */
    @Override
    public void presentResults(SearchOutputData outputData) {
        List<String> results = outputData.getResults();
        if (results.isEmpty()) {
            this.formattedResults = "No results found.";
        } else {
            StringBuilder builder = new StringBuilder();
            for (String result : results) {
                builder.append(result).append("\\n\\n");
            }
            this.formattedResults = builder.toString();
        }
    }

    /**
     * Returns the formatted search results.
     *
     * @return the formatted results as a string
     */
    public String getFormattedResults() {
        return formattedResults;
    }
}