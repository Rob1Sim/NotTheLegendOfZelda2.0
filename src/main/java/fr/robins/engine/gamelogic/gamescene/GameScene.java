package fr.robins.engine.gamelogic.gamescene;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Player;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.scene.layout.Pane;

import java.util.List;

public class GameScene {
    private final List<Displayable> displayableList;
    private final Pane pane;

    /**
     * Represent a game scene
     * @param tileManager tileManager with the map to display
     * @param displayableList List of item or entity that should be dispayed
     * @param player The player
     * @param playerPostitionTile The tiles where the player should spawn
     */
    public GameScene(TileManager tileManager, List<Displayable> displayableList, Player player, Vector2D playerPostitionTile) {
        this.displayableList = displayableList;

        //Pane settings
        pane = setMap(tileManager);
        pane.setPrefSize(Utilities.WINDOW_WIDTH,Utilities.WINDOW_HEIGHT);

        //Spawn enemy/PNJ/Items
        renderDisplayableList(displayableList,pane);

        //Spawn player
        renderPlayer(player,pane);
        Player.teleportPlayer(pane,TileManager.tilesToCoordinates(playerPostitionTile.getIntX(),playerPostitionTile.getIntY()));


    }

    public Pane getPane() {
        return pane;
    }


    /**
     * Display an entity with its hitBox
     * @param displayable
     * @param pane
     */
    public static void renderDisplayable(Displayable displayable, Pane pane) {
        pane.getChildren().addAll(displayable.draw(),displayable.getHitBox().draw());
    }
    /**
     * Display the displayables
     */
    public static void renderDisplayableList(List<Displayable> displayableList, Pane pane) {
        if (!displayableList.isEmpty()){
            for (Displayable e : displayableList) {
                renderDisplayable(e,pane);
            }
        }
    }

    public static void renderPlayer(Player displayable, Pane pane) {
        pane.getChildren().addAll(displayable.draw(),displayable.getHitBox().draw(), displayable.getCollisionHitBox().draw());
    }

    /**
     * Set map from the xml file
     * @param tileManager the mapManager
     * @return The pane
     */
    public static Pane setMap(TileManager tileManager){

        Pane backgroundPane = new Pane();

        //Display tiles
        for (int i = 0; i < tileManager.getNumberOfLayers(); i++) {
            tileManager.draw(i,backgroundPane);
        }
        return backgroundPane;
    }
}
