package fr.robins.items.interactable.destructible;

import fr.robins.entities.Entity;
import fr.robins.items.interactable.Interactable;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

public class Destructible extends Interactable {
    public Destructible(DestructibleType destructibleType, Vector2D position) {
        super(destructibleType.getName(), destructibleType.getPath(), position, true, true);
    }

    @Override
    public void interact(Entity entity) {
        return;
    }

    public static Destructible destructibleGenerator(int column, int row, DestructibleType destructibleType) {
        return new Destructible(destructibleType,TileManager.tilesToCoordinates(column,row));
    }
}
