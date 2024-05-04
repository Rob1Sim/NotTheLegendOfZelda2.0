package fr.robins.items.combat.weapons;


import fr.robins.entities.Fighter;
import fr.robins.items.Item;
import fr.robins.items.combat.IAttack;

public class WeaponItem extends Item implements IAttack {

    private final int damage;
    public WeaponItem(WeaponType weaponType) {
        super(weaponType.getName());
        this.damage = weaponType.getDamage();
    }

    @Override
    public void attack(Fighter fighter, Fighter target) {
        target.takeDamage(damage * (1+(fighter.getStrength()/100)), true);
    }

    public int getDamage() {
        return damage;
    }

    /**
    public static String listWeaponsNames(List<Item> weaponItems){
        StringBuilder names = new StringBuilder();
        for (int i = 0; i<weaponItems.size(); i++){
            WeaponItem weaponItem = (WeaponItem) weaponItems.get(i);
            names.append(i).append(": ").append(weaponItem.getName()).append(": ").append(weaponItem.getDamage()).append("dmg\n");
        }
        return names.toString();
    }**/
}
