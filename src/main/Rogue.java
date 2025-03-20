package main;
public class Rogue extends Character{
    
private int rollNum;

public Rogue(int player, int movespeed, int maxHP, int HP, String name, int attackDMG, int rollNum){
    super(player, movespeed, maxHP, HP, name, attackDMG);
    this.rollNum = rollNum;
}
public void roll(){
    System.out.println("roooool!");
}
public void stab(){
    System.out.println("stabs");
}
}