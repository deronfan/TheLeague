package main;

import java.awt.Color;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        //JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        //frame.setTitle("Game");
        //frame.setLocationRelativeTo(null);
        //frame.setVisible(true);
        //frame.setBackground(Color.white);
        //GamePanel gamePanel = new GamePanel(frame);
        //CharacterSelectionPanel selectionPanel = new CharacterSelectionPanel(gamePanel, 1);
        //frame.add(selectionPanel);
        //frame.pack();
        //frame.setVisible(true);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Game");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.white);
        GamePanel gp = new GamePanel(frame);
        frame.add(gp);
        frame.pack();
        System.out.println("Welcome to the League");
        System.out.println("You have the option between 6 seperate characters");
        System.out.println("All 6 characters have their own unique abilities");
        System.out.println("Player One uses WASD to move and Q and E for Primary and Secondary Abilities respectively");
        System.out.println("Player Two uses IJKL to move and U and O for Primary and Secondary Abilities respectively");
        System.out.println("Open up the public port and enjoy the game");
        }
}

