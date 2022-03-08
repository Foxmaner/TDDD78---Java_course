package com.company;

import pieces.*;

import java.awt.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private BoardSquare[][] squares;
    private int width;
    private int height;


    public Board(int width, int height) {
        this.squares = new BoardSquare[width][height];
        this.width = squares.length;
        this.height = squares[0].length;
        //Generate boardSquares with colours
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if(((y % 2) + (x % 2)) % 2 == 0){
                    squares[y][x] = new BoardSquare(Color.WHITE);
                }else{
                    squares[y][x] = new BoardSquare(Color.BLACK);
                }
            }
        }
        // Generate pawns
        for (int x = 0; x < getWidth(); x++) {
            Pawn pBlack = new Pawn(Color.BLACK, x, 1);
            squares[1][x].setPieceOnSquare(pBlack);
            Pawn pWhite = new Pawn(Color.WHITE, x, 6);
            squares[getHeight()-2][x].setPieceOnSquare(pWhite);
        }
        // Generate Rooks
        for (int x = 0; x < getWidth(); x += 7) {
            Rook rBlack = new Rook(Color.BLACK, x, 0);
            squares[0][x].setPieceOnSquare(rBlack);
            Rook rWhite = new Rook(Color.WHITE, x, 7);
            squares[getHeight()-1][x].setPieceOnSquare(rWhite);
        }

        // Generate Queen
        Queen qBlack = new Queen(Color.BLACK, 3, 0);
        squares[0][3].setPieceOnSquare(qBlack);

        Queen qWhite = new Queen(Color.WHITE, 3, 7);
        squares[getHeight()-1][3].setPieceOnSquare(qWhite);

        //Generate Bishop
        for (int x = 2; x < getWidth()-2; x += 3) {
            Bishop bBlack = new Bishop(Color.BLACK, x, 0);
            squares[0][x].setPieceOnSquare(bBlack);
            Bishop bWhite = new Bishop(Color.WHITE, x, 7);
            squares[getHeight()-1][x].setPieceOnSquare(bWhite);
        }

        // Generate Knight
        for (int x = 1; x < getWidth()-1; x += 5) {
            Knight kBlack = new Knight(Color.BLACK, x, 0);
            squares[0][x].setPieceOnSquare(kBlack);
            Knight kWhite = new Knight(Color.WHITE, x, 7);
            squares[getHeight()-1][x].setPieceOnSquare(kWhite);
        }

        // Generate King
        King kBlack = new King(Color.BLACK, 4, 0);
        squares[0][4].setPieceOnSquare(kBlack);

        King kWhite = new King(Color.WHITE, 4, 7);
        squares[getHeight()-1][4].setPieceOnSquare(kWhite);

    }

    public Point[] validMovesFilter(GamePiece p){

        System.out.println("number of moves in: " + p.validMoves().length);
        ArrayList<Point> validMoves = new ArrayList(List.of(p.validMoves()));

        //Moves are not valid if out of bounds
        validMoves.removeIf(s -> s.getX() < 0);
        validMoves.removeIf(s -> s.getX() > 7);
        validMoves.removeIf(s -> s.getY() < 0);
        validMoves.removeIf(s -> s.getY() > 7);

        //Moves are not valid if there is a friendly piece on position
        validMoves.removeIf(s -> getSquaresOnPoint(s).getPieceOnSquare() != null && getSquaresOnPoint(s).getPieceOnSquare().getColor() == p.getColor());

        //Knights can jump over friendly, so we can return now
        if (p.getClass() == Knight.class) {
            Point[] returnArray = validMoves.toArray(new Point[0]);
            return returnArray;
        }

        //All other pieces are not allowed to jump over other pieces
        //Specific for Pawn
        if (p.getClass() == Pawn.class) {
            if (((Pawn) p).getFirstMove()) {
                if (getSquares(p.getPos().x, p.getPos().y + ((Pawn) p).getDirection()).getPieceOnSquare() != null) {
                    System.out.println("here");
                    if (((Pawn) p).getDirection() == 1){ //if is black
                        System.out.println("black");
                        validMoves.removeIf(s -> s.getY() == p.getPos().y + ((Pawn) p).getDirection() * 2);
                    } else { // else (white)
                        System.out.println("white");
                        validMoves.removeIf(s -> s.getY() == p.getPos().y + ((Pawn) p).getDirection() * 2);
                    }
                }
            }
        }
        //Specific for Rook
        if (p.getClass() == Rook.class) {
            //positive x
            for (int x = p.getPos().x; x < 8; x++) {
                if (getSquares(x, p.getPos().y).getPieceOnSquare() != null) {
                    int finalX = x + 1;
                    validMoves.removeIf(s -> s.getX() > finalX);
                    break;
                }
            }
            //negative x
            for (int x = p.getPos().x; x >= 0; x--) {
                if (getSquares(x, p.getPos().y).getPieceOnSquare() != null) {
                    int finalX = x - 1;
                    validMoves.removeIf(s -> s.getX() < finalX);
                    break;
                }
            }
            //positive y
            for (int y = p.getPos().y; y < 8; y++) {
                if (getSquares(p.getPos().x, y).getPieceOnSquare() != null) {
                    int finalY = y + 1;
                    validMoves.removeIf(s -> s.getY() > finalY);
                    break;
                }
            }
            //negative y
            for (int y = p.getPos().y; y >= 0; y--) {
                if (getSquares(p.getPos().x, y).getPieceOnSquare() != null) {
                    int finalY = y - 1;
                    validMoves.removeIf(s -> s.getY() < finalY);
                    break;
                }
            }
        }


        //Specific for QUEEEN
        if (p.getClass() == Queen.class) {
            //straights
            //positive x (right)
            for (int x = p.getPos().x; x < 8; x++) {
                if (getSquares(x, p.getPos().y).getPieceOnSquare() != null) {
                    int finalX = x + 1;
                    validMoves.removeIf(s -> s.getX() > finalX && s.getY() == p.getPos().y);
                    break;
                }
            }
            //negative x (left)
            for (int x = p.getPos().x; x >= 0; x--) {
                if (getSquares(x, p.getPos().y).getPieceOnSquare() != null) {
                    int finalX = x - 1;
                    validMoves.removeIf(s -> s.getX() < finalX && s.getY() == p.getPos().y);
                    break;
                }
            }
            //positive y (down)
            for (int y = p.getPos().y; y < 8; y++) {
                if (getSquares(p.getPos().x, y).getPieceOnSquare() != null) {
                    int finalY = y + 1;
                    validMoves.removeIf(s -> s.getY() > finalY && s.getX() == p.getPos().x);
                    break;
                }
            }
            //negative y (up)
            for (int y = p.getPos().y; y >= 0; y--) {
                if (getSquares(p.getPos().x, y).getPieceOnSquare() != null) {
                    int finalY = y - 1;
                    validMoves.removeIf(s -> s.getY() < finalY && s.getX() == p.getPos().x);
                    break;
                }
            }
            //down right
            for (int k = 1; k < 7; k++) {
                if (p.getPos().x + k > 7 || p.getPos().y + k > 7) {
                    break;
                }
                if (getSquares(p.getPos().x + k, p.getPos().y + k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() > p.getPos().x + finalK && s.getY() > p.getPos().y + finalK);
                    break;
                }
            }
            //down left
            for (int k = 1; k < 7; k++) {
                if (p.getPos().x - k < 0 || p.getPos().y + k > 7) {
                    break;
                }
                if (getSquares(p.getPos().x - k, p.getPos().y + k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() < p.getPos().x - finalK && s.getY() > p.getPos().y + finalK);
                    break;
                }
            }
            //up right
            for (int k = 1; k < 7; k++) {
                if (p.getPos().x + k > 7 || p.getPos().y - k < 0) {
                    break;
                }
                if (getSquares(p.getPos().x + k, p.getPos().y - k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() > p.getPos().x + finalK && s.getY() < p.getPos().y - finalK);
                    break;
                }
            }
            //up left
            for (int k = 1; k < 7; k++) {
                if (p.getPos().x - k < 0 || p.getPos().y - k < 0) {
                    break;
                }
                if (getSquares(p.getPos().x - k, p.getPos().y - k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() < p.getPos().x - finalK && s.getY() < p.getPos().y - finalK);
                    break;
                }
            }
        }

        //Specific for Bishop
        if (p.getClass() == Bishop.class) {
            //down right
            for (int k = 1; k < 7; k++) {
                if (p.getPos().x + k > 7 || p.getPos().y + k > 7) {
                    break;
                }
                if (getSquares(p.getPos().x + k, p.getPos().y + k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() > p.getPos().x + finalK && s.getY() > p.getPos().y + finalK);
                    break;
                }
            }
            //down left
            for (int k = 1; k < 7; k++) {
                if (p.getPos().x - k < 0 || p.getPos().y + k > 7) {
                    break;
                }
                if (getSquares(p.getPos().x - k, p.getPos().y + k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() < p.getPos().x - finalK && s.getY() > p.getPos().y + finalK);
                    break;
                }
            }
            //up right
            for (int k = 1; k < 7; k++) {
                if (p.getPos().x + k > 7 || p.getPos().y - k < 0) {
                    break;
                }
                if (getSquares(p.getPos().x + k, p.getPos().y - k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() > p.getPos().x + finalK && s.getY() < p.getPos().y - finalK);
                    break;
                }
            }
            //up left
            for (int k = 1; k < 7; k++) {
                if (p.getPos().x - k < 0 || p.getPos().y - k < 0) {
                    break;
                }
                if (getSquares(p.getPos().x - k, p.getPos().y - k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() < p.getPos().x - finalK && s.getY() < p.getPos().y - finalK);
                    break;
                }
            }
        }

        Point[] returnArray = validMoves.toArray(new Point[0]);
        System.out.println("number of moves out: " + returnArray.length);
        return returnArray;
    };

    public void movePeice(GamePiece p, Point newPos) {
        setPeiceOnSquareInBoard(null, p.getPos());
        p.movePiece(newPos);
        setPeiceOnSquareInBoard(p, newPos);
    }

    public void setPeiceOnSquareInBoard(GamePiece p, Point pos) {
        BoardSquare square = getSquaresOnPoint(pos);
        square.setPieceOnSquare(p);
    }

    public BoardSquare getSquares(int x, int y) {
        return squares[y][x];
    }

    public BoardSquare getSquaresOnPoint(Point point) {
        return squares[point.y][point.x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
