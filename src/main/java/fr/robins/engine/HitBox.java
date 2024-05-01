package fr.robins.engine;

import fr.robins.entities.Entity;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HitBox implements Displayable {
    private final Rectangle hitBox;
    private boolean isColliding;
    private final Entity hitEntity;

    public HitBox(Entity entity) {

        this.hitEntity = entity;
        this.hitBox = new Rectangle();
        setHitBoxCoordinates(hitEntity.getWorldPosition());

        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.RED);
        this.isColliding = false;
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

    public boolean isColliding() {
        return isColliding;
    }
    public void setColliding(boolean isColliding) {
        this.isColliding = isColliding;
    }


    @Override
    public Node draw() {
        return hitBox;
    }
}
