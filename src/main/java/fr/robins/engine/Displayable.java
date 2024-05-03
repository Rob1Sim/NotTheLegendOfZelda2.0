package fr.robins.engine;

import fr.robins.engine.collisions.HitBox;
import javafx.scene.Node;

public interface Displayable {
    Node draw();
    HitBox getHitBox();
}
