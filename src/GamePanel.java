import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class GamePanel extends JPanel implements Runnable {
    final int btileSize = 8;
    final int scale = 2;
    final int tileSize = btileSize * scale;
    final int maxCol = 40;
    final int maxRow = 30;
    final int width = tileSize * maxCol;
    final int height = tileSize * maxRow;
    int FPS = 60;
    Thread thread;
    Controller con = new Controller();
    int p1x = 100;
    int p1y = 100;
    int p1speed = 4;
    int p2x = 500;
    int p2y = 100;
    int p2speed = 4;
    public GamePanel() {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(getFocusTraversalKeysEnabled());
        this.addKeyListener(con);
        this.setFocusable(true);
    }
    public void start() {
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
        if(con.aupPressed == true){
            p1y -= p1speed;
        }
        if(con.adownPressed == true){
            p1y += p1speed;
        }
        if(con.aleftPressed == true){
            p1x -= p1speed;
        }
        if(con.arightPressed == true){
            p1x += p1speed;
        }
        if(con.bupPressed == true){
            p2y -= p2speed;
        }
        if(con.bdownPressed == true){
            p2y += p2speed;
        }
        if(con.bleftPressed == true){
            p2x -= p2speed;
        }
        if(con.brightPressed == true){
            p2x += p2speed;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D p1 = (Graphics2D)g;
        p1.setColor(Color.blue);
        p1.fillRect(p1x, p1y, tileSize, tileSize);
        Graphics2D p2 = (Graphics2D)g;
        p2.setColor(Color.red);
        p2.fillRect(p2x, p2y, tileSize, tileSize);
        p1.dispose();
        p2.dispose();
    }   
}