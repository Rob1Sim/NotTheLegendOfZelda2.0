package fr.robins.entities.npc;

import fr.robins.engine.controller.SceneController;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

import java.util.Random;

public class Stealer extends NPC{
    public Stealer(NPCType type, Vector2D position) {
        super(type, position);
    }
    @Override
    public void interact(SceneController sceneController){
        if (sceneController.getPlayer().getInventory().getItems().isEmpty()){
            super.interact(sceneController);
        }else{
            int random = new Random().nextInt(sceneController.getPlayer().getInventory().getItems().size());
            sceneController.getPlayer().getInventory().removeItem(sceneController.getPlayer().getInventory().getItems().get(random));
        }
    }

    public static Stealer stealerGenerator(NPCType type,int column, int row) {
        return new Stealer(type, TileManager.tilesToCoordinates(column,row));
    }
}
