package fr.robins.engine;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.gamescene.menuscenes.InventoryGameScene;
import fr.robins.entities.Player;
import fr.robins.items.posable.Posable;
import fr.robins.types.DirectionType;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Inputs {
    public static void handleMovementInput(Player player, Pane backgroundPane, Stage stage, SceneController sceneController){

        stage.getScene().setOnKeyPressed(event ->{
            KeyCode keycode = event.getCode();
            switch (keycode) {
                case Z:
                    player.getVelocity().setY(+player.getSpeed());
                    player.setDirection(DirectionType.UP);
                    break;
                case S:
                    player.getVelocity().setY(-player.getSpeed());

                    player.setDirection(DirectionType.DOWN);
                    break;
                case D:
                    player.getVelocity().setX(-player.getSpeed());
                    player.setDirection(DirectionType.RIGHT);
                    break;
                case Q:
                    player.getVelocity().setX(+player.getSpeed());
                    player.setDirection(DirectionType.LEFT);
                    break;
                case F11:
                    Utilities.setFullScreen(stage,!stage.isFullScreen());
                case N:
                    if (Utilities.DEBUG)
                        Utilities.NO_CLIPPING = !Utilities.NO_CLIPPING;
                    break;
                case F1:
                    Utilities.DEBUG = !Utilities.DEBUG;
                    break;
                case I:
                    sceneController.switchToMenuScene(new InventoryGameScene(player));
                    break;
                case F:
                    if (player.getInventory().getPosableEquippedItem() != null){
                        ((Posable)player.getInventory().getPosableEquippedItem()).use(player, sceneController.getDisplayableObserver(), backgroundPane);
                    }
                    break;
                case E:
                    if (player.canInteract() && player.getInteractable() != null){
                        player.getInteractable().interact(sceneController);
                    }
            }
        });

        stage.getScene().setOnKeyReleased(keyEvent -> {
            KeyCode keycode = keyEvent.getCode();
            if (keycode == KeyCode.Z || keycode == KeyCode.S) {
                player.getVelocity().setY(0);
            }
            if (keycode == KeyCode.D || keycode == KeyCode.Q) {
                player.getVelocity().setX(0);
            }
        });
        if (!player.getHitBox().isColliding() || Utilities.NO_CLIPPING){
            //Correspond à la position du fond
            Vector2D cameraPosition = new Vector2D(-backgroundPane.getTranslateX(), -backgroundPane.getTranslateY());
            //Remets le joueur au millieu
            player.setWorldPosition(new Vector2D((cameraPosition.getX() + ((Utilities.WINDOW_WIDTH /2)-((double) Utilities.TILE_SIZE /2)) + player.getVelocity().getX()),
                    (cameraPosition.getY() + ( Utilities.WINDOW_HEIGHT /2)-((double) Utilities.TILE_SIZE /2))+ player.getVelocity().getY()));


            //déplace le fond
            backgroundPane.setTranslateY(backgroundPane.getTranslateY() + player.getVelocity().getY());
            backgroundPane.setTranslateX(backgroundPane.getTranslateX() + player.getVelocity().getX());
        }

    }

    public static void handleInventoryInput(Stage stage, SceneController sceneController){
        stage.getScene().setOnKeyPressed(keyEvent -> {
            KeyCode keycode = keyEvent.getCode();
            if (keycode == KeyCode.I || keycode == KeyCode.ESCAPE) {
                sceneController.switchToGameSceneAfterLeavingMenu();
            }
        });
    }

}

