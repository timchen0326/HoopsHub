package use_case.search;

/**
 * Input boundary interface for executing a search operation.
 */
public interface SearchInputBoundary {
    /**
     * Executes a search based on the provided request model.
     *
     * @param requestModel the request model containing search parameters
     * @return
     */
    String executeSearch(SearchRequestModel requestModel);
}
