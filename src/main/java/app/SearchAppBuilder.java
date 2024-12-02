package app;

import data_access.DBSearchDataAccessObject;
import use_case.search.SearchInteractor;
import use_case.search.SearchViewModel;

/**
 * SearchAppBuilder is responsible for constructing and returning a
 * SearchInteractor instance with its required dependencies.
 */
public class SearchAppBuilder {

    /**
     * Builds and returns a fully initialized SearchInteractor instance.
     *
     * @return a new instance of SearchInteractor
     */
    public SearchInteractor build() {
        final DBSearchDataAccessObject dataAccess = new DBSearchDataAccessObject();
        final SearchViewModel viewModel = new SearchViewModel();
        return new SearchInteractor(dataAccess, viewModel);
    }
}
