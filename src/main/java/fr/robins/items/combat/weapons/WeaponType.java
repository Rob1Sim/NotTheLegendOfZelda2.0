package fr.robins.items.combat.weapons;

public enum WeaponType {
    AXE("Hache",20,"/sprites/items/axe.png"),
    DOUBLE_AXE("Double Hache",25,"/sprites/items/double_axe.png"),
    KNIFE("Couteau",5,"/sprites/items/daguer.png"),
    SWORD("Epée",15,"/sprites/items/iron_sword.png");

    private final String name;
    private final int damage;
    private final String spritePath;
    WeaponType(String name, int damage, String spritePath) {
        this.name = name;
        this.damage = damage;
        this.spritePath = spritePath;
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
}
