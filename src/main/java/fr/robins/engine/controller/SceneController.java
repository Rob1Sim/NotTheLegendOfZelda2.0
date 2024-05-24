package fr.robins.engine.controller;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.gamescene.combatScene.CombatScene;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.engine.gamelogic.gamescene.endscene.WinGameScene;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.engine.gamelogic.displayable.DisplayableListObserver;
import fr.robins.engine.gamelogic.displayable.DisplayableSubject;
import fr.robins.engine.gamelogic.gamescene.GameSceneObserver;
import fr.robins.engine.gamelogic.gamescene.GameSceneSubject;
import fr.robins.entities.Fighter;
import fr.robins.entities.Player;
import fr.robins.entities.enemy.Enemy;
import fr.robins.entities.enemy.EnemyType;
import fr.robins.entities.npc.*;
import fr.robins.items.Item;
import fr.robins.items.ItemType;
import fr.robins.items.combat.weapons.WeaponItem;
import fr.robins.items.combat.weapons.WeaponType;
import fr.robins.items.combat.weapons.WeaponX;
import fr.robins.items.consumable.potions.Potion;
import fr.robins.items.consumable.potions.PotionType;
import fr.robins.items.interactable.Door;
import fr.robins.items.interactable.EndTile;
import fr.robins.items.interactable.WaterTile;
import fr.robins.items.interactable.destructible.Destructible;
import fr.robins.items.interactable.destructible.DestructibleType;
import fr.robins.items.others.OtherItem;
import fr.robins.items.others.OtherItemType;
import fr.robins.items.posable.Bomb;
import fr.robins.items.posable.NuclearBomb;
import fr.robins.types.Vector2D;
import fr.robins.world.MapScene;
import fr.robins.world.TileManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
    private final List<MapScene> mapScenes;

    //Observers
    private final DisplayableSubject displayableObserver;
    private final GameSceneSubject gameSceneObserver;


    public SceneController(GameController gameController, DisplayableSubject displayableObserver, GameSceneSubject gameSceneObserver) {
        this.gameController = gameController;

        this.displayableObserver = displayableObserver;
        this.gameSceneObserver = gameSceneObserver;

        this.displayableObserver.attach(this);
        this.gameSceneObserver.attach(this);

        mapScenes = new ArrayList<>();

        mapScenes.add(generateSpawnMap());
        mapScenes.add(examScene());

        this.displayableObserver.setDisplayables(mapScenes.getFirst().getDisplayableList());
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
        tileManager = mapScenes.getFirst().getTileManager();
        gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer()));

        //SPAWN
        switchToScene(new Vector2D(27,41));
        gameController.setGameState(GameState.WALKING);
    }

    /**
     * Call when player touch an enemy
     */
    public void switchToCombatScene(Fighter enemy) {
        gameController.setGameState(GameState.COMBAT);
        gameController.getPlayer().getVelocity().set(0,0);
        gameSceneObserver.setGameScene(new CombatScene(gameController.getPlayer(),enemy,this,gameController.getGameStateSubject()));
        switchToScene();
    }

    /**
     * Call when the button leave combat is pressed
     */
    public void switchToGameSceneAfterCombat(ActionEvent event) {
        stage = (Stage) (((Node)event.getSource())).getScene().getWindow();
        switchToGameSceneAfterLeavingMenu();
    }

    /**
     * Display the menu
     * @param gameScene Menu Scene
     */
    public void switchToMenuScene(GameScene gameScene){
        gameController.setGameState(GameState.MENU);
        gameSceneObserver.setGameScene(gameScene);
        switchToScene();
    }

    public void switchToMenuScene(GameScene gameScene, GameState gameState){
        gameController.setGameState(gameState);
        gameSceneObserver.setGameScene(gameScene);
        switchToScene();
    }

    /**
     * Call whenever a menu is closed
     */
    public void switchToGameSceneAfterLeavingMenu(){
        gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer()));
        switchToScene();
        Player.teleportPlayer(currentGameScene.getPane(),gameController.getPlayer().getPosition());
        gameController.setGameState(GameState.WALKING);
    }


    /**
     * Change the map displayed, and teleport the player, the map should be in the Map List
     * @param mapIndex index of the map list
     */
    public void switchToALocationScene(int mapIndex) {

        if (mapIndex < mapScenes.size() && mapIndex >= 0) {
            gameController.setGameState(GameState.LOADING);
            gameController.getPlayer().getVelocity().set(0,0);

            MapScene mapScene = mapScenes.get(mapIndex);

            //Affecte les élément posables à la liste des truc a rendre de la nouvelle scène
            for(Item item: gameController.getPlayer().getInventory().getItemsByClass(ItemType.POSABLE)){
                if (!mapScene.getDisplayableList().contains(item)){
                    mapScene.getDisplayableList().add(item);
                }
            }

            displayableObserver.setDisplayables( mapScene.getDisplayableList() );
            setTileManager(mapScene.getTileManager());

            gameSceneObserver.setGameScene(new GameScene(tileManager, displayable, gameController.getPlayer()));
            switchToScene(mapScene.getPlayerTilePosition());

        }
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

    public MapScene generateSpawnMap(){
        List<Displayable> spawnDisplaybles = new ArrayList<>();

        spawnDisplaybles.add(Door.doorGenerator(19,44,this,1));
        spawnDisplaybles.add(Trader.traderGenerator(NPCType.NPC_BLACK_SMITH,26,17));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.GHOST,26,57));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.GHOST,22,58));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.MAGE,51,43));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.RAT,48,38));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.RAT,58,35));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.ROMAN,42,23));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.CASPER,54,23));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.MAGE,58,10));
        spawnDisplaybles.add(WeaponItem.weaponGenerator(WeaponType.AXE,44,9));
        spawnDisplaybles.add(NPC.npcGenerator(NPCType.NPC_VIKING,33,42));
        spawnDisplaybles.add(NPC.npcGenerator(NPCType.NPC_WOMAN,21,42));
        spawnDisplaybles.add(Trader.traderGenerator(NPCType.NPC_TRADER,11,58));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.CASPER,51,48));

        spawnDisplaybles.add(WeaponX.weaponGenerator(WeaponType.WEAPON_X,35,45));
        spawnDisplaybles.add(Enemy.enemyGenerator(EnemyType.WEAPON_X,37,45));
        spawnDisplaybles.add(EndNPC.endNPCGenerator(NPCType.NPC_VIKING,32,45));
        spawnDisplaybles.add(EndTile.endTileGenerator(32,47,this));
        spawnDisplaybles.add(NuclearBomb.nuclearBombGenerator(32,48,this));


        spawnDisplaybles.add(WeaponItem.weaponGenerator(WeaponType.SHORT_SWORD,27,44));
        spawnDisplaybles.add(Destructible.destructibleGenerator(DestructibleType.BARREL,29,47));
        spawnDisplaybles.add(Destructible.destructibleGenerator(DestructibleType.TABLE,11,61));
        spawnDisplaybles.add(Destructible.destructibleGenerator(DestructibleType.CHAIR,11,60));
        spawnDisplaybles.add(Destructible.destructibleGenerator(DestructibleType.CRATE,47,14));
        spawnDisplaybles.add(Destructible.destructibleGenerator(DestructibleType.CRATE,48,14));
        spawnDisplaybles.add(Destructible.destructibleGenerator(DestructibleType.CRATE,49,14));

        Enemy boss = Enemy.enemyGenerator(EnemyType.BOSS_MAGE,12,21);
        spawnDisplaybles.add(boss);

        return new MapScene("/tiles/tilemap/finalMap.xml",spawnDisplaybles,new Vector2D(20,44));
    }

    public MapScene examScene(){
        List<Displayable> examDisplaybles = new ArrayList<>();

        examDisplaybles.add(Enemy.enemyGenerator(EnemyType.RAT,38,36));
        examDisplaybles.add(Door.doorGenerator(30,35,this,0));



        return new MapScene("/tiles/tilemap/map2.xml",examDisplaybles,new Vector2D(31,35));
    }

    public Player getPlayer(){
        return gameController.getPlayer();
    }

    public GameController getGameController(){
        return gameController;
    }
}
