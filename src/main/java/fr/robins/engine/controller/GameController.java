package fr.robins.engine.controller;

import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.engine.Inputs;
import fr.robins.engine.collisions.CollisionManager;
import fr.robins.engine.gamelogic.gamescene.GameSceneObserver;
import fr.robins.engine.gamelogic.gamescene.GameSceneSubject;
import fr.robins.engine.gamelogic.gamestate.GameStateObserver;
import fr.robins.engine.gamelogic.gamestate.GameStateSubject;
import fr.robins.engine.gamelogic.displayable.*;
import fr.robins.entities.Player;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;

public class GameController implements GameStateObserver, DisplayableListObserver, GameSceneObserver {

    private Player player;
    private final Stage stage;
    private final SceneController sceneController;

    //Observers
    private final GameStateSubject gmObserver;
    private final DisplayableSubject displayableListObserver;
    private final GameSceneSubject gameSceneObserver;

    private GameState gameState;
    private GameScene currentGameScene;

    private boolean isDisplayableListChanging = false;

    public GameController(Stage stage, Player player ) {
        this.stage = stage;
        this.player = player;

        this.gmObserver = new GameStateSubject();
        this.gmObserver.attach(this);
        this.displayableListObserver = new DisplayableSubject();
        this.displayableListObserver.attach(this);
        this.gameSceneObserver = new GameSceneSubject();
        this.gameSceneObserver.attach(this);

        sceneController = new SceneController(this,this.displayableListObserver, this.gameSceneObserver);

    }

    public void init(){
        //Initialisation
        gmObserver.setGameState(GameState.START);


        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/startScene.fxml");
            Pane root = loader.load(fxmlStream);


            //Button listener

            Button startButton = (Button) root.lookup("#startBtn");
            if (startButton != null) {
                startButton.setOnAction(sceneController::switchToGameScene);
            }

            Button leaveButton = (Button) root.lookup("#quitBtn");

            if (leaveButton != null) {
                leaveButton.setOnAction(actionEvent -> {
                    stage.close();
                });

            }

            //Scene settings
            //Start Menu
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/sceneBuilder/startScene.css")).toExternalForm());

            //Stage settings
            stage.setFullScreenExitHint("");
            stage.setResizable(false);
            stage.setTitle("Not the legend of zelda");
            //sceneController.test(stage);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            throw new RuntimeException("Error loading Start scene.", e);
        }
    }

    public void update(){
        if (gameState != GameState.DEAD && player.getHp() <= 0){
            gmObserver.setGameState(GameState.DEAD);
        }
        switch (gameState){
            case START:
                break;
            case WALKING:
                Inputs.handleMovementInput(sceneController.getCurrentScene(),player, sceneController.getPane(),stage);
                CollisionManager.environmentCollisionChecker(player,sceneController.getTileManager());
                CollisionManager.displayableCollisionChecker(displayableListObserver,currentGameScene.getPane(),player, sceneController);
                break;
            case WIN:
                sceneController.switchToEndScene();
                break;
            case DEAD:
                sceneController.switchToDeathScene();
                break;
        }
    }


    @Override
    public void updateGameState() {
        gameState = gmObserver.getGameState();
    }


    @Override
    public void updateDisplayableList() {
        if (gameState == GameState.WALKING){
            isDisplayableListChanging = true;
        }
    }

    @Override
    public void updateGameScene() {
        currentGameScene = gameSceneObserver.getGameScene();

    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gmObserver.setGameState(gameState);
    }

    public Player getPlayer() {
        return player;
    }

    public GameStateSubject getGameStateSubject() {
        return gmObserver;
    }
}
