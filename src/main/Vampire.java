package main;
import java.awt.Color;
public class Vampire extends Character{
    
private int leechnum;
private int range;
private long lastShootTime;
private long shootCooldown;

public Vampire(int movespeed, int maxHP, int HP, String name, int attackDMG, Color color, int range, int leachnum){
    super(movespeed, maxHP, HP, name, attackDMG, color);
    this.leechnum = 10;
    this.range = range;
    this.shootCooldown = 300;
    this.lastShootTime = 0;
}
public void attackOne(){ //leech
    System.out.println("leech!");
}

public void attackTwo(){ 
    System.out.println("stealth");
}
}


