package main;
import java.awt.Color;

public class Ranger extends Character{
private long lastShootTime;
private long shootCooldown;
private long lastWall;
private long wallCooldown;
public Ranger(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.shootCooldown = 600;
    this.lastShootTime = 0;
    this.wallCooldown = 7000;
    this.lastWall = 0;
}
public void attackOne(){ //shoot
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastShootTime >= shootCooldown) {
        if(player.pID == 1){
            player.shoot(attackDMG, player.gp.tileSize, 5, Color.orange, 100);
            //player.shoot(attackDMG, player.gp.tileSize,  player.gp.tileSize, 5, Color.orange, 100, true, player);
            //player.shoot(1, 64, 0, Color.orange, 1200, false, player.gp.p2, player.x, player.y, true);
        }
        if(player.pID == 2){
            player.shoot(attackDMG, player.gp.tileSize, 5, Color.cyan, 100);
        }
        lastShootTime = currentTime;
        shotsAmount++;
    }
}
public void attackTwo(){
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastWall >= wallCooldown) {
        if(player.pID == 1){
            player.shoot(attackDMG*3, player.gp.tileSize, 1, Color.red, 300, true, player.gp.p2);
        }
        if(player.pID == 2){
            player.shoot(attackDMG*3, player.gp.tileSize, 1, Color.blue, 300, true, player.gp.p1);
        }
        shotsAmount++;
        lastWall = currentTime;
    }
}
}