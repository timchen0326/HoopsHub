package app;

import interface_adapter.search.SearchViewModel;
import use_case.note.search.SearchInteractor;
import data_access.DBSearchDataAccessObject;
import view.SearchPanel;

public class MainSearchApplication {
    public static void main(String[] args) {
        DBSearchDataAccessObject dataAccess = new DBSearchDataAccessObject();
        SearchViewModel viewModel = new SearchViewModel();
        SearchInteractor interactor = new SearchInteractor(dataAccess, viewModel);

        // Launch the GUI
        new SearchPanel(interactor);
    }
}
