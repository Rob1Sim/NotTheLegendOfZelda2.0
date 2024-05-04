package fr.robins.entities.npc;

import fr.robins.entities.Entity;
import fr.robins.types.Vector2D;

public class NPC extends Entity {

    public NPC(NPCType type, Vector2D position) {
        super(type.getName(), type.getHp(),type.getMana(),type.getConstitution(), type.getStrength(), type.getDexterity() , type.getMoney(), position, type.getSprite());
    }

}
