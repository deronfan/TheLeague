package main;
import java.awt.Color;
public class Speedster extends Character{

private int maxRollNum;
private int speedCooldown;
private int roll;
private int rollPhase;
private long lastShootTime;
private long shootCooldown;
private Color norm;
private int normSpeed;
private boolean immune;

public Speedster(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color){
    super(movespeed, maxHP, HP, name, attackDMG, color);
}
public void attackOne(){ //seeking lightning bolt but low damage

}
public void attackTwo(){ //increase speed hecka
long currentTime = System.currentTimeMillis();
    if (currentTime - lastRageTime >= speedCooldown) {
        player.gp.backgroundColor = Color.darkGray;
        attackDMG = normDMG + rageBonus;
        movespeed = (int)(normSpeed*4);
        lastRageTime = currentTime;
        color = Color.yellow;
        player.hasafterimage = true;
    }
}
public void update(){

}
}


