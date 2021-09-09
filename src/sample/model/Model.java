package sample.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Model extends Game {

    public static final double BORDER = 50D;

    private Ball ball;

    private Paddle paddle1;
    private Paddle paddle2;

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
        clear();
        double centerY = getHeight() / 2D - Paddle.SIZE / 2D;

        ball = new Ball();
        add(ball);

        paddle1 = new Paddle();
        paddle1.setPosition(new Point2D(BORDER, centerY));
        add(paddle1);

        paddle2 = new Paddle();
        paddle2.setPosition(new Point2D(getWidth() - BORDER, centerY));
        add(paddle2);
    }

    @Override
    public void initialize(Game game, Canvas canvas) {
        reset();
    }

    @Override
    public void update(GameTime gt) {
        if (ball.getPosition().getX() <= paddle1.getPosition().getX() && ballInYRangeOf(paddle1)) {
            ball.reflectX();
        }
        if (ball.getPosition().getX() + Ball.SIZE >= paddle2.getPosition().getX() && ballInYRangeOf(paddle2)) {
            ball.reflectX();
        }
    }

    private boolean ballInYRangeOf(Paddle paddle) {
        return ball.getPosition().getY() > paddle.getPosition().getY() && ball.getPosition().getY() + Ball.SIZE < paddle.getPosition().getY() + Paddle.SIZE;
    }
}
