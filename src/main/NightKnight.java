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
            player.advmelee(attackDMG/2, player.gp.tileSize, player.gp.tileSize, 3, Color.darkGray,10, true, 4, 2);
        }
        if(player.pID == 2){
            player.advmelee(attackDMG/2, player.gp.tileSize, player.gp.tileSize, 3, Color.lightGray, 10, true, 4, 2);
        }
        lastShootTime = currentTime;
        shotsAmount++;
    }
}
public void attackTwo(){ //dark ray
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastRay >= rayCooldown) {
        if(player.pID == 1){
            for(int i = 0; i < 10; i++){
                player.shoot((int) (attackDMG * 0.15), player.gp.tileSize, 5+i, Color.ORANGE, 40);
            }
        }
        if(player.pID == 2){
            for(int i = 0; i < 10; i++){
                player.shoot((int)(attackDMG * 0.15), player.gp.tileSize, 5+i, Color.DARK_GRAY, 40);
            }
        }
        shotsAmount++;
        lastRay = currentTime;
    }
}

}


