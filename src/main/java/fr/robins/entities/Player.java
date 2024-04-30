package fr.robins.entities;

import fr.robins.items.Inventory;
import fr.robins.type.DirectionType;
import fr.robins.type.Vector2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Objects;


public class Player extends Entity{
    private ImageView imageView;
    private DirectionType direction = DirectionType.DOWN;

    protected Player() {
        super("Player",100,5,5,5,100,new Vector2D(0,0));

        sprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/chevalier_idle.png")));
        imageView = new ImageView(sprite);
    }

    @Override
    public Node draw() {
        return imageView;
    }

    public static void playerInput(Scene scene, Player player){
        scene.setOnKeyPressed(event ->{
            KeyCode keycode = event.getCode();
            Node playerNode = player.draw();
            switch (keycode) {
                case Z:
                    playerNode.setTranslateY(playerNode.getTranslateY() - player.getSpeed());
                    player.direction = DirectionType.UP;
                    break;
                case S:
                    playerNode.setTranslateY(playerNode.getTranslateY() + player.getSpeed());
                    player.direction = DirectionType.DOWN;
                    break;
                case D:
                    playerNode.setTranslateX(playerNode.getTranslateX() - player.getSpeed());
                    player.direction = DirectionType.LEFT;
                    break;
                case Q:
                    playerNode.setTranslateX(playerNode.getTranslateX() + player.getSpeed());
                    player.direction = DirectionType.RIGHT;
                    break;
            }
        });
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }
}
