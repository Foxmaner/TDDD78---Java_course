package com.company;

import pieces.*;

import java.awt.*;
import java.util.ArrayList;

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
            Pawn pBlack = new Pawn(Color.BLACK);
            squares[1][x].setPieceOnSquare(pBlack);
            Pawn pWhite = new Pawn(Color.WHITE);
            squares[getHeight()-2][x].setPieceOnSquare(pWhite);
        }
        // Generate Rooks
        for (int x = 0; x < getWidth(); x += 7) {
            Rook rBlack = new Rook(Color.BLACK);
            squares[0][x].setPieceOnSquare(rBlack);
            Rook rWhite = new Rook(Color.WHITE);
            squares[getHeight()-1][x].setPieceOnSquare(rWhite);
        }

        // Generate Queen
        Queen qBlack = new Queen(Color.BLACK);
        squares[0][3].setPieceOnSquare(qBlack);

        Queen qWhite = new Queen(Color.WHITE);
        squares[getHeight()-1][3].setPieceOnSquare(qWhite);

        //Generate Bishop
        for (int x = 2; x < getWidth()-2; x += 3) {
            Bishop bBlack = new Bishop(Color.BLACK);
            squares[0][x].setPieceOnSquare(bBlack);
            Bishop bWhite = new Bishop(Color.WHITE);
            squares[getHeight()-1][x].setPieceOnSquare(bWhite);
        }

        // Generate Knight
        for (int x = 1; x < getWidth()-1; x += 5) {
            Knight kBlack = new Knight(Color.BLACK);
            squares[0][x].setPieceOnSquare(kBlack);
            Knight kWhite = new Knight(Color.WHITE);
            squares[getHeight()-1][x].setPieceOnSquare(kWhite);
        }

        // Generate King
        King kBlack = new King(Color.BLACK);
        squares[0][4].setPieceOnSquare(kBlack);

        King kWhite = new King(Color.WHITE);
        squares[getHeight()-1][4].setPieceOnSquare(kWhite);

    }

    public BoardSquare getSquares(int x, int y) {
        return squares[y][x];
    }

    public void setSquares(BoardSquare squares, int x, int y) {
        this.squares[y][x] = squares;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
