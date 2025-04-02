package main;
import java.awt.Color;
public class Vampire extends Character{
    
private int leechnum;
private int normLeech;
private int normDMG;
private int rageBonus;
private long lastShootTime;
private long shootCooldown;
private int hitsProcessed;
private int rage;
private Color norm;
private int normSpeed;
private long lastRageTime;
private long rageCooldown;

public Vampire(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color, int leachnum){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.leechnum = leachnum;
    this.shootCooldown = 300;
    this.lastShootTime = 0;
    rage = 0;
    lastRageTime = 0;
    rageCooldown = 10000;
    normLeech = leachnum;
    normDMG = attackDMG;
    norm = color;
    normSpeed = movespeed;
    rageBonus = 45;
}
public void attackOne(){ //leech
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastShootTime >= shootCooldown) {
        lastShootTime = currentTime;
        if(player.pID == 1){
            player.melee(player.x-(int)(player.gp.tileSize*1.5), player.y-(int)(player.gp.tileSize*1.5),attackDMG, player.gp.tileSize*4, player.gp.tileSize*4, 6, Color.orange, 2, true);
        }
        if(player.pID == 2){
            player.melee(player.x-(int)(player.gp.tileSize*1.5), player.y-(int)(player.gp.tileSize*1.5),attackDMG, player.gp.tileSize*4, player.gp.tileSize*4, 6, Color.cyan, 2, true);
        }
        shotsAmount++;
    }
}

public void attackTwo(){ 
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastRageTime >= rageCooldown) {
        rage = 150;
        attackDMG = normDMG + rageBonus;
        leechnum = normLeech + rageBonus;
        movespeed = normSpeed + 1;
        lastRageTime = currentTime;
        color = Color.black;
    }
}

public void update(){
    stunnedCheck();
    if(hitsProcessed < shotsHit){
        HP += leechnum;
        if(HP > maxHP){
            HP = maxHP;
        }
        hitsProcessed++;
    }
    if(rage > 0){
        rage--;
    }
    if(rage <= 0){
        attackDMG = normDMG;
        leechnum = normLeech;
        movespeed = normSpeed;
        color = norm;
    }
}
}


