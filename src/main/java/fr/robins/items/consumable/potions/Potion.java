package fr.robins.items.consumable.potions;


import fr.robins.engine.collisions.HitBox;
import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Entity;
import fr.robins.items.consumable.Consumable;
import fr.robins.entities.entitiestype.CharacType;
import fr.robins.types.Vector2D;
import javafx.scene.Node;

public class Potion extends Consumable implements Displayable {

    private final CharacType type;
    private final int modificator;

    public Potion(PotionType potionType, Vector2D position) {
        super(potionType.getName(), potionType.getSpritePath() ,position,potionType.isUseHasWeapon());
        modificator = potionType.getModificator();
        this.type = potionType.getCharacType();
    }

    /**
     * use a potion, if the taret is an enemy, then use negative values
     * @param target
     */
    @Override
    public void use(Entity target) {
        decreaseQuantity();
        switch (type) {
            case HP -> target.setHp(target.getHp() + modificator);
            case MANA -> target.setMana(target.getMana() + modificator);
            case DEXTERITY -> target.setDexterity(target.getDexterity() + modificator);
            case CONSTITUTION -> target.setConstitution(target.getConstitution() + modificator);
            case STRENGTH -> target.setStrength(target.getStrength() + modificator);
        }
    }

}
