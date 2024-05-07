package fr.robins.engine.gamelogic.combat;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.engine.gamelogic.gamestate.GameStateSubject;
import fr.robins.entities.Player;
import fr.robins.entities.enemy.Enemy;
import javafx.scene.control.Label;


import java.util.Random;

/**
 * Store all data that the combat manager need to access
 */
public class CombatProperty {

    private Player player;
    private Enemy enemy;
    private int turnNumber;
    private final int starter;
    private final Label actionText;
    private final GameStateSubject gameState;

    private final SceneController sceneController;

    public CombatProperty(Player player, Enemy enemy, Label actionText,SceneController sceneController, GameStateSubject gameState){
        this.player = player;
        this.enemy = enemy;
        this.turnNumber = 0;
        this.starter = new Random().nextInt(2);
        this.actionText = actionText;
        this.sceneController = sceneController;
        this.gameState = gameState;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    /**
     * Starter number
     * @return 0 -> Player, 1 -> Enemy
     */
    public int getStarter() {
        return starter;
    }
    /**
     * Set the text displayed next to the buttons
     */
    public Label getActionText() {
        return actionText;
    }

    /**
     * Set the text displayed next to the buttons
     * @param actionText new text
     */
    public void setActionText(String actionText) {
        this.actionText.setText(actionText);
    }

    public SceneController getSceneController() {
        return sceneController;
    }

    public GameStateSubject getGameState() {
        return gameState;
    }
}
