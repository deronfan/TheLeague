package main;
import java.awt.Color;

public class Ranger extends Character{

private int range;
private long lastShootTime;
private long shootCooldown;
private long lastWall;
private long wallCooldown;
public Ranger(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color, int range){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.range = range;
    this.shootCooldown = 600;
    this.lastShootTime = 0;
    this.wallCooldown = 5000;
    this.lastWall = 0;
}
public void attackOne(){ //shoot
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastShootTime >= shootCooldown) {
        if(player.pID == 1){
            player.shoot(attackDMG, player.gp.tileSize, 5, Color.orange, 100);
        }
        if(player.pID == 2){
            player.shoot(attackDMG, player.gp.tileSize, 5, Color.cyan, 100);
        }
        lastShootTime = currentTime;
    }
}
public void attackTwo(){
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastWall >= wallCooldown) {
        if(player.pID == 1){
            player.shoot(attackDMG*5, player.gp.tileSize, 1, Color.red, 500, true, player.gp.p2);
        }
        if(player.pID == 2){
            player.shoot(attackDMG*5, player.gp.tileSize, 1, Color.blue, 500, true, player.gp.p1);
        }
        lastWall = currentTime;
    }
}
}