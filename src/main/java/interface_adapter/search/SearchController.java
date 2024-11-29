package interface_adapter.search;

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
     * Handles a search request by creating a request model and passing it to the interactor.
     *
     * @param username the username to search for
     */
    public void executeSearch(String username) {
        SearchRequestModel requestModel = new SearchRequestModel(username);
        interactor.executeSearch(String.valueOf(requestModel));
    }
}