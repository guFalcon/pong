package sample;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Paddle implements GameObject {

    private Line line = new Line(0, 0, 0, 60);
    private Point2D position = new Point2D(0, 0);
    private boolean isUp;
    private boolean isDown;

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
    }

    @Override
    public void update(PongGame game, float gameTime) {
        int modifier = 0;
        if (isUp)
            modifier = -10;
        if (isDown)
            modifier = 10;
        if (isUp && isDown)
            modifier = 0;
        position = new Point2D(position.getX(), position.getY() + modifier);
    }

    @Override
    public void draw(PongGame game, float gameTime) {
        line.setTranslateX(position.getX());
        line.setTranslateY(position.getY());
    }
}
