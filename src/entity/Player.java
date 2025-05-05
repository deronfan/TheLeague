package entity;
import main.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import main.KeyHandler;
import main.Character;
public class Player extends Entity{
    public GamePanel gp;
    public KeyHandler kh;
    public Character c;
    public int pID;
    public boolean isAlive = true;
    public boolean hasafterimage = false;
    public int pastX;
    public int pastY;
    public int pastX2;
    public int pastY2;
    public int pastX3;
    public int pastY3;
    public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public Player(int x, int y, int speed, GamePanel gp, KeyHandler kh, int size, int pID){
        super(x, y, speed);
        this.gp = gp;
        this.kh = kh;
        this.size = gp.tileSize;
        this.pID = pID;
        if(pID == 1){
            direction = "right";
        }
        if(pID == 2){
            direction = "left";
        }
    }
    public void update(){
        pastX3 = pastX2;
        pastY3 = pastY2;
        pastX2 = pastX;
        pastY2 = pastY;
        pastX = x;
        pastY = y;
        if(speed != c.getMovespeed()){
            speed = c.getMovespeed();
        }
        if(pID==1 && isAlive==true){
            //System.out.println("Player " + pID + " is Updating");
            if(kh.aupPressed == true){
                y -= speed;
                direction = "up";
            }
            if(kh.adownPressed == true){
                y += speed;
                direction = "down";
            }
            if(kh.aleftPressed == true){
                x -= speed;
                direction = "left";
            }
            if(kh.arightPressed == true){
                x += speed;
                direction = "right";
            }
            if(kh.aattackOnePressed == true){
                c.attackOne(); 
            }
            if(kh.aattackTwoPressed == true){
                c.attackTwo();
            }
        }
        if(pID==2 && isAlive==true){
            //System.out.println("Player " + pID + " is Updating");
            if(kh.bupPressed == true){
                y -= speed;
                direction = "up";
            }
            if(kh.bdownPressed == true){
                y += speed;
                direction = "down";
            }
            if(kh.bleftPressed == true){
                x -= speed;
                direction = "left";
            }
            if(kh.brightPressed == true){
                x += speed;
                direction = "right";
            }
            if(kh.battackOnePressed == true){
                c.attackOne();
            }
            if(kh.battackTwoPressed == true){
                c.attackTwo();
            }
        }
        if(x > gp.width-gp.tileSize){
            x = 0;
        }
        if(y > gp.height-gp.tileSize){
            y = 0;
        }
        if(x < 0){
            x = gp.width-gp.tileSize;
        }
        if(y < 0){
            y = gp.height-gp.tileSize;
        }
        if(c.getHP() <= 0){
            isAlive = false;
        }
        if(pID==1){
            gp.healthpacks++;
            if(gp.healthpacks >= 450){
                gp.healthpacks = 0;
                projectiles.add(new Projectile((int)(Math.random()*920+20), (int)(Math.random()*520+20), 0, direction, gp.tileSize*2, -50, Color.green, 1600));
            }
        }
    }
    public void draw(Graphics2D g){
        if(isAlive==true){
        g.setColor(c.color);
        g.fillRect(x, y, gp.tileSize, gp.tileSize);
        int rectWidth = gp.tileSize / 2; 
        int rectHeight = gp.tileSize / 2;
        int rectX = x;
        int rectY = y;
        if (hasafterimage) {
            g.setColor(new Color(c.color.getRed(), c.color.getGreen(), c.color.getBlue(), 100)); // Faint version of the player's color
            g.fillRect(pastX, pastY, gp.tileSize, gp.tileSize);
            g.fillRect(pastX2, pastY2, gp.tileSize, gp.tileSize);
            g.fillRect(pastX3, pastY3, gp.tileSize, gp.tileSize);
        }
        switch (direction) {
            case "up":
                rectX = x + gp.tileSize / 4;
                rectY = y - rectHeight; 
                break;
            case "down":
                rectX = x + gp.tileSize / 4;
                rectY = y + gp.tileSize;
                break;
            case "left":
                rectX = x - rectWidth;
                rectY = y + gp.tileSize / 4;
                break;
            case "right":
                rectX = x + gp.tileSize;
                rectY = y + gp.tileSize / 4;
                break;
        }
        if (pID == 1) {
            g.setColor(Color.orange);
        } else if (pID == 2) {
            g.setColor(Color.cyan);
        } else {
            g.setColor(Color.black);
        }
        g.fillRect(rectX, rectY, rectWidth, rectHeight);
        }    
        else{
            g.setColor(Color.black);
        }
    }
    
    public boolean checkCollision(Player p){
        if(Math.abs(x - p.x) < gp.tileSize && Math.abs(y - p.y) < gp.tileSize){
            return true;
        }
        return false;
    }
    public void setCharacter(Character c){
        this.c = c;
        speed = c.getMovespeed();
    }
    public void push(Player p){
        if(pID==1){
            if(kh.aupPressed == true){
                p.y -= speed;
            }
            if(kh.adownPressed == true){
                p.y += speed;
            }
            if(kh.aleftPressed == true){
                p.x -= speed;
            }
            if(kh.arightPressed == true){
                p.x += speed;
            }
        }
        if(pID==2){
            if(kh.bupPressed == true){
                p.y -= speed;
            }
            if(kh.bdownPressed == true){
                p.y += speed;
            }
            if(kh.bleftPressed == true){
                p.x -= speed;
            }
            if(kh.brightPressed == true){
                p.x += speed;
            }
        }
        if(!kh.brightPressed && !kh.bleftPressed && !kh.bdownPressed && !kh.bupPressed && !kh.arightPressed && !kh.aleftPressed && !kh.adownPressed && !kh.aupPressed){
            p.x += Math.random() * 10 - 5;
            p.y += Math.random() * 10 - 5;
        }
    }
    public void shoot(int damage, int size, int speed, Color color, int lt) {
        projectiles.add(new Projectile(x, y, speed, direction, size, damage, color, lt));
    }
    public void shoot(int damage, int size, int speed, Color color, int lt, boolean homing, Player target) {
        projectiles.add(new Projectile(x, y, speed, direction, size, damage, color, lt, homing, target));
    }
    public void shoot(int damage, int size, int speed, Color color, int lt, boolean homing, Player target, int x, int y, boolean persistant) {
        projectiles.add(new Projectile(x, y, speed, "", size, damage, color, lt, homing, target, persistant));
    }
    public void melee(int x, int y, int damage, int vsize, int hsize, int speed, Color color, int lt) {
        projectiles.add(new Projectile(x, y, speed, direction, vsize, hsize, damage, color, lt));
    }
    public void melee(int x, int y, int damage, int vsize, int hsize, int speed, Color color, int lt, boolean canBlock) {
        projectiles.add(new Projectile(x, y, speed, direction, vsize, hsize, damage, color, lt, canBlock));
    }
    public void shoot(int damage, int vsize, int hsize, int speed, Color color, int lt, boolean hasOnDeath, Player target) {
        projectiles.add(new Projectile(x, y, speed, direction, vsize, hsize, damage, color, lt, hasOnDeath, target));
    }
    public void advmelee(int damage, int vsize, int hsize, int speed, Color color, int lt, boolean canBlock, int length, int space) {
        if (direction.equals("up")) {
            for (int i = 0; i < length; i++) {
                projectiles.add(new Projectile(x, y-space*i, speed, direction, vsize, hsize, damage, color, lt, canBlock));
            }
        }
        if (direction.equals("down")) {
            for (int i = 0; i < length; i++) {
                projectiles.add(new Projectile(x, y+space*i, speed, direction, vsize, hsize, damage, color, lt, canBlock));
            }
        }
        if (direction.equals("left")) {
            for (int i = 0; i < length; i++) {
                projectiles.add(new Projectile(x-space*i, y, speed, direction, vsize, hsize, damage, color, lt, canBlock));
            }
        }
        if (direction.equals("right")) {
            for (int i = 0; i < length; i++) {
                projectiles.add(new Projectile(x+space*i, y, speed, direction, vsize, hsize, damage, color, lt, canBlock));
            }
        }
    }
    
    public boolean checkProjectileCollision(Projectile projectile) {
        boolean friendlyFire = true;
        for(Projectile x: projectiles){
            if( projectile.atkD > 0 && x == projectile){
                friendlyFire = false;
            }
        }
        return friendlyFire && ((this.x <= projectile.x + projectile.hsize && this.x >= projectile.x && this.y <= projectile.y + projectile.size && this.y >= projectile.y) || (this.x+size <= projectile.x + projectile.hsize && this.x+size >= projectile.x && this.y <= projectile.y + projectile.size && this.y >= projectile.y) || (this.x <= projectile.x + projectile.hsize && this.x >= projectile.x && this.y+size <= projectile.y + projectile.size && this.y+size >= projectile.y) || (this.x+size <= projectile.x + projectile.hsize && this.x+size >= projectile.x && this.y+size <= projectile.y + projectile.size && this.y+size >= projectile.y));
    }
    public void takeDamage(int damage) {
        if (c.takeDamage(damage) <= 0) {
            isAlive = false;
        }
    }
}
