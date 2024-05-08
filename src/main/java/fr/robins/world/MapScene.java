package fr.robins.world;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.types.Vector2D;

import java.util.List;

/**
 * Represent a map scene where the player can walk on, (for example, by interacting with a door)
 */
public class MapScene {
    private final TileManager tileManager;
    private final List<Displayable> displayableList;
    private final Vector2D playerTilePosition;

    public MapScene(String xmlPath, List<Displayable> displayableList, Vector2D playerTilePosition) {
        this.tileManager = new TileManager(xmlPath);
        this.displayableList = displayableList;
        this.playerTilePosition = playerTilePosition;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public List<Displayable> getDisplayableList() {
        return displayableList;
    }

    public Vector2D getPlayerTilePosition() {
        return playerTilePosition;
    }
}
