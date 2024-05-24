package fr.robins.items.combat.weapons;

import fr.robins.entities.Fighter;
import fr.robins.entities.enemy.Enemy;
import fr.robins.entities.enemy.EnemyType;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

import java.util.Objects;
import java.util.Random;

public class WeaponX extends WeaponItem {
    public WeaponX(WeaponType weaponType, Vector2D position) {
        super(weaponType, position);
    }

    public WeaponX(WeaponType weaponType) {
        super(weaponType);
    }

    @Override
    public void attack(Fighter fighter, Fighter target){
        if(target instanceof Enemy e){
            if (Objects.equals(e.getName(), EnemyType.WEAPON_X.getName())){
                target.setDamageEqualHp(0);
            }else {
                super.attack(fighter,target);
            }
        }

    }

    public static WeaponX weaponGenerator(WeaponType weaponType, int column, int row) {
        return new WeaponX(weaponType, TileManager.tilesToCoordinates(column,row));
    }
}
