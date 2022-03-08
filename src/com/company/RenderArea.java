package com.company;

import utils.ImgFetcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RenderArea extends JComponent {
    private Board board;
    private int TILE_SIZE = 100;
    private int PEICE_SIZE = 70;
    private Point selectedSquare = null;


    public RenderArea(Board board){
        this.board = board;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point mousePosition = new Point(e.getX(),e.getY());
                Point onBoard = new Point((int)((e.getX() / TILE_SIZE)), (int)((e.getY() / TILE_SIZE)));
                System.out.println("På board = " + onBoard);
                //System.out.println("Pjäs = " + board.getSquaresOnPoint(onBoard).getPieceOnSquare().getClass());
                if (board.getSquaresOnPoint(onBoard).getPieceOnSquare() != null){
                    setSelectedSquare(onBoard);
                    System.out.println(board.getSquaresOnPoint(onBoard).getPieceOnSquare().validMoves());
                } else {
                    setSelectedSquare(null); //FIXME REMOVE LATER
                }
                repaint();
            }
        });
    }

    public void setSelectedSquare(Point selectedSquare) {
        this.selectedSquare = selectedSquare;
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImgFetcher fetcher = new ImgFetcher();
        super.paintComponent(g);
        System.out.println("In Paint");
        final Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                // background tile color
                BoardSquare current = board.getSquares(x, y);
                g2d.setColor(current.getColour());
                g2d.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                if (selectedSquare != null) {
                    if (x == selectedSquare.x && y == selectedSquare.y) {
                        g2d.setColor(new Color(77, 255, 77));
                        g2d.fillRect(x * TILE_SIZE + 10, y * TILE_SIZE + 10, TILE_SIZE - 20, TILE_SIZE - 20);
                    }
                }

                // peices
                if (current.getPieceOnSquare() != null) {
                    //System.out.println(current.getPieceOnSquare().getColor());
                    if(current.getPieceOnSquare().getColor() == Color.WHITE) {
                        //g2d.setColor(new Color(181, 181, 181));
                        //g2d.fillRect(x * TILE_SIZE + 15, y * TILE_SIZE  + 15, PEICE_SIZE, PEICE_SIZE);
                    } else if (current.getPieceOnSquare().getColor() == Color.BLACK) {
                       // g2d.setColor(new Color(61, 61, 61));
                       // g2d.fillRect(x * TILE_SIZE + 15, y * TILE_SIZE  + 15, PEICE_SIZE, PEICE_SIZE);
                    }
                    g2d.scale(0.05,0.05);
                    g2d.drawImage(fetcher.fetchImage(current.getPieceOnSquare().fileName), x*TILE_SIZE*20, y*TILE_SIZE*20, null);
                    g2d.scale(20,20);
                }
            }
        }

    }


    @Override public Dimension getPreferredSize(){
        //Placeholder size
        return new Dimension(board.getWidth()*TILE_SIZE,board.getWidth()*TILE_SIZE);
    }

}
