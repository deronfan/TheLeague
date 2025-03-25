package main;
import java.awt.Color;
public class Tank extends Character{
    
private double defense;
private long lastBlockTime;
private long blockCooldown;
private boolean isBlocking;
private long lastShootTime;
private long shootCooldown;
private Color norm;

public Tank(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color, double defense){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.defense = defense;
    this.blockCooldown = 50000;
    this.lastBlockTime = 0;
    this.isBlocking = false;
    this.shootCooldown = 300;
    this.lastShootTime = 0;
    norm = color;
}
public int takeDamage(int damage){
    if(damage <= 0)
    {
        HP -= damage;
        if(HP > maxHP){
            HP = maxHP;
        }
        return HP;
    }
    if(isBlocking){
        isBlocking = false;
        color = norm;
        return HP;
    }
    HP -= (int)(damage*defense);
    return HP;
}

public void attackOne(){ //Stab
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastShootTime >= shootCooldown) {
        lastShootTime = currentTime;
        if(player.pID == 1){
            player.melee(player.x-player.gp.tileSize,player.y-player.gp.tileSize,attackDMG, player.gp.tileSize*3, player.gp.tileSize*3, 2, Color.orange, 2, true);
        }
        if(player.pID == 2){
            player.melee(player.x-player.gp.tileSize,player.y-player.gp.tileSize,attackDMG, player.gp.tileSize*3, player.gp.tileSize*3, 2, Color.cyan, 2, true);
        }
        
    }
}

public void attackTwo(){ //Block
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastBlockTime >= blockCooldown) {
        isBlocking = true;
        lastBlockTime = currentTime;
        color = Color.yellow;
    }
}

}