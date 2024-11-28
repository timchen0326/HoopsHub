import entity.SearchHistoryRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class SearchHistoryRecordTest {

    @Test
    void testConstructor() {
        // Given
        LocalDateTime timestamp = LocalDateTime.now();
        String query = "Win the game";

        // When
        SearchHistoryRecord record = new SearchHistoryRecord(query, timestamp);

        // Then
        assertEquals(query, record.getQuery());  // Verifies the query is set correctly
        assertEquals(timestamp, record.getTimestamp());  // Verifies the timestamp is set correctly
    }

    @Test
    void testGetQuery() {
        // Given
        String query = "Test query";
        SearchHistoryRecord record = new SearchHistoryRecord(query, LocalDateTime.now());

        // When & Then
        assertEquals(query, record.getQuery());  // Verifies that getQuery() returns the correct query
    }

    @Test
    void testGetTimestamp() {
        // Given
        LocalDateTime timestamp = LocalDateTime.now();
        SearchHistoryRecord record = new SearchHistoryRecord("Test query", timestamp);

        // When & Then
        assertEquals(timestamp, record.getTimestamp());  // Verifies that getTimestamp() returns the correct timestamp
    }

    @Test
    void testGetResultWin() {
        // Given
        SearchHistoryRecord record = new SearchHistoryRecord("Win the game", LocalDateTime.now());

        // When & Then
        assertEquals("Win", record.getResult());  // Verifies that getResult() returns "Win" for "win" query
    }

    @Test
    void testGetResultLose() {
        // Given
        SearchHistoryRecord record = new SearchHistoryRecord("Lose the game", LocalDateTime.now());

        // When & Then
        assertEquals("Lose", record.getResult());  // Verifies that getResult() returns "Lose" for "lose" query
    }

    @Test
    void testGetResultUnknown() {
        // Given
        SearchHistoryRecord record = new SearchHistoryRecord("Play the game", LocalDateTime.now());

        // When & Then
        assertEquals("Unknown", record.getResult());  // Verifies that getResult() returns "Unknown" for a query with neither "win" nor "lose"
    }

    @Test
    void testToString() {
        // Given
        LocalDateTime timestamp = LocalDateTime.now();
        SearchHistoryRecord record = new SearchHistoryRecord("Test query", timestamp);

        // When
        String result = record.toString();

        // Then
        assertTrue(result.contains("Search: Test query"));  // Verifies that the toString method returns the query part correctly
        assertTrue(result.contains("Time: " + timestamp));  // Verifies that the toString method returns the timestamp correctly
    }
}
