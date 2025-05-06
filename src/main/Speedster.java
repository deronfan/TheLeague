package main;
import java.awt.Color;
public class Speedster extends Character{

    private int normDMG;
    private int rageBonus;
    private long lastBoltTime;
    private long boltCooldown;
    private int hitsProcessed;
    private int rage;
    private Color norm;
    private int normSpeed;
    private long lastRageTime;
    private long rageCooldown;
    private int tick;

public Speedster(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.boltCooldown = 8000;
    rageBonus = 3;
    rageCooldown = 10000;
    normDMG = attackDMG;
    norm = color;
    normSpeed = movespeed;
    stunAmount = 70;
}

public int takeDamage(int damage){
    HP -= damage;
    if(rage>-1){
        HP -= damage;
    }
    if(HP > maxHP){
        HP = maxHP;
    }
    return HP;
}
public void attackOne(){ //seeking lightning bolt but low damage
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastBoltTime >= boltCooldown) {
        if(player.pID == 1){
            player.shoot(attackDMG, player.gp.tileSize, 3, Color.red, 300, true, player.gp.p2, player.x, player.y, true);
        }
        if(player.pID == 2){
            player.shoot(attackDMG, player.gp.tileSize, 3, Color.blue, 300, true, player.gp.p1, player.x, player.y, true);
        }
        shotsAmount++;
        lastBoltTime = currentTime;
    }

}


public void attackTwo(){  //increase speed hecka
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastRageTime >= rageCooldown) {
        rage = 60;
        attackDMG = normDMG + rageBonus;
        movespeed = (int)(normSpeed*2);
        lastRageTime = currentTime;
        if(player.pID==1){
            color = new Color(255, 200, 200);
        }
        else{
            color = new Color(200, 255, 200);
        }
        player.hasafterimage = true;
    }
}
public void update(){
    if(shotsAmount < shotsHit){
        shotsHit = shotsAmount;
    }
    if(Math.abs(player.gp.p1.x - player.gp.p2.x) <= player.gp.tileSize+3 && Math.abs(player.gp.p1.y - player.gp.p2.y) <= player.gp.tileSize+3){
        if(player.pID==1 && ((player.kh.aupPressed || player.kh.adownPressed || player.kh.aleftPressed || player.kh.arightPressed))){
            player.gp.p2.takeDamage(attackDMG);
        }
        else if(player.pID==2 && ((player.kh.bupPressed || player.kh.bdownPressed || player.kh.bleftPressed || player.kh.brightPressed))){
            player.gp.p1.takeDamage(attackDMG);
        }
    }
    stunnedCheck();
    tick++;
    if(rage > -1){
        rage--;
    }
    if(rage == 0){
        player.hasafterimage = false;
        attackDMG = normDMG;
        movespeed = normSpeed;
        color = norm;
    }
}
}


