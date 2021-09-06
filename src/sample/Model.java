package sample;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Model {

    private Paddle paddle1;
    private Paddle paddle2;

    public Paddle getPaddle1() {
        return paddle1;
    }

    public Paddle getPaddle2() {
        return paddle2;
    }

    public void initialize(Pane pane) {
        PongGame game = new PongGame(pane);
        paddle1 = new Paddle();
        paddle1.setPosition(new Point2D(50, pane.getHeight() / 2 + 30));
        paddle2 = new Paddle();
        paddle2.setPosition(new Point2D(pane.getWidth() - 500, pane.getHeight() / 2 + 30));
        game.add(paddle1);
        game.add(paddle2);
        Thread thread = new Thread(game);
        thread.start();
    }
}
