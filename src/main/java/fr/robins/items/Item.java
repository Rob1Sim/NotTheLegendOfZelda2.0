package fr.robins.items;

import fr.robins.engine.collisions.HitBox;
import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Item implements Displayable {
    private final String name;
    private ImageView sprite;
    private final HitBox hitBox;
    private Vector2D position;
    private String spritePath;
    private int price;

    public Item(String name, String spritePath, Vector2D position, int price) {
        this.name = name;
        this.spritePath = spritePath;
        sprite = new ImageView(spritePath);
        sprite.setCache(false);
        this.position = position;
        this.price = price;
        hitBox = new HitBox(position, Utilities.TILE_SIZE/2,Utilities.TILE_SIZE/2, Color.GREEN);
        setPosition(position);
    }

    public String getName() {
        return name;
    }

    @Override
    public Node draw(){
        return sprite;
    }

    @Override
    public HitBox getHitBox(){
        return hitBox;
    }
    @Override
    public Vector2D getPosition(){
        return position;
    }

    public void setPosition(Vector2D position){
        this.position = position;

        hitBox.setCoords(position);
        sprite.setTranslateX(position.getX());
        sprite.setTranslateY(position.getY());
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public void setSprite(String spritePath) {
        if (spritePath != null) {
            Image sprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(spritePath)));
            this.sprite = new ImageView(sprite);
        }else {
            this.sprite = null;
        }
    }

    public ImageView getSprite() {
        return sprite;
    }

    @Override
    public void deleteSprite(){
        sprite.setImage(null);
    }

    protected void resetSprite(){
        sprite.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(spritePath))));
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
