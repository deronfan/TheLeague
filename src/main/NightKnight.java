package main;
public class NightKnight extends Character{
    
private int attackRange;

public NightKnight(int player, int movespeed, int maxHP, int HP, String name, int attackDMG, int attackRange){
    super(player, movespeed, maxHP, HP, name, attackDMG);
    this.attackRange = attackRange;
}
public void darkStab(){
    System.out.println("Stab");
}
public void moonRay(){
    System.out.println("voom");
}

}