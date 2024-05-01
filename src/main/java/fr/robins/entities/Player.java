package fr.robins.entities;

import fr.robins.items.Inventory;
import fr.robins.types.DirectionType;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.Objects;


public class Player extends Entity{
    private DirectionType direction = DirectionType.DOWN;

    private Image[] sprites;
    private Image currentSprite;

    public Player(Vector2D spawnPosition) {
        super("Player",100,5,5,5,100,spawnPosition, new Inventory(),"/sprites/player/chevalier_idle.png" );

        sprites = new Image[4];
        sprites[0] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/chevalier_idle.png")));
        sprites[1] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/chevalier_back.png")));
        sprites[2] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/chevalier_left.png")));
        sprites[3] = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/chevalier_right.png")));

        currentSprite = sprites[0];

    }

    @Override
    public Node draw() {
        switch (this.direction){
            case UP:
                currentSprite = sprites[1];
                break;
            case DOWN:
                currentSprite = sprites[0];
                break;
            case LEFT:
                currentSprite = sprites[3];
                break;
            case RIGHT:
                currentSprite = sprites[2];
                break;
        }
        super.getSprite().setImage(currentSprite);


        return super.getSprite();
    }

    public static void handlePlayerInput(Scene scene, Player player){
        Node playerNode = player.draw();

        scene.setOnKeyPressed(event ->{
            KeyCode keycode = event.getCode();
            switch (keycode) {
                case Z:
                    player.velocity.setY(-player.getSpeed());
                    player.direction = DirectionType.UP;
                    break;
                case S:
                    player.velocity.setY(player.getSpeed());
                    player.direction = DirectionType.DOWN;
                    break;
                case D:
                    player.velocity.setX(player.getSpeed());
                    player.direction = DirectionType.LEFT;
                    break;
                case Q:
                    player.velocity.setX(-player.getSpeed());
                    player.direction = DirectionType.RIGHT;
                    break;
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            KeyCode keycode = keyEvent.getCode();
            if (keycode == KeyCode.Z || keycode == KeyCode.S) {
                player.velocity.setY(0);
            }
            if (keycode == KeyCode.D || keycode == KeyCode.Q) {
                player.velocity.setX(0);
            }
        });

        playerNode.setTranslateY(playerNode.getTranslateY() + player.velocity.getY());
        playerNode.setTranslateX(playerNode.getTranslateX() + player.velocity.getX());

    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }
}
