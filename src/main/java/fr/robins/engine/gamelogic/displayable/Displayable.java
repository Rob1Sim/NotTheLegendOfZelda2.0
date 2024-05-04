package fr.robins.engine.gamelogic.displayable;

import fr.robins.engine.collisions.HitBox;
import javafx.scene.Node;

public interface Displayable {
    Node draw();
    HitBox getHitBox();
}
