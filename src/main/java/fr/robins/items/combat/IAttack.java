package fr.robins.items.combat;


import fr.robins.entities.Fighter;

public interface IAttack {
    /**
     * Used when it is needed to attack
     * @param target Targer
     */
    void attack(Fighter fighter, Fighter target);

}
