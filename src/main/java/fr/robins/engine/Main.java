package fr.robins.engine;

import fr.robins.engine.controller.GameController;

import fr.robins.engine.gamelogic.gamescene.GameSceneSubject;
import fr.robins.engine.gamelogic.gamescene.deadscene.DeadGameScene;
import fr.robins.entities.Player;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private boolean isPlayerDead = false;

    @Override
    public void start(Stage stage) {
        Font.loadFont(getClass().getResourceAsStream("/sceneBuilder/fonts/ARCADEIN.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/sceneBuilder/fonts/ARCADEOUT.ttf"), 12);

        Player player = new Player(new Vector2D(Utilities.WINDOW_WIDTH /2,Utilities.WINDOW_HEIGHT /2));


        GameController gameController = new GameController(stage, player);

        gameController.init();

        //Dead Scene
        Scene deadScene = new Scene(new DeadGameScene().getPane());
        deadScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/sceneBuilder/startScene.css")).toExternalForm());

        Button retryBtn = (Button) deadScene.lookup("#retry");
        retryBtn.setOnAction(event ->  stage.close());

        Button leaveBtn = (Button) deadScene.lookup("#leave");
        leaveBtn.setOnAction(event ->  stage.close());


        //GameLoop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (player.getHp()<=0 && !isPlayerDead){
                    isPlayerDead = true;
                    stage.setScene(deadScene);

                }
                gameController.update();
            }
        };
        gameLoop.start();

    }
}