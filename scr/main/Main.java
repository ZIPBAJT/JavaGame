package main;

import javax.swing.*;
import java.util.Objects;

public class Main {


    public static void main(String[] args) {

        JFrame window = new JFrame("Chess Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);


        //Add Main.Main.GamePanel to the window
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.lauchGame();
    }
}