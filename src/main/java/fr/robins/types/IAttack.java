package fr.robins.types;


import fr.robins.entities.Fighter;

public interface IAttack {
    /**
     * Used when it is needed to attack
     * @param target Targer
     */
    void attack(Fighter fighter, Fighter target);

}
