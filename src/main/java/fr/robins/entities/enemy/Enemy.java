package fr.robins.entities.enemy;


import fr.robins.entities.Fighter;
import fr.robins.types.Vector2D;
import fr.robins.types.entities.EnemyType;

public class Enemy extends Fighter {
    public Enemy(EnemyType enemyType, Vector2D worldPosition) {
        super(enemyType.getName(), enemyType.getHp(), enemyType.getMana(), enemyType.getConstitution(), enemyType.getStrength(), enemyType.getDexterity(),enemyType.getMoney(),worldPosition,enemyType.getSprite(), enemyType.getSpells());
        setInventory(enemyType.getInventory());
    }
}
