package main;

import entity.Particle;
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
    boolean gameEnded = false;
    String endScreenMessage = "";
    public KeyHandler con = new KeyHandler();
    public int healthpacks = 0;
    public int FPS = 45;
    public Color backgroundColor = Color.gray;
    Thread thread;
    int p1x = 200;
    int p1y = 250;
    int p1size = tileSize * 3;
    int p1speed = 4;
    int p2x = 760;
    int p2y = 250;
    int p2size = tileSize * 3;
    int p2speed = 4;
    private ArrayList<Particle> particles = new ArrayList<>();
    JFrame frame;
    Character Tank1 = new Tank(4, 500, 500, "Tank", 45, Color.red, 0.5);
    Character Ranger1 = new Ranger(5, 200, 200, "Ranger", 20, Color.red);
    Character Vampire1 = new Vampire(5, 180, 180, "Vampire", 15, Color.red, 10);
    Character DarkKnight1 = new NightKnight(5, 300, 300, "Dark Knight", 40, Color.red);
    Character Rogue1 = new Rogue(7, 150, 150, "Rogue", 10, Color.red, 3);
    Character Pyro1 = new Pyro(5, 300, 300, "Pyro", 50, Color.red);
    Character Engineer1 = new Engineer(5, 180, 180, "Engineer", 5, Color.red);
    Character Speedster1 = new Speedster(10, 80, 80, "Speedster", 2, Color.red);
    Character Tank2 = new Tank(4, 500, 500, "Tank", 30, Color.blue, 0.5);
    Character Ranger2 = new Ranger(5, 200, 200, "Ranger", 20, Color.blue);
    Character Vampire2 = new Vampire(5, 180, 180, "Vampire", 15, Color.blue, 10);
    Character DarkKnight2 = new NightKnight(5, 300, 300, "Dark Knight", 40, Color.blue);
    Character Rogue2 = new Rogue(7, 150, 150, "Rogue", 10, Color.blue, 3);
    Character Engineer2 = new Engineer(5, 180, 180, "Engineer", 5, Color.blue);
    Character Pyro2 = new Pyro(5, 300, 300, "Pyro", 50, Color.blue);
    Character Speedster2 = new Speedster(10, 80, 80, "Speedster", 2, Color.blue);
    public Player p1 = new Player(p1x, p1y, p1speed, this, con, p1size, 1);
    public Player p2 = new Player(p2x, p2y, p2speed, this, con, p2size, 2);

    private int playerNumber = 1; // Track which player is selecting their character
    private boolean characterSelectionComplete = false;

    public GamePanel(JFrame frame) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(backgroundColor);
        this.setDoubleBuffered(getFocusTraversalKeysEnabled());
        this.addKeyListener(con);
        this.setFocusable(true);
        this.frame = frame;

        // Start character selection
        showCharacterSelection();
    }

    private void showCharacterSelection() {
        frame.setTitle("Player 1 Character Selection");
        this.setLayout(new GridLayout(3, 3)); // Grid layout for buttons
        addCharacterButton("Tank", Tank1, Tank2);
        addCharacterButton("Ranger", Ranger1, Ranger2);
        addCharacterButton("Vampire", Vampire1, Vampire2);
        addCharacterButton("Dark Knight", DarkKnight1, DarkKnight2);
        JButton Helpful = new JButton();
        Helpful.setText("<html>Player One uses WASD to move and Q and E for Primary and Secondary Abilities respectively.<br>Player Two uses IJKL to move and U and O for Primary and Secondary Abilities respectively.<br>Green healthpacks will randomly spawn that can be picked up by touching it.<br>You can hover over the character's button for a brief description of their abilities.</html>");
        this.add(Helpful);
        addCharacterButton("Rogue", Rogue1, Rogue2);
        addCharacterButton("Engineer", Engineer1, Engineer2);
        addCharacterButton("Pyro", Pyro1, Pyro2);
        addCharacterButton("Speedster", Speedster1, Speedster2);
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
                    frame.setTitle("Player 2 Character Selection");
                } else if (playerNumber == 2) {
                    p2.setCharacter(player2Character);
                    player2Character.setPlayer(p2);
                    characterSelectionComplete = true;
                    System.out.println("Player 2 selected: " + name);
                    frame.setTitle("");
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
        if(player1Character == Tank1 || player1Character == Tank2){
            button.setToolTipText("<html>Primary Ability: Claymore - Huge Slow Melee Attack in the direction you are facing<br>Secondary Ability: Block - Block all incoming damage for a short period of time</html>");
        }
        else if(player1Character == Ranger1 || player1Character == Ranger2){
            button.setToolTipText("<html>Primary Ability: Shoot - Shoot a projectile in the direction you are facing<br>Secondary Ability: Guided Bolt - Send forth a projectile that will follow the enemy</html>");
        }
        else if(player1Character == Vampire1 || player1Character == Vampire2){
            button.setToolTipText("<html>Primary Ability: Bite - Deal damage in an area around you and heal for a portion of the damage dealt<br>Secondary Ability: Bloodlust - Gain a burst of speed and increased and damage for a short period of time while slowing down time.</html>");
        }
        else if(player1Character == DarkKnight1 || player1Character == DarkKnight2){
            button.setToolTipText("<html>Primary Ability: Longsword - Fast Thin Melee Attack in the direction you are facing<br>Secondary Ability: Dark Blast - Shoot a lazer beam in the direction you are facing</html>");
        }
        else if(player1Character == Rogue1 || player1Character == Rogue2){
            button.setToolTipText("<html>Primary Ability: Dagger - Quickly throws a dagger that does chip damage and homes in on the enemy<br>Secondary Ability: Dodge Roll - Gain a burst of speed and become invulnerable for a short period of time</html>");
        }
        else if(player1Character == Engineer1 || player1Character == Engineer2){
            button.setToolTipText("<html>Primary Ability: Tazer - Shoot a projectile that will apply stacks of stun on an enemy<br>Secondary Ability: Turret - Place a turret that shoots at the enemy<br>Stun - Once enough stacks are applied, the enemy will be stunned for a short period of time</html>");
        }
        else if(player1Character == Pyro1 || player1Character == Pyro2){
            button.setToolTipText("<html>Primary Ability: Flame Thrower - Shoot a stream that deals chip damage<br>Secondary Ability: Flame Pool - Charge up for a second and release a pool of fire</html>");
        }
        else if(player1Character == Speedster1 || player1Character == Speedster2){
            button.setToolTipText("<html>Primary Ability: Lightning Bolt - Shoot a quick homing bolt that does small damage over time<br>Secondary Ability: Rush - Go really fast<br>Passive: Ram: Whenever you run into someone, they take damage</html>");
        }
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
            target = 1000000000 / FPS;
            delta += (now - lastTime) / target;
            lastTime = now;
            if (delta >= 1) {
                update();
                this.setBackground(backgroundColor);
                delta--;
            }
            repaint();
        }
    }

    public void update() {
        if(!gameEnded){
        p1.update();
        p2.update();
        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile : p1.projectiles) {
            projectile.update();
            if (p1.checkProjectileCollision(projectile)) {
                p1.takeDamage(projectile.atkD);
                if (!projectile.persistant) {
                    projectilesToRemove.add(projectile);
                }
                if (projectile.atkD > 0) {
                    p2.c.shotsHit++;
                    createParticles(p1.x + tileSize / 2, p1.y + tileSize / 2, p1.c.color);
                }
            }
            else if (p2.checkProjectileCollision(projectile)) {
                p2.takeDamage(projectile.atkD);
                if(!projectile.persistant) {
                    projectilesToRemove.add(projectile);
                }
                if(projectile.atkD > 0){
                    p1.c.shotsHit++;
                    createParticles(p2.x + tileSize / 2, p2.y + tileSize / 2, p2.c.color);
                    int seed = (int)(Math.random()*20);
                    if(seed == 0){
                        frame.setTitle("Player 1's aim is insane!");
                    }
                    else if(seed == 1){
                        frame.setTitle("Player 2 can not get a break today.");
                    }
                    else if(seed == 2){
                        frame.setTitle("Player 2 is throwing!");
                    }
                    else if(seed == 3){
                        frame.setTitle("Player 1 is going crazy.");
                    }
                }
            }
            else if (projectile.x < 0 || projectile.x > width || projectile.y < 0 || projectile.y > height|| projectile.lt <= 0) {
                if(projectile.hasOnDeath){
                    p1.shoot(20, tileSize, 5, Color.orange, 100);
                }
                projectilesToRemove.add(projectile);
            }
            else if (projectile.canBlock){
                for (Projectile projectile2 : p2.projectiles) {
                    if (projectile.checkCollision(projectile2)) {
                        projectilesToRemove.add(projectile2);
                        frame.setTitle("That was a CRAZY block by player 1!");
                    }
                }
            }
        }
        for (Projectile projectile : p2.projectiles) {
            projectile.update();
            if (p1.checkProjectileCollision(projectile)) {
                p1.takeDamage(projectile.atkD);
                if(!projectile.persistant){
                    projectilesToRemove.add(projectile);
                }
                if(projectile.atkD > 0){
                    p2.c.shotsHit++;
                    createParticles(p1.x + tileSize / 2, p1.y + tileSize / 2, p1.c.color);
                    int seed = (int)(Math.random()*20);
                    if(seed == 0){
                        frame.setTitle("Player 2 with the sick shot!");
                    }
                    else if(seed == 1){
                        frame.setTitle("And player 2 with the snipe");
                    }
                    else if(seed == 2){
                        frame.setTitle("Player 1 is throwing!");
                    }
                    else if(seed == 3){
                        frame.setTitle("Player 1 keeps eating his attacks!");
                    }
                }
            }
            else if (projectile.x < 0 || projectile.x > width || projectile.y < 0 || projectile.y > height || projectile.lt <= 0 ) {
                projectilesToRemove.add(projectile);
            }
            else if (projectile.canBlock){
                for (Projectile projectile2 : p1.projectiles) {
                    if (projectile.checkCollision(projectile2)) {
                        projectilesToRemove.add(projectile2);
                        frame.setTitle("That was a CRAZY block by player 2!");
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
        ArrayList<Particle> particlesToRemove = new ArrayList<>();
        for (Particle particle : particles) {
            particle.update();
            if (particle.lifetime <= 0) {
                particlesToRemove.add(particle);
        }
}
particles.removeAll(particlesToRemove);
        }
        if (p1.c.getHP() <= 0 && p2.c.getHP() <= 0) {
            frame.setTitle("and this is a tie!");
            endScreenMessage = "Game Tie\n" + getPlayerStats();
            gameEnded = true;
        } else if (p1.c.getHP() <= 0) {
            frame.setTitle("AND THE CROWD GOES WILD FOR PLAYER 2");
            endScreenMessage = "Player 2 Wins!\n" + getPlayerStats();
            gameEnded = true;
        } else if (p2.c.getHP() <= 0) {
            frame.setTitle("Another FREE win for player 1");
            endScreenMessage = "Player 1 Wins!\n" + getPlayerStats();
            gameEnded = true;
        }
        // if(p1.c.getHP() <= 0 && p2.c.getHP() <= 0){
        //     System.out.println("Game Tie");
        //     System.out.println("Player 2 shots hit: " + p2.c.shotsHit);
        //     if(p2.c.shotsAmount == 0){
        //         System.out.println("Player 2 Accuracy: 0%");
        //     }
        //     else if(p2.c.shotsAmount < p2.c.shotsHit){
        //         System.out.println("Player 2 Accuracy: 100%");
        //     }
        //     else{
        //         System.out.println("Player 2 Accuracy: " + (int)(100*p2.c.shotsHit/p2.c.shotsAmount) + "%");
        //     }
        //     System.out.println("Player 1 shots hit: " + p1.c.shotsHit);
        //     if(p1.c.shotsAmount == 0){
        //         System.out.println("Player 1 Accuracy: 0%");
        //     }
        //     else if(p1.c.shotsAmount < p1.c.shotsHit){
        //         System.out.println("Player 1 Accuracy: 100%");
        //     }
        //     else{
        //         System.out.println("Player 1 Accuracy: " + (int)(100*p1.c.shotsHit/p1.c.shotsAmount) + "%");
        //     }
        //     System.exit(0);
        // }
        // else if(p1.c.getHP() <= 0){
        //     System.out.println("Player 2 wins!");
        //     System.out.println("Player 2 shots hit: " + p2.c.shotsHit);
        //     if(p2.c.shotsAmount == 0){
        //         System.out.println("Player 2 Accuracy: 0%");
        //     }
        //     else if(p2.c.shotsAmount < p2.c.shotsHit){
        //         System.out.println("Player 2 Accuracy: 100%");
        //     }
        //     else{
        //         System.out.println("Player 2 Accuracy: " + (int)(100*p2.c.shotsHit/p2.c.shotsAmount) + "%");
        //     }
        //     System.out.println("Player 1 shots hit: " + p1.c.shotsHit);
        //     if(p1.c.shotsAmount == 0){
        //         System.out.println("Player 1 Accuracy: 0%");
        //     }
        //     else if(p1.c.shotsAmount < p1.c.shotsHit){
        //         System.out.println("Player 1 Accuracy: 100%");
        //     }
        //     else{
        //         System.out.println("Player 1 Accuracy: " + (int)(100*p1.c.shotsHit/p1.c.shotsAmount) + "%");
        //     }
        //     System.exit(0);
        // }
        // else if(p2.c.getHP() <= 0){
        //     System.out.println("Player 1 wins!");
        //     System.out.println("Player 2 shots hit: " + p2.c.shotsHit);
        //     if(p2.c.shotsAmount == 0){
        //         System.out.println("Player 2 Accuracy: 0%");
        //     }
        //     else if(p2.c.shotsAmount < p2.c.shotsHit){
        //         System.out.println("Player 2 Accuracy: 100%");
        //     }
        //     else{
        //         System.out.println("Player 2 Accuracy: " + (int)(100*p2.c.shotsHit/p2.c.shotsAmount) + "%");
        //     }
        //     System.out.println("Player 1 shots hit: " + p1.c.shotsHit);
        //     if(p1.c.shotsAmount == 0){
        //         System.out.println("Player 1 Accuracy: 0%");
        //     }
        //     else if(p1.c.shotsAmount < p1.c.shotsHit){
        //         System.out.println("Player 1 Accuracy: 100%");
        //     }
        //     else{
        //         System.out.println("Player 1 Accuracy: " + (int)(100*p1.c.shotsHit/p1.c.shotsAmount) + "%");
        //     }
        //     System.exit(0);
        // }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameEnded) {
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over", width / 2 - 100, height / 4);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            int y = height / 3;
            for (String line : endScreenMessage.split("\n")) {
                g.drawString(line, width / 4, y);
                y += 30;
            }
        } else if (characterSelectionComplete) {
            Graphics2D p1g = (Graphics2D) g;
            Graphics2D p2g = (Graphics2D) g;
            for (Particle particle : particles) {
                particle.draw((Graphics2D) g);
            }
            for (Projectile projectile : p1.projectiles) {
                projectile.draw(p1g);
            }
            for (Projectile projectile : p2.projectiles) {
                projectile.draw(p2g);
            }
            p1.draw(p1g);
            p2.draw(p2g);
            p1g.setColor(Color.BLACK);
            p1g.setFont(new Font("Arial", Font.BOLD, 14));
            p1g.drawString("P1 HP: " + p1.c.getHP() + "/" + p1.c.getMaxHP(), 40, height - 40);
            p1g.drawString("P2 HP: " + p2.c.getHP() + "/" + p2.c.getMaxHP(), 800, height - 40);
            p1g.dispose();
            p2g.dispose();

        }
    }
    private String getPlayerStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("Player 1 Stats:\n");
        stats.append("Final HP: ").append(p1.c.HP).append("\n");
        stats.append("Shots Hit: ").append(p1.c.shotsHit).append("\n");
        stats.append("Shots Fired: ").append(p1.c.shotsAmount).append("\n");
        stats.append("Accuracy: ").append(p1.c.shotsAmount == 0 ? "0%" : (int) (100.0 * p1.c.shotsHit / p1.c.shotsAmount) + "%").append("\n\n");
        stats.append("Player 2 Stats:\n");
        stats.append("Final HP: ").append(p2.c.HP).append("\n");
        stats.append("Shots Hit: ").append(p2.c.shotsHit).append("\n");
        stats.append("Shots Fired: ").append(p2.c.shotsAmount).append("\n");
        stats.append("Accuracy: ").append(p2.c.shotsAmount == 0 ? "0%" : (int) (100.0 * p2.c.shotsHit / p2.c.shotsAmount) + "%").append("\n");
        return stats.toString();
    }
    public void setFPS(int FPS) {
        this.FPS = FPS;
    }
    private void createParticles(int x, int y, Color color) {
        for (int i = 0; i < 10; i++) { 
            int dx = (int) (Math.random() * 6 - 3); // Random x direction
            int dy = (int) (Math.random() * 6 - 3); // Random y direction
            int size = (int) (Math.random() * 4 + 2); 
            int lifetime = (int) (Math.random() * 20 + 10);
            particles.add(new Particle(x, y, dx, dy, size, lifetime, new Color(color.getRed(), color.getGreen(), color.getBlue(), 50)));
        }
    }
}