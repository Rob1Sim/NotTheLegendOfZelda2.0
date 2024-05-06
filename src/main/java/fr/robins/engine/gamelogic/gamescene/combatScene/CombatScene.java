package fr.robins.engine.gamelogic.gamescene.combatScene;

import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.entities.Fighter;
import fr.robins.items.Item;
import fr.robins.items.combat.spells.Spell;
import fr.robins.types.Utilities;
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


public class CombatScene extends GameScene {

    private final Fighter player;
    private final Fighter enemy;

    public CombatScene( Fighter player, Fighter enemy) {
        super();
        this.player = player;
        this.enemy = enemy;

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
            }

            if (enemyHpBar != null && enemyMpBar != null){
                enemyHpBar.setProgress((double) enemy.getHp() /enemy.getMaxHp());
                enemyMpBar.setProgress((double) enemy.getMana() /enemy.getMaxMana());
            }

            //Set Action text
            Label actionText = (Label) root.lookup("#actionTxt");
            if (actionText != null){
                actionText.setWrapText(true);
                actionText.setText("Oh non un "+enemy.getName()+" apparait !");
            }

            //Set Attack/Object/Spell button
            setEquippedButton(player.getInventory().getEquippedWeapons(), "attack",root);
            setEquippedButton(player.getInventory().getEquippedConsumables(), "object",root);
            for (int i = 0; i < player.getSpells().length; i++){
                Button spellButton = (Button) root.lookup("#spell" + (i+1));
                if (spellButton != null){
                    spellButton.setText(player.getSpells()[i].getSpellName());
                }
            }
            disabledEmptyButton(root);

            root.setPrefSize(Utilities.WINDOW_WIDTH,Utilities.WINDOW_HEIGHT);
            super.pane = root;

        } catch (IOException e) {
            throw new RuntimeException("Error have occured while loading the scene :  "+e);
        }


    }

    private static void setEquippedButton(List<Item>  equippedItem, String btnId , Pane root){
        for (int i = 0; i < equippedItem.size(); i ++){
            Button iItem = (Button) root.lookup("#"+btnId+(i+1));
            if (iItem != null){
                iItem.setText(equippedItem.get(i).getName());
            }
        }
    }

    private static void disabledEmptyButton(Pane root){
        String[] ids = new String[]{"#attack","#object","spell"};
        for (int i = 0; i < ids.length; i++){
            for (int y = 0; y < 4; y++){
                Button idButton = (Button) root.lookup(ids[i]+(y+1));
                if (idButton != null && Objects.equals(idButton.getText(), "")){
                    idButton.setDisable(true);
                }
            }
        }
    }
}
