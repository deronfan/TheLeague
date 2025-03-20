package entity;
import entity.Projectile;
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
    public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public Player(int x, int y, int speed, GamePanel gp, KeyHandler kh, int size, int pID){
        super(x, y, speed);
        this.gp = gp;
        this.kh = kh;
        this.size = size;
        this.pID = pID;
        if(pID == 1){
            direction = "right";
        }
        if(pID == 2){
            direction = "left";
        }
    }
    public void update(){
        if(pID==1 && isAlive==true){
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
                shoot();
            }
            if(kh.aattackTwoPressed == true){
                shoot();
            }
        }
        if(pID==2 && isAlive==true){
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
                shoot();
            }
            if(kh.battackTwoPressed == true){
                shoot();
            }
        }
        if(x > gp.width-gp.tileSize){
            x = gp.width-gp.tileSize;
        }
        if(y > gp.height-gp.tileSize){
            y = gp.height-gp.tileSize;
        }
        if(x < 0){
            x = 0;
        }
        if(y < 0){
            y = 0;
        }
        if(c.getHP() <= 0){
            isAlive = false;
        }
    }
    public void draw(Graphics2D g){
        if(isAlive==true){
        if(pID==1){
            g.setColor(Color.BLUE);
        }
        if(pID==2){
            g.setColor(Color.RED);
        }
        g.fillRect(x, y, gp.tileSize, gp.tileSize);
        
        }
        else{
            g.setColor(Color.gray);
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
            if(kh.arightPressed == false && kh.aleftPressed == false && kh.adownPressed == false && kh.aupPressed == false){
                c.HP -= 1;
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
            if(kh.brightPressed == false && kh.bleftPressed == false && kh.bdownPressed == false && kh.bupPressed == false){
                c.HP -= 1;
            }
        }
        if(!kh.brightPressed && !kh.bleftPressed && !kh.bdownPressed && !kh.bupPressed && !kh.arightPressed && !kh.aleftPressed && !kh.adownPressed && !kh.aupPressed){
            p.x += Math.random() * 10 - 5;
            p.y += Math.random() * 10 - 5;
        }
    }
    public void shoot() {
        projectiles.add(new Projectile(x, y, 8, direction, gp.tileSize, c.attackDMG));
    }
    public boolean checkProjectileCollision(Projectile projectile) {
        boolean friendlyFire = true;
        for(Projectile x: projectiles){
            if(x == projectile){
                friendlyFire = false;
            }
        }
        return Math.abs(x - projectile.x) < projectile.size && Math.abs(y - projectile.y) < projectile.size && friendlyFire;
    }
    public void takeDamage(int damage) {
        if (c.takeDamage(damage) <= 0) {
            isAlive = false;
        }
    }
}
