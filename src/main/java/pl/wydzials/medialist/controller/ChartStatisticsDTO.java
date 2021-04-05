package pl.wydzials.medialist.controller;

public class ChartStatisticsDTO {

    private final String medium;
    private final long count;
    private final int time;

    public ChartStatisticsDTO(String medium, long count, int time) {
        this.medium = medium;
        this.count = count;
        this.time = time;
    }

    public String getMedium() {
        return medium;
    }

    public long getCount() {
        return count;
    }

    public int getTime() {
        return time;
    }
}
