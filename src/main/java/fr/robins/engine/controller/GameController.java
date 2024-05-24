package fr.robins.engine.controller;

import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.engine.gamelogic.gamescene.endscene.WinGameScene;
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
import fr.robins.world.TileManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

public class GameController implements GameStateObserver, DisplayableListObserver, GameSceneObserver {

    private final Player player;
    private final Stage stage;
    private final SceneController sceneController;

    //Observers
    private final GameStateSubject gmObserver;
    private final DisplayableSubject displayableListObserver;
    private final GameSceneSubject gameSceneObserver;

    private GameState gameState;
    private GameScene currentGameScene;

    private boolean isGameFinished = false;

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

        //region Start Menu

        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/startScene.fxml");
            Pane root = loader.load(fxmlStream);

            try {

                Button startButton = (Button) root.lookup("#startBtn");
                if (startButton != null) {
                    startButton.setOnAction(sceneController::switchToGameScene);
                }
            }catch (java.lang.UnsupportedOperationException e){
                System.out.println(e.getMessage());
            }

            Button leaveButton = (Button) root.lookup("#quitBtn");

            if (leaveButton != null) {
                leaveButton.setOnAction(actionEvent -> stage.close());
            }



            //Scene settings
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/sceneBuilder/css/startScene.css")).toExternalForm());
            //Stage settings
            stage.setFullScreenExitHint("");
            stage.setResizable(false);
            stage.setTitle("Not the legend of zelda");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            throw new RuntimeException("Error loading Start scene.", e);
        }
        //endregion

    }

    public void update(){
        if (gameState != GameState.DEAD && player.getHp() <= 0){
            gmObserver.setGameState(GameState.DEAD);
        }

        switch (gameState){
            case WALKING:
                Inputs.handleMovementInput(player, sceneController.getPane(),stage, sceneController);
                CollisionManager.environmentCollisionChecker(player,sceneController.getTileManager());
                CollisionManager.displayableCollisionChecker(displayableListObserver,currentGameScene.getPane(),player, sceneController);
                break;
            case WIN:
                sceneController.switchToMenuScene(new WinGameScene());
                isGameFinished = true;
                break;
            case MENU:
                Inputs.handleInventoryInput(stage,sceneController);
                break;
            case WIN_MENU:
                Inputs.handleWinMenuInputs(stage,sceneController);
                break;
            case LOADING:
                if (player.getPosition().getX() != ((Utilities.WINDOW_WIDTH /2)-((double) Utilities.TILE_SIZE /2))){
                    setGameState(GameState.WALKING);
                }
        }
    }


    @Override
    public void updateGameState() {
        gameState = gmObserver.getGameState();
    }


    @Override
    public void updateDisplayableList() {
    }

    @Override
    public void updateGameScene() {
        currentGameScene = gameSceneObserver.getGameScene();

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
