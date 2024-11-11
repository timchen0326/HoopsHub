package interface_adapter;

import org.json.JSONObject;

public class PlayerStatsFormatter {
    public static String formatPlayerStats(String jsonString) {
        JSONObject stats = new JSONObject(jsonString);
        StringBuilder formattedStats = new StringBuilder();

        // Formatting logic as explained before
        formattedStats.append("Player Name: ").append(stats.getString("playerName")).append("\n");
        formattedStats.append("Age: ").append(stats.getInt("age")).append("\n");
        formattedStats.append("Position: ").append(stats.getString("position")).append("\n");
        formattedStats.append("Team: ").append(stats.getString("team")).append("\n");
        formattedStats.append("Season: ").append(stats.getInt("season")).append("\n\n");

        formattedStats.append("Games Played: ").append(stats.getInt("games")).append("\n");
        formattedStats.append("Games Started: ").append(stats.getInt("gamesStarted")).append("\n");
        formattedStats.append("Minutes Played: ").append(stats.getDouble("minutesPg")).append("\n\n");

        formattedStats.append("Field Goals Made: ").append(stats.getInt("fieldGoals")).append("\n");
        formattedStats.append("Field Goal Attempts: ").append(stats.getInt("fieldAttempts")).append("\n");
        formattedStats.append("Field Goal Percentage: ").append(stats.getDouble("fieldPercent")).append("\n\n");

        formattedStats.append("Three-Point FG Made: ").append(stats.getInt("threeFg")).append("\n");
        formattedStats.append("Three-Point Attempts: ").append(stats.getInt("threeAttempts")).append("\n");
        formattedStats.append("Three-Point Percentage: ").append(stats.getDouble("threePercent")).append("\n\n");

        formattedStats.append("Two-Point FG Made: ").append(stats.getInt("twoFg")).append("\n");
        formattedStats.append("Two-Point Attempts: ").append(stats.getInt("twoAttempts")).append("\n");
        formattedStats.append("Two-Point Percentage: ").append(stats.getDouble("twoPercent")).append("\n");
        formattedStats.append("Effective Field Goal Percentage: ").append(stats.getDouble("effectFgPercent")).append("\n\n");

        formattedStats.append("Free Throws Made: ").append(stats.getInt("ft")).append("\n");
        formattedStats.append("Free Throw Attempts: ").append(stats.getInt("ftAttempts")).append("\n");
        formattedStats.append("Free Throw Percentage: ").append(stats.getDouble("ftPercent")).append("\n\n");

        formattedStats.append("Offensive Rebounds: ").append(stats.getInt("offensiveRb")).append("\n");
        formattedStats.append("Defensive Rebounds: ").append(stats.getInt("defensiveRb")).append("\n");
        formattedStats.append("Total Rebounds: ").append(stats.getInt("totalRb")).append("\n\n");

        formattedStats.append("Assists: ").append(stats.getInt("assists")).append("\n");
        formattedStats.append("Steals: ").append(stats.getInt("steals")).append("\n");
        formattedStats.append("Blocks: ").append(stats.getInt("blocks")).append("\n\n");

        formattedStats.append("Turnovers: ").append(stats.getInt("turnovers")).append("\n");
        formattedStats.append("Personal Fouls: ").append(stats.getInt("personalFouls")).append("\n");
        formattedStats.append("Points Scored: ").append(stats.getInt("points")).append("\n");

        return formattedStats.toString();
    }
}