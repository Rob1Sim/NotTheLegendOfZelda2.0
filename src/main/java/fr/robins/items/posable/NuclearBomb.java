package fr.robins.items.posable;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.displayable.DisplayableSubject;
import fr.robins.engine.gamelogic.gamestate.GameState;
import fr.robins.entities.Player;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class NuclearBomb extends Posable{
    private final SceneController sceneController;
    public NuclearBomb(Vector2D position, SceneController sceneController) {
        super("Bombe nucléaire", "/sprites/items/bomb.png", position, 100);
        this.sceneController = sceneController;
    }

    @Override
    public void use(Player player, DisplayableSubject displayablesEntity, Pane pane) {
        sceneController.getGameController().getGameStateSubject().setGameState(GameState.WIN);
    }

    public static NuclearBomb nuclearBombGenerator(int column, int row, SceneController sceneController) {
        return new NuclearBomb(TileManager.tilesToCoordinates(column,row),sceneController);
    }
}
