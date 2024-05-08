package fr.robins.engine.gamelogic.gamescene.inventoryscene;

import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.entities.Player;
import fr.robins.items.Inventory;
import fr.robins.items.Item;
import fr.robins.items.ItemType;
import fr.robins.types.exceptions.InventoryLoadingException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class InventoryGameScene extends GameScene {
    public InventoryGameScene(Player player) {
        super();
        Inventory inventory = player.getInventory();
        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/inventoryScene.fxml");

            pane = loader.load(fxmlStream);

            pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/sceneBuilder/css/inventory.css")).toExternalForm());

            VBox weaponVBox = (VBox) pane.lookup("#weaponVBox");
            VBox collectableVBox = (VBox) pane.lookup("#objectVBox");
            VBox posableObjects = (VBox) pane.lookup("#posableVBox");
            Label goldLabel = (Label) pane.lookup("#goldLabel");
            ProgressBar hpBar = (ProgressBar) pane.lookup("#hpLBar");
            ProgressBar mpBar = (ProgressBar) pane.lookup("#mpLBar");
            Label warningLabel = (Label) pane.lookup("#warningTxt");

            if (hpBar != null && mpBar != null && goldLabel != null) {
                hpBar.setProgress((double) player.getHp() /player.getMaxHp());
                mpBar.setProgress((double) player.getMana() /player.getMaxMana());
                goldLabel.setText((player.getMoney()).toString());
            }

            //Load items into the interface
            if(weaponVBox != null && collectableVBox != null && warningLabel != null){

                for(Item item : inventory.getItemsByClass(ItemType.WEAPON)) {
                    CheckBox checkBox = new CheckBox(item.getName());
                    if (inventory.getEquippedWeapons().contains(item))
                        checkBox.setSelected(true);
                    checkBox.setOnAction(new EquipmentChanger(player.getInventory(),warningLabel));
                    checkBox.getStyleClass().add("checkbox");
                    weaponVBox.getChildren().add(checkBox);
                    checkBox.setPrefSize(229,21);

                }

                for(Item item : inventory.getItemsByClass(ItemType.CONSUMABLE)) {
                    CheckBox checkBox = new CheckBox(item.getName());
                    if (inventory.getEquippedConsumables().contains(item))
                        checkBox.setSelected(true);
                    checkBox.setOnAction(new EquipmentChanger(player.getInventory(),warningLabel));
                    checkBox.getStyleClass().add("checkbox");
                    collectableVBox.getChildren().add(checkBox);
                    checkBox.setPrefSize(229,21);
                }

                //TODO: Finir ça quand y'aura des trucs posables
                for(Item item : inventory.getItemsByClass(ItemType.POSABLE)) {
                    System.out.println("test");
                    CheckBox checkBox = new CheckBox(item.getName());
                    if (inventory.getPosableEquippedItem() != null)
                        if (inventory.getPosableEquippedItem().equals(item))
                            checkBox.setSelected(true);
                    checkBox.setOnAction(new EquipmentChanger(player.getInventory(),warningLabel));
                    checkBox.getStyleClass().add("checkbox");
                    posableObjects.getChildren().add(checkBox);
                    checkBox.setPrefSize(229,21);
                }

            }else {
                throw new InventoryLoadingException("Error while loadings weapons");
            }


        } catch (IOException e) {
            throw new RuntimeException("Error have occured while loading the scene :  " + e);
        } catch (InventoryLoadingException e) {
            throw new RuntimeException(e);
        }
    }

    static class EquipmentChanger implements EventHandler<ActionEvent> {
        Inventory inventory;
        Label warningTxt;
        EquipmentChanger(Inventory inventory, Label warningTxt) {
            this.inventory = inventory;
            this.warningTxt = warningTxt;
        }

        @Override
        public void handle(ActionEvent event) {
            CheckBox checkBox = (CheckBox) event.getSource();
            String name = checkBox.getText();
            String vboxId = ((CheckBox) event.getSource()).getParent().getId();
            //if selected check if the item is in the inventory, and if it can be added
            if (checkBox.isSelected()) {
                switch (vboxId){
                    case "weaponVBox":
                            if (!inventory.getEquippedWeapons().contains(inventory.getItemByName(name))){
                                if (inventory.getEquippedWeapons().size() < 4){
                                    inventory.getEquippedWeapons().add(inventory.getItemByName(name));
                                    warningTxt.setVisible(false);
                                }
                                else{
                                    warningTxt.setText("Vous ne pouvez pas avoir plus de 4 objets par catégorie ! ");
                                    warningTxt.setVisible(true);
                                }
                            }
                        break;
                    case "objectVBox":
                        if (!inventory.getEquippedConsumables().contains(inventory.getItemByName(name))){
                            if (inventory.getEquippedConsumables().size() < 4){
                                inventory.getEquippedConsumables().add(inventory.getItemByName(name));
                                warningTxt.setVisible(false);
                            }
                            else {
                                warningTxt.setText("Vous ne pouvez pas avoir plus de 4 objets par catégorie ! ");
                                warningTxt.setVisible(true);
                            }
                        }
                        break;
                    case "posableVBox":
                        Item item = inventory.getItemByName(name);
                        if (inventory.getPosableEquippedItem() == null || !inventory.getPosableEquippedItem().equals(item)){
                            inventory.setPosableEquippedItem(item);
                        }else{
                                warningTxt.setText("Vous ne pouvez pas avoir plus de 1 objets dans cette catégorie !");
                                warningTxt.setVisible(true);
                        }

                        break;
                }
            }else {
                //if it not checked remove it frome inventory
                switch (vboxId){
                    case "weaponVBox":
                        inventory.getEquippedWeapons().remove(inventory.getItemByName(name));
                        break;
                    case "objectVBox":
                        inventory.getEquippedConsumables().remove(inventory.getItemByName(name));
                        break;
                    case "posableVBox":
                        inventory.setPosableEquippedItem(null);
                        break;
                }
            }

        }
    }
}
