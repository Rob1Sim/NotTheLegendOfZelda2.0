package fr.robins.entities;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.collisions.EntityHitbox;
import fr.robins.engine.collisions.HitBox;
import fr.robins.engine.gamelogic.displayable.DisplayableSubject;
import fr.robins.items.Inventory;
import fr.robins.types.DirectionType;
import fr.robins.types.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Objects;


public abstract class Entity implements Displayable {

    private String name;
    private int hp;
    private int mana;
    private int constitution;
    private int strength;
    private int dexterity;
    private int money;
    private final double speed = 4;
    private int maxHp;
    private int maxMana;
    private final String spritePath;

    private EntityHitbox hitBox;
    private final HitBox collisionHitBox;
    protected DirectionType direction;

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
        this.direction = DirectionType.DOWN;
        this.name = name;
        this.hp = 50;
        this.strength = 5;
        this.constitution = 5;
        this.money = 50;
        this.worldPosition = new Vector2D(0, 0);
        this.inventory = new Inventory();
        this.velocity = new Vector2D(0, 0);

        collisionHitBox = new HitBox(worldPosition,1,1, Color.BLUE);
        hitBox = new EntityHitbox(this);
        this.spritePath = spritePath;
        maxHp = hp;
        maxMana = mana;
        setSprite(spritePath);
    }
    protected Entity(String name, int hp, int mana, int constitution, int strength, int dexterity, int money, Vector2D worldPosition, String spritePath ) {
        this(name, spritePath);
        this.hp = hp;
        this.mana = mana;
        this.strength = strength;
        this.constitution = constitution;
        this.dexterity = dexterity;
        this.money = money;
        this.inventory = new Inventory();
        maxHp = hp;
        maxMana = mana;

        setWorldPosition(worldPosition);
    }

    /**
     * Set an entity on the map
     */
    public void setWorldPosition(Vector2D newPosition) {
        worldPosition = newPosition;

        hitBox.setHitBoxCoordinates(worldPosition);

        spriteView.setTranslateX(this.worldPosition.getX());
        spriteView.setTranslateY(this.worldPosition.getY());
    }

    @Override
    public Node draw() {
        return spriteView;
    }

    //region Getter et Setter
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getSpeed() {
        return speed;
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

    public Integer getMoney() {
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

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

    public void setHitBox(EntityHitbox hitBox) {
        this.hitBox = hitBox;
    }
    @Override
    public Vector2D getPosition() {
        return worldPosition;
    }
    public Inventory getInventory() {
        return inventory;
    }


    public void setSprite(String spritePath) {
        Image sprite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(spritePath)));
        this.spriteView = new ImageView(sprite);
        this.spriteView.setCache(false);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    public HitBox getCollisionHitBox() {
        return collisionHitBox;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    protected void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return getName()+" - hp: "+getHp()+", Mana: "+getMana()+", Const: "+getConstitution()+", Strength: "+getStrength()+", Dexterity: "+getDexterity()+", Money: "+getMoney()+", Inventory: "+getInventory();
    }

    protected ImageView getSprite(){
        return spriteView;
    }

    @Override
    public void deleteSprite() {
        spriteView.setImage(null);
    }

    public String getSpritePath() {
        return spritePath;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxMana() {
        return maxMana;
    }
    //endregion

}
