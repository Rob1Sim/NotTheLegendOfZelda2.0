package fr.robins.entities;

import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Objects;



public class Player extends Entity{

    private final Image[] sprites;
    private Image currentSprite;

    public Player(Vector2D spawnPosition) {
        super("Player",100,30,10,10,10,100,spawnPosition ,"/sprites/player/chevalier_idle.png" );
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
                currentSprite = sprites[2];
                break;
            case RIGHT:
                currentSprite = sprites[3];
                break;
        }
        super.getSprite().setImage(currentSprite);


        return super.getSprite();
    }



    public static void teleportPlayer(Pane backgroundPane, Vector2D coordinates){
        backgroundPane.setTranslateX(-coordinates.getX() + ((Utilities.WINDOW_WIDTH /2)-((double) Utilities.TILE_SIZE /2)));
        backgroundPane.setTranslateY(-coordinates.getY() + ((Utilities.WINDOW_WIDTH /2)-((double) Utilities.TILE_SIZE /2)));
    }

    @Override
    public void setWorldPosition(Vector2D newWorldPosition) {
        super.setWorldPosition(newWorldPosition);
        if (sprites != null){
            this.draw();
        }
    }

}
