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

        if (entityTypeToModify == EntityType.ENEMY){
            if (characToModify == CharacType.HP){
                if (target instanceof Enemy enemy)
                    enemy.takeDamage(modificator,true, AttackType.MAGICAL);
                else
                    target.takeDamage(modificator,true, AttackType.MAGICAL);

            }else{
                fighter.modifyStatistics(-modificator, characToModify, true);
            }
        }else{
            fighter.modifyStatistics(modificator, characToModify, false);
        }
    }

    @Override
    public int getDamage() {
        return modificator;
    }

    /**
    public static String spellsNamesFromList(Spell[] spells){
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < spells.length; i++){
            Spell spell = spells[i];
            names.append(i).append(": ").append(spell.getName()).append(": ").append(spell.getManaCost()).append(" mp, ");
            int modifcatorToString = spell.modificator;
            if (spell.entityTypeToModify == EntityType.PLAYER){
                names.append("ajoute ");
            }else {
                names.append("inflige ");
                modifcatorToString = -modifcatorToString;
            }
            names.append(modifcatorToString).append(" ").append(spell.characToModify).append("\n");

        }
        return names.toString();
    }**/

    private String getName() {
        return name;
    }

    public String getSpellName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getModificator() {
        return modificator;
    }

    public CharacType getCharacToModify() {
        return characToModify;
    }

    public EntityType getEntityTypeToModify() {
        return entityTypeToModify;
    }


}
