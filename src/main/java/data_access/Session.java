package app;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private static Session instance;

    private String username;
    private String password;
    private int win;
    private int lose;
    private List<JSONObject> history;

    private Session() {
        this.history = new ArrayList<>(); // Initialize the history list
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public List<JSONObject> getHistory() {
        return history;
    }

    public void setHistory(List<JSONObject> history) {
        this.history = history;
    }

    public void addHistoryEntry(JSONObject entry) {
        this.history.add(entry);
    }

    public void clear() {
        this.username = null;
        this.password = null;
        this.win = 0;
        this.lose = 0;
        this.history = new ArrayList<>();
    }

    public boolean isUserLoggedIn() {
        return this.username != null && this.password != null;
    }
}
