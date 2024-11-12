package view;

import javax.swing.*;
import java.awt.*;
import data_access.DBSearchDataAccessObject;
import interface_adapter.search.SearchViewModel;
import use_case.note.search.SearchInteractor;

public class HomePanel extends JPanel {
    public HomePanel(MainFrame frame) {
        setLayout(new GridLayout(1, 3));

        JButton playButton = new JButton("Play Game");
        playButton.addActionListener(e -> frame.switchTo("Play"));
        add(playButton);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // Provide necessary parameters to SearchInteractor
            DBSearchDataAccessObject dataAccess = new DBSearchDataAccessObject();
            SearchViewModel viewModel = new SearchViewModel();
            SearchInteractor interactor = new SearchInteractor(dataAccess, viewModel);
            new SearchPanel(interactor);
        });
        add(searchButton);

        add(new JLabel("Welcome to the Game App"));
    }
}
