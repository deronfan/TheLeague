package main;
import entity.Player;
import entity.Projectile;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Scanner;
public class GamePanel extends JPanel implements Runnable {
    final int btileSize = 8;
    final int scale = 2;
    public final int tileSize = btileSize * scale;
    final int maxCol = 40;
    final int maxRow = 30;
    public final int width = tileSize * maxCol;
    public final int height = tileSize * maxRow;
    KeyHandler con = new KeyHandler();
    int FPS = 60;
    Thread thread;
    int p1x = 100;
    int p1y = 100;
    int p1size = tileSize*3;
    int p1speed = 4;
    int p2x = 500;
    int p2y = 100;
    int p2size = tileSize*3;
    int p2speed = 4;
    JFrame frame;
    Character Tank1 = new Tank(1, 4, 200, 200, "Tank", 1, 0.5, 50, 50);
    Character Ranger1 = new Ranger(2, 5, 100, 100, "Ranger", 2, 5);
    Character Tank2 = new Tank(1, 4, 200, 200, "Tank", 1, 0.5, 50, 50);
    Character Ranger2 = new Ranger(2, 5, 100, 100, "Ranger", 2, 5);
    Player p1 = new Player(p1x, p1y, p1speed, this, con, p1size, 1);
    Player p2 = new Player(p2x, p2y, p2speed, this, con, p2size, 2);
    public GamePanel(JFrame frame){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(getFocusTraversalKeysEnabled());
        this.addKeyListener(con);
        this.setFocusable(true);
        this.frame = frame;
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int PlayerDeciding = 1;
        while(PlayerDeciding==1){
            System.out.println("Player 1, choose your character (Tank/Ranger): ");
            String pChoice = scanner.nextLine();
            if (pChoice.equalsIgnoreCase("Tank")) {
                p1.setCharacter(Tank1);
                PlayerDeciding = 2;
            } 
            else if (pChoice.equalsIgnoreCase("Ranger")) {
                p1.setCharacter(Ranger1);
                PlayerDeciding = 2;
            }
        }
        while(PlayerDeciding==2){
            System.out.println("Player 2, choose your character (Tank/Ranger): ");
            String pChoice = scanner.nextLine();
            if (pChoice.equalsIgnoreCase("Tank")) {
                p2.setCharacter(Tank2);
                PlayerDeciding = -1;
            } 
            else if (pChoice.equalsIgnoreCase("Ranger")) {
                p2.setCharacter(Ranger2);
                PlayerDeciding = -1;
            }
        }
        scanner.close();
        thread = new Thread(this);
        thread.start();
    }
    public void run(){
        double target = 1000000000 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while(thread != null){
            now = System.nanoTime();
            delta += (now - lastTime) / target;
            lastTime = now;
            if(delta >= 1){
                update();
                delta--;
            }
            repaint();
        }
    }
    public void update(){
        p1.update();
        p2.update();
        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();

        for (Projectile projectile : p1.projectiles) {
            projectile.update();
            if (p2.checkProjectileCollision(projectile)) {
                p2.takeDamage(projectile.atkD);
                projectilesToRemove.add(projectile);
            }
        }

        for (Projectile projectile : p2.projectiles) {
            projectile.update();
            if (p1.checkProjectileCollision(projectile)) {
                p1.takeDamage(projectile.atkD);
                projectilesToRemove.add(projectile);
            }
        }

        p1.projectiles.removeAll(projectilesToRemove);
        p2.projectiles.removeAll(projectilesToRemove);

        if(p1.checkCollision(p2) && p2.checkCollision(p1)){
            p1.push(p2);
            p2.push(p1);
        }
        frame.setTitle("P1 HP:" + p1.c.getHP() + " P2 HP:" + p2.c.getHP());
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D p1g = (Graphics2D)g;
        Graphics2D p2g = (Graphics2D)g;
        p1.draw(p1g);
        p2.draw(p2g);
        for (Projectile projectile : p1.projectiles) {
            projectile.draw(p1g);
        }
        for (Projectile projectile : p2.projectiles) {
            projectile.draw(p2g);
        }
        p1g.dispose();
        p2g.dispose();
    }   
}