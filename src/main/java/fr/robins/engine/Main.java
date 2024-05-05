package fr.robins.engine;

import fr.robins.engine.controller.GameController;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) {
        Font.loadFont(getClass().getResourceAsStream("/sceneBuilder/fonts/ARCADEIN.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/sceneBuilder/fonts/ARCADEOUT.ttf"), 12);

        GameController gameController = new GameController(stage);

        gameController.init();

        //GameLoop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameController.update();
            }
        };
        gameLoop.start();
    }
}