package fr.robins.entities.npc;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.gamescene.menuscenes.DialogueGameScene;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

import java.util.Random;

public class EndNPC extends NPC{
    public EndNPC(NPCType type, Vector2D position) {
        super(type, position);
        setDialogueText("Bravo tu as gagné !");
    }
    @Override
    public void interact(SceneController sceneController){
        sceneController.switchToMenuScene(new DialogueGameScene(this), GameState.WIN_MENU);
    }

    public static EndNPC endNPCGenerator(NPCType type, int column, int row) {
        return new EndNPC(type, TileManager.tilesToCoordinates(column,row));
    }
}
