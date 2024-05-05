package fr.robins.items;

import fr.robins.items.combat.weapons.WeaponItem;
import fr.robins.items.consumable.Consumable;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items;
    private final List<Consumable> equipedConsumables;
    private final List<WeaponItem> equipedWeapons;

    public Inventory() {
        items = new ArrayList<>();
        equipedConsumables = new ArrayList<>();
        equipedWeapons = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (item instanceof Consumable consumableItem){
            Item consumable = getItemByName(consumableItem.getName());
            if (consumable != null){

                ((Consumable)consumable).addQuantity(consumableItem.getQuantity());

            }else{
                items.add(item);
                if (equipedConsumables.size() < 4)
                    equipedConsumables.add(consumableItem);
            }
        }else{
            items.add(item);
            //if
            if (item instanceof WeaponItem weaponItem && equipedWeapons.size() < 4)
                equipedWeapons.add(weaponItem);

        }
    }


    public void removeItem(Item item) {
        if (item instanceof Consumable consumableItem){
            Consumable c = (Consumable) getItemByName(consumableItem.getName());
            if (c != null){
                c.decreaseQuantity();
                if (c.getQuantity() <= 0){
                    items.remove(item);
                    equipedConsumables.remove(c);
                }
            }
        }else{
            items.remove(item);
            if (item instanceof WeaponItem)
                equipedWeapons.remove(item);
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

    public List<Consumable> getEquipedConsumables() {
        return equipedConsumables;
    }

    public List<WeaponItem> getEquipedWeapons() {
        return equipedWeapons;
    }
}
