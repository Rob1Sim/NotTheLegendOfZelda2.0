package fr.robins.engine.controller;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.gamescene.combatScene.CombatScene;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.engine.gamelogic.gamescene.inventoryscene.InventoryGameScene;
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
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneController implements DisplayableListObserver, GameSceneObserver {
    private Stage stage;

    private Scene currentScene;

    private TileManager tileManager;
    private final GameController gameController;

    private GameScene currentGameScene;
    private List<Displayable> displayable;

    MediaPlayer mediaPlayer;
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
                Enemy.enemyGenerator(EnemyType.GHOST,33,38),
                Enemy.enemyGenerator(EnemyType.RAT,40,38),
                Enemy.enemyGenerator(EnemyType.MAGE,42,38),
                WeaponItem.weaponGenerator(WeaponType.DOUBLE_AXE,36,31),
                WeaponItem.weaponGenerator(WeaponType.KNIFE,37,31),
                WeaponItem.weaponGenerator(WeaponType.IRON_SWORD,38,31),
                WeaponItem.weaponGenerator(WeaponType.AXE,39,31),
                WeaponItem.weaponGenerator(WeaponType.SHORT_SWORD,42,42),
                Potion.potionGenerator(PotionType.HEAL_POTION,32,36),
                Potion.potionGenerator(PotionType.HEAL_POTION,38,35),
                Potion.potionGenerator(PotionType.MANA_POTION, 39,35));

        Media walkingMusic = new Media(Objects.requireNonNull(getClass().getResource("/music/walking.wav")).toExternalForm());
        mediaPlayer = new MediaPlayer(walkingMusic);
    }

    /**
     * Load the first map when the button is clicked
     */
    public void switchToGameScene(ActionEvent event) {
        stage = (Stage) (((Node)event.getSource())).getScene().getWindow();

        //Change player name
        TextField playerName = (TextField) stage.getScene().lookup("#playerName");
        if (playerName != null) {
            if (playerName.getText() != null) {
                gameController.getPlayer().setName(playerName.getText());
            }else{
                gameController.getPlayer().setName("Link");
            }
        }

        //Map de spawn
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer()));

        switchToScene(new Vector2D(35,33));
        gameController.setGameState(GameState.WALKING);
        //Mets la musique

        mediaPlayer.setAutoPlay(true);
    }

    public void switchToCombatScene(Fighter enemy) {

        gameController.setGameState(GameState.COMBAT);

        //Désactive la vélocité pour éviter que le joueur s'envole en repassant dans le monde
        gameController.getPlayer().getVelocity().set(0,0);

        gameSceneObserver.setGameScene(new CombatScene(gameController.getPlayer(),enemy,this,gameController.getGameStateSubject()));
        switchToScene();

        Media combatMusic = new Media(getClass().getResource("/music/fight.wav").toExternalForm());
        mediaPlayer.pause();
        mediaPlayer = new MediaPlayer(combatMusic);
        mediaPlayer.setAutoPlay(true);
    }

    public void switchToGameSceneAfterCombat(ActionEvent event) {
        stage = (Stage) (((Node)event.getSource())).getScene().getWindow();

        switchToGameSceneAfterLeavingMenu();

        Media combatMusic = new Media(getClass().getResource("/music/walking.wav").toExternalForm());
        mediaPlayer.pause();
        mediaPlayer = new MediaPlayer(combatMusic);
        mediaPlayer.setAutoPlay(true);
    }

    public void switchToInventoryScene(){
        gameController.setGameState(GameState.INVENTORY);

        gameSceneObserver.setGameScene(new InventoryGameScene(gameController.getPlayer()));
        switchToScene();
    }

    public void switchToGameSceneAfterLeavingMenu(){
        gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer()));
        switchToScene();

        //Renvoie le joueur à sa position antérieur, on enlève la moitié de l'écran moins une tuile pour éviter que avec le recentrement de l'écran le joueur se déplace
        Vector2D newPosition = new Vector2D(gameController.getPlayer().getPosition().getX(),gameController.getPlayer().getPosition().getY()+ ((Utilities.WINDOW_HEIGHT/2)-((double) Utilities.TILE_SIZE )));

        Player.teleportPlayer(currentGameScene.getPane(),newPosition);
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
