package fr.robins.engine.collisions;

import fr.robins.entities.Entity;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.scene.paint.Color;


public class CollisionManager {
    /**
     * Checks colision of the given entity with the tiles in the environement;
     */
    public static void environmentCollisionChecker(Entity entity, TileManager tileManager){
        Vector2D tileToCheck;
        HitBox hitBox = entity.getCollisionHitBox();
        switch (entity.getDirection()){
            case UP:

                tileToCheck = entity.getHitBox().getCoords().add(new Vector2D(0,2));
                isACollision(tileManager,tileToCheck,entity);

                hitBox.setCoords(entity.getHitBox().getCoords().subtract(new Vector2D(0,2)));
                hitBox.setHeight(2);
                hitBox.setWidth(entity.getHitBox().getWidth());
                hitBox.draw();

                break;
            case DOWN:
                tileToCheck = entity.getHitBox().getCoords().subtract(new Vector2D(0,entity.getHitBox().getHeight()+2));
                isACollision(tileManager,tileToCheck,entity);

                hitBox.setCoords(entity.getHitBox().getCoords().add(new Vector2D(0,entity.getHitBox().getHeight())));
                hitBox.setHeight(2);
                hitBox.setWidth(entity.getHitBox().getWidth());
                hitBox.draw();

                break;
            case LEFT:
                tileToCheck = entity.getHitBox().getCoords().subtract(new Vector2D(2,0));
                isACollision(tileManager,tileToCheck,entity);

                hitBox.setCoords(entity.getHitBox().getCoords().subtract(new Vector2D(2,0)));
                hitBox.setHeight(entity.getHitBox().getHeight());
                hitBox.setWidth(2);
                hitBox.draw();

                break;
            case RIGHT:
                tileToCheck = entity.getHitBox().getCoords().add(new Vector2D(entity.getHitBox().getWidth()+2,0));
                isACollision(tileManager,tileToCheck,entity);

                hitBox.setCoords(entity.getHitBox().getCoords().add(new Vector2D(entity.getHitBox().getWidth(),0)));
                hitBox.setHeight(entity.getHitBox().getHeight());
                hitBox.setWidth(2);
                hitBox.draw();

                break;
        }
    }

    /**
     * Check if the entity must stop.
     */
    private static void isACollision(TileManager tileManager, Vector2D tileToCheck, Entity entity){
        int[] tileCoordonate = TileManager.coordinatesToTiles(tileToCheck);
        System.out.println(tileManager.getLayersMapData(0).get(tileCoordonate[0]).get(tileCoordonate[1]));
        if (tileManager.getLayersMapData(1).get(tileCoordonate[0]).get(tileCoordonate[1]) != -1){
            //TODO: Ajouter les collisions avec les ennemies
            entity.getCollisionHitBox().setColliding(true);
        }else {
            entity.getCollisionHitBox().setColliding(false);
        }
    }
}
