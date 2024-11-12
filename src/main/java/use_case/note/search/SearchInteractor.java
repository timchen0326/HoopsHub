package use_case.note.search;

import interface_adapter.search.SearchViewModel;
import data_access.DBSearchDataAccessObject;
import entity.SearchResult;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary {
    private final DBSearchDataAccessObject dataAccess;
    private final SearchViewModel viewModel;

    public SearchInteractor(DBSearchDataAccessObject dataAccess, SearchViewModel viewModel) {
        this.dataAccess = dataAccess;
        this.viewModel = viewModel;
    }

    @Override
    public String executeSearch(String username) {
        List<SearchResult> results = dataAccess.fetchData(username);
        return viewModel.formatResults(results);
    }
}
