package entity;
import java.awt.Color;
import java.awt.Graphics2D;
public class Particle {
    public int x, y;
    public int dx, dy; // Direction of movement
    public int size; 
    public int lifetime;
    public Color color; 

    public Particle(int x, int y, int dx, int dy, int size, int lifetime, Color color) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.size = size;
        this.lifetime = lifetime;
        this.color = color;
    }

    public void update() {
        x += dx;
        y += dy;
        lifetime--;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
}