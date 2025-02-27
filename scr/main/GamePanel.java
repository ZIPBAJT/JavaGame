package main;

//import piece.*;

import pieces.*;
import pieces.Pieces;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
//implements Runnable;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    public static ArrayList<Pieces> pieces = new ArrayList<>();
    public static ArrayList<Pieces> simPieces = new ArrayList<>();
    ArrayList<Pieces> promoPieces = new ArrayList<>();
    public static Pieces castlingP;
    Pieces activeP, checkingP;

    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    boolean canMove;
    boolean validSquare;
    boolean promotion;
    boolean gameOver;
    boolean stalemate;

    public GamePanel() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
//        testPromotion();
//      testIllegal();
        copyPieces(pieces, simPieces);
    }

    public void lauchGame() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void setPieces() {

        // WHITE TEAM
        pieces.add(new Pawn(0, 6, WHITE));
        pieces.add(new Pawn(1, 6, WHITE));
        pieces.add(new Pawn(2, 6, WHITE));
        pieces.add(new Pawn(3, 6, WHITE));
        pieces.add(new Pawn(4, 6, WHITE));
        pieces.add(new Pawn(5, 6, WHITE));
        pieces.add(new Pawn(6, 6, WHITE));
        pieces.add(new Pawn(7, 6, WHITE));
        pieces.add(new Rook(0, 7, WHITE));
        pieces.add(new Rook(7, 7, WHITE));
        pieces.add(new Knight(1, 7, WHITE));
        pieces.add(new Knight(6, 7, WHITE));
        pieces.add(new Bishop(2, 7, WHITE));
        pieces.add(new Bishop(5, 7, WHITE));
        pieces.add(new Queen(3, 7, WHITE));
        pieces.add(new King(4, 7, WHITE));

        // BLACK TEAM
        pieces.add(new Pawn(0, 1, BLACK));
        pieces.add(new Pawn(1, 1, BLACK));
        pieces.add(new Pawn(2, 1, BLACK));
        pieces.add(new Pawn(3, 1, BLACK));
        pieces.add(new Pawn(4, 1, BLACK));
        pieces.add(new Pawn(5, 1, BLACK));
        pieces.add(new Pawn(6, 1, BLACK));
        pieces.add(new Pawn(7, 1, BLACK));
        pieces.add(new Rook(0, 0, BLACK));
        pieces.add(new Rook(7, 0, BLACK));
        pieces.add(new Knight(1, 0, BLACK));
        pieces.add(new Knight(6, 0, BLACK));
        pieces.add(new Bishop(2, 0, BLACK));
        pieces.add(new Bishop(5, 0, BLACK));
        pieces.add(new Queen(3, 0, BLACK));
        pieces.add(new King(4, 0, BLACK));
    }

    private void copyPieces(ArrayList<Pieces> source, ArrayList<Pieces> target) {
        target.clear();
        for (int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
    }

    @Override
    public void run() {

        //Game loop
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        if (mouse.pressed) {
            if (activeP == null) {
                // If the activeP is Null, check if you can pick up a piece
                for (Pieces piece : simPieces) {
                    // if the mouse is on an all piece, pick it up as the activeP
                    if (piece.color == currentColor &&
                            piece.col == mouse.x / Board.SQUARE_SIZE &&
                            piece.row == mouse.y / Board.SQUARE_SIZE) {
                        activeP = piece;
                    }
                }
            } else {
                // if the player is holding a piece, simulate the move
                simulate();
            }

            }
        if (!mouse.pressed){

                if (activeP != null){

                    if (validSquare){
                        // Move confirmed

                        //Update the piece list in case a piece has been captured and removed during the simulation
                        copyPieces(simPieces, pieces);
                        activeP.updatePosition();

                    }else {
                        //The move is not valid so reset everything
                        copyPieces(pieces, simPieces);
                        activeP.resetPosition();
                        activeP = null;
                    }
                }
            }
        }




    private void simulate() {

        canMove = false;
        validSquare = false;

        copyPieces(pieces, simPieces);


        activeP.x = mouse.x - Board.HALF_SQUARE_SIZE;
        activeP.y = mouse.y - Board.HALF_SQUARE_SIZE;
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);


        if (activeP.canMove(activeP.col, activeP.row)) {

            canMove = true;


            if (activeP.hittingP != null) {
                simPieces.remove(activeP.hittingP.getIndex());
            }

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        board.draw(g2);

        for (Pieces p : simPieces) {
            p.draw(g2);
        }


        /*if (canMove) {
            g2.setColor(Color.white);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OUT, 0.7f));
            g2.fillRect(activeP.col*Board.SQUARE_SIZE, activeP.row*Board.SQUARE_SIZE,
                    Board.SQUARE_SIZE, Board.SQUARE_SIZE);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            activeP.draw(g2);
        }*/

}





}