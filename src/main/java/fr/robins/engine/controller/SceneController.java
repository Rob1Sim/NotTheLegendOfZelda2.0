package fr.robins.engine.controller;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.gamescene.combatScene.CombatScene;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.engine.gamelogic.displayable.DisplayableListObserver;
import fr.robins.engine.gamelogic.displayable.DisplayableSubject;
import fr.robins.engine.gamelogic.gamescene.GameSceneObserver;
import fr.robins.engine.gamelogic.gamescene.GameSceneSubject;
import fr.robins.entities.Fighter;
import fr.robins.entities.Player;
import fr.robins.entities.enemy.Enemy;
import fr.robins.entities.enemy.EnemyType;
import fr.robins.items.combat.weapons.WeaponItem;
import fr.robins.items.combat.weapons.WeaponType;
import fr.robins.items.consumable.potions.Potion;
import fr.robins.items.consumable.potions.PotionType;
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
        this.displayableObserver.add(
                new Enemy(EnemyType.ENEMY_GHOST,TileManager.tilesToCoordinates(38,33)),
                new WeaponItem(WeaponType.AXE, TileManager.tilesToCoordinates(35,31)),
                new WeaponItem(WeaponType.DOUBLE_AXE, TileManager.tilesToCoordinates(36,31)),
                new WeaponItem(WeaponType.KNIFE, TileManager.tilesToCoordinates(37,31)),
                new WeaponItem(WeaponType.IRON_SWORD, TileManager.tilesToCoordinates(38,31)),
                new WeaponItem(WeaponType.AXE, TileManager.tilesToCoordinates(39,31)),
                new Potion(PotionType.HEAL_POTION,TileManager.tilesToCoordinates(32,36)),
                new Potion(PotionType.HEAL_POTION,TileManager.tilesToCoordinates(38,35)));
    }

    /**
     * Load the first map when the button is clicked
     */
    public void switchToGameScene(ActionEvent event) {
        stage = (Stage) (((Node)event.getSource())).getScene().getWindow();

        //Map de spawn
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer()));

        switchToScene(new Vector2D(35,33));
        gameController.setGameState(GameState.WALKING);
    }

    public void switchToCombatScene(Fighter enemy) {

        gameController.setGameState(GameState.COMBAT);

        //Désactive la vélocité pour éviter que le joueur s'envole en repassant dans le monde
        gameController.getPlayer().getVelocity().set(0,0);

        gameSceneObserver.setGameScene(new CombatScene(gameController.getPlayer(),enemy,this,gameController.getGameStateSubject()));
        switchToScene();
    }

    public void switchToGameSceneAfterCombat(ActionEvent event) {
        stage = (Stage) (((Node)event.getSource())).getScene().getWindow();

        gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer()));

        switchToScene();
        //Renvoie le joueur à sa position antérieur
        Player.teleportPlayer(currentGameScene.getPane(),gameController.getPlayer().getPosition());
        gameController.setGameState(GameState.WALKING);
    }

    public void switchToEndScene() {
        gameController.setGameState(GameState.WIN);
    }

    public void switchToALocationScene(String xmlMapPath) {
        setTileManager(new TileManager(xmlMapPath));
    }

    /**
     * Change the scene with javafx and move the player to the desired position
     * @param playerTilePosition on which tile should the player spawn, if set to null
     */
    private void switchToScene(Vector2D playerTilePosition) {
        switchToScene();
        Player.teleportPlayer(currentGameScene.getPane(), TileManager.tilesToCoordinates(playerTilePosition.getIntX(),playerTilePosition.getIntY()));
    }


    /**
     * Change the scene with javafx
     */
    private void switchToScene(){
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

    public DisplayableSubject getDisplayableObserver() {
        return displayableObserver;
    }


}
