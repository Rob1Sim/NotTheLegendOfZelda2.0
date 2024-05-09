package fr.robins.engine.gamelogic.gamescene.menuscenes;

import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.entities.npc.NPC;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class DialogueGameScene extends GameScene {
    public DialogueGameScene(NPC npc) {
        super();
        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/dialogueScene.fxml");
            pane = loader.load(fxmlStream);
            pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/sceneBuilder/css/menu.css")).toExternalForm());

            Label dialogueLabel = (Label) pane.lookup("#dialogueLabel");
            Label npcName = (Label) pane.lookup("#npcName");
            ImageView imageView = (ImageView) pane.lookup("#npcSprite");

            if (dialogueLabel != null && npcName != null && imageView != null) {
                npcName.setText(npc.getName());
                dialogueLabel.setText(npc.getDialogueText());
                Image npcSprite  = new Image(Objects.requireNonNull(getClass().getResourceAsStream(npc.getSpritePath())));
                imageView.setImage(npcSprite);
            }

        } catch (IOException e) {
        throw new RuntimeException("Error have occured while loading the scene :  " + e);
    }
    }
}
