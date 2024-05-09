package fr.robins.items.combat.weapons;

public enum WeaponType {
    AXE("Hache",20,"/sprites/items/axe.png", 30),
    DOUBLE_AXE("Double Hache",25,"/sprites/items/double_axe.png",40),
    KNIFE("Couteau",5,"/sprites/items/daguer.png",10),
    IRON_SWORD("Epée",15,"/sprites/items/iron_sword.png",20),
    SHORT_SWORD("Epée courte",10,"/sprites/items/short_sword.png",15),
    GHOST_FIST("Ghost Fist",15,"/sprites/items/short_sword.png",0),
    RAT_CLAWS("Rat Claws",5,"/sprites/items/short_sword.png",0),;

    private final String name;
    private final int damage;
    private final String spritePath;
    private final int price;
    WeaponType(String name, int damage, String spritePath, int price) {
        this.name = name;
        this.damage = damage;
        this.spritePath = spritePath;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public int getPrice() {
        return price;
    }
}
