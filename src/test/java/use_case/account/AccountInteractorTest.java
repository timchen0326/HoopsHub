package use_case.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import use_case.account.AccountDataAccessInterface;
import use_case.account.AccountOutputBoundary;
import use_case.account.AccountInteractor;
import app.Session;
import entity.User;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.List;
import java.util.ArrayList;

public class AccountInteractorTest {
    @Mock
    private AccountDataAccessInterface mockDataAccess;

    @Mock
    private AccountOutputBoundary mockOutputBoundary;

    private AccountInteractor accountInteractor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        accountInteractor = new AccountInteractor(mockDataAccess, mockOutputBoundary);

        // Reset Singleton Session before each test
        Session.getInstance().setUsername(null);
        Session.getInstance().setPassword(null);
        Session.getInstance().setWin(0);
        Session.getInstance().setLose(0);
        Session.getInstance().setHistory(null);
    }

    @Test
    public void testCreateAccount_Success() throws Exception {
        // Arrange
        String username = "testUser";
        String password = "testPass";

        // Act
        accountInteractor.createAccount(username, password);

        // Assert
        verify(mockDataAccess).saveUser(any(User.class));
        assertEquals(username, Session.getInstance().getUsername());
        assertEquals(password, Session.getInstance().getPassword());
        verify(mockOutputBoundary).prepareSuccessView("Account created successfully.");
    }

    @Test
    public void testLoginUser_Success() throws Exception {
        // Arrange
        String username = "existingUser";
        String password = "correctPass";

        // Prepare mock user details JSON
        JSONObject mockUserDetails = new JSONObject();
        JSONObject infoObject = new JSONObject();
        infoObject.put("win", 5);
        infoObject.put("lose", 3);

        JSONArray historyArray = new JSONArray();
        JSONObject historyEntry = new JSONObject();
        historyEntry.put("gameId", "game1");
        historyArray.put(historyEntry);

        infoObject.put("history", historyArray);
        mockUserDetails.put("info", infoObject);

        // Setup mocks
        when(mockDataAccess.loginUser(username, password)).thenReturn(true);
        when(mockDataAccess.loadUserDetails(username)).thenReturn(mockUserDetails);

        // Act
        accountInteractor.loginUser(username, password);

        // Assert
        verify(mockDataAccess).loginUser(username, password);
        verify(mockDataAccess).loadUserDetails(username);

        Session session = Session.getInstance();
        assertEquals(username, session.getUsername());
        assertEquals(password, session.getPassword());
        assertEquals(5, session.getWin());
        assertEquals(3, session.getLose());
        assertNotNull(session.getHistory());
        assertEquals(1, session.getHistory().size());

        verify(mockOutputBoundary).prepareSuccessView("Login successful.");
    }

    @Test
    public void testLoginUser_Failure() throws Exception {
        // Arrange
        String username = "invalidUser";
        String password = "wrongPass";

        // Setup mock
        when(mockDataAccess.loginUser(username, password)).thenReturn(false);

        // Act
        accountInteractor.loginUser(username, password);

        // Assert
        verify(mockDataAccess).loginUser(username, password);
        verify(mockOutputBoundary).prepareFailView("Login failed. Invalid username or password.");

        // Verify session remains unchanged
        Session session = Session.getInstance();
        assertNull(session.getUsername());
        assertNull(session.getPassword());
    }

    @Test
    public void testSaveUserException() throws Exception {
        // Arrange
        String username = "testUser";
        String password = "testPass";

        // Setup mock to throw exception
        doThrow(new Exception("Save failed")).when(mockDataAccess).saveUser(any(User.class));

        // Act & Assert
        assertThrows(Exception.class, () -> {
            accountInteractor.createAccount(username, password);
        });
    }
}