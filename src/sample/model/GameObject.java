package sample.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public interface GameObject {

    void initialize(Game game, Canvas canvas);

    void update(GameTime gameTime);
    
    void draw(GameTime gameTime, GraphicsContext context);
}
