package fr.robins;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 600,400);

        stage.setScene(scene);
        stage.setTitle("Not the legend of zelda");
        stage.show();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //Boucle de gameplay

            }
        };
        gameLoop.start();
    }
}