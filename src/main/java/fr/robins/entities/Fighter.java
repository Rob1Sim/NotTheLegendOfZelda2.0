package fr.robins.entities;


import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.entitiestype.CharacType;
import fr.robins.items.combat.AttackType;
import fr.robins.items.combat.spells.Spell;
import fr.robins.types.Vector2D;

import java.util.Random;

public abstract class Fighter extends Entity implements Displayable {
    private final Spell[] spells;
    private String textToDisplay;

    public Fighter(String name, int hp, int mana, int constitution, int strength, int dexterity, int money, Vector2D worldPosition, String spritePath, Spell[] spells) {
        super(name, hp, mana, constitution, strength,dexterity,money,worldPosition,spritePath);
        this.spells = spells;
        textToDisplay ="";
    }

    /**
     * Modify player statistics
     * @param modificator how many modificaiton
     * @param characType the charac to modify
     * @param isDodgeable is it can be dodge
     */
    public void modifyStatistics(int modificator, CharacType characType, boolean isDodgeable, boolean alwaysDodgeable){
        if(isDodging(isDodgeable,alwaysDodgeable)){
            modificator = 0;
            textToDisplay = getName() + " à esquivé !";
        }else {
            textToDisplay = getName() + " reçois "+modificator+" de "+characType.getName()+" !";
        }

        switch (characType){
            case HP -> this.setHp(this.getHp() + modificator);
            case CONSTITUTION -> this.setConstitution(this.getHp() + modificator);
            case DEXTERITY -> this.setDexterity(this.getDexterity() + modificator);
            case STRENGTH -> this.setStrength(this.getStrength() + modificator);
        }
    }

    /**
     * Call when something harm the fighter
     * @param isDodgeable False -> the fighter will always dodge
     * @param attackType Which kind of attaks it is (Magical, Bodily
     */
    public void takeDamage(int damage, boolean isDodgeable,boolean alwaysDodgeable ,AttackType attackType) {
        if (!isDodging(isDodgeable,alwaysDodgeable)) {
            int realDamage = damage - getConstitution();
            if (realDamage> 0){
                setDamageEqualHp(getHp() - realDamage);
            }else{
                textToDisplay = getName() + " prend encaisse les coups !";
            }
        }else {
            textToDisplay = getName() + " à esquivé une attaque !";
        }
    }

    /**
     * Set the hp of the target to the damage value
     * @param damage
     */
    public void setDamageEqualHp(int damage) {
        setHp(damage);
        textToDisplay = getName() + " prend "+damage+" dégats !";
    }

    /**
     * Calculate if the dodge the attack
     * @param isDodgeable  If is isDodgeable is False, the attack can not be dodge
     * @return False -> if it doe not dodge
     */
    private boolean isDodging(boolean isDodgeable, boolean alwaysDodgeable){
        int probablity = new Random().nextInt(100);
        return alwaysDodgeable || isDodgeable && (probablity <= getDexterity());
    }

    public Spell[] getSpells() {
        return spells;
    }

    public String getTextToDisplay() {
        return textToDisplay;
    }

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }
}
