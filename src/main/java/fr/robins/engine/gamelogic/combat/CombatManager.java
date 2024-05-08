package fr.robins.engine.gamelogic.combat;


import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.gamescene.combatScene.CombatScene;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.entities.Entity;
import fr.robins.entities.Fighter;
import fr.robins.entities.Player;
import fr.robins.entities.enemy.Enemy;
import fr.robins.items.ItemType;
import fr.robins.items.combat.IAttack;
import fr.robins.items.combat.weapons.WeaponItem;
import fr.robins.items.consumable.Consumable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Represent one turn of a combat, called when the player select an action
 */
public class CombatManager implements EventHandler<ActionEvent> {

    private final Player player;
    private final Enemy enemy;
    /**
     * Contains all data needed to assure a combat (cant pass it in other way because it is called by a button)
     */
    private final CombatProperty combatProperty;

    public CombatManager(CombatProperty combatProperty){

        this.player = combatProperty.getPlayer();
        this.enemy = combatProperty.getEnemy();
        this.combatProperty = combatProperty;
    }

    @Override
    public void handle(ActionEvent event) {
        String id =  ((Button)event.getSource()).getId();

        if (combatProperty.getStarter() != 0){
            enemyTurn(event, id);
        }
        if (!isCombatOver()){

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
            refreshUI(event, id,true);

            if (isCombatOver()){
                endCombat(event);
            }else{
                if (combatProperty.getStarter() == 0){
                    disableMenu(event,true);
                    enemyTurn(event, id);
                    disableMenu(event,false);

                }
                combatProperty.setTurnNumber(combatProperty.getTurnNumber()+1);
            }
        }else
            endCombat(event);
    }

    private void enemyTurn(ActionEvent event, String id){
        int whatDoIDo = new Random().nextInt(3);
        int spellNumber = -1;
        if(whatDoIDo == 0 && enemy.getSpells().length>0 ){
            spellNumber = new Random().nextInt(enemy.getSpells().length);
            if ((enemy.getMana() - enemy.getSpells()[spellNumber].getManaCost())<=0){
                whatDoIDo = 1;
            }
        } else if (whatDoIDo == 2 && enemy.getInventory().getItemsByClass(ItemType.CONSUMABLE).isEmpty()) {
            whatDoIDo = 1;
        }else if(enemy.getSpells().length == 0 ) {
            whatDoIDo = 1;
        }

        switch (whatDoIDo){
            case 0:
                enemy.getSpells()[spellNumber].attack(enemy,player);
                break;
            case 1:
                int whichAttack = new Random().nextInt(enemy.getInventory().getItemsByClass(ItemType.WEAPON).size());
                WeaponItem weapon = (WeaponItem) enemy.getInventory().getItemsByClass(ItemType.WEAPON).get(whichAttack);
                weapon.attack(enemy,player);

                break;
            case 2:
                int whichPotion = new Random().nextInt(enemy.getInventory().getItemsByClass(ItemType.CONSUMABLE).size());
                Consumable consumable = (Consumable) enemy.getInventory().getItemsByClass(ItemType.CONSUMABLE).get(whichPotion);
                consumable.use(enemy);

                break;
        }
        refreshUI(event,id,false);
    }

    private boolean isCombatOver(){
        return player.getHp()<= 0 || enemy.getHp() <= 0;
    }

    /**
     * Instruction to do before leaving combat
     */
    private void endCombat(ActionEvent event){
        if (enemy.getHp()<=0){
            Platform.runLater(()-> combatProperty.setActionText(enemy.getDeathPhrase()));
            Button leaveButton = (Button) ((Button)event.getSource()).getScene().lookup("#leaveBtn");
            leaveButton.setDisable(false);
            leaveButton.setText("Quitter");
            leaveButton.setOnAction(new LeaveCombat(combatProperty,enemy));
        }else{
            combatProperty.getGameState().setGameState(GameState.DEAD);
        }
    }


    private void attack(IAttack attackItem, Fighter caster, Fighter target){
        attackItem.attack(caster,target);
    }

    private void useConsumables(Consumable consumable, ActionEvent event){
        if (consumable.isUseHasWeapon())
            consumable.use(enemy);
        else consumable.use(player);
        player.getInventory().removeItem(consumable);
        Pane root = (Pane) ((Button)event.getSource()).getScene().getRoot();
        CombatScene.setEquippedButton(player.getInventory().getEquippedConsumables(),"object",root);
    }

    /**
     * Refresh variables display on the screen
     */
    private void refreshUI(ActionEvent event, String id, boolean playerTurn) {
        Platform.runLater(()->{

            if (playerTurn){
                combatProperty.setActionText(combatProperty.getActionText().getText()+"\n>"+enemy.getTextToDisplay());
            }else {
                combatProperty.setActionText(combatProperty.getActionText().getText()+"\n>"+player.getTextToDisplay());
            }
            //refresh the bars
            refreshLoadingBars(event);

            //reset the menu
            resetMenu(event, id);

        });
    }

    /**
     * Set the menu to the 4th main buttons (Attack, Spell, Objects, Run)
     */
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

    private void disableMenu(ActionEvent event, boolean isDisable){
        (((Button)event.getSource()).getScene().lookup("#attackBtn")).setDisable(isDisable);
        (((Button)event.getSource()).getScene().lookup("#spellBtn")).setDisable(isDisable);
        (((Button)event.getSource()).getScene().lookup("#objectBtn")).setDisable(isDisable);
        (((Button)event.getSource()).getScene().lookup("#runBtn")).setDisable(isDisable);
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


    }

    /**
     * Class Event called whenever the combat has ended, it can access player position
     */
    private static class LeaveCombat implements EventHandler<ActionEvent>{

        CombatProperty combatProperty;
        Enemy enemy;

        LeaveCombat(CombatProperty combatProperty, Enemy enemy){
            this.combatProperty = combatProperty;
            this.enemy = enemy;
        }

        @Override
        public void handle(ActionEvent event) {
            combatProperty.getSceneController().getDisplayableObserver().remove(enemy);
            combatProperty.getSceneController().switchToGameSceneAfterCombat(event);
        }
    }
}

