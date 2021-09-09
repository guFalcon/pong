package sample.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

public class Model {

    public static final double BORDER = 50D;

    private PongGame game;

    private Paddle paddle1;
    private Paddle paddle2;

    public Paddle getPaddle1() {
        return paddle1;
    }

    public Paddle getPaddle2() {
        return paddle2;
    }

    public void initialize(Canvas canvas, double width, double height) {
        game = new PongGame(canvas, width, height);
        double centerY = game.getHeight() / 2D - Paddle.SIZE / 2D;

        paddle1 = new Paddle();
        paddle1.setPosition(new Point2D(BORDER, centerY));
        game.add(paddle1);

        paddle2 = new Paddle();
        paddle2.setPosition(new Point2D(game.getWidth() - BORDER, centerY));
        game.add(paddle2);

        Thread thread = new Thread(game);
        thread.start();
    }

    public void shutdown() {
        game.setRunning(false);
    }
}
