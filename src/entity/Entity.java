package entity;
public class Entity{
    public int x;
    public int y;
    public int speed;
    public String direction;
    public int size;
    public boolean canBlock;
    public Entity(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
    public void canBlock(boolean canBlock){
        this.canBlock = canBlock;
    }
    public void update(){
        System.out.println("Entity Update");
        //IF THIS IS CALLED THEN SOMETHING IS WRONG
    }
}