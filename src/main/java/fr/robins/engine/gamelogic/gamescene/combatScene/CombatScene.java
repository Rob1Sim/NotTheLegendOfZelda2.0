package fr.robins.engine.gamelogic.gamescene.combatScene;


import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.combat.CombatManager;
import fr.robins.engine.gamelogic.combat.CombatProperty;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.engine.gamelogic.gamestate.GameStateSubject;
import fr.robins.entities.Fighter;
import fr.robins.entities.Player;
import fr.robins.entities.enemy.Enemy;
import fr.robins.items.Item;
import fr.robins.types.Utilities;
import fr.robins.types.exceptions.CombatLoadingException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * Initialise a combat scene, the panel can be accessed by the method getPanel()
 */
public class CombatScene extends GameScene {

    CombatProperty combatProperty;

    public CombatScene(Fighter player, Fighter enemy, SceneController sceneController, GameStateSubject gameState) {
        super();


        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/combatScene.fxml");
            Pane root = loader.load(fxmlStream);

            //Set Sprites
            ImageView playerImage = (ImageView) root.lookup("#playerImage");
            ImageView enemyView = (ImageView) root.lookup("#enemyImage");
            if (playerImage != null && enemyView != null){
                Image playerSprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(player.getSpritePath())));
                Image enemySprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(enemy.getSpritePath())));

                enemyView.setImage(enemySprite);
                playerImage.setImage(playerSprite);
            }

            //Set progressBar value
            ProgressBar playerHpBar = (ProgressBar) root.lookup("#playerHp");
            ProgressBar playerMpBar = (ProgressBar) root.lookup("#playerMp");
            ProgressBar enemyHpBar = (ProgressBar) root.lookup("#enemyHp");
            ProgressBar enemyMpBar = (ProgressBar) root.lookup("#enemyMp");

            if (playerHpBar != null && playerMpBar != null){
                playerHpBar.setProgress((double) player.getHp() /player.getMaxHp());
                playerMpBar.setProgress((double) player.getMana() /player.getMaxMana());
            }else {
                throw new CombatLoadingException("Error while loading player loading bars !");
            }

            if (enemyHpBar != null && enemyMpBar != null){
                enemyHpBar.setProgress((double) enemy.getHp() /enemy.getMaxHp());
                enemyMpBar.setProgress((double) enemy.getMana() /enemy.getMaxMana());
            }else{
                throw new CombatLoadingException("Error while loading enemy loading bars !");
            }

            //Set Action text
            Label actionText = (Label) root.lookup("#actionTxt");
            if (actionText != null){
                actionText.setWrapText(true);
                actionText.setText("Oh non un "+enemy.getName()+" apparait !");
            }else{
                throw new CombatLoadingException("Error while loading action text !");
            }
            Label enemyName = (Label) root.lookup("#enemyName");
            if (enemyName != null){
                enemyName.setText(enemy.getName());
            }else{
                throw new CombatLoadingException("Error while loading enemy name Text !");
            }



            //Set default combat system values
            combatProperty = new CombatProperty((Player) player, (Enemy) enemy, actionText,sceneController, gameState);
            //Set Attack/Object/Spell button
            setEquippedButton(player.getInventory().getEquippedWeapons(), "attack",root);
            setEquippedButton(player.getInventory().getEquippedConsumables(), "object",root);
            for (int i = 0; i < player.getSpells().length; i++){
                Button spellButton = (Button) root.lookup("#spell" + (i+1));
                if (spellButton != null){
                    spellButton.setText(player.getSpells()[i].getSpellName());
                }
            }
            disabledEmptyButtonAndSetAction(root);

            root.setPrefSize(Utilities.WINDOW_WIDTH,Utilities.WINDOW_HEIGHT);
            super.pane = root;

        } catch (IOException e) {
            throw new RuntimeException("Error have occured while loading the scene :  "+e);
        } catch (CombatLoadingException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Change name of the buttons for the quipement pass
     * @param equippedItem list of equiped that should be visible on the menu
     * @param btnId the id of the button (attack, spell, object)
     * @param root the pane
     */
    public static void setEquippedButton(List<Item>  equippedItem, String btnId , Pane root){
        for (int i = 0; i < 4; i ++){
            Button iItem = (Button) root.lookup("#"+btnId+(i+1));
            if (iItem != null){

                if (i >= equippedItem.size()){
                    iItem.setText("");
                    iItem.setDisable(true);
                }else{
                    iItem.setText(equippedItem.get(i).getName());
                }
            }

        }
    }

    private void disabledEmptyButtonAndSetAction(Pane root){
        String[] ids = new String[]{"#attack","#object","#spell"};
        for (String id : ids) {
            for (int y = 0; y < 4; y++) {
                Button idButton = (Button) root.lookup(id + (y + 1));
                if (idButton != null) {
                    if (Objects.equals(idButton.getText(), ""))
                        idButton.setDisable(true);
                    else
                        idButton.setOnAction(new CombatManager(combatProperty));
                }
            }
        }
    }
}
