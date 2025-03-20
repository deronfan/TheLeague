package main;
public class Tank extends Character{
    
private double defense;
private int maxShield;
private int shield;

public Tank(int player, int movespeed, int maxHP, int HP, String name, int attackDMG, double defense, int maxShield, int shield){
    super(player, movespeed, maxHP, HP, name, attackDMG);
    this.defense = defense;
    this.maxShield = maxShield;
    this.shield = shield;
}
public int takeDamage(int damage){
    HP -= (int)(damage*defense);
    return HP;
}

public void attackOne(){ //Stab
    System.out.println("im mad");
}

public void headbutt(){ //Block
    System.out.println("rest");
}

}