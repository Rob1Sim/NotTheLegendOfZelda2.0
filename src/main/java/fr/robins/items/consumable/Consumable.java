package fr.robins.items.consumable;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Entity;
import fr.robins.items.Collectable;
import fr.robins.items.Item;
import fr.robins.types.Vector2D;


public abstract class Consumable extends Item implements Collectable, Displayable {
    private int quantity;
    protected boolean useHasWeapon = false;
    public Consumable(String name, String spritePath , Vector2D position, boolean useHasWeapon) {
        this(name,spritePath,position);
    }
    public Consumable(String name, String spritePath, Vector2D position){
        super(name,spritePath,position);
        quantity = 1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public abstract void use(Entity entity);


    public boolean isUseHasWeapon() {
        return useHasWeapon;
    }

    @Override
    public String toString() {
        return getName()+" - Quantité: "+quantity;
    }
}
