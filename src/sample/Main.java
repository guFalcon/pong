package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.Controller;

/**
 * Thanks to EdenCoding
 * https://github.com/edencoding/javafx-game-dev
 */
public class Main extends Application {

    public static final double WIDTH = 750;
    public static final double HEIGHT = 450;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/game.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Controller controller = loader.getController();
        controller.setInputHandlers(scene);
        stage.setOnCloseRequest(event -> controller.shutdownModel());

        stage.setTitle("Pong!");
        stage.setScene(scene);
        stage.show();
    }
}
