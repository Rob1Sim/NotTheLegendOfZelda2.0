package fr.robins.items.consumable;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Entity;
import fr.robins.items.Collectable;
import fr.robins.items.Item;
import fr.robins.types.Vector2D;

import java.util.ArrayList;
import java.util.List;


public abstract class Consumable extends Item implements Collectable, Displayable {
    private int quantity;
    protected boolean useHasWeapon = false;
    public Consumable(String name, String spritePath , Vector2D position, boolean useHasWeapon, int price) {
        this(name,spritePath,position,price);
    }
    public Consumable(String name, String spritePath , Vector2D position, boolean useHasWeapon, int quantity ,int price) {
        this(name,spritePath,position,quantity,price);
    }
    public Consumable(String name, String spritePath, Vector2D position, int price){
        this(name,spritePath,position,1,price);
    }
    public Consumable(String name, String spritePath, Vector2D position, int quantity, int price){
        super(name,spritePath,position, price);
        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity() {
        quantity--;
    }

    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Subdivide a consumble with n quantity, into n number of the consumable
     * @return
     */
    public static List<Consumable> divideQuantity(Consumable consumable) {
        List<Consumable> consumables = new ArrayList<Consumable>();
        int quantity = consumable.quantity;
        consumable.setQuantity(1);
        consumables.add(consumable);
        for (int i = 0; i < quantity-1; i++) {
            Consumable consumable1 = consumable.clone();
            consumable1.setQuantity(1);
            consumables.add(consumable1);
        }
        return consumables;
    }

    public abstract Consumable clone();
}
