
import java.awt.Color;

import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Map");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.white);
        GamePanel gp = new GamePanel();
        frame.add(gp);
        frame.pack();
        gp.start();
    }
}

