package fr.robins.items.others;

public enum OtherItemType {
    KEY("Clé","/tiles/tile_0009.png",0);

    private final String name;
    private final String sprite;
    private final int cost;
    OtherItemType(String name, String sprite, int price) {

        this.name = name;
        this.sprite = sprite;
        this.cost = price;
    }
    public String getName() {
        return name;
    }
    public String getSprite() {
        return sprite;
    }
    public int getCost() {
        return cost;
    }
}
