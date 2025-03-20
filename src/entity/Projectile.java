package entity;
import java.awt.Color;
import java.awt.Graphics2D;

public class Projectile extends Entity{
    public int atkD;
    public Projectile(int x, int y, int speed, String direction, int size, int atkD){
        super(x, y, speed);
        this.direction = direction;
        this.size = size;
        this.atkD = atkD;
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
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, size, size);
    }
}
