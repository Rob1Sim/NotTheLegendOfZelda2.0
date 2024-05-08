package fr.robins.items.interactable;

import fr.robins.engine.controller.SceneController;
import fr.robins.entities.Entity;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

public class Door extends Interactable{
    SceneController sceneController;
    int mapIndex;
    public Door(Vector2D position, SceneController sceneController, int mapIndex) {
        super("Door", "/tiles/tile_0009.png", position, false, false);
        this.sceneController = sceneController;
        this.mapIndex = mapIndex;
     }

    @Override
    public void interact(Entity entity) {
        sceneController.switchToALocationScene(mapIndex);
    }

    public static Door doorGenerator(int column, int row, SceneController sceneController,int mapIndex) {
        return new Door(TileManager.tilesToCoordinates(column,row), sceneController, mapIndex);
    }
}
