package sample.model;

public class GameTime {
    private double overall;
    private double deltaFrame;

    public GameTime(double overall, double deltaFrame) {
        this.overall = overall;
        this.deltaFrame = deltaFrame;
    }

    public double getOverall() {
        return overall;
    }

    public double getDeltaFrame() {
        return deltaFrame;
    }
}
