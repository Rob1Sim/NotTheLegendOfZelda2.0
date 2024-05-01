package fr.robins.entities;

import fr.robins.engine.Displayable;
import fr.robins.engine.HitBox;
import fr.robins.items.Inventory;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;


public abstract class Entity implements Displayable {
    private double hp;
    private double speed = 4;
    private int strength;
    private int constitution;
    private double range;
    private int money;
    private String name;

    private HitBox dmgHitBox;

    protected Vector2D worldPosition;
    protected Vector2D velocity;
    protected Inventory inventory;
    private ImageView spriteView;

    /**
     * Define a Entity
     * Position, inventory and sprites needs to be redefined
     * @param spritePath path to the sprite of the entity
     */
    protected Entity(String name, String spritePath) {
        this.name = name;
        this.hp = 50;
        this.strength = 5;
        this.constitution = 5;
        this.range = 5;
        this.money = 50;
        this.worldPosition = new Vector2D(0, 0);
        this.inventory = new Inventory();
        this.velocity = new Vector2D(0, 0);

        dmgHitBox = new HitBox(this);
        setSprite(spritePath);
    }
    protected Entity(String name, double hp, int strength, int constitution, double range, int money, Vector2D worldPosition, Inventory inventory, String spritePath ) {
        this(name, spritePath);
        this.hp = hp;
        this.strength = strength;
        this.constitution = constitution;
        this.range = range;
        this.money = money;
        this.inventory = inventory;

        setWorldPosition(worldPosition);
    }

    /**
     * Set an entity on the map
     */
    public void setWorldPosition(Vector2D newPosition) {
        worldPosition = newPosition;

        dmgHitBox.setHitBoxCoordinates(worldPosition);

        spriteView.setTranslateX(this.worldPosition.getX());
        spriteView.setTranslateY(this.worldPosition.getY());
    }

    @Override
    public Node draw() {
        return spriteView;
    }

    public static void renderEntity(Entity entity,Pane pane) {
        pane.getChildren().addAll(entity.draw(),entity.getDmgHitBox().draw());
    }
    //region Getter et Setter
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

    public HitBox getDmgHitBox() {
        return dmgHitBox;
    }

    public void setHitBox(HitBox hitBox) {
        this.dmgHitBox = hitBox;
    }

    public Vector2D getWorldPosition() {
        return worldPosition;
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
    //endregion

}
