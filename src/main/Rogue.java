package main;
import java.awt.Color;
public class Rogue extends Character{
    
private int rollNum;
private int maxRollNum;
private int rollCooldown;
private int roll;
private int rollPhase;
private long lastShootTime;
private long shootCooldown;
private Color norm;
private int normSpeed;
private boolean immune;

public Rogue(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color, int rollNum){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.rollNum = rollNum;
    immune = false;
    this.rollCooldown = 300;
    this.lastShootTime = 0;
    this.shootCooldown = 300;
    maxRollNum = rollNum;
    normSpeed = movespeed;
    norm = color;
}
public int takeDamage(int damage){
    if(immune){
        return HP;
    }
    HP -= damage;
    if(HP > maxHP){
        HP = maxHP;
    }
    return HP;
}
public void attackOne(){ //dagger throw
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastShootTime >= shootCooldown) {
        if(player.pID == 1){
            player.shoot(attackDMG*3, player.gp.tileSize, 6, Color.darkGray, 10, true, player.gp.p2);
        }
        if(player.pID == 2){
            player.shoot(attackDMG*3, player.gp.tileSize, 6, Color.lightGray, 10, true, player.gp.p1);
        }
        shotsAmount++;
        lastShootTime = currentTime;
    }
}
public void attackTwo(){ //roll
    if(rollNum > 0 && roll == 0){
        roll = 15;
        immune = true;
        movespeed = normSpeed + 10;
        player.hasafterimage = true;
        color = Color.lightGray;
        rollNum--;
    }
}
public void update(){
    stunnedCheck();
    if(rollPhase > 0){
        rollPhase--;
    }
    if(rollPhase == 0 && rollNum < maxRollNum){
        rollNum++;
        rollPhase = rollCooldown;
    }
    if(roll > 0){
        roll--;
    }
    if(roll == 1){
        immune = false;
        color = norm;
        movespeed = normSpeed;
        player.hasafterimage = false;
    }
    // System.out.println("Rollnum: " + rollNum);
    // System.out.println("Roll: " + roll);
    // System.out.println("RollPhase: " + rollPhase);
}
}