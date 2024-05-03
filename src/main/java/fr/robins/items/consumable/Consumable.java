package fr.robins.items.consumable;

import fr.robins.entities.Entity;
import fr.robins.items.Item;

import java.util.List;

public abstract class Consumable extends Item {
    private int quantity;
    protected boolean useHasWeapon = false;
    public Consumable(String name, boolean useHasWeapon) {
        super(name);
        quantity = 1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }
    public void decreaseQuantity() {
        quantity--;
    }

    public abstract void use(Entity entity);

    /**
    public static String getNameOfConsumableList(List<Item> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Consumable consumable = (Consumable) list.get(i);
            if (consumable.getQuantity() > 0) {
                sb.append(i).append(": ").append(consumable.getName()).append(", Quantité : ").append(consumable.getQuantity()).append("\n");
            }
        }
        if (sb.length() < 0) {
            sb.append("Tu n'a pas de consumable !");
        }
        return sb.toString();
    }**/

    public boolean isUseHasWeapon() {
        return useHasWeapon;
    }
}
