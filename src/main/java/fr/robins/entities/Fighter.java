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
     * @param modificator
     * @param characType
     * @param isDodgeable
     */
    public void modifyStatistics(int modificator, CharacType characType, boolean isDodgeable){
        if(isDodging(isDodgeable)){
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
    public void takeDamage(int damage, boolean isDodgeable, AttackType attackType) {
        if (isDodging(isDodgeable)) {
            int realDamage = damage - getConstitution();
            if (realDamage> 0){
                setHp(getHp() - realDamage);
                textToDisplay = getName() + " prend "+realDamage+" dégats !";
            }else{
                textToDisplay = getName() + " prend encaisse les coups !";
            }
        }else {
            textToDisplay = getName() + " à esquivé une attaque !";
        }
    }

    private boolean isDodging(boolean isDodgeable){
        int probablity = new Random().nextInt(100);
        return (probablity >= getDexterity() && isDodgeable);
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
