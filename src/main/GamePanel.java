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
    public KeyHandler con = new KeyHandler();
    public int healthpacks = 0;
    int FPS = 30;
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
    Character Tank1 = new Tank(4, 200, 200, "Tank", 45, Color.red, 0.5);
    Character Ranger1 = new Ranger(5, 100, 100, "Ranger", 20, Color.red);
    Character Vampire1 = new Vampire(5, 100, 100, "Vampire", 35, Color.red, 10);
    Character DarkKnight1 = new NightKnight(5, 150, 150, "Dark Knight", 40, Color.red);
    Character Tank2 = new Tank(4, 200, 200, "Tank", 30, Color.blue, 0.5);
    Character Ranger2 = new Ranger(5, 100, 100, "Ranger", 20,Color.blue);
    Character Vampire2 = new Vampire(5, 100, 100, "Vampire", 35, Color.blue, 10);
    Character DarkKnight2 = new NightKnight(5, 150, 150, "Dark Knight", 40, Color.blue);
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
            System.out.println("Player 1, choose your character (Tank/Ranger/Vampire/Dark Knight): ");
            String pChoice = scanner.nextLine();
            if (pChoice.equalsIgnoreCase("Tank")) {
                p1.setCharacter(Tank1);
                Tank1.setPlayer(p1);
                PlayerDeciding = 2;
            } 
            else if (pChoice.equalsIgnoreCase("Ranger")) {
                p1.setCharacter(Ranger1);
                Ranger1.setPlayer(p1);
                PlayerDeciding = 2;
            }
            else if (pChoice.equalsIgnoreCase("Vampire")) {
                p1.setCharacter(Vampire1);
                Vampire1.setPlayer(p1);
                PlayerDeciding = 2;
            }
            else if (pChoice.equalsIgnoreCase("Dark Knight")) {
                p1.setCharacter(DarkKnight1);
                DarkKnight1.setPlayer(p1);
                PlayerDeciding = 2;
            }
        }
        while(PlayerDeciding==2){
            System.out.println("Player 2, choose your character (Tank/Ranger/Vampire/Dark Knight): ");
            String pChoice = scanner.nextLine();
            if (pChoice.equalsIgnoreCase("Tank")) {
                p2.setCharacter(Tank2);
                Tank2.setPlayer(p2);
                PlayerDeciding = -1;
            } 
            else if (pChoice.equalsIgnoreCase("Ranger")) {
                p2.setCharacter(Ranger2);
                Ranger2.setPlayer(p2);
                PlayerDeciding = -1;
            }
            else if (pChoice.equalsIgnoreCase("Vampire")) {
                p2.setCharacter(Vampire2);
                Vampire2.setPlayer(p2);
                PlayerDeciding = -1;
            }
            else if (pChoice.equalsIgnoreCase("Dark Knight")) {
                p2.setCharacter(DarkKnight2);
                DarkKnight2.setPlayer(p2);
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
            if (p1.checkProjectileCollision(projectile)) {
                p1.takeDamage(projectile.atkD);
                projectilesToRemove.add(projectile);
            }
            else if (p2.checkProjectileCollision(projectile)) {
                p2.takeDamage(projectile.atkD);
                projectilesToRemove.add(projectile);
                if(projectile.atkD > 0){
                    p1.c.shotsHit++;
                }
            }
            else if (projectile.x < 0 || projectile.x > width || projectile.y < 0 || projectile.y > height|| projectile.lt <= 0) {
                projectilesToRemove.add(projectile);
            }
            else if (projectile.canBlock){
                for (Projectile projectile2 : p2.projectiles) {
                    if (projectile.checkCollision(projectile2)) {
                        projectilesToRemove.add(projectile2);
                    }
                }
            }
        }

        for (Projectile projectile : p2.projectiles) {
            projectile.update();
            if (p1.checkProjectileCollision(projectile)) {
                p1.takeDamage(projectile.atkD);
                projectilesToRemove.add(projectile);
                if(projectile.atkD > 0){
                    p2.c.shotsHit++;
                }
            }
            else if (projectile.x < 0 || projectile.x > width || projectile.y < 0 || projectile.y > height || projectile.lt <= 0 ) {
                projectilesToRemove.add(projectile);
            }
            else if (projectile.canBlock){
                for (Projectile projectile2 : p1.projectiles) {
                    if (projectile.checkCollision(projectile2)) {
                        projectilesToRemove.add(projectile2);
                    }
                }
            }
        }

        p1.projectiles.removeAll(projectilesToRemove);
        p2.projectiles.removeAll(projectilesToRemove);

        if(p1.checkCollision(p2) && p2.checkCollision(p1)){
            p1.push(p2);
            p2.push(p1);
        }
        p1.c.update();
        p2.c.update();
        frame.setTitle("P1 HP:" + p1.c.getHP() + " P2 HP:" + p2.c.getHP());
        if(p1.c.getHP() <= 0){
            System.out.println("Player 2 wins!");
            System.out.println("Player 2 shots hit: " + p2.c.shotsHit);
            System.out.println("Player 2 Accuracy: " + (int)(100*p2.c.shotsHit/p2.c.shotsAmount) + "%");
            System.out.println("Player 1 shots hit: " + p1.c.shotsHit);
            System.out.println("Player 1 Accuracy: " + (int)(100*p1.c.shotsHit/p1.c.shotsAmount) + "%");
            System.exit(0);
        }
        if(p2.c.getHP() <= 0){
            System.out.println("Player 1 wins!");
            System.out.println("Player 2 shots hit: " + p2.c.shotsHit);
            System.out.println("Player 2 Accuracy: " + (int)(100*p2.c.shotsHit/p2.c.shotsAmount) + "%");
            System.out.println("Player 1 shots hit: " + p1.c.shotsHit);
            System.out.println("Player 1 Accuracy: " + (int)(100*p1.c.shotsHit/p1.c.shotsAmount) + "%");
            System.exit(0);
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D p1g = (Graphics2D)g;
        Graphics2D p2g = (Graphics2D)g;
        for (Projectile projectile : p1.projectiles) {
            projectile.draw(p1g);
        }
        for (Projectile projectile : p2.projectiles) {
            projectile.draw(p2g);
        }
        p1.draw(p1g);
        p2.draw(p2g);
        p1g.dispose();
        p2g.dispose();
    }   
}