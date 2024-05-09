package fr.robins.items.posable;

import fr.robins.engine.collisions.HitBox;
import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.displayable.DisplayableSubject;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.entities.Entity;
import fr.robins.entities.Player;
import fr.robins.items.interactable.Door;
import fr.robins.items.interactable.Interactable;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Bomb extends Posable{
    public Bomb(Vector2D position) {
        super("Bombe", "/sprites/items/bomb.png", position, 50);
    }

    @Override
    public void use(Player player, DisplayableSubject displayablesEntity, Pane pane) {
        switch (player.getDirection()){
            case UP -> this.setPosition(player.getPosition().add(new Vector2D(0, (double) -Utilities.TILE_SIZE /2)));
            case DOWN -> this.setPosition(player.getPosition().add(new Vector2D(0, Utilities.TILE_SIZE*1.2)));
            case LEFT -> this.setPosition(player.getPosition().add(new Vector2D(-Utilities.TILE_SIZE, 0)));
            case RIGHT -> this.setPosition(player.getPosition().add(new Vector2D(Utilities.TILE_SIZE, 0)));
        }

        List<Displayable> touchDisplayables = new ArrayList<>();

        HitBox explosionHitBox =  new HitBox(this.getPosition().subtract(new Vector2D(Utilities.TILE_SIZE,Utilities.TILE_SIZE)),Utilities.TILE_SIZE*3,Utilities.TILE_SIZE*3, Color.VIOLET );
        for(Displayable displayable : displayablesEntity.getDisplayables()){
            if (explosionHitBox.isIntersecting(displayable.getHitBox())){
                touchDisplayables.add(displayable);
            }
        }

        for (Displayable displayable : touchDisplayables){
            if (displayable instanceof Entity entity){
                entity.setHp(entity.getHp() - 10);
                if (entity.getHp() <= 0){
                    Displayable.removeDisplayable(displayable,displayablesEntity,pane);
                }
            }
            if (displayable instanceof Interactable interactable){
                if (interactable.isDestructible()){
                    Displayable.removeDisplayable(displayable,displayablesEntity,pane);
                }
            }
        }

        //Faire disparaitre la bombe
        Displayable.removeDisplayable(this,displayablesEntity,pane);
        player.getInventory().removeItem(this);

    }

    public static Bomb bombGenerator(int column, int row) {
        return new Bomb(TileManager.tilesToCoordinates(column,row));
    }

}
