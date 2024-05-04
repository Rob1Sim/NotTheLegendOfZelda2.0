package fr.robins.engine;

import fr.robins.engine.controller.GameController;

import fr.robins.engine.gamestate.GameStateObserver;
import fr.robins.engine.gamestate.GameStateSubject;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) {
        GameStateSubject gameState = new GameStateSubject();
        GameController gameController = new GameController(stage, gameState);

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