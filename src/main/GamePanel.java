package main;

import entity.Player;
import entity.Projectile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    final int btileSize = 8;
    final int scale = 2;
    public final int tileSize = btileSize * scale;
    final int maxCol = 60;
    final int maxRow = 35;
    public final int width = tileSize * maxCol;
    public final int height = tileSize * maxRow;
    public KeyHandler con = new KeyHandler();
    public int healthpacks = 0;
    int FPS = 30;
    Thread thread;
    int p1x = 200;
    int p1y = 250;
    int p1size = tileSize * 3;
    int p1speed = 4;
    int p2x = 760;
    int p2y = 250;
    int p2size = tileSize * 3;
    int p2speed = 4;
    JFrame frame;
    Character Tank1 = new Tank(4, 200, 200, "Tank", 45, Color.red, 0.5);
    Character Ranger1 = new Ranger(5, 100, 100, "Ranger", 20, Color.red);
    Character Vampire1 = new Vampire(5, 100, 100, "Vampire", 35, Color.red, 10);
    Character DarkKnight1 = new NightKnight(5, 150, 150, "Dark Knight", 40, Color.red);
    Character Rogue1 = new Rogue(7, 80, 80, "Rogue", 10, Color.red, 3);
    Character Engineer1 = new Engineer(5, 100, 100, "Engineer", 5, Color.red);
    Character Tank2 = new Tank(4, 200, 200, "Tank", 30, Color.blue, 0.5);
    Character Ranger2 = new Ranger(5, 100, 100, "Ranger", 20, Color.blue);
    Character Vampire2 = new Vampire(5, 100, 100, "Vampire", 35, Color.blue, 10);
    Character DarkKnight2 = new NightKnight(5, 150, 150, "Dark Knight", 40, Color.blue);
    Character Rogue2 = new Rogue(7, 80, 80, "Rogue", 10, Color.blue, 3);
    Character Engineer2 = new Engineer(5, 100, 100, "Engineer", 5, Color.blue);
    Player p1 = new Player(p1x, p1y, p1speed, this, con, p1size, 1);
    Player p2 = new Player(p2x, p2y, p2speed, this, con, p2size, 2);

    private int playerNumber = 1; // Track which player is selecting their character
    private boolean characterSelectionComplete = false;

    public GamePanel(JFrame frame) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(getFocusTraversalKeysEnabled());
        this.addKeyListener(con);
        this.setFocusable(true);
        this.frame = frame;

        // Start character selection
        showCharacterSelection();
    }

    private void showCharacterSelection() {
        this.setLayout(new GridLayout(3, 2)); // Grid layout for buttons
        addCharacterButton("Tank", Tank1, Tank2);
        addCharacterButton("Ranger", Ranger1, Ranger2);
        addCharacterButton("Vampire", Vampire1, Vampire2);
        addCharacterButton("Dark Knight", DarkKnight1, DarkKnight2);
        addCharacterButton("Rogue", Rogue1, Rogue2);
        addCharacterButton("Engineer", Engineer1, Engineer2);
    }

    private void addCharacterButton(String name, Character player1Character, Character player2Character) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerNumber == 1) {
                    p1.setCharacter(player1Character);
                    player1Character.setPlayer(p1);
                    playerNumber = 2;
                    System.out.println("Player 1 selected: " + name);
                } else if (playerNumber == 2) {
                    p2.setCharacter(player2Character);
                    player2Character.setPlayer(p2);
                    characterSelectionComplete = true;
                    System.out.println("Player 2 selected: " + name);
                }

                // Remove buttons after selection
                if (characterSelectionComplete) {
                    removeAll();
                    setLayout(null); // Reset layout for gameplay
                    revalidate();
                    repaint();
                    startGame();
                }
            }
        });
        this.add(button);
    }

    private void startGame() {
        System.out.println("Starting game...");
        thread = new Thread(this);
        thread.start();
        this.requestFocusInWindow();
    }

    public void run() {
        this.setFocusable(true);
        System.out.println("Game Started");
        double target = 1000000000 / FPS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while (thread != null) {
            now = System.nanoTime();
            delta += (now - lastTime) / target;
            lastTime = now;
            if (delta >= 1) {
                update();
                delta--;
            }
            repaint();
        }
    }

    public void update() {
        p1.update();
        p2.update();
        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile : p1.projectiles) {
            projectile.update();
            if (p1.checkProjectileCollision(projectile)) {
                p1.takeDamage(projectile.atkD);
                projectilesToRemove.add(projectile);
            }
            else if (p2.checkProjectileCollision(projectile) && !projectile.persistant) {
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
                    if (projectile.checkCollision(projectile2) && !projectile.persistant) {
                        projectilesToRemove.add(projectile2);
                    }
                }
            }
        }

        for (Projectile projectile : p2.projectiles) {
            projectile.update();
            if (p1.checkProjectileCollision(projectile) && !projectile.persistant) {
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
                    if (projectile.checkCollision(projectile2) && !projectile.persistant) {
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
        if(p1.c.getHP() <= 0 && p2.c.getHP() <= 0){
            System.out.println("Game Tie");
            System.out.println("Player 2 shots hit: " + p2.c.shotsHit);
            if(p2.c.shotsAmount == 0){
                System.out.println("Player 2 Accuracy: 0%");
            }
            else if(p2.c.shotsAmount < p2.c.shotsHit){
                System.out.println("Player 2 Accuracy: 100%");
            }
            else{
                System.out.println("Player 2 Accuracy: " + (int)(100*p2.c.shotsHit/p2.c.shotsAmount) + "%");
            }
            System.out.println("Player 1 shots hit: " + p1.c.shotsHit);
            if(p1.c.shotsAmount == 0){
                System.out.println("Player 1 Accuracy: 0%");
            }
            else if(p1.c.shotsAmount < p1.c.shotsHit){
                System.out.println("Player 1 Accuracy: 100%");
            }
            else{
                System.out.println("Player 1 Accuracy: " + (int)(100*p1.c.shotsHit/p1.c.shotsAmount) + "%");
            }
            System.exit(0);
        }
        else if(p1.c.getHP() <= 0){
            System.out.println("Player 2 wins!");
            System.out.println("Player 2 shots hit: " + p2.c.shotsHit);
            if(p2.c.shotsAmount == 0){
                System.out.println("Player 2 Accuracy: 0%");
            }
            else if(p2.c.shotsAmount < p2.c.shotsHit){
                System.out.println("Player 2 Accuracy: 100%");
            }
            else{
                System.out.println("Player 2 Accuracy: " + (int)(100*p2.c.shotsHit/p2.c.shotsAmount) + "%");
            }
            System.out.println("Player 1 shots hit: " + p1.c.shotsHit);
            if(p1.c.shotsAmount == 0){
                System.out.println("Player 1 Accuracy: 0%");
            }
            else if(p1.c.shotsAmount < p1.c.shotsHit){
                System.out.println("Player 1 Accuracy: 100%");
            }
            else{
                System.out.println("Player 1 Accuracy: " + (int)(100*p1.c.shotsHit/p1.c.shotsAmount) + "%");
            }
            System.exit(0);
            System.exit(0);
        }
        else if(p2.c.getHP() <= 0){
            System.out.println("Player 1 wins!");
            System.out.println("Player 2 shots hit: " + p2.c.shotsHit);
            if(p2.c.shotsAmount == 0){
                System.out.println("Player 2 Accuracy: 0%");
            }
            else if(p2.c.shotsAmount < p2.c.shotsHit){
                System.out.println("Player 2 Accuracy: 100%");
            }
            else{
                System.out.println("Player 2 Accuracy: " + (int)(100*p2.c.shotsHit/p2.c.shotsAmount) + "%");
            }
            System.out.println("Player 1 shots hit: " + p1.c.shotsHit);
            if(p1.c.shotsAmount == 0){
                System.out.println("Player 1 Accuracy: 0%");
            }
            else if(p1.c.shotsAmount < p1.c.shotsHit){
                System.out.println("Player 1 Accuracy: 100%");
            }
            else{
                System.out.println("Player 1 Accuracy: " + (int)(100*p1.c.shotsHit/p1.c.shotsAmount) + "%");
            }
            System.exit(0);
            System.exit(0);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (characterSelectionComplete) {
            Graphics2D p1g = (Graphics2D) g;
            Graphics2D p2g = (Graphics2D) g;
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
}