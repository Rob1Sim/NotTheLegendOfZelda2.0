package fr.robins.items.consumable.potions;


import fr.robins.engine.collisions.HitBox;
import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Entity;
import fr.robins.entities.Fighter;
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
        Fighter fighter = (Fighter) target;
        String sType= "";
        switch (type) {
            case HP -> {
                fighter.setHp(fighter.getHp() + modificator);
                sType = "HP";
            }
            case MANA -> {
                fighter.setMana(fighter.getMana() + modificator);
                sType = "de Mana";
            }
            case DEXTERITY -> {
                fighter.setDexterity(fighter.getDexterity() + modificator);
                sType = "de dextérité";
            }
            case CONSTITUTION -> {
                fighter.setConstitution(fighter.getConstitution() + modificator);
                sType = "de constitution";
            }
            case STRENGTH -> {
                fighter.setStrength(fighter.getStrength() + modificator);
                sType = "de force";
            }
        }
        fighter.setTextToDisplay(target.getName()+" reçois "+modificator +sType+" !");
        System.out.println("test");
    }

}
