package sample.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Game implements Runnable, GameObject {

    private List<GameObject> gameObjects = new ArrayList<>();
    private List<GameObject> gameObjectsToAdd = new ArrayList<>();
    private List<GameObject> gameObjectsToRemove = new ArrayList<>();
    private volatile boolean running = true;
    private Canvas canvas;
    private GraphicsContext context;
    private double width;
    private double height;

    private Object gameObjectsLock = new Object();

    public Game(Canvas canvas, double width, double height) {
        this.canvas = canvas;
        context = canvas.getGraphicsContext2D();
        this.width = width;
        this.height = height;
        gameObjectsToAdd.add(this);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void shutdown() {
        this.running = false;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void start() {
        this.running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long time = System.currentTimeMillis();
        while (running) {
            long curr = System.currentTimeMillis();
            long diff = curr - time;
            List<GameObject> newObjects = new ArrayList<>();
            synchronized (gameObjectsLock) {
                gameObjects.removeAll(gameObjectsToRemove);
                gameObjects.addAll(gameObjectsToAdd);
                newObjects.addAll(gameObjectsToAdd);
                gameObjectsToRemove.clear();
                gameObjectsToAdd.clear();
            }
            for (GameObject gameObject : newObjects) {
                gameObject.initialize(this, canvas);
            }
            if (diff >= 16) {
                GameTime gt = new GameTime(System.currentTimeMillis() - startTime, diff);
                update(gt);
                for (GameObject gameObject : gameObjects) {
                    gameObject.update(gt);
                }
                context.setFill(new Color(0.68, 0.68, 0.68, 1.0));
                context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                draw(gt, context);
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
        synchronized (gameObjectsLock) {
            gameObjectsToAdd.add(gameObject);
        }
    }

    public void remove(GameObject gameObject) {
        synchronized (gameObjectsLock) {
            gameObjectsToRemove.add(gameObject);
        }
    }

    public void clear() {
        synchronized (gameObjectsLock) {
            gameObjectsToRemove.addAll(gameObjects);
        }
    }

    @Override
    public void initialize(Game game, Canvas canvas) {
    }

    @Override
    public void update(GameTime gameTime) {
    }

    @Override
    public void draw(GameTime gameTime, GraphicsContext context) {
    }
}
