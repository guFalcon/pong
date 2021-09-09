package sample.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Model extends Game {

    public static final double BORDER = 50D;

    private Ball ball;

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
        double posLeft = ball.getPosition().getX();
        if (ball.getDirection().getX() < 0 &&
                posLeft <= paddle1.getPosition().getX() + Paddle.WIDTH / 2D &&
                posLeft >= paddle1.getPosition().getX() - Paddle.WIDTH / 2D &&
                ballInYRangeOf(paddle1)) {
            ball.reflectX();
        }

        double posRight = ball.getPosition().getX() + Ball.SIZE;
        if (ball.getDirection().getX() > 0 &&
                posRight >= paddle2.getPosition().getX() - Paddle.WIDTH / 2D &&
                posRight <= paddle2.getPosition().getX() + Paddle.WIDTH / 2D &&
                ballInYRangeOf(paddle2)) {
            ball.reflectX();
        }

        double maxXPos = getWidth() - Ball.SIZE;
        if (ball.getPosition().getX() > maxXPos) {
            p1Counter++;
            resetGame();
        }
        if (ball.getPosition().getX() < 0) {
            p2Counter++;
            resetGame();
        }
    }

    @Override
    public void draw(GameTime gt, GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        context.fillText(p1Counter + "", getWidth() / 4, getHeight() / 10);
        context.fillText(p2Counter + "", 3 * getWidth() / 4, getHeight() / 10);
    }

    private boolean ballInYRangeOf(Paddle paddle) {
        return ball.getPosition().getY() > paddle.getPosition().getY() && ball.getPosition().getY() + Ball.SIZE < paddle.getPosition().getY() + Paddle.SIZE;
    }
}
