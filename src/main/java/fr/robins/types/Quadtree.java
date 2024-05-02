package fr.robins.types;

import fr.robins.engine.collisions.Collisionable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Quadtree {
    private static final int MAX_OBJECTS = 20;
    private static final int MAX_LEVELS = 10;

    private final int level;
    private final List<Collisionable> objectsWithHitBoxs;
    private final Quadtree[] nodes;
    private final Rectangle bounds;

    /**
     * Tree that split hitboxs in differents regions if the number of them are too much
     * @param level
     * @param bounds
     */
    public Quadtree(int level, Rectangle bounds) {
        this.level = level;
        objectsWithHitBoxs = new ArrayList<>();
        this.bounds = bounds;
        this.nodes = new Quadtree[4];
    }

    public void insert(Collisionable object){
        if (nodes[0] != null){
            int index = getIndex(object);
            //Si on trouve de la place dans une des cases
            if (index != -1){
                //Appel récursif
                nodes[index].insert(object);
            }
        }
        //le noeud n'a pas été subdivisé ça veut dire qu'on peut encore ajouté un objet
        //Si il y a encore de la place dans les noeud actuel
        objectsWithHitBoxs.add(object);


        if (objectsWithHitBoxs.size() > MAX_OBJECTS && level < MAX_LEVELS) {
            //Dans le cas ou est trouvé aucune place (-1) ou que le noeud n'a pas été encore subdivisé on le subdivise
            if (nodes[0] == null){
                split();
            }

            //On va enlever tout les objet du grand noeud pour les remettre dans des plus petit noeud plus approprié
            int i = 0;
            while (i < objectsWithHitBoxs.size()){
                int index = getIndex(objectsWithHitBoxs.get(i));
                if (index != -1){
                    nodes[index].insert(objectsWithHitBoxs.remove(i));
                }else {
                    i++;
                }
            }
        }
    }

    private void split(){
        double subWidth = bounds.getWidth() / 2;
        double subHeight = bounds.getHeight() / 2;
        double x = bounds.getX();
        double y = bounds.getY();

        Rectangle r1 = new Rectangle(x + subWidth, y, subWidth, subHeight);
        Rectangle r2 = new Rectangle(x, y, subWidth, subHeight);
        Rectangle r3 = new Rectangle(x, y + subHeight, subWidth, subHeight);
        Rectangle r4 = new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight);

        r1.setFill(Color.TRANSPARENT);
        r2.setFill(Color.TRANSPARENT);
        r3.setFill(Color.TRANSPARENT);
        r4.setFill(Color.TRANSPARENT);

        nodes[0] = new Quadtree(level + 1, r1);
        nodes[1] = new Quadtree(level + 1, r2);
        nodes[2] = new Quadtree(level + 1, r3);
        nodes[3] = new Quadtree(level + 1, r4);
    }


    /**
     * Choose in wich case the object will finish
     * @return
     */
    private int getIndex(Collisionable object){
        int index = -1;

        double x = object.getHitBox().getX();
        double y = object.getHitBox().getY();

        double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
        double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

        //Détermine dans quel carré l'objet se situe
        boolean topQuadrant = (x < horizontalMidpoint && y + object.getHitBox().getHeight() < horizontalMidpoint);
        boolean bottomQuadrant = (y > horizontalMidpoint);

        if (x < verticalMidpoint && x + object.getHitBox().getWidth() < verticalMidpoint) {
            if(topQuadrant){
                index = 1;
            }
            else if(bottomQuadrant){
                index = 2;
            }
        } else if (x  > verticalMidpoint) {
            if(topQuadrant){
                index = 0;
            }
            else if(bottomQuadrant){
                index = 3;
            }
        }
        return index;
    }
}
