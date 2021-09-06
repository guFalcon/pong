package sample.model;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Paddle implements GameObject {

    public static final double SIZE = 60D;
    public static final double MOVEMENT_DELTA = 10D;

    private Line line = new Line(0, 0, 0, SIZE);
    private Point2D position = new Point2D(0, 0);
    private boolean isUp;
    private boolean isDown;
    private double maxY;
    private double minY;

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public boolean isDown() {
        return isDown;
    }

    public void setDown(boolean down) {
        isDown = down;
    }

    @Override
    public void initialize(PongGame game, Pane pane) {
        pane.getChildren().add(line);
        minY = 0D;
        maxY = game.getHeight();
    }

    @Override
    public void update(PongGame game, float gameTime) {
        double modifier = getModifier();
        if (modifier != 0D) {
            position = new Point2D(position.getX(), position.getY() + getModifier());
            position = clampPosition(position);
        }
    }

    private double getModifier() {
        if (isUp && isDown)
            return 0D;

        if (isUp)
            return -MOVEMENT_DELTA;
        if (isDown)
            return MOVEMENT_DELTA;

        return 0D;
    }

    private Point2D clampPosition(Point2D position) {
        double maxYPos = maxY - SIZE;
        if (position.getY() > maxYPos)
            return new Point2D(position.getX(), maxYPos);
        if (position.getY() < 0)
            return new Point2D(position.getX(), 0);
        return position;
    }

    @Override
    public void draw(PongGame game, float gameTime) {
        line.setTranslateX(position.getX());
        line.setTranslateY(position.getY());
    }
}
