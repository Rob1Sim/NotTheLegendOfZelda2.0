package fr.robins.engine;


import fr.robins.entities.Fighter;
import fr.robins.entities.Player;
import fr.robins.entities.enemy.Enemy;
import fr.robins.types.IAttack;

import java.util.Random;

public class CombatManager {

    private final Player player;
    private final Enemy enemy;

    public CombatManager(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
    }

    private static boolean starter(){
        return new Random().nextBoolean();
    }

    public void createCombat(){
    }

    private void playerTurn(){

    }

    private void enemyTurn(){

    }

    private boolean isCombatOver(){
        return player.getHp()<= 0 || enemy.getHp() <= 0;
    }



    private void attack(IAttack attackItem, Fighter caster, Fighter target){
        attackItem.attack(caster,target);
    }



}
