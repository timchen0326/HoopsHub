package entity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatisticFactory {

    public static List<PlayerStatistic> fromJson(String json) {
        JSONArray jsonArray = new JSONArray(json);
        List<PlayerStatistic> statistics = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            PlayerStatistic statistic = new PlayerStatistic(
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
