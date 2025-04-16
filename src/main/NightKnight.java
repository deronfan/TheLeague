package main;
import java.awt.Color;
public class NightKnight extends Character{
private long lastShootTime;
private long shootCooldown;
private long rayCooldown;
private long lastRay;
public NightKnight(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.shootCooldown = 600;
    this.lastShootTime = 0;
    this.rayCooldown = 16000;
    this.lastRay = 0;
}

public void attackOne(){ //dark stab
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastShootTime >= shootCooldown) {
        lastShootTime = currentTime;
        if(player.pID == 1){
            player.advmelee(attackDMG/2, player.gp.tileSize, player.gp.tileSize, 3, Color.darkGray,10, true, 10, 3);
        }
        if(player.pID == 2){
            player.advmelee(attackDMG/2, player.gp.tileSize, player.gp.tileSize, 3, Color.lightGray, 10, true, 10, 3);
        }
        lastShootTime = currentTime;
        shotsAmount+=10;
    }
}
public void attackTwo(){ //dark ray
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastRay >= rayCooldown) {
        if(player.pID == 1){
            for(int i = 0; i < 15; i++){
                player.shoot((int) (attackDMG * 0.15), player.gp.tileSize, 8+(int)(i/2), Color.ORANGE, 70);
            }
        }
        if(player.pID == 2){
            for(int i = 0; i < 15; i++){
                player.shoot((int)(attackDMG * 0.15), player.gp.tileSize, 8+(int)(i/2), Color.DARK_GRAY, 70);
            }
        }
        shotsAmount+=15;
        lastRay = currentTime;
    }
}

}


