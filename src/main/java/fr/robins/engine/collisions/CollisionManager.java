package fr.robins.engine.collisions;

import fr.robins.engine.controller.SceneController;
import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.displayable.DisplayableSubject;
import fr.robins.engine.gamelogic.gamescene.GameScene;
import fr.robins.engine.gamelogic.gamescene.GameSceneSubject;
import fr.robins.entities.Entity;
import fr.robins.entities.Player;
import fr.robins.entities.enemy.Enemy;
import fr.robins.entities.npc.NPC;
import fr.robins.items.Collectable;
import fr.robins.items.Item;
import fr.robins.items.interactable.Interactable;
import fr.robins.items.interactable.InteractableWithInput;
import fr.robins.items.posable.Posable;
import fr.robins.types.Utilities;

import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class CollisionManager {
    /**
     * Checks colision of the given entity with the tiles in the environement;
     */
    public static void environmentCollisionChecker(Entity entity, TileManager tileManager){

        //à chaque début on désactive les collisions du joueur

        entity.getHitBox().setColliding(false);

        int entityLeftWorldx = (int) (entity.getHitBox().getX());
        int entityRightWorldx = (int) (entity.getHitBox().getX() + entity.getHitBox().getWidth());
        int entityTopWorldy = (int) (entity.getHitBox().getY());
        int entityBottomWorldy = (int) (entity.getHitBox().getY() + entity.getHitBox().getHeight());



        int entityLeftCol = entityLeftWorldx/Utilities.TILE_SIZE;
        int entityRightCol = entityRightWorldx/Utilities.TILE_SIZE;
        int entityTopRow = entityTopWorldy/Utilities.TILE_SIZE;
        int entityBottomRow = entityBottomWorldy/Utilities.TILE_SIZE;


        List<int[]> listOfFrontTilesOnLayers = new ArrayList<>();
        //on initialise chaque devant pour chaque layer (en enlevant le premier layer)
        for (int i = 0; i < tileManager.getNumberOfLayers() - 1; i++) {
            //Les deux tiles sur les différents layers
            int[] frontTilesLayer = new int[]{-1,-1};
            listOfFrontTilesOnLayers.add(frontTilesLayer);
        }



        switch (entity.getDirection()){
            case UP:
                //on prédit la case qui va arrivé
                entityTopRow = (int) ((entityTopWorldy - entity.getSpeed())/Utilities.TILE_SIZE);

                getTilesFromLayersBottomTop(tileManager, entityLeftCol, entityRightCol, entityTopRow, listOfFrontTilesOnLayers);
                isThereACollision(entity,listOfFrontTilesOnLayers, tileManager);
                break;

            case DOWN:
                //on prédit la case qui va arrivé
                entityBottomRow = (int) ((entityBottomWorldy + entity.getSpeed())/Utilities.TILE_SIZE);
                getTilesFromLayersBottomTop(tileManager, entityLeftCol, entityRightCol, entityBottomRow, listOfFrontTilesOnLayers);
                isThereACollision(entity,listOfFrontTilesOnLayers, tileManager);
                break;

            case LEFT:
                //on prédit la case qui va arrivé
                entityLeftCol = (int) ((entityLeftWorldx - entity.getSpeed())/Utilities.TILE_SIZE);
                getTilesFromLayersLeftRight(tileManager, entityLeftCol, entityTopRow, entityBottomRow, listOfFrontTilesOnLayers);
                isThereACollision(entity,listOfFrontTilesOnLayers, tileManager);
                break;

            case RIGHT:
                //on prédit la case qui va arrivé
                entityRightCol = (int) ((entityRightWorldx + entity.getSpeed())/Utilities.TILE_SIZE);

                getTilesFromLayersLeftRight(tileManager, entityRightCol, entityTopRow, entityBottomRow, listOfFrontTilesOnLayers);
                isThereACollision(entity,listOfFrontTilesOnLayers,tileManager);
                break;


        }
    }

    /**
     * Add the bottom and top tiles into the tile list
     */
    private static void getTilesFromLayersBottomTop(TileManager tileManager, int entityLeftCol, int entityRightCol, int row, List<int[]> listOfFrontTilesOnLayers) {
        for (int i = 0; i < listOfFrontTilesOnLayers.size(); i++) {
            int leftCol = tileManager.getLayersMapData(i+1).get(row).get(entityLeftCol);
            int rightCol = tileManager.getLayersMapData(i+1).get(row).get(entityRightCol);

            listOfFrontTilesOnLayers.set(i,new int[]{leftCol,rightCol});
        }
    }
    /**
     * Add the right and left tiles into the tile list
     */
    private static void getTilesFromLayersLeftRight(TileManager tileManager, int column, int entityTopRow, int entityBottomRow, List<int[]> listOfFrontTilesOnLayers) {
        for (int i = 0; i < listOfFrontTilesOnLayers.size(); i++) {
            int topRow = tileManager.getLayersMapData(i+1).get(entityTopRow).get(column);
            int bottomRow = tileManager.getLayersMapData(i+1).get(entityBottomRow).get(column);

            listOfFrontTilesOnLayers.set(i,new int[]{topRow,bottomRow});
        }
    }

    /**
     * Check if the entity must stop.
     */
    private static void isThereACollision(Entity entity,List<int[]> listOfFrontTilesOnLayers, TileManager tileManager){
        for (int[] listOfFrontTilesOnLayer : listOfFrontTilesOnLayers) {
            if (listOfFrontTilesOnLayer[0] != -1 && tileManager.getTilesMap().get(listOfFrontTilesOnLayer[0]).isCollision() ||
                    listOfFrontTilesOnLayer[1] != -1 && tileManager.getTilesMap().get(listOfFrontTilesOnLayer[1]).isCollision()){
                entity.getHitBox().setColliding(true);
            }
        }
    }

    /**
     * Check if there a collision from the player with an Entity/Item/Interactable
     * @param displayableObserver the observer of the list of displayable
     * @param entity the player
     */
    public static void displayableCollisionChecker(DisplayableSubject displayableObserver, Pane pane, Entity entity, SceneController sceneController){
        int index = -1;

        //RayCast to see if the player encounter an input interactible items/NPC
        int indexInteractable = -1;

        List<Displayable> items = displayableObserver.getDisplayables();

        for (int i = 0; i < items.size(); i++) {
            HitBox futureHitBox = new HitBox();

            futureHitBox.setHeight(entity.getHitBox().getHeight());
            futureHitBox.setWidth(entity.getHitBox().getWidth());


            switch (entity.getDirection()){
                case UP:
                    //Collisions
                    futureHitBox.setCoords(new Vector2D(entity.getHitBox().getX(),entity.getHitBox().getY() - entity.getSpeed()*1.5));
                    if (futureHitBox.isIntersecting(items.get(i).getHitBox())){
                        index = i;
                        indexInteractable = i;
                        checkCollision(entity,items.get(i));
                    }

                    break;
                case DOWN:
                    futureHitBox.setCoords(new Vector2D(entity.getHitBox().getX(),entity.getHitBox().getY() + entity.getHitBox().getHeight()/3 + entity.getSpeed()));
                    if (futureHitBox.isIntersecting(items.get(i).getHitBox())){
                        index = i;
                        indexInteractable = i;
                        checkCollision(entity,items.get(i));
                    }

                    break;
                case LEFT:
                    futureHitBox.setCoords(new Vector2D(entity.getHitBox().getX() - entity.getSpeed()*1.5,entity.getHitBox().getY()));
                    if (futureHitBox.isIntersecting(items.get(i).getHitBox())){
                        index = i;
                        indexInteractable = i;
                        checkCollision(entity,items.get(i));
                    }

                    break;
                case RIGHT:
                    futureHitBox.setCoords(new Vector2D(entity.getHitBox().getX() + entity.getHitBox().getWidth()/3 + entity.getSpeed() ,entity.getHitBox().getY()));
                    if (futureHitBox.isIntersecting(items.get(i).getHitBox())){
                        index = i;
                        indexInteractable = i;
                        checkCollision(entity,items.get(i));
                    }
            }
        }

        if (index != -1){
            if (items.get(index) instanceof Collectable collectable){
                entity.getInventory().addItem((Item) collectable);
                //Si c'est un item posable on ne le supprime pas des objets afficher pour pouvoir l'utiliser
                if (collectable instanceof Posable posableI){
                    posableI.setPosition(new Vector2D());
                }else{
                    Displayable.removeDisplayable(items.get(index),displayableObserver,pane);
                }
            }else if (items.get(index) instanceof Interactable interactableI){
                interactableI.interact(entity);

            }else if (items.get(index) instanceof Enemy enemy){
                sceneController.switchToCombatScene(enemy);
            }
        }

        if (indexInteractable != -1){
            if (items.get(indexInteractable) instanceof InteractableWithInput interactable){
                ((Player)entity).setCanInteract(true,interactable);
            }
        }else{
            ((Player)entity).setCanInteract(false,null);
        }
    }


    /**
     * check and set collision with a displayable if there is one
     */
    private static void checkCollision( Entity entity, Displayable items){
        if (items instanceof Interactable interactableItem){
            if (interactableItem.isCollisionable()){
                entity.getHitBox().setColliding(true);
            }
        }
        if (items instanceof NPC){
            entity.getHitBox().setColliding(true);
        }
    }
}
