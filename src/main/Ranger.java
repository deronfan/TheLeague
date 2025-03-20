package main;
public class Ranger extends Character{

private int range;
public Ranger(int player, int movespeed, int maxHP, int HP, String name, int attackDMG, int range){
    super(player, movespeed, maxHP, HP, name, attackDMG);
    this.range = range;
}
public void Shoot(){
    System.out.println("pew");
}
public void wall(){
    System.out.println("Great Wall");
}

}