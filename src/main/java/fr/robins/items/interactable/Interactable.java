package fr.robins.items.interactable;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Entity;
import fr.robins.entities.Player;
import fr.robins.items.Item;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;

public abstract class Interactable extends Item implements Displayable {
    private boolean isCollisionable;
    private boolean isDestructible;
    public Interactable(String name, String spritePath , Vector2D position, boolean isCollisionable, boolean isDestructible) {
        super(name,spritePath,position);
        this.isCollisionable = isCollisionable;
        this.isDestructible = isDestructible;
        this.getHitBox().setWidth((double) Utilities.TILE_SIZE /2);
        this.getHitBox().setHeight((double) Utilities.TILE_SIZE /2);
        this.getHitBox().setCoords(this.getHitBox().getPosition().add(new Vector2D(this.getHitBox().getWidth()/2, this.getHitBox().getHeight()/2)));
    }

    public boolean isCollisionable() {
        return isCollisionable;
    }

    public void setCollisionable(boolean collisionable) {
        isCollisionable = collisionable;
    }

    public abstract void interact(Entity entity);

    public boolean isDestructible() {
        return isDestructible;
    }
}
