package use_case.search;

import java.util.List;

import data_access.DBSearchDataAccessObject;
import entity.SearchResult;
import interface_adapter.search.SearchViewModel;

/**
 * Interactor responsible for executing search operations and
 * formatting the results.
 */
public class SearchInteractor implements SearchInputBoundary {
    private final DBSearchDataAccessObject dataAccess;
    private final SearchViewModel viewModel;

    /**
     * Constructs a SearchInteractor with the specified data access object and view model.
     *
     * @param dataAccess the data access object for retrieving search data; must not be null
     * @param viewModel  the view model for formatting the results; must not be null
     */
    public SearchInteractor(DBSearchDataAccessObject dataAccess, SearchViewModel viewModel) {
        this.dataAccess = dataAccess;
        this.viewModel = viewModel;
    }

    /**
     * Executes a search based on the provided username and returns the formatted results.
     *
     * @param username the username to search for; must not be null
     * @return the formatted search results as a string
     */
    @Override
    public String executeSearch(String username) {
        final List<SearchResult> results = dataAccess.fetchData(username);
        return viewModel.formatResults(results);
    }
}
