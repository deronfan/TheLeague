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
        width = 640;
        height = 480;
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
        width = 640;
        height = 480;
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
        width = 640;
        height = 480;
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
        width = 640;
        height = 640;
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
            if(lt < startlt-20 && homespeed < 6*speed){
            startlt = lt;
            homespeed += 2; 
            }
            double angle = Math.atan2(target.y - y, target.x - x);
            x += homespeed * Math.cos(angle);
            y += homespeed * Math.sin(angle);
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
        g.fillOval(x, y, size, hsize);
    }

    public boolean checkCollision(Projectile other) {
        if (this == other || other.homing) {
            return false;
        }
        return Math.abs(this.x - other.x) < this.size+8 && Math.abs(this.y - other.y) < this.hsize+8;
    }

}
