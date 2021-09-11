package sample.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Ball implements GameObject {

    public static final double SIZE = 10D;
    public static final double MOVEMENT_DELTA_MIN = 2D;
    public static final double MOVEMENT_DELTA_MAX = 4D;
    public static final double MULTIPLY_TIMER = 5000;
    public static final Color COLOR_STANDARD = new Color(0.3D, 0.3D, 0.3D, 1D);
    public static final Color COLOR_MULTIPLY = new Color(0.0D, 0.0D, 0.8D, 0.8D);

    private Game game;

    private Point2D position = new Point2D(0, 0);
    private Point2D direction = new Point2D(0, 0);

    private boolean isUp;
    private boolean isDown;

    private Color color;

    private Point2D min;
    private Point2D max;

    private Random random;
    private double multiplyTimer;

    public Ball(Game game) {
        random = new Random(System.currentTimeMillis());
        this.game = game;
        position = new Point2D(game.getWidth() / 2D - SIZE / 2D, game.getHeight() / 2D - SIZE / 2D);
        direction = withRandomVelocity(newRandomDirection());
    }

    public Ball(Game game, Point2D position, Point2D direction) {
        random = new Random(System.currentTimeMillis());
        this.game = game;
        this.position = position;
        this.direction = direction;
    }

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
        min = new Point2D(0, 0);
        max = new Point2D(game.getWidth(), game.getHeight());
        color = COLOR_STANDARD;
        multiplyTimer = 0;
    }

    private Point2D withRandomVelocity(Point2D vector) {
        vector = vector.normalize();
        double velocity = (MOVEMENT_DELTA_MAX - MOVEMENT_DELTA_MIN) * random.nextDouble() + MOVEMENT_DELTA_MIN;
        return vector.multiply(velocity);
    }

    private Point2D newRandomDirection() {
        // Get random vector ([0..1], [0..1]).
        return new Point2D(random.nextDouble(), random.nextDouble());
    }

    @Override
    public void update(GameTime gameTime) {
        position = position.add(direction);
        position = clampPosition(position);

        multiplyTimer -= gameTime.getDeltaFrame();
        if (multiplyTimer < 0)
            multiplyTimer = 0;

        if (multiplyTimer == 0) {
            double rnd = random.nextDouble();
            if (rnd < 0.5F / 16F / 5F / 2F)
                multiplyTimer = MULTIPLY_TIMER;
        }

        if (multiplyTimer == 0)
            color = COLOR_STANDARD;
        else
            color = COLOR_MULTIPLY;
    }

    private Point2D clampPosition(Point2D position) {
        double maxYPos = max.getY() - SIZE;

        double newX = position.getX();
        double newY = position.getY();

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

    public void reflectX(boolean isPaddleCollision) {
        direction = new Point2D(-direction.getX(), direction.getY());
        if (isPaddleCollision && multiplyTimer > 0D) {
            Ball newBall = new Ball(game, position, withRandomVelocity(new Point2D(direction.getX(), -direction.getY())));
            ((Model) game).addBall(newBall);
        }
    }

    public void reflectY() {
        direction = new Point2D(direction.getX(), -direction.getY());
    }

    @Override
    public void draw(GameTime gameTime, GraphicsContext context) {
        context.setLineWidth(3D);
        context.setFill(color);
        context.fillRect(position.getX(), position.getY(), SIZE, SIZE);
    }
}
