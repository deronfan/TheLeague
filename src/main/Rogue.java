package main;
public class Rogue extends Character{
    
private int rollNum;
private boolean immune;

public Rogue(int movespeed, int maxHP, int HP, String name, int attackDMG, int rollNum){
    super(movespeed, maxHP, HP, name, attackDMG);
    this.rollNum = rollNum;
    immune = false;
}
public int takeDamage(int damage){
    if(immune){
        return HP;
    }
    HP -= damage;
    return HP;
}
public void attackOne(){ //stab
    System.out.println("stabs");
}
public void attackTwo(){ //roll
    System.out.println("rolls");
}
}