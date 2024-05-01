package fr.robins.engine;

import fr.robins.entities.Player;
import fr.robins.types.DirectionType;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Inputs {
    public static void handleKeyInput(Scene scene, Player player, Pane backgroundPane, Stage stage){
        Node playerNode = player.draw();

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
                    player.setDirection(DirectionType.LEFT);
                    break;
                case Q:
                    player.getVelocity().setX(+player.getSpeed());
                    player.setDirection(DirectionType.RIGHT);
                    break;
                case F11:
                    Utilities.setFullScreen(stage,!stage.isFullScreen());
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


        Vector2D cameraPosition = new Vector2D(-backgroundPane.getTranslateX(), -backgroundPane.getTranslateY());

        player.getPosition().set((cameraPosition.getX() + ((Utilities.WINDOW_WIDTH /2)-((double) Utilities.TILE_SIZE /2)) + player.getVelocity().getX()),
                (cameraPosition.getY() + ( Utilities.WINDOW_HEIGHT /2)-((double) Utilities.TILE_SIZE /2))+ player.getVelocity().getY());

        playerNode.setTranslateX(player.getPosition().getX());
        playerNode.setTranslateY(player.getPosition().getY());


        backgroundPane.setTranslateY(backgroundPane.getTranslateY() + player.getVelocity().getY());
        backgroundPane.setTranslateX(backgroundPane.getTranslateX() + player.getVelocity().getX());

    }

}
