package entity.user;

import entity.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User("testUser", "testPassword");

        // Test initial values
        assertEquals("testUser", user.getName());
        assertEquals("testPassword", user.getPassword());
        assertEquals(0, user.getWin());
        assertEquals(0, user.getLose());
    }

    @Test
    public void testSetWin() {
        User user = new User("testUser", "testPassword");

        // Set win count and verify
        user.setWin(5);
        assertEquals(5, user.getWin());
    }

    @Test
    public void testSetLose() {
        User user = new User("testUser", "testPassword");

        // Set lose count and verify
        user.setLose(3);
        assertEquals(3, user.getLose());
    }

    @Test
    public void testWinAndLoseTogether() {
        User user = new User("testUser", "testPassword");

        // Set win and lose counts
        user.setWin(7);
        user.setLose(2);

        // Verify both values
        assertEquals(7, user.getWin());
        assertEquals(2, user.getLose());
    }
}