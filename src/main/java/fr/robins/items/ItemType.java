package fr.robins.items;


import fr.robins.items.combat.weapons.WeaponItem;
import fr.robins.items.consumable.Consumable;
import fr.robins.items.consumable.potions.Potion;
import fr.robins.items.posable.Posable;

public enum ItemType {
    WEAPON(WeaponItem.class),
    CONSUMABLE(Consumable.class),
    POTION(Potion.class),
    POSABLE(Posable.class);

    final Class<? extends Item> itemClass;
    ItemType(Class<? extends Item> itemClass) {
        this.itemClass = itemClass;
    }

    public Class<? extends Item> getItemClass() {
        return itemClass;
    }
}
