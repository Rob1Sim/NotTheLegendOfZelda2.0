package fr.robins.engine.gamelogic.displayable;

import fr.robins.engine.collisions.HitBox;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface Displayable {
    Node draw();
    HitBox getHitBox();
    Vector2D getPosition();
    void setSprite(String spritePath);
    void deleteSprite();

    /**
     * Remove from scene, and displayable list a displayable object
     */
    static void removeDisplayable(Displayable displayable, DisplayableSubject displayableObserver, Pane pane){
        GameScene.removeDisplayable(displayable,pane);
        displayableObserver.remove(displayable);

    }
}
