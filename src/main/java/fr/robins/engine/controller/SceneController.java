package fr.robins.engine.controller;

import fr.robins.engine.Displayable;
import fr.robins.engine.GameScene;
import fr.robins.engine.gamestate.GameState;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SceneController {
    private Stage stage;
    private Scene currentScene;

    private TileManager tileManager;
    private final GameController gameController;
    private GameScene currentGameScene;

    public SceneController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Load the first map when the button is clicked
     */
    public void switchToGameScene(ActionEvent event) {
        stage = (Stage) (((Node)event.getSource())).getScene().getWindow();

        //Map de spawn
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        currentGameScene = new GameScene(tileManager, new ArrayList<Displayable>(), gameController.getPlayer(), new Vector2D(35,33));

        switchToScene();
        gameController.setGameState(GameState.WALKING);
    }

    public void test(Stage stage) {
        this.stage = stage;

        //Map de spawn
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        currentGameScene = new GameScene(tileManager, new ArrayList<Displayable>(), gameController.getPlayer(), new Vector2D(35,33));

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


}
