package com.company;

import pieces.*;
import utils.ImgFetcher;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private BoardSquare[][] squares;
    private int width;
    private int height;
    private final int BOARD_HEIGHT_OR_WIDTH = 8;
    private final int BOARD_LAST_INDEX = BOARD_HEIGHT_OR_WIDTH - 1;
    private int moveCounter = 0;
    private Boolean gameOver = false;
    private String winner;


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
        //Create image loader
        ImgFetcher fetcher = new ImgFetcher();



        // Generate pawns
        for (int x = 0; x < getWidth(); x++) {
            Pawn pBlack = new Pawn(Color.BLACK, x, 1, fetcher.fetchImage("bP.png"));
            squares[1][x].setPieceOnSquare(pBlack);
            Pawn pWhite = new Pawn(Color.WHITE, x, 6, fetcher.fetchImage("wP.png"));
            squares[getHeight()-2][x].setPieceOnSquare(pWhite);
        }
        // Generate Rooks
        for (int x = 0; x < getWidth(); x += BOARD_LAST_INDEX) {
            Rook rBlack = new Rook(Color.BLACK, x, 0, fetcher.fetchImage("bR.png"));
            squares[0][x].setPieceOnSquare(rBlack);
            Rook rWhite = new Rook(Color.WHITE, x, BOARD_LAST_INDEX, fetcher.fetchImage("wR.png"));
            squares[getHeight()-1][x].setPieceOnSquare(rWhite);
        }

        // Generate Queen
        Queen qBlack = new Queen(Color.BLACK, 3, 0, fetcher.fetchImage("bQ.png"));
        squares[0][3].setPieceOnSquare(qBlack);

        Queen qWhite = new Queen(Color.WHITE, 3, BOARD_LAST_INDEX, fetcher.fetchImage("wQ.png"));
        squares[getHeight()-1][3].setPieceOnSquare(qWhite);

        //Generate Bishop
        for (int x = 2; x < getWidth()-2; x += 3) {
            Bishop bBlack = new Bishop(Color.BLACK, x, 0, fetcher.fetchImage("bB.png"));;
            squares[0][x].setPieceOnSquare(bBlack);
            Bishop bWhite = new Bishop(Color.WHITE, x, BOARD_LAST_INDEX, fetcher.fetchImage("wB.png"));;
            squares[getHeight()-1][x].setPieceOnSquare(bWhite);
        }

        // Generate Knight
        for (int x = 1; x < getWidth()-1; x += 5) {
            Knight kBlack = new Knight(Color.BLACK, x, 0, fetcher.fetchImage("bN.png"));
            squares[0][x].setPieceOnSquare(kBlack);
            Knight kWhite = new Knight(Color.WHITE, x, 7, fetcher.fetchImage("wN.png"));
            squares[getHeight()-1][x].setPieceOnSquare(kWhite);
        }

        // Generate King
        King kBlack = new King(Color.BLACK, 4, 0, fetcher.fetchImage("bK.png"));
        squares[0][4].setPieceOnSquare(kBlack);

        King kWhite = new King(Color.WHITE, 4, BOARD_LAST_INDEX, fetcher.fetchImage("wK.png"));
        squares[getHeight()-1][4].setPieceOnSquare(kWhite);

    }

    public Point[] validMovesFilter(GamePiece p){

        System.out.println("number of moves in: " + p.validMoves().length);
        ArrayList<Point> validMoves = new ArrayList(List.of(p.validMoves()));

        //Moves are not valid if out of bounds
        validMoves.removeIf(s -> s.getX() < 0);
        validMoves.removeIf(s -> s.getX() > BOARD_LAST_INDEX);
        validMoves.removeIf(s -> s.getY() < 0);
        validMoves.removeIf(s -> s.getY() > BOARD_LAST_INDEX);

        //Moves are not valid if there is a friendly piece on position
        validMoves.removeIf(s -> getSquaresOnPoint(s).getPieceOnSquare() != null && getPieceOnSqaureOnPoint(s).getColor() == p.getColor());


        //All other pieces are not allowed to jump over other pieces
        //Specific for Pawn
        if (p instanceof Pawn) {

            //Moves are not valid if there is a piece on position
            validMoves.removeIf(s -> !isBoardSquareEmpty(s));

            if (p.getY() != 0 && p.getY() != BOARD_LAST_INDEX) {
                // right
                if (p.getX() + 1 != BOARD_HEIGHT_OR_WIDTH) { // not out of bounds
                    if (getSquaresOnPoint(((Pawn) p).getRightAttackSquare()).isSquareOccupied() &&
                            getPieceOnSqaureOnPoint(((Pawn) p).getRightAttackSquare()).getColor() != p.getColor()) {
                        validMoves.add(((Pawn) p).getRightAttackSquare());
                    }
                }
                // left
                if (p.getX() - 1 != -1) { // not out of bounds
                    if (getSquaresOnPoint(((Pawn) p).getLeftAttackSquare()).isSquareOccupied() &&
                            getPieceOnSqaureOnPoint(((Pawn) p).getLeftAttackSquare()).getColor() != p.getColor()) {
                        validMoves.add(((Pawn) p).getLeftAttackSquare());
                    }
                }
            }
            // check if piece is in the way of first move
            if (((Pawn) p).getFirstMove()) {
                if (getSquaresOnPoint(((Pawn) p).getPointInfront()).isSquareOccupied()) {
                    if (((Pawn) p).getDirection() == 1){ //if is black
                        validMoves.removeIf(s -> s.getY() == p.getY() + ((Pawn) p).getDirection() * 2);
                    } else { // else (white)
                        validMoves.removeIf(s -> s.getY() == p.getY() + ((Pawn) p).getDirection() * 2);
                    }
                }
            }
        }
        //Specific for Rook
        else if (p instanceof Rook) {
            //positive x
            for (int x = p.getX() + 1; x < BOARD_HEIGHT_OR_WIDTH; x++) {
                if (getSquares(x, p.getY()).getPieceOnSquare() != null) {
                    int finalX = x;
                    validMoves.removeIf(s -> s.getX() > finalX);
                    break;
                }
            }
            //negative x
            for (int x = p.getX() - 1; x >= 0; x--) {
                if (getSquares(x, p.getY()).getPieceOnSquare() != null) {
                    int finalX = x;
                    validMoves.removeIf(s -> s.getX() < finalX);
                    break;
                }
            }
            //positive y
            for (int y = p.getY() + 1; y < BOARD_HEIGHT_OR_WIDTH; y++) {
                if (getSquares(p.getX(), y).getPieceOnSquare() != null) {
                    int finalY = y;
                    validMoves.removeIf(s -> s.getY() > finalY);
                    break;
                }
            }
            //negative y
            for (int y = p.getY() - 1; y >= 0; y--) {
                if (getSquares(p.getX(), y).getPieceOnSquare() != null) {
                    int finalY = y;
                    validMoves.removeIf(s -> s.getY() < finalY);
                    break;
                }
            }
        }

        //Specific for Queen
        else if (p instanceof Queen) {
            //straights
            //positive x (right)
            for (int x = p.getX() + 1; x < BOARD_HEIGHT_OR_WIDTH; x++) {
                if (getSquares(x, p.getY()).getPieceOnSquare() != null) {
                    int finalX = x;
                    validMoves.removeIf(s -> s.getX() > finalX && s.getY() == p.getY());
                    break;
                }
            }
            //negative x (left)
            for (int x = p.getX() - 1; x >= 0; x--) {
                if (getSquares(x, p.getY()).getPieceOnSquare() != null) {
                    int finalX = x;
                    validMoves.removeIf(s -> s.getX() < finalX && s.getY() == p.getY());
                    break;
                }
            }
            //positive y (down)
            for (int y = p.getY() + 1; y < BOARD_HEIGHT_OR_WIDTH; y++) {
                if (getSquares(p.getX(), y).getPieceOnSquare() != null) {
                    int finalY = y;
                    validMoves.removeIf(s -> s.getY() > finalY && s.getX() == p.getX());
                    break;
                }
            }
            //negative y (up)
            for (int y = p.getY() - 1; y >= 0; y--) {
                if (getSquares(p.getX(), y).getPieceOnSquare() != null) {
                    int finalY = y;
                    validMoves.removeIf(s -> s.getY() < finalY && s.getX() == p.getX());
                    break;
                }
            }
            //down right
            for (int k = 1; k < BOARD_LAST_INDEX; k++) {
                if (p.getX() + k > BOARD_LAST_INDEX || p.getY() + k > 7) {
                    break;
                }
                if (getSquares(p.getX() + k, p.getY() + k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() > p.getX() + finalK && s.getY() > p.getY() + finalK);
                    break;
                }
            }
            //down left
            for (int k = 1; k < BOARD_LAST_INDEX; k++) {
                if (p.getPos().x - k < 0 || p.getY() + k > 7) {
                    break;
                }
                if (getSquares(p.getX() - k, p.getY() + k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() < p.getX() - finalK && s.getY() > p.getY() + finalK);
                    break;
                }
            }
            //up right
            for (int k = 1; k < BOARD_LAST_INDEX; k++) {
                if (p.getX() + k > BOARD_LAST_INDEX || p.getY() - k < 0) {
                    break;
                }
                if (getSquares(p.getX() + k, p.getY() - k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() > p.getX() + finalK && s.getY() < p.getY() - finalK);
                    break;
                }
            }
            //up left
            for (int k = 1; k < BOARD_LAST_INDEX; k++) {
                if (p.getX() - k < 0 || p.getY() - k < 0) {
                    break;
                }
                if (getSquares(p.getX() - k, p.getY() - k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() < p.getX() - finalK && s.getY() < p.getY() - finalK);
                    break;
                }
            }
        }

        //Specific for Bishop
        else if (p instanceof Bishop) {
            //down right
            for (int k = 1; k < BOARD_LAST_INDEX; k++) {
                if (p.getX() + k > BOARD_LAST_INDEX || p.getY() + k > 7) {
                    break;
                }
                if (getSquares(p.getX() + k, p.getY() + k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() > p.getX() + finalK && s.getY() > p.getY() + finalK);
                    break;
                }
            }
            //down left
            for (int k = 1; k < BOARD_LAST_INDEX; k++) {
                if (p.getX() - k < 0 || p.getY() + k > BOARD_LAST_INDEX) {
                    break;
                }
                if (getSquares(p.getX() - k, p.getY() + k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() < p.getX() - finalK && s.getY() > p.getY() + finalK);
                    break;
                }
            }
            //up right
            for (int k = 1; k < BOARD_LAST_INDEX; k++) {
                if (p.getX() + k > BOARD_LAST_INDEX || p.getY() - k < 0) {
                    break;
                }
                if (getSquares(p.getX() + k, p.getY() - k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() > p.getX() + finalK && s.getY() < p.getY() - finalK);
                    break;
                }
            }
            //up left
            for (int k = 1; k < BOARD_LAST_INDEX; k++) {
                if (p.getX() - k < 0 || p.getY() - k < 0) {
                    break;
                }
                if (getSquares(p.getX() - k, p.getY() - k).getPieceOnSquare() != null) {
                    int finalK = k;
                    validMoves.removeIf(s -> s.getX() < p.getX() - finalK && s.getY() < p.getY() - finalK);
                    break;
                }
            }
        }

        //Knights can jump over friendly, so no need to filter moves further.

        Point[] returnArray = validMoves.toArray(new Point[0]);
        System.out.println("number of moves out: " + returnArray.length);
        return returnArray;
    };

    public void movePeice(GamePiece p, Point newPos) {
        if (getSquaresOnPoint(newPos).getPieceOnSquare() != null && getSquaresOnPoint(newPos).getPieceOnSquare().getClass() == King.class){
            gameOver = true;
            if (p.getColor() == Color.WHITE) {
                winner = "White";
            } else if (p.getColor() == Color.BLACK){
                winner = "Black";
            }
        }
        setPeiceOnSquareInBoard(null, p.getPos());
        p.movePiece(newPos);
        setPeiceOnSquareInBoard(p, newPos);
        moveCounter++;
    }

    public void setPeiceOnSquareInBoard(GamePiece p, Point pos) {
        BoardSquare square = getSquaresOnPoint(pos);
        square.setPieceOnSquare(p);
    }

    private Boolean isBoardSquareEmpty(Point p) {
        return getSquaresOnPoint(p).getPieceOnSquare() == null;
    }

    public BoardSquare getSquares(int x, int y) {
        return squares[y][x];
    }

    public BoardSquare getSquaresOnPoint(Point point) {
        return squares[point.y][point.x];
    }

    public GamePiece getPieceOnSqaureOnPoint(Point p) {
        return getSquaresOnPoint(p).getPieceOnSquare();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMoveCounter() {
        return moveCounter;
    }

    public String getWinner() {
        return winner;
    }

    public Boolean getGameOver() {
        return gameOver;
    }
}
