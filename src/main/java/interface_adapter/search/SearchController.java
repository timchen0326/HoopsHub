package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchRequestModel;

/**
 * Controller for handling search requests.
 */
public class SearchController {

    private final SearchInputBoundary interactor;

    /**
     * Constructs a SearchController with the specified interactor.
     *
     * @param interactor the interactor responsible for handling search logic
     */
    public SearchController(SearchInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes a search operation by wrapping the username in a request model.
     *
     * @param username the username to search for
     */
    public void executeSearch(String username) {
        final SearchRequestModel requestModel = new SearchRequestModel(username);
        interactor.executeSearch(requestModel);
    }
}
