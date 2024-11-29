package use_case.search;

import java.util.List;
import java.util.stream.Collectors;

import entity.SearchResult;
import interface_adapter.search.SearchOutputBoundary;
import interface_adapter.search.SearchOutputData;
import interface_adapter.search.SearchRequestModel;

/**
 * Interactor responsible for executing search operations.
 */
public class SearchInteractor implements SearchInputBoundary {

    private final SearchDataAccessInterface dataAccess;
    private final SearchOutputBoundary outputBoundary;

    /**
     * Constructs a SearchInteractor with the specified data access and output boundary.
     *
     * @param dataAccess the data access object for retrieving search data
     * @param outputBoundary the output boundary for presenting results
     */
    public SearchInteractor(SearchDataAccessInterface dataAccess, SearchOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Executes a search based on the provided request model.
     *
     * @param requestModel the request model containing search parameters
     */
    @Override
    public void executeSearch(SearchRequestModel requestModel) {
        // Fetch data from the data access layer
        List<SearchResult> results = dataAccess.fetchData(requestModel.getUsername());

        // Convert results to a list of strings for output
        List<String> formattedResults = results.stream()
                .map(SearchResult::toString)
                .collect(Collectors.toList());

        // Wrap results in output data
        SearchOutputData outputData = new SearchOutputData(formattedResults);

        // Pass results to the output boundary
        outputBoundary.presentResults(outputData);
    }

    @Override
    public String executeSearch(String username) {
        List<SearchResult> results = dataAccess.fetchData(username);

        if (results.isEmpty()) {
            return ""; // Return an empty string if no results are found
        }

        return results.stream()
                .map(SearchResult::toString)
                .collect(Collectors.joining("\n"));
    }

}
