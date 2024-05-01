package fr.robins.entities.enemy;

import fr.robins.items.Inventory;
import fr.robins.types.Vector2D;
import fr.robins.types.entities.EntityType;

public class RangeEnemy extends Enemy{
    public RangeEnemy(EntityType type, Vector2D position, Inventory inventory) {
        super(type, position, inventory);
    }
}
