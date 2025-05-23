package main;
import java.awt.Color;

public class Engineer extends Character{
private long lastShootTime;
private long shootCooldown;
private long lastTurret;
private long turretCooldown;
private int hitsProcessed;
private int turretX;
private int turretY;
private int turretLT;
private int turretLS;

public Engineer(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.shootCooldown = 250;
    this.lastShootTime = 0;
    this.turretCooldown = 20000;
    this.lastTurret = 0;
    turretLS = 900;
    stunAmount = 30;
}
public void attackOne(){ //taser
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastShootTime >= shootCooldown) {
        if(player.pID == 1){
            player.shoot(attackDMG*3, player.gp.tileSize, 7, Color.orange, 10, true, player.gp.p2);
        }
        if(player.pID == 2){
            player.shoot(attackDMG*3, player.gp.tileSize, 7, Color.cyan, 10, true, player.gp.p1);
        }
        lastShootTime = currentTime;
        shotsAmount++;
        }
    }
public void attackTwo(){ //turret
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastTurret >= turretCooldown) {
        turretX = player.x;
        turretY = player.y;
        turretLT = turretLS;
        lastTurret = currentTime;
    }
}
public void update(){
    stunnedCheck();
    if(hitsProcessed < shotsHit){
        if(hitsProcessed%4 == 0){
            if(player.pID == 1){
                player.gp.p2.c.stun();
            }
            if(player.pID == 2){
                player.gp.p1.c.stun();
            }
        }
        hitsProcessed++;
    }
    if(turretLT == turretLS){
        if(player.pID == 1){
            player.shoot(10, player.gp.tileSize*3, 0, Color.orange, turretLS, false, player.gp.p2, turretX-player.gp.tileSize, turretY-player.gp.tileSize, true);
        }
        if(player.pID == 2){
            player.shoot(10, player.gp.tileSize*3, 0, Color.cyan, turretLS, false, player.gp.p1, turretX-player.gp.tileSize, turretY-player.gp.tileSize, true);
        }
    }
    if(turretLT > 0){
        turretLT--;
        if(player.pID == 1 && turretLT%60 == 0){
            player.shoot(5, player.gp.tileSize, 0, Color.orange, 120, true, player.gp.p2, turretX+5, turretY, false);
            player.shoot(5, player.gp.tileSize, 0, Color.orange, 120, true, player.gp.p2, turretX-5, turretY, false);
            player.shoot(5, player.gp.tileSize, 0, Color.orange, 120, true, player.gp.p2, turretX, turretY+5, false);
            player.shoot(5, player.gp.tileSize, 0, Color.orange, 120, true, player.gp.p2, turretX, turretY-5, false);
        }
        if(player.pID == 2 && turretLT%60 == 0){
            player.shoot(5, player.gp.tileSize, 0, Color.cyan, 120, true, player.gp.p1, turretX+5, turretY, false);
            player.shoot(5, player.gp.tileSize, 0, Color.cyan, 120, true, player.gp.p1, turretX-5, turretY, false);
            player.shoot(5, player.gp.tileSize, 0, Color.cyan, 120, true, player.gp.p1, turretX, turretY+5, false);
            player.shoot(5, player.gp.tileSize, 0, Color.cyan, 120, true, player.gp.p1, turretX, turretY-5, false);
        }
        shotsAmount++;
    }
}
}
    

