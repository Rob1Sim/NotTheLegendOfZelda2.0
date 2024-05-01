package fr.robins.entities.npc;

import fr.robins.entities.Entity;
import fr.robins.items.Inventory;
import fr.robins.types.Vector2D;
import fr.robins.types.entities.EntityType;

public class NPC extends Entity {

    public NPC(EntityType type, Vector2D position, Inventory inventory) {
        super(type.getName(), type.getHp(), type.getStrength(), type.getConstitution(), type.getRange(), type.getMoney(), position, inventory, type.getSpritePath());
    }

}
