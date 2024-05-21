package fr.robins.items.interactable;

import fr.robins.engine.controller.SceneController;
import fr.robins.entities.Entity;
import fr.robins.items.others.OtherItemType;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

public class WaterTile extends Interactable{
    public WaterTile(Vector2D position) {
        super("water", "/tiles/tile_0015.png", position, false, false);
    }

    @Override
    public void interact(Entity entity) {
        if (entity.getInventory().getItemByName(OtherItemType.KEY.getName()) == null){
            entity.setHp(0);
        }
    }

    public static WaterTile waterTileGenerator(int column, int row) {
        return new WaterTile(TileManager.tilesToCoordinates(column,row));
    }
}
