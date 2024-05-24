package fr.robins.items.interactable;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.entities.Entity;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

public class EndTile extends Interactable{
    SceneController sceneController;
    int mapIndex;
    public EndTile(Vector2D position, SceneController sceneController) {
        super("Fin", "/tiles/tile_0009.png", position, false, false);
        this.sceneController = sceneController;
     }

    @Override
    public void interact(Entity entity) {
        sceneController.getGameController().getGameStateSubject().setGameState(GameState.WIN);
    }

    public static EndTile endTileGenerator(int column, int row, SceneController sceneController) {
        return new EndTile(TileManager.tilesToCoordinates(column,row), sceneController);
    }
}
