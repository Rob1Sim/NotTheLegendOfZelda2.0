package fr.robins.engine.gamelogic.displayable;

import fr.robins.engine.collisions.HitBox;
import fr.robins.types.Vector2D;
import javafx.scene.Node;

public interface Displayable {
    Node draw();
    HitBox getHitBox();
    Vector2D getPosition();
    void setSprite(String spritePath);
    void deleteSprite();
    static void removeDisplayable(Displayable displayable, DisplayableSubject displayableObserver){
        displayableObserver.remove(displayable);
    }
}
