package com.company;

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

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if(((y % 2) + (x % 2)) % 2 == 0){
                    squares[y][x] = new BoardSquare(Color.WHITE);
                }else{
                    squares[y][x] = new BoardSquare(Color.BLACK);
                }
            }
        }
    }

    public BoardSquare getSquares(int x, int y) {
        return squares[x][y];
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
