package fr.robins.items;

import fr.robins.items.combat.weapons.WeaponItem;
import fr.robins.items.consumable.Consumable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {
    private final List<Item> items;
    private final List<Item> equippedConsumables;
    private final List<Item> equippedWeapons;

    public Inventory() {
        items = new ArrayList<>();
        equippedConsumables = new ArrayList<>();
        equippedWeapons = new ArrayList<>();
    }

    public Inventory(Item... items) {
        this();
        this.items.addAll(Arrays.asList(items));

    }

    public void addItem(Item item) {
        if (item instanceof Consumable consumableItem){
            Item consumable = getItemByName(consumableItem.getName());
            if (consumable != null){
                ((Consumable)consumable).addQuantity(consumableItem.getQuantity());
            }else{

                items.add(item);
                if (equippedConsumables.size() < 4)
                    equippedConsumables.add(consumableItem);
            }
        }else{
            items.add(item);
            //if
            if (item instanceof WeaponItem weaponItem && equippedWeapons.size() < 4)
                equippedWeapons.add(weaponItem);

        }
    }


    public void removeItem(Item item) {
        if (item instanceof Consumable consumableItem){
            Consumable c = (Consumable) getItemByName(consumableItem.getName());
            if (c != null){
                c.decreaseQuantity();
                if (c.getQuantity() <= 0){
                    items.remove(item);
                    equippedConsumables.remove(c);
                }
            }
        }else{
            items.remove(item);
            if (item instanceof WeaponItem)
                equippedWeapons.remove(item);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    /**
     * Return an item by its name
     * @param name name of the item
     * @return null if it does not exist
     */
    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


    /**
     * Return a list of item by its class
     * @param itemType
     * @return
     */
    public List<Item> getItemsByClass(ItemType itemType) {
        List<Item> items = new ArrayList<>();
        for (Item item : this.items) {
            if (itemType.getItemClass().equals(item.getClass()) || item.getClass().getSuperclass().equals(itemType.getItemClass())) {
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }

    public List<Item> getEquippedConsumables() {
        return equippedConsumables;
    }

    public List<Item> getEquippedWeapons() {
        return equippedWeapons;
    }
}
