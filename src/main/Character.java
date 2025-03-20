package main;
public class Character{
protected int player;
protected int movespeed;
protected int maxHP;
public int HP;
protected String name;
public int attackDMG;

public Character(int player, int movespeed, int maxHP, int HP, String name, int attackDMG){
    this.player = player;
    this.movespeed = movespeed;
    this.maxHP = maxHP;
    this.HP = HP;
    this.name = name;
    this.attackDMG = attackDMG;
}
public int takeDamage(int damage){
    HP -= damage;
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

}