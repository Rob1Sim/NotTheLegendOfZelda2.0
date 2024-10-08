package fr.robins.world;


import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.collisions.HitBox;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Tile implements Displayable {
    private ImageView tileImage;
    private boolean collision = false;
    private boolean isDestructive = false;

    protected Tile(ImageView tileImage, boolean collision, boolean isDestructive) {
        this.tileImage = tileImage;
        this.collision = collision;
        this.isDestructive = isDestructive;
    }

    public Tile(){
        tileImage = new ImageView();

    }

    private ImageView getTileImage() {
        return tileImage;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setTileImage(ImageView tileImage) {
        this.tileImage = tileImage;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean isDestructive() {
        return isDestructive;
    }

    public void setDestructive(boolean destructive) {
        isDestructive = destructive;
    }

    @Override
    public Node draw() {
        return tileImage;
    }

    @Override
    public HitBox getHitBox() {
        return null;
    }

    @Override
    public Vector2D getPosition() {
        return null;
    }

    @Override
    public void setSprite(String spritePath) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void deleteSprite() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public ImageView getUniqueImageView() {
        return  new ImageView(this.getTileImage().getImage());
    }
}
