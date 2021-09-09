package sample.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PongGame implements Runnable {

    private List<GameObject> gameObjects = new ArrayList<>();
    private volatile boolean running = true;
    private Canvas canvas;
    private GraphicsContext context;
    private double width;
    private double height;

    public PongGame(Canvas canvas, double width, double height) {
        this.canvas = canvas;
        context = canvas.getGraphicsContext2D();
        this.width = width;
        this.height = height;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void run() {
        for (GameObject gameObject : gameObjects) {
            gameObject.initialize(this, canvas);
        }
        long startTime = System.currentTimeMillis();
        long time = System.currentTimeMillis();
        while (running) {
            long curr = System.currentTimeMillis();
            long diff = curr - time;
            if (diff >= 16) {
                GameTime gt = new GameTime(System.currentTimeMillis() - startTime, diff);
                for (GameObject gameObject : gameObjects) {
                    gameObject.update(gt);
                }
                context.setFill(new Color(0.68, 0.68, 0.68, 1.0));
                context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                for (GameObject gameObject : gameObjects) {
                    gameObject.draw(gt, context);
                }
                time = curr;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
