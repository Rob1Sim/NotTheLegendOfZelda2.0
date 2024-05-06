package fr.robins.engine.gamelogic.combat;


import fr.robins.engine.gamelogic.gamescene.combatScene.CombatScene;
import fr.robins.entities.Fighter;
import fr.robins.entities.Player;
import fr.robins.entities.enemy.Enemy;
import fr.robins.items.Item;
import fr.robins.items.combat.IAttack;
import fr.robins.items.combat.spells.Spell;
import fr.robins.items.combat.weapons.WeaponItem;
import fr.robins.items.consumable.Consumable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.Random;

public class CombatManager implements EventHandler<ActionEvent> {

    private final Player player;
    private final Enemy enemy;
    private CombatProperty combatProperty;
    public CombatManager(CombatProperty combatProperty){

        this.player = combatProperty.getPlayer();
        this.enemy = combatProperty.getEnemy();
        this.combatProperty = combatProperty;
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

    private void useConsumables(Consumable consumable, ActionEvent event){
        if (consumable.isUseHasWeapon())
            consumable.use(enemy);
        else consumable.use(player);
        player.getInventory().removeItem(consumable);
        System.out.println(player.getInventory().getEquippedConsumables());
        Pane root = (Pane) ((Button)event.getSource()).getScene().getRoot();
        CombatScene.setEquippedButton(player.getInventory().getEquippedConsumables(),"object",root);
    }

    @Override
    public void handle(ActionEvent event) {
        if (combatProperty.getTurnNumber() == 0 && combatProperty.getStarter() != 0){
            enemyTurn();
        }
        String id =  ((Button)event.getSource()).getId();

        int actionIndex = Integer.parseInt(String.valueOf(id.charAt(id.length() - 1)));
        actionIndex--;
        IAttack attack = null;
        if (id.contains("attack")){
            attack  = (WeaponItem) player.getInventory().getEquippedWeapons().get(actionIndex);
        } else if (id.contains("spell")) {
            attack = player.getSpells()[actionIndex];
        }else {
            Consumable item = (Consumable) player.getInventory().getEquippedConsumables().get(actionIndex);
            useConsumables(item,event);
        }
        if (attack != null){
            attack(attack,player,enemy);
        }

        //Display the modifications
        Platform.runLater(()->{
            if (!Objects.equals(enemy.getTextToDisplay(), ""))
                combatProperty.setActionText(enemy.getTextToDisplay());
            else combatProperty.setActionText(player.getTextToDisplay());
            //refresh the bars
            refreshLoadingBars(event);

            //reset the menu
            resetMenu(event,id);

        });


        //Disables les boutons
        //Bloquer le thread le temps que les joueurs puissent lire
        //si l'enemie ou le joueur est mort quitter le combat

    }

    private void resetMenu(ActionEvent event, String id){
        for (int i = 1; i < 5; i ++){
            String idToRemove = id.substring(0,id.length()-1);
            (((Button)event.getSource()).getScene().lookup("#"+idToRemove+i)).setVisible(false);
        }
        (((Button)event.getSource()).getScene().lookup("#attackBtn")).setVisible(true);
        (((Button)event.getSource()).getScene().lookup("#spellBtn")).setVisible(true);
        (((Button)event.getSource()).getScene().lookup("#objectBtn")).setVisible(true);
        (((Button)event.getSource()).getScene().lookup("#runBtn")).setVisible(true);
    }

    private void refreshLoadingBars(ActionEvent event){
        ProgressBar playerHpBar = (ProgressBar) ((Button)event.getSource()).getScene().lookup("#playerHp");
        ProgressBar playerMpBar = (ProgressBar) ((Button)event.getSource()).getScene().lookup("#playerMp");
        ProgressBar enemyHpBar = (ProgressBar) ((Button)event.getSource()).getScene().lookup("#enemyHp");
        ProgressBar enemyMpBar = (ProgressBar) ((Button)event.getSource()).getScene().lookup("#enemyMp");

        playerHpBar.setProgress((double) player.getHp() /player.getMaxHp());
        playerMpBar.setProgress((double) player.getMana() /player.getMaxMana());
        enemyHpBar.setProgress((double) enemy.getHp() /enemy.getMaxHp());
        enemyMpBar.setProgress((double) enemy.getMana() /enemy.getMaxMana());

        System.out.println("Mana : "+playerMpBar.getProgress());
        System.out.println("Real Mana : "+player.getMana() +" Max mana : "+player.getMaxMana()+" division:"+ player.getMana()/player.getMaxMana());

    }
}

/**
 *             Spell attackSpell = (Spell)attack;
 *
 *             //text to display
 *             switch (attackSpell.getEntityTypeToModify()){
 *                 case ENEMY :
 *                     combatProperty.setActionText(player.getName()+" inflige "+attack.getDamage()+" à sa/ses "+attackSpell.getCharacToModify()+" !");
 *                     break;
 *                 case PLAYER:
 *                     combatProperty.setActionText(player.getName()+" récupère "+attack.getDamage()+" à sa/ses "+attackSpell.getCharacToModify()+" !");
 *             }
 */