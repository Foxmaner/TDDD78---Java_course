package com.company;

import pieces.GamePiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RenderArea extends JComponent {
    private Board board;
    private final int TILE_SIZE = 100;
    private final int PEICE_SIZE = 70;
    private Point[] validMoves = null;
    private Point selectedSquare = null;
    private GameInfo gameInfo;


    public RenderArea(Board board, GameInfo gameInfo){
        this.board = board;
        this.gameInfo = gameInfo;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point mousePosition = new Point(e.getX(), e.getY());
                Point onBoard = new Point((int) ((e.getX() / TILE_SIZE)), (int) ((e.getY() / TILE_SIZE)));
                System.out.println("Clicked on square = " + onBoard);
                if (!board.getGameOver()) {
                    if (validMoves != null) {
                        for (int i = 0; i < validMoves.length; i++) {
                            if (validMoves[i].x == onBoard.x && validMoves[i].y == onBoard.y) {
                                GamePiece selectedPiece = board.getSquaresOnPoint(selectedSquare).getPieceOnSquare();
                                System.out.println("moving " + selectedPiece.getClass() + " to " + onBoard.toString());
                                board.movePeice(selectedPiece, onBoard);
                                validMoves = null;
                                selectedSquare = null;
                                onBoard = null;
                                break;
                            }
                        }
                        validMoves = null;
                    }
                    if (onBoard != null && board.getSquaresOnPoint(onBoard).getPieceOnSquare() != null) {

                        if (board.getMoveCounter() % 2 == 0 && board.getSquaresOnPoint(onBoard).getPieceOnSquare().getColor() == Color.WHITE) {
                            setSelectedSquare(onBoard);
                            validMoves = board.validMovesFilter(board.getSquaresOnPoint(onBoard).getPieceOnSquare());
                        } else if (board.getMoveCounter() % 2 == 1 && board.getSquaresOnPoint(onBoard).getPieceOnSquare().getColor() == Color.BLACK) {
                            setSelectedSquare(onBoard);
                            validMoves = board.validMovesFilter(board.getSquaresOnPoint(onBoard).getPieceOnSquare());
                        }
                    } else {
                        setSelectedSquare(null); //FIXME REMOVE LATER
                    }
                    repaint();
                }
            }
        });
    }

    public void setSelectedSquare(Point selectedSquare) {
        this.selectedSquare = selectedSquare;
    }
    /*
    public Point[] validMoves(Point[] allValidPoints){
    return [];
    }
    */
    @Override
    protected void paintComponent(Graphics g) {
        //ImgFetcher fetcher = new ImgFetcher();
        super.paintComponent(g);
        //System.out.println("In Paint");
        final Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        // background tile color and selected square
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                BoardSquare current = board.getSquares(x, y);
                g2d.setColor(current.getColour());
                g2d.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                if (selectedSquare != null) {
                    if (x == selectedSquare.x && y == selectedSquare.y) {
                        g2d.setColor(new Color(77, 255, 77));
                        g2d.fillRect(x * TILE_SIZE + 10, y * TILE_SIZE + 10, TILE_SIZE - 20, TILE_SIZE - 20);
                    }
                }
            }
        }

        if (validMoves != null) {
            for (int i = 0; i < validMoves.length; i++) {
                g2d.setColor(new Color(255, 0, 0));
                g2d.fillRect(validMoves[i].x * TILE_SIZE + 10, validMoves[i].y * TILE_SIZE + 10, TILE_SIZE - 20, TILE_SIZE - 20);
            }
        }

        // peices
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                BoardSquare current = board.getSquares(x, y);


                if (current.getPieceOnSquare() != null) {
                    /* LEGACY NO IMAGES
                    if(current.getPieceOnSquare().getColor() == Color.WHITE) {
                        g2d.setColor(new Color(181, 181, 181));
                        g2d.fillRect(x * TILE_SIZE + 15, y * TILE_SIZE  + 15, PEICE_SIZE, PEICE_SIZE);
                    } else if (current.getPieceOnSquare().getColor() == Color.BLACK) {
                        g2d.setColor(new Color(61, 61, 61));
                        g2d.fillRect(x * TILE_SIZE + 15, y * TILE_SIZE  + 15, PEICE_SIZE, PEICE_SIZE);
                    }
                    */
                    g2d.scale(0.05,0.05);
                    g2d.drawImage(current.getPieceOnSquare().getPicture(), x*TILE_SIZE*20, y*TILE_SIZE*20, null);
                    g2d.scale(20,20);
                }
            }
        }
        gameInfo.updateInfo();
    }


    @Override public Dimension getPreferredSize(){
        //Placeholder size
        return new Dimension(board.getWidth()*TILE_SIZE,board.getWidth()*TILE_SIZE);
    }

}
