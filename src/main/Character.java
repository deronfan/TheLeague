package main;
import entity.Player;
import java.awt.Color;
public class Character{
protected Player player;
protected int movespeed;
protected int maxHP;
public int HP;
protected String name;
public int attackDMG;
public Color color;
public int shotsAmount;
public int shotsHit;

public Character(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color){
    this.movespeed = movespeed;
    this.maxHP = maxHP;
    this.HP = HP;
    this.name = name;
    this.attackDMG = attackDMG;
    this.color = color;

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
}
}