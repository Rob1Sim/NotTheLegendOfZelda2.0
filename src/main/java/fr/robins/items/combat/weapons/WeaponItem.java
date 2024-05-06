package fr.robins.items.combat.weapons;


import fr.robins.engine.collisions.HitBox;
import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Fighter;
import fr.robins.entities.enemy.Enemy;
import fr.robins.items.Collectable;
import fr.robins.items.Item;
import fr.robins.items.combat.AttackType;
import fr.robins.items.combat.IAttack;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class WeaponItem extends Item implements IAttack, Collectable, Displayable {

    private final int damage;
    public WeaponItem(WeaponType weaponType, Vector2D position) {
        super(weaponType.getName(), weaponType.getSpritePath(), position);
        this.damage = weaponType.getDamage();
    }

    public WeaponItem(WeaponType weaponType){
        this(weaponType, new Vector2D());
    }

    @Override
    public void attack(Fighter fighter, Fighter target) {
        if (target instanceof Enemy enemy)
            enemy.takeDamage(damage * (1+(fighter.getStrength()/100)), true, AttackType.BODILICAL);
        else
            target.takeDamage(damage * (1+(fighter.getStrength()/100)), true, AttackType.BODILICAL);
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return getName()+" : "+getDamage();
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
