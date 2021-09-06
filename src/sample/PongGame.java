package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class PongGame implements Runnable {

    private List<GameObject> gameObjects = new ArrayList<>();
    private volatile boolean running = true;
    private Pane pane;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public PongGame(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void run() {
        for (GameObject gameObject : gameObjects) {
            gameObject.initialize(this, pane);
        }
        long time = System.currentTimeMillis();
        while(running) {
            long curr = System.currentTimeMillis();
            long diff = curr - time;
            if(diff >= 16) {
                for (GameObject gameObject : gameObjects) {
                    gameObject.update(this, diff);
                }
                for (GameObject gameObject : gameObjects) {
                    gameObject.draw(this, diff);
                }
                time = curr;
            }
        }
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
