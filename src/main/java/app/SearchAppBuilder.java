package app;

import use_case.note.search.SearchInteractor;
import interface_adapter.search.SearchViewModel;
import data_access.DBSearchDataAccessObject;

public class SearchAppBuilder {
    public SearchInteractor build() {
        DBSearchDataAccessObject dataAccess = new DBSearchDataAccessObject();
        SearchViewModel viewModel = new SearchViewModel();
        return new SearchInteractor(dataAccess, viewModel);
    }
}
