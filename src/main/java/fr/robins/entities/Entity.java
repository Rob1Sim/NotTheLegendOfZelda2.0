package fr.robins.entities;

import fr.robins.engine.Displayable;
import fr.robins.items.Inventory;
import fr.robins.types.Vector2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;


public abstract class Entity implements Displayable {
    private double hp;
    private double speed = 4;
    private int strength;
    private int constitution;
    private double range;
    private int money;
    private String name;
    private boolean isCollidable;
    private Rectangle2D hitBox;

    protected Vector2D position;
    protected Vector2D velocity;
    protected Inventory inventory;
    private ImageView spriteView;

    /**
     * Define a Entity
     * Position, inventory and sprites needs to be redefined
     * @param spritePath path to the sprite of the entity
     */
    protected Entity(String name, double hp, int strength, int constitution, double range, int money, Vector2D position, Inventory inventory, String spritePath ) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.constitution = constitution;
        this.range = range;
        this.money = money;
        this.inventory = inventory;
        this.velocity = new Vector2D(0, 0);

        setSprite(spritePath);
        setPosition(position);
    }

    /**
     * Constructor for the player class
     */
    protected Entity(String name, double hp, int strength, int constitution, double range, int money, Inventory inventory, String spritePath ) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.constitution = constitution;
        this.range = range;
        this.money = money;
        this.inventory = inventory;
        this.velocity = new Vector2D(0, 0);

        setSprite(spritePath);
    }

    protected Entity(String name, Vector2D position, String spritePath) {
        this.name = name;
        this.hp = 50;
        this.strength = 5;
        this.constitution = 5;
        this.range = 5;
        this.money = 50;
        this.inventory = new Inventory();
        this.velocity = new Vector2D(0, 0);

        setSprite(spritePath);
        setPosition(position);
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public void setCollidable(boolean collidable) {
        isCollidable = collidable;
    }

    public Rectangle2D getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle2D hitBox) {
        this.hitBox = hitBox;
    }

    public Vector2D getPosition() {
        return position;
    }

    /**
     * Set an entity on the map
     */
    public void setPosition(Vector2D newPosition) {
        position = newPosition.cloneVector();
        spriteView.setTranslateX(this.position.getX());
        spriteView.setTranslateY(this.position.getY());
    }
    public Inventory getInventory() {
        return inventory;
    }

    protected ImageView getSprite() {
        return spriteView;
    }

    protected void setSprite(String spritePath) {
        Image sprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(spritePath)));
        this.spriteView = new ImageView(sprite);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public Node draw() {
        return spriteView;
    }
}
