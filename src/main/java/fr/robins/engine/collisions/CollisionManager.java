package fr.robins.engine.collisions;

import fr.robins.entities.Entity;
import fr.robins.types.Utilities;
import fr.robins.world.TileManager;

import java.util.ArrayList;
import java.util.List;


public class CollisionManager {
    /**
     * Checks colision of the given entity with the tiles in the environement;
     */
    public static void environmentCollisionChecker(Entity entity, TileManager tileManager){

        //à chaque début on désactive les collisions du joueur

        entity.getCollisionHitBox().setColliding(false);

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

                entity.getCollisionHitBox().setColliding(true);
            }
        }
    }
}
