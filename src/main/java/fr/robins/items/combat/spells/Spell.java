package fr.robins.items.combat.spells;


import fr.robins.entities.Fighter;
import fr.robins.entities.enemy.Enemy;
import fr.robins.items.combat.AttackType;
import fr.robins.items.combat.IAttack;
import fr.robins.entities.entitiestype.CharacType;
import fr.robins.entities.entitiestype.EntityType;

public class Spell implements IAttack {
    private final String name;
    private final int manaCost;
    private final int modificator;
    private final CharacType characToModify;
    private final EntityType entityTypeToModify;

    public Spell(SpellType spellType) {
        this.name = spellType.getName();
        this.manaCost = spellType.getManaCost();
        this.modificator = spellType.getModifier();
        this.characToModify = spellType.getCaracType();
        this.entityTypeToModify = spellType.getEntityType();
    }




    @Override
    public void attack(Fighter fighter , Fighter target) {
        if (fighter.getMana() - manaCost < 0){
            fighter.setTextToDisplay("Tu n'a pas assez de mana !");
            return;
        }

        fighter.setMana(fighter.getMana() - manaCost);
        fighter.setTextToDisplay(fighter.getName()+" lance "+this.getSpellName()+" !");
        if (entityTypeToModify == EntityType.ENEMY){
            if (characToModify == CharacType.HP){
                if (target instanceof Enemy enemy)
                    enemy.takeDamage(modificator,true, false,AttackType.MAGICAL);
                else
                    target.takeDamage(modificator,true, false,AttackType.MAGICAL);

            }else{
                fighter.modifyStatistics(-modificator, characToModify, true, false);
            }
        }else{
            fighter.modifyStatistics(modificator, characToModify, false, false);
        }
    }

    @Override
    public int getDamage() {
        return modificator;
    }

    public String getSpellName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }



}
