package fr.robins.entities.npc;

import fr.robins.entities.Entity;
import fr.robins.items.Inventory;
import fr.robins.types.Vector2D;
import fr.robins.types.entities.NPCType;

public class NPC extends Entity {

    public NPC(NPCType type, Vector2D position) {
        super(type.getName(), type.getHp(),type.getMana(),type.getConstitution(), type.getStrength(), type.getDexterity() , type.getMoney(), position, type.getSprite());
    }

}
