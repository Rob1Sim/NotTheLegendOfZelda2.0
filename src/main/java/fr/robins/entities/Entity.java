package fr.robins.entities;

import fr.robins.engine.Displayable;
import fr.robins.items.Inventory;
import fr.robins.type.Vector2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;


public abstract class Entity implements Displayable {
    private double hp;
    private double speed;
    private int strength;
    private int constitution;
    private double range;
    private int money;
    private String name;
    private boolean isCollidable;
    private Rectangle2D hitBox;

    protected Vector2D position;
    protected Inventory inventory;
    protected Image sprite;

    protected Entity(String name, double hp, int strength, int constitution, double range, int money, Vector2D position, Inventory inventory, Image sprite ) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.constitution = constitution;
        this.range = range;
        this.money = money;
        speed = 4;
        this.position = position;
        this.inventory = inventory;
        this.sprite = sprite;
    }
    protected Entity(String name, double hp, int strength, int constitution, double range, int money, Vector2D position) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.constitution = constitution;
        this.range = range;
        this.money = money;
        speed = 4;
        this.position = position;
        this.inventory = new Inventory();

    }

    protected Entity(String name, Vector2D position, Image sprite) {
        this.name = name;
        this.hp = 50;
        this.strength = 5;
        this.constitution = 5;
        this.range = 5;
        this.money = 50;
        speed = 4;
        this.position = position;
        this.inventory = new Inventory();
        this.sprite = sprite;
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

    public Inventory getInventory() {
        return inventory;
    }

    public Image getSprite() {
        return sprite;
    }
}
