package fr.robins.engine.controller;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.engine.gamelogic.displayable.DisplayableListObserver;
import fr.robins.engine.gamelogic.displayable.DisplayableSubject;
import fr.robins.engine.gamelogic.gamescene.GameSceneObserver;
import fr.robins.engine.gamelogic.gamescene.GameSceneSubject;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SceneController implements DisplayableListObserver, GameSceneObserver {
    private Stage stage;
    private Scene currentScene;

    private TileManager tileManager;
    private final GameController gameController;

    private GameScene currentGameScene;
    private List<Displayable> displayable;

    //Observers
    private final DisplayableSubject displayableObserver;
    private final GameSceneSubject gameSceneObserver;


    public SceneController(GameController gameController, DisplayableSubject displayableObserver, GameSceneSubject gameSceneObserver) {
        this.gameController = gameController;

        this.displayableObserver = displayableObserver;
        this.gameSceneObserver = gameSceneObserver;

        this.displayableObserver.attach(this);
        this.gameSceneObserver.attach(this);

        this.displayableObserver.setDisplayables(new ArrayList<>());

    }

    /**
     * Load the first map when the button is clicked
     */
    public void switchToGameScene(ActionEvent event) {
        stage = (Stage) (((Node)event.getSource())).getScene().getWindow();

        //Map de spawn
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer(), new Vector2D(35,33)));

        switchToScene();
        gameController.setGameState(GameState.WALKING);
    }

    public void test(Stage stage) {
        this.stage = stage;

        //Map de spawn
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer(), new Vector2D(35,33)));

        switchToScene();
        gameController.setGameState(GameState.WALKING);
    }

    public void switchToCombatScene() {
        gameController.setGameState(GameState.COMBAT);
    }

    public void switchToEndScene() {
        gameController.setGameState(GameState.WIN);
    }

    public void switchToALocationScene(String xmlMapPath) {
        setTileManager(new TileManager(xmlMapPath));
    }

    /**
     * Change the scene with javafx
     */
    private void switchToScene() {
        Scene scene = new Scene(currentGameScene.getPane());
        currentScene = scene;
        stage.setScene(scene);
        stage.show();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public Pane getPane() {
        return currentGameScene.getPane();
    }


    @Override
    public void updateDisplayableList() {
        displayable = displayableObserver.getDisplayables();
    }

    @Override
    public void updateGameScene() {
        currentGameScene = gameSceneObserver.getGameScene();
    }
}
