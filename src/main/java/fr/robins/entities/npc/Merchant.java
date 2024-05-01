package fr.robins.entities.npc;

import fr.robins.items.Inventory;
import fr.robins.types.Vector2D;
import fr.robins.types.entities.EntityType;

public class Merchant extends NPC{
    public Merchant(EntityType type, Vector2D position, Inventory inventory) {
        super(type, position, inventory);
    }
}
