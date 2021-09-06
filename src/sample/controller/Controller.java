package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sample.Main;
import sample.model.Model;
import sample.model.Paddle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Model model;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setFocusTraversable(true);
        pane.requestFocus();
        // pane.setStyle("-fx-background-color: #9a4e4e");

        model = new Model();
        model.initialize(pane, Main.WIDTH, Main.HEIGHT);
    }

    public void setInputHandlers(Scene scene) {
        scene.setOnKeyPressed(event -> {
            handleInput(model, event, true);
        });

        scene.setOnKeyReleased(event -> {
            handleInput(model, event, false);
        });
    }

    private void handleInput(Model model, KeyEvent event, boolean b) {
        handleInputForPaddle(model.getPaddle1(), event, b, KeyCode.W, KeyCode.S);
        handleInputForPaddle(model.getPaddle2(), event, b, KeyCode.UP, KeyCode.DOWN);
    }

    private void handleInputForPaddle(Paddle paddle, KeyEvent event, boolean b, KeyCode keyUp, KeyCode keyDown) {
        if (event.getCode() == keyUp) {
            paddle.setUp(b);
        }
        if (event.getCode() == keyDown) {
            paddle.setDown(b);
        }
    }
}
