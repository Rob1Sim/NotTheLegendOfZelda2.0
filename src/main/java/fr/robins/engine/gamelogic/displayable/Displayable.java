package fr.robins.engine.gamelogic.displayable;

import fr.robins.engine.collisions.HitBox;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public interface Displayable {
    Node draw();
    HitBox getHitBox();
    Vector2D getPosition();
    void setSprite(String spritePath);
    void deleteSprite();
}
