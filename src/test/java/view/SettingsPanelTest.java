import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import view.MainFrame;
import view.SettingsPanel;
import interface_adapter.PlayGameAspects.PlayGameController;
import use_case.playgame.FetchPlayerYearsUseCase;
import use_case.playgame.FetchPlayerStatsUseCase;
import use_case.playgame.GetAverageStatUseCase;
import use_case.search.SearchInteractor;
import data_access.DBSearchDataAccessObject;
import interface_adapter.search.SearchViewModel;

public class SettingsPanelTest {

    private SettingsPanel settingsPanel;
    private JButton toggleButton;
    private JButton muteButton;

    @BeforeEach
    public void setup() {
        FetchPlayerYearsUseCase yearsUseCase = mock(FetchPlayerYearsUseCase.class);
        FetchPlayerStatsUseCase statsUseCase = mock(FetchPlayerStatsUseCase.class);
        GetAverageStatUseCase averageStatUseCase = mock(GetAverageStatUseCase.class);

        PlayGameController playGameController = new PlayGameController(yearsUseCase, statsUseCase, averageStatUseCase);

        DBSearchDataAccessObject dbSearchData = mock(DBSearchDataAccessObject.class);
        SearchViewModel searchViewModel = mock(SearchViewModel.class);

        SearchInteractor searchInteractor = new SearchInteractor(dbSearchData, searchViewModel);

        MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);
        settingsPanel = new SettingsPanel(mainFrame);
    }


    @Test
    public void testToggleTheme() {
        // Simulate button click for toggling theme
        toggleButton.doClick();

        // Validate the theme change indirectly
        assertNotNull(settingsPanel);
        assertTrue(toggleButton.getText().contains("Mode")); // Should update label to reflect theme change
    }

    @Test
    public void testMuteUnmute() {
        // Simulate button click for toggling mute state
        muteButton.doClick();

        // Validate mute/unmute state indirectly
        assertNotNull(settingsPanel);
        assertTrue(muteButton.getText().contains("Music")); // Should update label to reflect mute state
    }
}
