package entity;
import java.awt.Color;
import java.awt.Graphics2D;

public class Projectile extends Entity{
    public int atkD;
    public Color color;
    public int lt;
    public int width;
    public int height;
    public int hsize;
    public boolean homing;
    public Player target;
    private int startlt;
    private int homespeed;
    private int prevXDirection;
    private int prevYDirection;
    private int prevX2Direction;
    private int prevY2Direction;
    private int prevX3Direction;
    private int prevY3Direction;
    private int prevX4Direction;
    private int prevY4Direction;
    public boolean persistant;
    public boolean hasOnDeath;
    public boolean rectangular;
    public Projectile(int x, int y, int speed, String direction, int size, int atkD, Color color, int lt){
        super(x, y, speed);
        this.direction = direction;
        this.size = size;
        this.hsize = size;
        this.atkD = atkD;
        this.color = color;
        this.lt = lt;
        startlt = lt;
        homing = false;
        canBlock = false;
        width = 960;
        height = 560;
    }
    public Projectile(int x, int y, int speed, String direction, int size, int atkD, Color color, int lt, boolean homing, Player target, boolean persistant){
        super(x, y, speed);
        this.direction = direction;
        this.size = size;
        this.hsize = size;
        this.atkD = atkD;
        this.color = color;
        this.lt = lt;
        startlt = lt;
        this.homing = homing;
        this.homespeed = speed;
        this.target = target;
        this.persistant = persistant;
        canBlock = false;
        width = 960;
        height = 560;
    }
    public Projectile(int x, int y, int speed, String direction, int size, int atkD, Color color, int lt, boolean homing, Player target){
        super(x, y, speed);
        this.direction = direction;
        this.size = size;
        this.hsize = size;
        this.atkD = atkD;
        this.color = color;
        this.lt = lt;
        startlt = lt;
        this.homing = homing;
        this.homespeed = speed;
        this.target = target;
        canBlock = false;
        width = 960;
        height = 560;
    }
    public Projectile(int x, int y, int speed, String direction, int vsize, int hsize, int atkD, Color color, int lt){
        super(x, y, speed);
        this.direction = direction;
        this.size = vsize;
        this.hsize = hsize;
        startlt = lt;
        this.atkD = atkD;
        this.color = color;
        this.lt = lt;
        homing = false;
        canBlock = false;
        width = 960;
        height = 560;
    }
    public Projectile(int x, int y, int speed, String direction, int vsize, int hsize, int atkD, Color color, int lt, boolean hasOnDeath, Player target){
        super(x, y, speed);
        this.direction = direction;
        this.size = vsize;
        this.hsize = hsize;
        startlt = lt;
        this.atkD = atkD;
        this.color = color;
        this.lt = lt;
        this.hasOnDeath = hasOnDeath;
        this.target = target;
        width = 960;
        height = 560;
    }
    public Projectile(int x, int y, int speed, String direction, int vsize, int hsize, int atkD, Color color, int lt, boolean canBlock){
        super(x, y, speed);
        this.direction = direction;
        this.size = vsize;
        this.hsize = hsize;
        startlt = lt;
        this.atkD = atkD;
        this.color = color;
        this.lt = lt;
        this.canBlock = canBlock;
        homing = false;
        width = 960;
        height = 560;
    }
    public void update() {
            switch (direction) {
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "left":
                x -= speed;
                break;
            case "right":
                x += speed;
                break;
            }
        if(homing) {
            if (target != null) {
            if(lt < startlt-20 && (homespeed < 6*speed || speed == 0 && homespeed < 4)){
            startlt = lt;
            homespeed += 2; 
            }
            double angle = Math.atan2(target.y - y, target.x - x);
            prevX4Direction = prevX3Direction;
            prevY4Direction = prevY3Direction;
            prevX3Direction = prevX2Direction;
            prevY3Direction = prevY2Direction;
            prevX2Direction = prevXDirection;
            prevY2Direction = prevYDirection;
            prevXDirection = (int)(homespeed * Math.cos(angle));
            prevYDirection = (int)(homespeed * Math.sin(angle));
            x += prevX4Direction;
            y += prevY4Direction;
            }
        }
        if(x > width-16){
            x = 0;
        }
        if(y > height-16){
            y = 0;
        }
        if(x < 0){
            x = width-16;
        }
        if(y < 0){
            y = height-16;
        }
        lt--;
    }
    

    public void draw(Graphics2D g) {
        g.setColor(color);
        if(rectangular){
            g.fillRect(x, y, size, hsize);
        }
        else{
            g.fillOval(x, y, size, hsize);
        }
    }

    public boolean checkCollision(Projectile other) {
        if (this == other) {
            return false;
        }
        return this.x < other.x + other.size && this.x + this.size > other.x && this.y < other.y + other.hsize && this.y + this.hsize > other.y;
    }
}
