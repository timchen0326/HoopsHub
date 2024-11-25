package entity.Player;

import entity.PlayerStatistic;
import entity.PlayerStatisticFactory;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerStatisticFactoryTest {

    @Test
    void testFromJson() {
        String json = """
                [
                    {
                        "age": 25,
                        "assists": 300,
                        "blocks": 50,
                        "defensiveRb": 200,
                        "effectFgPercent": 0.56,
                        "fieldAttempts": 800,
                        "fieldGoals": 450,
                        "fieldPercent": 0.5625,
                        "ft": 120,
                        "ftAttempts": 150,
                        "ftPercent": 0.8,
                        "games": 82,
                        "gamesStarted": 75,
                        "id": 1,
                        "minutesPg": 2400,
                        "offensiveRb": 100,
                        "personalFouls": 200,
                        "playerId": "12345",
                        "playerName": "John Doe",
                        "points": 1500,
                        "position": "Guard",
                        "season": 2023,
                        "steals": 100,
                        "team": "Lakers",
                        "threeAttempts": 400,
                        "threeFg": 150,
                        "threePercent": 0.375,
                        "totalRb": 300,
                        "turnovers": 100,
                        "twoAttempts": 200,
                        "twoFg": 100,
                        "twoPercent": 0.5
                    }
                ]
                """;

        List<PlayerStatistic> statistics = PlayerStatisticFactory.fromJson(json);

        assertEquals(1, statistics.size());

        PlayerStatistic statistic = statistics.get(0);

        assertEquals(25, statistic.getAge());
        assertEquals("John Doe", statistic.getPlayerName());
        assertEquals("Guard", statistic.getPosition());
        assertEquals("Lakers", statistic.getTeam());
        assertEquals(2023, statistic.getSeason());
        assertEquals(1500, statistic.getPoints());
        assertEquals(0.5625, statistic.getFieldGoalPercentage(), 0.0001);
        assertEquals(0.5, statistic.getTwoPointPercentage(), 0.0001);
        assertEquals(0.375, statistic.getThreePointPercentage(), 0.0001);
        assertEquals(0.8, statistic.getFreeThrowPercentage(), 0.0001);
    }
}