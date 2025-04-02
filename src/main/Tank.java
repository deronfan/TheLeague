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
private int block;

public Tank(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color, double defense){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.defense = defense;
    this.blockCooldown = 10000;
    this.lastBlockTime = 0;
    this.isBlocking = false;
    this.shootCooldown = 300;
    this.lastShootTime = 0;
    norm = color;
    block = 0;
    stunAmount = 30;
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
            player.advmelee(attackDMG/3, (int)(player.gp.tileSize*1.5), (int)(player.gp.tileSize*1.5), 3, Color.orange,8, true, 8, 3);
        }
        if(player.pID == 2){
            player.advmelee(attackDMG/3, (int)(player.gp.tileSize*1.5), (int)(player.gp.tileSize*1.5), 3, Color.cyan, 8, true, 8, 3);
        }
        shotsAmount++;
    }
}

public void attackTwo(){ //Block
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastBlockTime >= blockCooldown) {
        isBlocking = true;
        lastBlockTime = currentTime;
        block = 60;
        color = Color.yellow;
    }
}

public void update(){
    stunnedCheck();
    if(block > 0){
        block--;
    }
    if(block <= 0){
        isBlocking = false;
        color = norm;
    }
}
}