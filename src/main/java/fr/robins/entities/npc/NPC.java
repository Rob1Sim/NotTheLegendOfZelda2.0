package fr.robins.entities.npc;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Entity;
import fr.robins.types.Vector2D;

public class NPC extends Entity implements Displayable {

    public NPC(NPCType type, Vector2D position) {
        super(type.getName(), type.getHp(),type.getMana(),type.getConstitution(), type.getStrength(), type.getDexterity() , type.getMoney(), position, type.getSprite());
    }

}
