package fr.robins.entities.npc;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.gamescene.menuscenes.DialogueGameScene;
import fr.robins.entities.Entity;
import fr.robins.items.interactable.InteractableWithInput;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

public class NPC extends Entity implements Displayable, InteractableWithInput {

    private final String dialogueText;
    public NPC(NPCType type, Vector2D position) {
        super(type.getName(), type.getHp(),type.getMana(),type.getConstitution(), type.getStrength(), type.getDexterity() , type.getMoney(), position, type.getSprite());
        dialogueText = type.getDialogueText();
    }


    @Override
    public void interact(SceneController sceneController) {
        sceneController.switchToMenuScene(new DialogueGameScene(this));
    }

    public static NPC npcGenerator(int column, int row, NPCType type) {
        return new NPC(type, TileManager.tilesToCoordinates(column,row));
    }

    public String getDialogueText() {
        return dialogueText;
    }
}
