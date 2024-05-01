package fr.robins.world;

import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.List;
import java.util.*;

public class TileManager {
    private final Map<Integer,Tile> tilesMap;

    //Matrice de toute ls tiles
    private List<List<List<Integer>>> mapData;
    //Ensemble qui contient le numéro unique de chaque tile
    private final Set<Integer> tileSet;
    //Ensemble qui contient le numéro unique des tiles avec colision
    private final Set<Integer> collisableTileSet;
    //Ensemble qui contient le numéro unique des tiles destructible
    private final Set<Integer> destructibleTileSet;

    private int mapWidth;
    private int mapHeight;

    private int numberOfLayers;

    /**
     * In charge of loading and displaying a map
     * @param pathToMap Path to the xml file containing the tile map
     */
    public TileManager(String pathToMap) {
        tileSet = new HashSet<>();
        collisableTileSet = new HashSet<>();
        destructibleTileSet = new HashSet<>();
        tilesMap = new HashMap<>();

        loadXMLMap(pathToMap);
        loadTileImage();

    }

    private void loadXMLMap(String mapPath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            //Récupération de la taille de la map
            NodeList map = doc.getElementsByTagName("map");

            mapWidth = Integer.parseInt(map.item(0).getAttributes().getNamedItem("width").getNodeValue());
            mapHeight = Integer.parseInt(map.item(0).getAttributes().getNamedItem("height").getNodeValue());

            //Récupération des calque et des données des différentes tiles
            System.out.println("mapWidth: " + mapWidth);
            mapData = new ArrayList<>();
            NodeList nData = doc.getElementsByTagName("data");
            numberOfLayers = nData.getLength();
            for (int i = 0; i < nData.getLength(); i++) {
                mapData.add(new ArrayList<>());
                Node dataNode =  nData.item(i);

                String dataContent = dataNode.getTextContent().trim();

                String[] lines = dataContent.split("\n");

                for(String line : lines){
                    String[] values = line.split(",");

                    List<Integer> row = new ArrayList<>();
                    for (String value : values) {
                        row.add(Integer.parseInt(value.trim())-1);
                        tileSet.add(Integer.parseInt(value.trim())-1);

                        if (i == 1){
                            collisableTileSet.add(Integer.parseInt(value.trim())-1);
                        }
                        if (i == 2){
                            destructibleTileSet.add(Integer.parseInt(value.trim())-1);
                        }
                    }

                    mapData.get(i).add(row);
                }
            }

        }catch (Exception e){
            System.out.println("Error loading XML map: "+e.getMessage());
        }
    }

    private void loadTileImage(){
        try{

            //Récupère toute les tiles présente dans la matrice et les charges en mémoire
            for (int i = 0; i < tileSet.size(); i++){
                int tileNumber = (Integer) (tileSet.toArray()[i]);

                if (tileNumber > -1){
                    Tile newTile = new Tile();

                    String tileNumberString;
                    if (tileNumber < 10){
                        tileNumberString = "000"+tileNumber;
                    } else if (tileNumber < 100) {
                        tileNumberString = "00"+tileNumber;
                    }else {
                        tileNumberString = "0"+tileNumber;
                    }

                    if (collisableTileSet.contains(tileNumber)){
                        newTile.setCollision(true);
                    }
                    if (destructibleTileSet.contains(tileNumber)){
                        newTile.setCollision(true);
                        newTile.setDestructive(true);
                    }
                    Image tileImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile_"+tileNumberString+".png")));
                    ImageView tileImageView = new ImageView(tileImage);
                    newTile.setTileImage(tileImageView);

                    tilesMap.put(tileNumber,newTile);
                }
            }

        }catch (NullPointerException e){
            System.out.println("Error loading tile image : " + e.getMessage());
        }
    }


    /**
     * Draw a layer of the loaded map
     * 0 -> Non-collisable tiles
     * 1 -> collisable tiles
     * 2 -> destructible tiles
     * @param layer should be between 0 and 2, if not return
     * @param pane the pane where the tiles will be drawn
     */
    public void draw(int layer, Pane pane){

        if (layer < 0 || layer > numberOfLayers ){
            throw new IllegalArgumentException("Invalid layer number");
        }

        int worldCurrentColumn = 0;
        int worldCurrentRow = 0;

        while( worldCurrentRow < getMapHeight() && worldCurrentColumn < getMapWidth()){

            int currentTile = mapData.get(layer).get(worldCurrentRow).get(worldCurrentColumn);

            //TODO: Décharger quand la caméra n'est pas là
            if (currentTile != -1){
                Tile tileToDraw = tilesMap.get(currentTile);
                ImageView tileView = tileToDraw.getUniqueImageView();

                tileView.setSmooth(false);
                tileView.setTranslateX(worldCurrentColumn * Utilities.TILE_SIZE);
                tileView.setTranslateY(worldCurrentRow * Utilities.TILE_SIZE);

                pane.getChildren().add(tileView);
            }

            worldCurrentColumn++;
            if (worldCurrentColumn == getMapWidth() && worldCurrentRow != getMapHeight()){
                worldCurrentColumn = 0;
                worldCurrentRow++;

            }
        }

    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public List<List<List<Integer>>> getMapData() {
        return mapData;
    }

    public Map<Integer, Tile> getTilesMap() {
        return tilesMap;
    }

    public int getNumberOfLayers() {
        return numberOfLayers;
    }

    /**
     * Return the corresponding coordonates of the column and row entered
     * @return coordinates
     */
    public static Vector2D tilesToCoordinates(int column, int row){
        return new Vector2D(column * Utilities.TILE_SIZE, row * Utilities.TILE_SIZE);
    }
}
