package sample.model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class PongGame implements Runnable {

    private List<GameObject> gameObjects = new ArrayList<>();
    private volatile boolean running = true;
    private Pane pane;
    private double width;
    private double height;

    public PongGame(Pane pane, double width, double height) {
        this.pane = pane;
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
            gameObject.initialize(this, pane);
        }
        long time = System.currentTimeMillis();
        while (running) {
            long curr = System.currentTimeMillis();
            long diff = curr - time;
            if (diff >= 16) {
                for (GameObject gameObject : gameObjects) {
                    gameObject.update(this, diff);
                }
                for (GameObject gameObject : gameObjects) {
                    gameObject.draw(this, diff);
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
