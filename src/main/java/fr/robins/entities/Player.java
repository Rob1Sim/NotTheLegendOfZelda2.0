package fr.robins.entities;

import fr.robins.type.DirectionType;
import fr.robins.type.Vector2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.Objects;


public class Player extends Entity{
    private ImageView imageView;
    private DirectionType direction = DirectionType.DOWN;

    public Player() {
        super("Player",100,5,5,5,100,new Vector2D(0,0));

        sprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/chevalier_idle.png")));
        imageView = new ImageView(sprite);
    }

    @Override
    public Node draw() {
        return imageView;
    }

    public static void handlePlayerInput(Scene scene, Player player){
        Node playerNode = player.draw();

        scene.setOnKeyPressed(event ->{
            KeyCode keycode = event.getCode();
            switch (keycode) {
                case Z:
                    //TODO ajouter la vélocité
                    player.direction = DirectionType.UP;
                    break;
                case S:
                    player.direction = DirectionType.DOWN;
                    break;
                case D:
                    player.direction = DirectionType.LEFT;
                    break;
                case Q:
                    player.direction = DirectionType.RIGHT;
                    break;
            }
        });

        playerNode.setTranslateY(playerNode.getTranslateY() - player.getSpeed());
        playerNode.setTranslateX(playerNode.getTranslateX() + player.getSpeed());

    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }
}
