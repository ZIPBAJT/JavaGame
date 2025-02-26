package main;

//import piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//implements Runnable;

public class GamePanel extends JPanel implements Runnable{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    public GamePanel() {

        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.black);
        //addMouseMotionListener(mouse);
        //addMouseListener(mouse);

//        setPieces();
//        testPromotion();
//      testIllegal();
//        copyPieces(pieces, simPieces);
    }

    public void lauchGame() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

    }
    public void update() {

    }
    public void paintComponent(Graphics2D g2){
        super.paintComponent(g2);
    }




}