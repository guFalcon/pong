package sample.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashSet;
import java.util.Set;

public class Model extends Game {

    public static final double BORDER = 50D;

    private Set<Ball> balls = new HashSet<>();
    private Set<Ball> ballsToAdd = new HashSet<>();
    private Set<Ball> ballsToRemove = new HashSet<>();

    private Paddle paddle1;
    private Paddle paddle2;

    private int p1Counter;
    private int p2Counter;

    public Model(Canvas canvas, double width, double height) {
        super(canvas, width, height);
    }

    public Paddle getPaddle1() {
        return paddle1;
    }

    public Paddle getPaddle2() {
        return paddle2;
    }

    public void reset() {
        p1Counter = 0;
        p2Counter = 0;
        resetGame();
    }

    private void resetGame() {
        clear();
        double centerY = getHeight() / 2D - Paddle.SIZE / 2D;

        addBall(new Ball(this));

        paddle1 = new Paddle();
        paddle1.setPosition(new Point2D(BORDER, centerY));
        add(paddle1);

        paddle2 = new Paddle();
        paddle2.setPosition(new Point2D(getWidth() - BORDER, centerY));
        add(paddle2);
    }

    public void addBall(Ball ball) {
        ballsToAdd.add(ball);
        add(ball);
    }

    public void removeBall(Ball ball) {
        ballsToRemove.add(ball);
        remove(ball);
    }

    @Override
    public void initialize(Game game, Canvas canvas) {
        reset();
    }

    @Override
    public void update(GameTime gt) {
        balls.removeAll(ballsToRemove);
        balls.addAll(ballsToAdd);
        ballsToRemove.clear();
        ballsToAdd.clear();
        for (Ball ball : balls)
            updateBall(ball);
    }

    public void updateBall(Ball ball) {
        double posLeft = ball.getPosition().getX();
        if (ball.getDirection().getX() < 0 &&
                posLeft <= paddle1.getPosition().getX() + Paddle.WIDTH / 2D &&
                posLeft >= paddle1.getPosition().getX() - Paddle.WIDTH / 2D &&
                isOnPaddleY(paddle1, ball)) {
            ball.reflectX(true);
        }

        double posRight = ball.getPosition().getX() + Ball.SIZE;
        if (ball.getDirection().getX() > 0 &&
                posRight >= paddle2.getPosition().getX() - Paddle.WIDTH / 2D &&
                posRight <= paddle2.getPosition().getX() + Paddle.WIDTH / 2D &&
                isOnPaddleY(paddle2, ball)) {
            ball.reflectX(true);
        }

        double maxXPos = getWidth() - Ball.SIZE;
        if (ball.getPosition().getX() > maxXPos) {
            p1Counter++;
            removeBall(ball);
            if (balls.size() - ballsToRemove.size() + ballsToAdd.size() == 0)
                resetGame();
        }
        if (ball.getPosition().getX() < 0) {
            p2Counter++;
            removeBall(ball);
            if (balls.size() - ballsToRemove.size() + ballsToAdd.size() == 0)
                resetGame();
        }
    }

    private boolean isOnPaddleY(Paddle paddle, Ball ball) {
        return ball.getPosition().getY() + Ball.SIZE > paddle.getPosition().getY() && ball.getPosition().getY() < paddle.getPosition().getY() + Paddle.SIZE;
    }

    @Override
    public void draw(GameTime gt, GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        context.fillText(p1Counter + "", getWidth() / 4, getHeight() / 10);
        context.fillText(p2Counter + "", 3 * getWidth() / 4, getHeight() / 10);
    }
}
