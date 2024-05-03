package fr.robins.types.items;


import fr.robins.items.Item;
import fr.robins.items.combat.WeaponItem;
import fr.robins.items.consumable.Consumable;
import fr.robins.items.consumable.Potion;

public enum ItemType {
    WEAPON(WeaponItem.class),
    CONSUMABLE(Consumable.class),
    POTION(Potion.class);

    final Class<? extends Item> itemClass;
    ItemType(Class<? extends Item> itemClass) {
        this.itemClass = itemClass;
    }

    public Class<? extends Item> getItemClass() {
        return itemClass;
    }
}
