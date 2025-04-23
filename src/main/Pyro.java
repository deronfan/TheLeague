package main;
import java.awt.Color;

public class Pyro extends Character{
private long lastShootTime;
private long shootCooldown;
private long lastWall;
private long wallCooldown;
private int boom;
private Color normColor;
private int normSpeed;
public Pyro(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.shootCooldown = 40;
    this.lastShootTime = 0;
    this.wallCooldown = 5000;
    this.lastWall = 0;
    normColor = color;
    normSpeed = movespeed;
}
public void attackOne(){ //shoot
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastShootTime >= shootCooldown) {
        if(player.pID == 1){
            player.shoot(attackDMG/10, player.gp.tileSize, 6, Color.orange, 30);
        }
        if(player.pID == 2){
            player.shoot(attackDMG/10, player.gp.tileSize, 6, Color.cyan, 30);
        }
        lastShootTime = currentTime;
        shotsAmount++;
    }
}
public void attackTwo(){
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastWall >= wallCooldown) {
        boom = 120;
        if (player.pID == 1) {
            color = new Color(130, 0, 0); // deep shade of red   
        }
        else{
            color = new Color(0, 0, 130); // deep shade of blue
        }
        movespeed--;
        lastWall = currentTime;
    }
}
public void update(){
    if(boom > 0){
        boom--;
    }
    if(boom == 1){
        color = normColor;
        movespeed = normSpeed;
        if(player.pID == 1){
            player.shoot(2, player.gp.tileSize*5, 0, Color.orange, 1000, false, player.gp.p2, player.x, player.y, true);
        }
        if(player.pID == 2){
            player.shoot(2, player.gp.tileSize*5, 0, Color.cyan, 1000, false, player.gp.p1, player.x, player.y, true);
        }
    }
}
}