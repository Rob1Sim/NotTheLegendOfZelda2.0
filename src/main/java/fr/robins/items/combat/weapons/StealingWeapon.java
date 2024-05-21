package fr.robins.items.combat.weapons;

import fr.robins.entities.Fighter;
import fr.robins.types.Vector2D;

import java.util.Random;

public class StealingWeapon extends WeaponItem {
    public StealingWeapon(WeaponType weaponType, Vector2D position) {
        super(weaponType, position);
    }

    public StealingWeapon(WeaponType weaponType) {
        super(weaponType);
    }

    @Override
    public void attack(Fighter fighter, Fighter target){
        if (!target.getInventory().getItems().isEmpty()){
            int randomInt = new Random().nextInt(target.getInventory().getItems().size());
            target.getInventory().removeItem(target.getInventory().getItems().get(randomInt));
        }else {
            super.attack(fighter,target);
        }

    }
}
