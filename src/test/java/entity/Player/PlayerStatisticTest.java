package entity.Player;

import entity.PlayerStatistic;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerStatisticTest {

    @Test
    public void testConstructorAndGetters() {
        PlayerStatistic stat = new PlayerStatistic(25, 10, 3, 7, 0.65, 20, 13, 0.65,
                5, 6, 0.83, 82, 82, 1, 2500, 3, 4,
                "p123", "Michael Jordan", 2500, "SG", 1996, 3,
                "Chicago Bulls", 120, 50, 0.42, 700, 40, 250, 120, 0.48);

        assertEquals(25, stat.getAge());
        assertEquals(10, stat.getAssists());
        assertEquals(3, stat.getBlocks());
        assertEquals(7, stat.getDefensiveRebounds(), 0);
        assertEquals(0.65, stat.getEffectiveFieldGoalPercentage(), 0.001);
        assertEquals(20, stat.getFieldGoalsAttempted());
        assertEquals(13, stat.getFieldGoalsMade());
        assertEquals(0.65, stat.getFieldGoalPercentage(), 0.001);
        assertEquals(5, stat.getFreeThrowsMade());
        assertEquals(6, stat.getFreeThrowsAttempted());
        assertEquals(0.83, stat.getFreeThrowPercentage(), 0.001);
        assertEquals(82, stat.getGamesPlayed());
        assertEquals(82, stat.getGamesStarted());
        assertEquals(1, stat.getId());
        assertEquals(2500, stat.getMinutesPlayed());
        assertEquals(3, stat.getOffensiveRebounds());
        assertEquals(4, stat.getPersonalFouls());
        assertEquals("p123", stat.getPlayerId());
        assertEquals("Michael Jordan", stat.getPlayerName());
        assertEquals(2500, stat.getPoints());
        assertEquals("SG", stat.getPosition());
        assertEquals(1996, stat.getSeason());
        assertEquals(3, stat.getSteals());
        assertEquals("Chicago Bulls", stat.getTeam());
        assertEquals(120, stat.getThreePointAttempts());
        assertEquals(50, stat.getThreePointMade());
        assertEquals(0.42, stat.getThreePointPercentage(), 0.001);
        assertEquals(700, stat.getTotalRebounds());
        assertEquals(40, stat.getTurnovers());
        assertEquals(250, stat.getTwoPointAttempts());
        assertEquals(120, stat.getTwoPointMade());
        assertEquals(0.48, stat.getTwoPointPercentage(), 0.001);
    }

    @Test
    public void testFormatPlayerStats() {
        PlayerStatistic stat = new PlayerStatistic(30, 8, 2, 5, 0.58, 18, 9, 0.5,
                6, 7, 0.85, 70, 70, 2, 2400, 3, 3,
                "p456", "Kobe Bryant", 2000, "SG", 2006, 4,
                "Los Angeles Lakers", 100, 40, 0.4, 600, 30, 200, 100, 0.5);

        String formattedStats = stat.formatPlayerStats();

        assertTrue(formattedStats.contains("Player Name: Kobe Bryant"));
        assertTrue(formattedStats.contains("Age: 30"));
        assertTrue(formattedStats.contains("Position: SG"));
        assertTrue(formattedStats.contains("Team: Los Angeles Lakers"));
        assertTrue(formattedStats.contains("Season: 2006"));
        assertTrue(formattedStats.contains("Games Played: 70"));
        assertTrue(formattedStats.contains("Field Goals Made: 9"));
        assertTrue(formattedStats.contains("Field Goal Percentage: 0.5"));
        assertTrue(formattedStats.contains("Three-Point FG Made: 40"));
        assertTrue(formattedStats.contains("Total Rebounds: 600"));
        assertTrue(formattedStats.contains("Points Scored: 2000"));
    }

    @Test
    void testFormatPlayerStats2() {
        PlayerStatistic statistic = new PlayerStatistic(
                25, 300, 50, 200, 0.56, 800, 450, 0.5625,
                120, 150, 0.8, 82, 75, 1, 2400, 100, 200,
                "12345", "John Doe", 1500, "Guard", 2023, 100,
                "Lakers", 400, 150, 0.375, 300, 100, 200, 100, 0.5
        );

        String expectedOutput = """
                Player Name: John Doe
                Age: 25
                Position: Guard
                Team: Lakers
                Season: 2023

                Games Played: 82
                Games Started: 75
                Minutes Played: 2400

                Field Goals Made: 450
                Field Goal Attempts: 800
                Field Goal Percentage: 0.5625

                Three-Point FG Made: 150
                Three-Point Attempts: 400
                Three-Point Percentage: 0.375

                Two-Point FG Made: 100
                Two-Point Attempts: 200
                Two-Point Percentage: 0.5
                Effective Field Goal Percentage: 0.56

                Free Throws Made: 120
                Free Throw Attempts: 150
                Free Throw Percentage: 0.8

                Offensive Rebounds: 100
                Defensive Rebounds: 200
                Total Rebounds: 300

                Assists: 300
                Steals: 100
                Blocks: 50

                Turnovers: 100
                Personal Fouls: 200
                Points Scored: 1500
                """;

        assertEquals(expectedOutput, statistic.formatPlayerStats());
    }

    @Test
    public void testEdgeCases() {
        PlayerStatistic stat = new PlayerStatistic(19, 0, 0, 0, 0.0, 0, 0, 0.0,
                0, 0, 0.0, 0, 0, 3, 0, 0, 0,
                "p789", "Rookie Player", 0, "PF", 2024, 0,
                "New Team", 0, 0, 0.0, 0, 0, 0, 0, 0.0);

        assertEquals(19, stat.getAge());
        assertEquals(0, stat.getPoints());
        assertEquals("Rookie Player", stat.getPlayerName());
        assertEquals("PF", stat.getPosition());
        assertEquals(2024, stat.getSeason());
        assertEquals("New Team", stat.getTeam());

        String formattedStats = stat.formatPlayerStats();
        assertTrue(formattedStats.contains("Player Name: Rookie Player"));
        assertTrue(formattedStats.contains("Points Scored: 0"));
    }
}