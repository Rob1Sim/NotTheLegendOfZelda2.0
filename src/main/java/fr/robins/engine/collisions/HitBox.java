package fr.robins.engine.collisions;

import fr.robins.engine.Displayable;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HitBox implements Displayable {
    protected final Rectangle hitBox;
    private boolean isColliding;

    protected HitBox() {

        this.hitBox = new Rectangle();

        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.TRANSPARENT);

        if(Utilities.DEBUG){
            hitBox.setStroke(Color.RED);
        }

        this.isColliding = false;
    }

    public HitBox(int x, int y, int width, int height){
        this();
        hitBox.setTranslateX(x);
        hitBox.setTranslateY(y);
        hitBox.setWidth(width);
        hitBox.setHeight(height);
    }

    public HitBox(int x, int y, int width, int height, Color debugColor){
        this(x, y, width, height);
        if(Utilities.DEBUG){
            hitBox.setStroke(debugColor);
        }
    }

    public HitBox(Vector2D coords, int width, int height, Color debugColor){
        this(coords.getIntX(), coords.getIntY(), width, height, debugColor);
    }

    public boolean isIntersecting(HitBox hitBox){
        return this.hitBox.intersects(hitBox.hitBox.getLayoutBounds());
    }

    //region getter and setter
    public boolean isColliding() {
        return isColliding;
    }
    public void setColliding(boolean isColliding) {
        this.isColliding = isColliding;
    }

    public void setCoords(Vector2D coords){
        hitBox.setTranslateX(coords.getX());
        hitBox.setTranslateY(coords.getY());
    }

    public void setWidth(double width){
        hitBox.setWidth(width);
    }
    public void setHeight(double height){
        hitBox.setHeight(height);
    }

    public double getWidth(){
        return hitBox.getWidth();
    }
    public double getHeight(){
        return hitBox.getHeight();
    }
    public double getX(){
        return hitBox.getTranslateX();
    }
    public double getY(){
        return hitBox.getTranslateY();
    }
    public Vector2D getCoords(){
        return new Vector2D(hitBox.getTranslateX(), hitBox.getTranslateY());
    }
    //endregion
    @Override
    public Node draw() {
        return hitBox;
    }
}
