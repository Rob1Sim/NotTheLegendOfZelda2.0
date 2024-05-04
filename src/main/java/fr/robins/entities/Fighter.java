package fr.robins.entities;


import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.entitiestype.CharacType;
import fr.robins.items.combat.spells.Spell;
import fr.robins.types.Vector2D;

import java.util.Random;

public abstract class Fighter extends Entity implements Displayable {
    private final Spell[] spells;

    public Fighter(String name, int hp, int mana, int constitution, int strength, int dexterity, int money, Vector2D worldPosition, String spritePath, Spell[] spells) {
        super(name, hp, mana, constitution, strength,dexterity,money,worldPosition,spritePath);
        this.spells = spells;
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
            System.out.println(getName() + " à esquivé !");
        }else {
            System.out.println(getName() + " reçois "+modificator+" de "+characType.getName()+" !");
        }

        switch (characType){
            case HP -> this.setHp(this.getHp() + modificator);
            case CONSTITUTION -> this.setConstitution(this.getHp() + modificator);
            case DEXTERITY -> this.setDexterity(this.getDexterity() + modificator);
            case STRENGTH -> this.setStrength(this.getStrength() + modificator);
        }
    }

    public void takeDamage(int damage, boolean isDodgeable) {

        if (isDodging(isDodgeable)) {
            int realDamage = damage - getConstitution();
            if (realDamage> 0){
                setHp(getHp() - realDamage);
                System.out.println(getName() + " prend "+realDamage+" dégats !");
            }else{
                System.out.println(getName() + " prend encaisse les coups !");
            }
        }else {
            System.out.println(getName() + " à esquivé une attaque !");
        }
    }

    private boolean isDodging(boolean isDodgeable){
        int probablity = new Random().nextInt(100);
        return (probablity >= getDexterity() && isDodgeable);
    }

    public Spell[] getSpells() {
        return spells;
    }
}
