package fr.robins.engine;

import fr.robins.entities.Player;
import fr.robins.types.DirectionType;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Inputs {
    public static void handleMovementInput(Scene scene, Player player, Pane backgroundPane, Stage stage){

        scene.setOnKeyPressed(event ->{
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
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
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

}

