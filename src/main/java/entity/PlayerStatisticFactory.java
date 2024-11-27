package entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Factory class for creating PlayerStatistic objects from JSON data.
 */
public class PlayerStatisticFactory {

    /**
     * Creates a list of PlayerStatistic objects from a JSON string.
     *
     * @param json the JSON string containing player statistics data
     * @return a list of PlayerStatistic objects
     */
    public static List<PlayerStatistic> fromJson(String json) {
        final JSONArray jsonArray = new JSONArray(json);
        final List<PlayerStatistic> statistics = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);

            final PlayerStatistic statistic = new PlayerStatistic(
                    jsonObject.getInt("age"),
                    jsonObject.getInt("assists"),
                    jsonObject.getInt("blocks"),
                    jsonObject.getInt("defensiveRb"),
                    jsonObject.getDouble("effectFgPercent"),
                    jsonObject.getInt("fieldAttempts"),
                    jsonObject.getInt("fieldGoals"),
                    jsonObject.getDouble("fieldPercent"),
                    jsonObject.getInt("ft"),
                    jsonObject.getInt("ftAttempts"),
                    jsonObject.getDouble("ftPercent"),
                    jsonObject.getInt("games"),
                    jsonObject.getInt("gamesStarted"),
                    jsonObject.getInt("id"),
                    jsonObject.getInt("minutesPg"),
                    jsonObject.getInt("offensiveRb"),
                    jsonObject.getInt("personalFouls"),
                    jsonObject.getString("playerId"),
                    jsonObject.getString("playerName"),
                    jsonObject.getInt("points"),
                    jsonObject.getString("position"),
                    jsonObject.getInt("season"),
                    jsonObject.getInt("steals"),
                    jsonObject.getString("team"),
                    jsonObject.getInt("threeAttempts"),
                    jsonObject.getInt("threeFg"),
                    jsonObject.getDouble("threePercent"),
                    jsonObject.getInt("totalRb"),
                    jsonObject.getInt("turnovers"),
                    jsonObject.getInt("twoAttempts"),
                    jsonObject.getInt("twoFg"),
                    jsonObject.getDouble("twoPercent")
            );

            statistics.add(statistic);
        }

        return statistics;
    }
}
