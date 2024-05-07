package fr.robins.engine.gamelogic.gamescene.combatScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class CombatSceneController {

    public Button leaveBtn;
    public Label enemyName;
    public ProgressBar enemyHp;
    public ProgressBar enemyMp;
    public ProgressBar playerHp;
    public ProgressBar playerMp;
    public ImageView enemyImage;
    public ImageView playerImage;
    @FXML
    private Button attackBtn;

    @FXML
    private Button spellBtn;

    @FXML
    private Button objectBtn;

    @FXML
    private Button runBtn;

    @FXML
    private Button returnBtn;


    //region Attack button
    @FXML
    private Button attack1;

    @FXML
    private Button attack2;

    @FXML
    private Button attack3;

    @FXML
    private Button attack4;
    //endregion

    //region Spell buttons
    @FXML
    private Button spell1;

    @FXML
    private Button spell2;

    @FXML
    private Button spell3;

    @FXML
    private Button spell4;
    //endregion

    //region Object Button
    @FXML
    private Button object1;

    @FXML
    private Button object2;

    @FXML
    private Button object3;

    @FXML
    private Button object4;
    //endregion

    @FXML
    public void showAttackMenu(ActionEvent event) {
        changeMenuBtnVisibility(false);
        changeAttackButtonVisibility(true);
        changeReturnButtonState();
    }
    public void showSpellMenu(ActionEvent event) {
        changeMenuBtnVisibility(false);
        changeSpellButtonVisibility(true);
        changeReturnButtonState();
    }
    public void showObjectMenu(ActionEvent event) {
        changeMenuBtnVisibility(false);
        changeObjectButtonVisibility(true);
        changeReturnButtonState();
    }

    public void returnButton(ActionEvent event) {
        if (attack1.isVisible()){
            changeAttackButtonVisibility(false);
        } else if (spell1.isVisible()) {
            changeSpellButtonVisibility(false);
        }else {
            changeObjectButtonVisibility(false);
        }
        changeMenuBtnVisibility(true);
        changeReturnButtonState();
    }

    /**
     * Set the visibility of the 4 mains buttons
     * @param visible true -> visible, else false
     */
    private void changeMenuBtnVisibility(boolean visible) {
        attackBtn.setVisible(visible);
        spellBtn.setVisible(visible);
        objectBtn.setVisible(visible);
        runBtn.setVisible(visible);
    }

    private void changeAttackButtonVisibility(boolean visible) {
        attack1.setVisible(visible);
        attack2.setVisible(visible);
        attack3.setVisible(visible);
        attack4.setVisible(visible);
    }

    private void changeSpellButtonVisibility(boolean visible) {
        spell1.setVisible(visible);
        spell2.setVisible(visible);
        spell3.setVisible(visible);
        spell4.setVisible(visible);
    }

    private void changeObjectButtonVisibility(boolean visible) {
        object1.setVisible(visible);
        object2.setVisible(visible);
        object3.setVisible(visible);
        object4.setVisible(visible);
    }

    /**
     * Change the return button disability (if its make sense)
     */
    private void changeReturnButtonState(){
        returnBtn.setDisable(false);
    }
}
