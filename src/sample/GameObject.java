package sample;

import javafx.scene.layout.Pane;

public interface GameObject {
    void initialize(PongGame game, Pane pane);
    void update(PongGame game, float gameTime);
    void draw(PongGame game, float gameTime);
}
