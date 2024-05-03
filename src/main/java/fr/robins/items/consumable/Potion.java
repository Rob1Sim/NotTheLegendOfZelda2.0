package fr.robins.items.consumable;


import fr.robins.entities.Entity;
import fr.robins.types.entities.CharacType;
import fr.robins.types.items.PotionType;

public class Potion extends Consumable {

    private final CharacType type;
    private final int modificator;

    public Potion(PotionType potionType) {
        super(potionType.getName(), potionType.isUseHasWeapon());
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
