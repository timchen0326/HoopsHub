package entity;

/**
 * Represents the statistics of a player.
 */
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

    /**
     * Constructs a new PlayerStatistic.
     *
     * @param age the age of the player
     * @param assists the number of assists
     * @param blocks the number of blocks
     * @param defensiveRebounds the number of defensive rebounds
     * @param effectiveFieldGoalPercentage the effective field goal percentage
     * @param fieldGoalsAttempted the number of field goals attempted
     * @param fieldGoalsMade the number of field goals made
     * @param fieldGoalPercentage the field goal percentage
     * @param freeThrowsMade the number of free throws made
     * @param freeThrowsAttempted the number of free throws attempted
     * @param freeThrowPercentage the free throw percentage
     * @param gamesPlayed the number of games played
     * @param gamesStarted the number of games started
     * @param id the ID of the player
     * @param minutesPlayed the number of minutes played
     * @param offensiveRebounds the number of offensive rebounds
     * @param personalFouls the number of personal fouls
     * @param playerId the ID of the player
     * @param playerName the name of the player
     * @param points the number of points scored
     * @param position the position of the player
     * @param season the season year
     * @param steals the number of steals
     * @param team the team of the player
     * @param threePointAttempts the number of three-point attempts
     * @param threePointMade the number of three-point shots made
     * @param threePointPercentage the three-point percentage
     * @param totalRebounds the total number of rebounds
     * @param turnovers the number of turnovers
     * @param twoPointAttempts the number of two-point attempts
     * @param twoPointMade the number of two-point shots made
     * @param twoPointPercentage the two-point percentage
     */
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

    /**
     * Formats the player statistics into a readable format.
     *
     * @return a string containing the formatted player statistics
     */
    public String formatPlayerStats() {
        final StringBuilder formattedStats = new StringBuilder();
        final String nl = "\n";
        final String dl = "\n\n";

        formattedStats.append("Player Name: ").append(getPlayerName()).append(nl);
        formattedStats.append("Age: ").append(getAge()).append(nl);
        formattedStats.append("Position: ").append(getPosition()).append(nl);
        formattedStats.append("Team: ").append(getTeam()).append(nl);
        formattedStats.append("Season: ").append(getSeason()).append(dl);

        formattedStats.append("Games Played: ").append(getGamesPlayed()).append(nl);
        formattedStats.append("Games Started: ").append(getGamesStarted()).append(nl);
        formattedStats.append("Minutes Played: ").append(getMinutesPlayed()).append(dl);

        formattedStats.append("Field Goals Made: ").append(getFieldGoalsMade()).append(nl);
        formattedStats.append("Field Goal Attempts: ").append(getFieldGoalsAttempted()).append(nl);
        formattedStats.append("Field Goal Percentage: ").append(getFieldGoalPercentage()).append(dl);

        formattedStats.append("Three-Point FG Made: ").append(getThreePointMade()).append(nl);
        formattedStats.append("Three-Point Attempts: ").append(getThreePointAttempts()).append(nl);
        formattedStats.append("Three-Point Percentage: ").append(getThreePointPercentage()).append(dl);

        formattedStats.append("Two-Point FG Made: ").append(getTwoPointMade()).append(nl);
        formattedStats.append("Two-Point Attempts: ").append(getTwoPointAttempts()).append(nl);
        formattedStats.append("Two-Point Percentage: ").append(getTwoPointPercentage()).append(nl);
        formattedStats.append("Effective Field Goal Percentage: ").append(getEffectiveFieldGoalPercentage()).append(dl);

        formattedStats.append("Free Throws Made: ").append(getFreeThrowsMade()).append(nl);
        formattedStats.append("Free Throw Attempts: ").append(getFreeThrowsAttempted()).append(nl);
        formattedStats.append("Free Throw Percentage: ").append(getFreeThrowPercentage()).append(dl);

        formattedStats.append("Offensive Rebounds: ").append(getOffensiveRebounds()).append(nl);
        formattedStats.append("Defensive Rebounds: ").append(getDefensiveRebounds()).append(nl);
        formattedStats.append("Total Rebounds: ").append(getTotalRebounds()).append(dl);

        formattedStats.append("Assists: ").append(getAssists()).append(nl);
        formattedStats.append("Steals: ").append(getSteals()).append(nl);
        formattedStats.append("Blocks: ").append(getBlocks()).append(dl);

        formattedStats.append("Turnovers: ").append(getTurnovers()).append(nl);
        formattedStats.append("Personal Fouls: ").append(getPersonalFouls()).append(nl);
        formattedStats.append("Points Scored: ").append(getPoints()).append(nl);

        return formattedStats.toString();
    }
}
