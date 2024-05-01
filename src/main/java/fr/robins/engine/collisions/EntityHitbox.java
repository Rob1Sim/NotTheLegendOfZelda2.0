package fr.robins.engine.collisions;

import fr.robins.entities.Entity;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;

public class EntityHitbox extends HitBox{
    private final Entity hitEntity;

    public EntityHitbox(Entity entity) {
        super();
        this.hitEntity = entity;
        setHitBoxCoordinates(hitEntity.getWorldPosition());


    }
    /**
     * Caluclate the hitBox in function of the world coordinates
     */
    public void setHitBoxCoordinates(Vector2D worldCoordonates) {
        hitBox.setTranslateX(worldCoordonates.getX()+ (double) Utilities.TILE_SIZE /4);
        hitBox.setTranslateY(worldCoordonates.getY()+ (double) Utilities.TILE_SIZE/2 );
        hitBox.setWidth((double) Utilities.TILE_SIZE /2);
        hitBox.setHeight((double) Utilities.TILE_SIZE /2);
    }
}
