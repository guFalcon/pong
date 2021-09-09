package sample.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Ball implements GameObject {

    public static final double SIZE = 10D;
    public static final double MOVEMENT_DELTA = 2D;

    private Point2D position = new Point2D(0, 0);
    private Point2D direction = new Point2D(0, 0);

    private boolean isUp;
    private boolean isDown;

    private Point2D min;
    private Point2D max;

    private Random random;

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
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
    public void initialize(Game game, Canvas canvas) {
        random = new Random(System.currentTimeMillis());

        position = new Point2D(game.getWidth() / 2D - SIZE / 2D, game.getHeight() / 2D - SIZE / 2D);

        direction = newRandomDirection(MOVEMENT_DELTA);
        min = new Point2D(0, 0);
        max = new Point2D(game.getWidth(), game.getHeight());
    }

    private Point2D newRandomDirection(double velocity) {
        // Get random vector ([0..1], [0..1]).
        Point2D vector = new Point2D(random.nextDouble(), random.nextDouble());
        // Normalizing it sets its length to 1.
        vector = vector.normalize();
        vector = vector.multiply(velocity);
        return vector;
    }

    @Override
    public void update(GameTime gameTime) {
        position = position.add(direction);
        position = clampPosition(position);
    }

    private Point2D clampPosition(Point2D position) {
        double maxXPos = max.getX() - SIZE;
        double maxYPos = max.getY() - SIZE;

        double newX = position.getX();
        double newY = position.getY();

        if (position.getX() > maxXPos) {
            newX = maxXPos;
            reflectX();
        }
        if (position.getX() < 0) {
            newX = 0;
            reflectX();
        }
        if (position.getY() > maxYPos) {
            newY = maxYPos;
            reflectY();
        }
        if (position.getY() < 0) {
            newY = 0;
            reflectY();
        }

        return new Point2D(newX, newY);
    }

    public void reflectX() {
        direction = new Point2D(-direction.getX(), direction.getY());
    }

    public void reflectY() {
        direction = new Point2D(direction.getX(), -direction.getY());
    }

    @Override
    public void draw(GameTime gameTime, GraphicsContext context) {
        context.setLineWidth(3D);
        context.setFill(Color.BLACK);
        context.fillRect(position.getX(), position.getY(), SIZE, SIZE);
    }
}
