package entity;

public class PlayerStatistic {
    private final int age;
    private final int assists;
    private final int blocks;
    private final int defensiveRebounds;
    private final double effectiveFieldGoalPercentage;
    private final int fieldGoalsAttempted;
    private final int fieldGoalsMade;
    private final double fieldGoalPercentage;
    private final int freeThrowsMade;
    private final int freeThrowsAttempted;
    private final double freeThrowPercentage;
    private final int gamesPlayed;
    private final int gamesStarted;
    private final int id;
    private final int minutesPlayed;
    private final int offensiveRebounds;
    private final int personalFouls;
    private final String playerId;
    private final String playerName;
    private final int points;
    private final String position;
    private final int season;
    private final int steals;
    private final String team;
    private final int threePointAttempts;
    private final int threePointMade;
    private final double threePointPercentage;
    private final int totalRebounds;
    private final int turnovers;
    private final int twoPointAttempts;
    private final int twoPointMade;
    private final double twoPointPercentage;

    public PlayerStatistic(int age, int assists, int blocks, int defensiveRebounds,
                           double effectiveFieldGoalPercentage, int fieldGoalsAttempted,
                           int fieldGoalsMade, double fieldGoalPercentage, int freeThrowsMade,
                           int freeThrowsAttempted, double freeThrowPercentage, int gamesPlayed,
                           int gamesStarted, int id, int minutesPlayed, int offensiveRebounds,
                           int personalFouls, String playerId, String playerName, int points,
                           String position, int season, int steals, String team,
                           int threePointAttempts, int threePointMade, double threePointPercentage,
                           int totalRebounds, int turnovers, int twoPointAttempts,
                           int twoPointMade, double twoPointPercentage) {
        this.age = age;
        this.assists = assists;
        this.blocks = blocks;
        this.defensiveRebounds = defensiveRebounds;
        this.effectiveFieldGoalPercentage = effectiveFieldGoalPercentage;
        this.fieldGoalsAttempted = fieldGoalsAttempted;
        this.fieldGoalsMade = fieldGoalsMade;
        this.fieldGoalPercentage = fieldGoalPercentage;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.freeThrowPercentage = freeThrowPercentage;
        this.gamesPlayed = gamesPlayed;
        this.gamesStarted = gamesStarted;
        this.id = id;
        this.minutesPlayed = minutesPlayed;
        this.offensiveRebounds = offensiveRebounds;
        this.personalFouls = personalFouls;
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
        this.position = position;
        this.season = season;
        this.steals = steals;
        this.team = team;
        this.threePointAttempts = threePointAttempts;
        this.threePointMade = threePointMade;
        this.threePointPercentage = threePointPercentage;
        this.totalRebounds = totalRebounds;
        this.turnovers = turnovers;
        this.twoPointAttempts = twoPointAttempts;
        this.twoPointMade = twoPointMade;
        this.twoPointPercentage = twoPointPercentage;
    }

    // Getters for all fields
    public int getAge() {
        return age;
    }

    public int getAssists() {
        return assists;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public double getEffectiveFieldGoalPercentage() {
        return effectiveFieldGoalPercentage;
    }

    public int getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public double getFieldGoalPercentage() {
        return fieldGoalPercentage;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public double getFreeThrowPercentage() {
        return freeThrowPercentage;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public int getId() {
        return id;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public int getPersonalFouls() {
        return personalFouls;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPoints() {
        return points;
    }

    public String getPosition() {
        return position;
    }

    public int getSeason() {
        return season;
    }

    public int getSteals() {
        return steals;
    }

    public String getTeam() {
        return team;
    }

    public int getThreePointAttempts() {
        return threePointAttempts;
    }

    public int getThreePointMade() {
        return threePointMade;
    }

    public double getThreePointPercentage() {
        return threePointPercentage;
    }

    public int getTotalRebounds() {
        return totalRebounds;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public int getTwoPointAttempts() {
        return twoPointAttempts;
    }

    public int getTwoPointMade() {
        return twoPointMade;
    }

    public double getTwoPointPercentage() {
        return twoPointPercentage;
    }

    public String formatPlayerStats() {
        StringBuilder formattedStats = new StringBuilder();

        formattedStats.append("Player Name: ").append(getPlayerName()).append("\n");
        formattedStats.append("Age: ").append(getAge()).append("\n");
        formattedStats.append("Position: ").append(getPosition()).append("\n");
        formattedStats.append("Team: ").append(getTeam()).append("\n");
        formattedStats.append("Season: ").append(getSeason()).append("\n\n");

        formattedStats.append("Games Played: ").append(getGamesPlayed()).append("\n");
        formattedStats.append("Games Started: ").append(getGamesStarted()).append("\n");
        formattedStats.append("Minutes Played: ").append(getMinutesPlayed()).append("\n\n");

        formattedStats.append("Field Goals Made: ").append(getFieldGoalsMade()).append("\n");
        formattedStats.append("Field Goal Attempts: ").append(getFieldGoalsAttempted()).append("\n");
        formattedStats.append("Field Goal Percentage: ").append(getFieldGoalPercentage()).append("\n\n");

        formattedStats.append("Three-Point FG Made: ").append(getThreePointMade()).append("\n");
        formattedStats.append("Three-Point Attempts: ").append(getThreePointAttempts()).append("\n");
        formattedStats.append("Three-Point Percentage: ").append(getThreePointPercentage()).append("\n\n");

        formattedStats.append("Two-Point FG Made: ").append(getTwoPointMade()).append("\n");
        formattedStats.append("Two-Point Attempts: ").append(getTwoPointAttempts()).append("\n");
        formattedStats.append("Two-Point Percentage: ").append(getTwoPointPercentage()).append("\n");
        formattedStats.append("Effective Field Goal Percentage: ").append(getEffectiveFieldGoalPercentage()).append("\n\n");

        formattedStats.append("Free Throws Made: ").append(getFreeThrowsMade()).append("\n");
        formattedStats.append("Free Throw Attempts: ").append(getFreeThrowsAttempted()).append("\n");
        formattedStats.append("Free Throw Percentage: ").append(getFreeThrowPercentage()).append("\n\n");

        formattedStats.append("Offensive Rebounds: ").append(getOffensiveRebounds()).append("\n");
        formattedStats.append("Defensive Rebounds: ").append(getDefensiveRebounds()).append("\n");
        formattedStats.append("Total Rebounds: ").append(getTotalRebounds()).append("\n\n");

        formattedStats.append("Assists: ").append(getAssists()).append("\n");
        formattedStats.append("Steals: ").append(getSteals()).append("\n");
        formattedStats.append("Blocks: ").append(getBlocks()).append("\n\n");

        formattedStats.append("Turnovers: ").append(getTurnovers()).append("\n");
        formattedStats.append("Personal Fouls: ").append(getPersonalFouls()).append("\n");
        formattedStats.append("Points Scored: ").append(getPoints()).append("\n");

        return formattedStats.toString();
    }
}