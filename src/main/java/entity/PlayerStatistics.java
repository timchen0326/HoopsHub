package entity;

public class PlayerStatistics {
    private final String name;
    private final String stats;

    public PlayerStatistics(String name, String stats) {
        this.name = name;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public String getStats() {
        return stats;
    }
}
