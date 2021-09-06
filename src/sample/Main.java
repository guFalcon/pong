package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.Controller;

public class Main extends Application {

    public static final double WIDTH = 800;
    public static final double HEIGHT = 400;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/game.fxml"));

        Parent root = loader.load();
        Controller controller = loader.getController();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        controller.setInputHandlers(scene);

        primaryStage.setTitle("Pong!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
