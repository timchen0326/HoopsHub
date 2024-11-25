package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchResultTest {
    @Test
    public void testGetters() {
        // Create a SearchResult object
        SearchResult searchResult = new SearchResult("testUser", "1", 9, 7);

        // Verify the values using getters
        assertEquals("testUser", searchResult.getUsername());
        assertEquals("1", searchResult.getUserId());
        assertEquals(9, searchResult.getWins());
        assertEquals(7, searchResult.getLosses());
    }

    @Test
    public void testToString() {
        // Create a SearchResult object
        SearchResult searchResult = new SearchResult("testUser", "1", 9, 7);

        // Verify the toString output
        String expectedString = "Username: testUser\nUserID: 1\nWins: 9\nLosses: 7";
        assertEquals(expectedString, searchResult.toString());
    }
}
