package main;
public class Vampire extends Character{
    
private int leachnum;

public Vampire(int player, int movespeed, int maxHP, int HP, String name, int attackDMG, int leachnum){
    super(player, movespeed, maxHP, HP, name, attackDMG);
    this.leachnum = leachnum;
}
public void leech(){
    System.out.println("leech!");
}

public void stealth(){
    System.out.println("stealth");
}
}