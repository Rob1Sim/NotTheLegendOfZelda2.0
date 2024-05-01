package fr.robins.entities.enemy;

import fr.robins.items.Inventory;
import fr.robins.types.Vector2D;
import fr.robins.types.entities.EntityType;

/**
 * Close Combat Enemy
 */
public class CCEnemy extends Enemy{
    public CCEnemy(EntityType type, Vector2D position, Inventory inventory) {
        super(type, position, inventory);
    }
}
