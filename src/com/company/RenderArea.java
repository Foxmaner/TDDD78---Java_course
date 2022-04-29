package com.company;

import pieces.GamePiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RenderArea extends JComponent {
    private Board board;
    private final int TILE_SIZE = 100;
    private final int SELECTED_PIECE_PADDING = 10;
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
                                GamePiece selectedPiece = board.getPieceOnSqaureOnPoint(selectedSquare);
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
                    if (onBoard != null && board.getPieceOnSqaureOnPoint(onBoard) != null) {

                        if (board.getMoveCounter() % 2 == 0 && board.getPieceOnSqaureOnPoint(onBoard).getColor() == Color.WHITE) {
                            setSelectedSquare(onBoard);
                            validMoves = board.validMovesFilter(board.getPieceOnSqaureOnPoint(onBoard));
                        } else if (board.getMoveCounter() % 2 == 1 && board.getPieceOnSqaureOnPoint(onBoard).getColor() == Color.BLACK) {
                            setSelectedSquare(onBoard);
                            validMoves = board.validMovesFilter(board.getPieceOnSqaureOnPoint(onBoard));
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
                        g2d.fillRect(x * TILE_SIZE + SELECTED_PIECE_PADDING, y * TILE_SIZE + SELECTED_PIECE_PADDING, TILE_SIZE - SELECTED_PIECE_PADDING*2, TILE_SIZE - SELECTED_PIECE_PADDING*2);
                    }
                }
            }
        }

        if (validMoves != null) {
            for (int i = 0; i < validMoves.length; i++) {
                g2d.setColor(new Color(255, 0, 0));
                g2d.fillRect(validMoves[i].x * TILE_SIZE + SELECTED_PIECE_PADDING, validMoves[i].y * TILE_SIZE + SELECTED_PIECE_PADDING, TILE_SIZE - SELECTED_PIECE_PADDING*2, TILE_SIZE - SELECTED_PIECE_PADDING*2);
            }
        }

        // peices
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                BoardSquare current = board.getSquares(x, y);
                if (current.getPieceOnSquare() != null) {
                    g2d.drawImage(current.getPieceOnSquare().getPicture(), x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
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
