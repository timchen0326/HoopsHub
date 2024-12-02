
import org.junit.jupiter.api.Test;
import use_case.search.SearchHistoryOutputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for SearchHistoryOutputData.
 */
public class SearchHistoryOutputDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String expectedQuery = "search example";
        LocalDateTime expectedTimestamp = LocalDateTime.of(2024, 11, 28, 10, 15);

        // Act
        SearchHistoryOutputData outputData = new SearchHistoryOutputData(expectedQuery, expectedTimestamp);

        // Assert
        assertEquals(expectedQuery, outputData.getQuery(), "The query should match the expected value.");
        assertEquals(expectedTimestamp, outputData.getTimestamp(), "The timestamp should match the expected value.");
    }

    @Test
    public void testNullQuery() {
        // Arrange
        String nullQuery = null;
        LocalDateTime timestamp = LocalDateTime.now();

        // Act
        SearchHistoryOutputData outputData = new SearchHistoryOutputData(nullQuery, timestamp);

        // Assert
        assertNull(outputData.getQuery(), "The query should be null.");
        assertNotNull(outputData.getTimestamp(), "The timestamp should not be null.");
    }

    @Test
    public void testNullTimestamp() {
        // Arrange
        String query = "valid query";
        LocalDateTime nullTimestamp = null;

        // Act
        SearchHistoryOutputData outputData = new SearchHistoryOutputData(query, nullTimestamp);

        // Assert
        assertNotNull(outputData.getQuery(), "The query should not be null.");
        assertNull(outputData.getTimestamp(), "The timestamp should be null.");
    }

    @Test
    public void testEmptyQuery() {
        // Arrange
        String emptyQuery = "";
        LocalDateTime timestamp = LocalDateTime.now();

        // Act
        SearchHistoryOutputData outputData = new SearchHistoryOutputData(emptyQuery, timestamp);

        // Assert
        assertEquals(emptyQuery, outputData.getQuery(), "The query should be empty.");
        assertNotNull(outputData.getTimestamp(), "The timestamp should not be null.");
    }
}
