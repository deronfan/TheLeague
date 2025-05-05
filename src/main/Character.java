package main;
import entity.Player;
import java.awt.Color;
public class Character{
protected Player player;
public int movespeed;
public int basespeed;
protected int maxHP;
public int HP;
protected String name;
public int attackDMG;
public Color color;
public int shotsAmount;
public int shotsHit;
public boolean isStunned;
public int stunTime;
public int stunAmount;

public Character(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color){
    this.movespeed = movespeed;
    this.maxHP = maxHP;
    this.HP = HP;
    this.name = name;
    this.attackDMG = attackDMG;
    this.color = color;
    basespeed = movespeed;
    stunTime = 0;
    stunAmount = 45;

}
public void setPlayer(Player player){
    this.player = player;
}
public int takeDamage(int damage){
    HP -= damage;
    if(HP > maxHP){
        HP = maxHP;
    }
    return HP;
}

public int getMovespeed(){
    return movespeed;
}

public int getHP(){
    return HP;
}
public int getMaxHP(){
    return maxHP;
}
public void attackOne(){
System.out.println("Attack Uno");
}
public void attackTwo(){
    System.out.println("Attack Dos");
}
public void update(){
    stunnedCheck();
}
public void stunnedCheck(){
    if(isStunned){
        movespeed = 0;
        if(stunTime > 0){
            stunTime--;
            if(player.pID == 1){
                color = Color.orange;
            }
            else if(player.pID == 2){
                color = Color.cyan;
            }
        }
        if(stunTime == 0){
            isStunned = false;
            movespeed = basespeed;
            if(player.pID == 1){
                color = Color.red;
            }
            else if(player.pID == 2){
                color = Color.blue;
            }
        }
    }
}
public void stun(){
    isStunned = true;
    stunTime = stunAmount;
}
public void stun(int x){
    isStunned = true;
    stunTime = x;
}
}