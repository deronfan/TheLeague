package main;
public class Mage extends Character{
private int mana;
private int maxMana;
public Mage(int player, int movespeed, int maxHP, int HP, String name, int attackDMG, int mana, int maxMana){
    super(player, movespeed, maxHP, HP, name, attackDMG);
    this.mana = mana;
    this.maxMana = maxMana;
}
public void randomFunnyBusiness(){
    System.out.println("avada");
}

public void explosion(){
    System.out.println("kedavra");
}

}