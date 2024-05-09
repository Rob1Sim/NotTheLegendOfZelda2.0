package fr.robins.engine.gamelogic.gamescene.menuscenes;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.entities.Entity;
import fr.robins.entities.Player;
import fr.robins.entities.npc.NPC;
import fr.robins.items.Inventory;
import fr.robins.items.Item;
import fr.robins.items.consumable.Consumable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TradeGameScene extends GameScene {
    public TradeGameScene(NPC npc, SceneController sceneController) {
        super();
        FXMLLoader loader = new FXMLLoader();
        Player player = sceneController.getPlayer();

        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/tradeScene.fxml");
            pane = loader.load(fxmlStream);
            pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/sceneBuilder/css/menu.css")).toExternalForm());

            Label dialogueLabel = (Label) pane.lookup("#dialogueLabel");
            Label npcName = (Label) pane.lookup("#npcName");
            Label playerName = (Label) pane.lookup("#playerLabel");
            Label warningLabel = (Label) pane.lookup("#warningLabel") ;
            Label playerGold = (Label) pane.lookup("#playerGold") ;
            Label traderGold = (Label) pane.lookup("#traderGold") ;


            ImageView imageView = (ImageView) pane.lookup("#npcSprite");
            VBox traderVBox = (VBox) pane.lookup("#traderVBox");
            VBox playerVBox = (VBox) pane.lookup("#playerVBox");

            if (dialogueLabel != null && npcName != null && imageView != null && playerName != null
                    && traderVBox != null && playerVBox != null && warningLabel != null && playerGold != null
                    && traderGold != null) {
                npcName.setText(npc.getName());
                playerName.setText(player.getName());
                dialogueLabel.setText(npc.getDialogueText());
                playerGold.setText(String.valueOf(player.getMoney()));
                traderGold.setText(String.valueOf(npc.getMoney()));

                Image npcSprite  = new Image(Objects.requireNonNull(getClass().getResourceAsStream(npc.getSpritePath())));
                imageView.setImage(npcSprite);

                createBoxForEachItem(playerVBox,player.getInventory(),player,npc,warningLabel, playerGold, traderGold);
                createBoxForEachItem(traderVBox,npc.getInventory(),player,npc,warningLabel, playerGold, traderGold);


            }

        } catch (IOException e) {
        throw new RuntimeException("Error have occured while loading the scene :  " + e);
    }
    }

    private void createBoxForEachItem(VBox vBox, Inventory inventory, Player player, NPC npc, Label labelWarning, Label playerGoldLabel, Label traderGoldLabel){
        boolean isBuyButton = false;
        String buttonTxt = "Vendre";
        if (vBox.getId().equals("traderVBox")){
            isBuyButton = true;
            buttonTxt = "Acheter";
        }


        for (Item item : inventory.getItems()) {
            HBox hBox = new HBox();

            Label label = new Label(item.getName());
            label.setStyle("-fx-font-size: 20");
            label.getStyleClass().add("txt");

            Button button = new Button();
            button.setStyle("-fx-font-size: 20");
            button.setText(buttonTxt);
            button.getStyleClass().add("action-button");

            hBox.getChildren().add(label);

            Label quantityLabel = new Label("1");

            if (item instanceof Consumable consumable){
                quantityLabel = new Label(String.valueOf(consumable.getQuantity()));
                quantityLabel.setStyle("-fx-font-size: 20;");
                quantityLabel.getStyleClass().add("txt");

                hBox.getChildren().add(quantityLabel);
            }

            button.setOnAction(new Trade(player,npc,labelWarning,item,isBuyButton, quantityLabel, playerGoldLabel, traderGoldLabel));


            hBox.getChildren().add(button);
            hBox.setSpacing(10);
            hBox.setAlignment(Pos.CENTER);
            vBox.getChildren().add(hBox);
        }

    }

    /**
     * When the player click on sell/Buy
     */
    private static class Trade implements EventHandler<ActionEvent> {


        Player player;
        NPC npc;
        Label warningLabel;
        Item item;
        boolean isBuyButton;
        Label quantityLabel;
        Label playerGoldLabel;
        Label traderGoldLabel;

        public Trade(Player player,NPC npc , Label warningLabel, Item item, boolean isBuyButton, Label quantityLabel, Label playerGoldLabel, Label traderGoldLabel) {
            this.player = player;
            this.npc = npc;
            this.warningLabel = warningLabel;
            this.item = item;
            this.isBuyButton = isBuyButton;
            this.quantityLabel = quantityLabel;
            this.playerGoldLabel = playerGoldLabel;
            this.traderGoldLabel = traderGoldLabel;
        }

        @Override
        public void handle(ActionEvent event) {
            Button buyButton = (Button) event.getSource();
            boolean isTransactionDone;
            if (isBuyButton){
                isTransactionDone = transaction(item.getPrice(),player,npc);
            }else{
                isTransactionDone = transaction(item.getPrice(),npc,player);
            }
            if (isTransactionDone){
                int quantity = Integer.parseInt(quantityLabel.getText());
                quantityLabel.setText(String.valueOf(quantity-1));
                if (quantity <= 1){
                    buyButton.setDisable(true);
                }
                playerGoldLabel.setText(String.valueOf(player.getMoney()));
                traderGoldLabel.setText(String.valueOf(npc.getMoney()));

                warningLabel.setVisible(false);
            }else{
                warningLabel.setVisible(true);
                warningLabel.setText("Il manque de l'or ! ");
                warningLabel.setStyle("-fx-text-fill: red");
            }
        }

        private boolean transaction( Integer money, Entity buyer, Entity seller) {
            if (money <= buyer.getMoney()) {
                seller.setMoney(seller.getMoney() + item.getPrice());
                buyer.setMoney(buyer.getMoney() - item.getPrice());
                Item buyItem = item;
                if (buyItem instanceof Consumable consumable){
                    buyItem = consumable.clone();
                    ((Consumable)buyItem).setQuantity(1);
                    buyer.getInventory().addItem(buyItem);
                }else{
                    buyer.getInventory().addItem(buyItem);
                }
                seller.getInventory().removeItem(item);
                return true;
            }else
                return false;
        }

    }
}
