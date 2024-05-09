package fr.robins.entities.npc;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.gamescene.menuscenes.DialogueGameScene;
import fr.robins.engine.gamelogic.gamescene.menuscenes.TradeGameScene;
import fr.robins.items.interactable.InteractableWithInput;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

public class Trader extends NPC implements InteractableWithInput {
    public Trader(NPCType type, Vector2D position) {
        super(type, position);
    }

    @Override
    public void interact(SceneController sceneController) {
        sceneController.switchToMenuScene(new TradeGameScene(this,sceneController));
    }

    public static Trader traderGenerator(NPCType type,int column, int row) {
        return new Trader(type, TileManager.tilesToCoordinates(column,row));
    }
}
