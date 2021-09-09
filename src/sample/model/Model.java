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
}
