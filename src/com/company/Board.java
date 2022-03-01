package com.company;

import java.util.ArrayList;

public class Board {
    private BoardSquare[][] squares;
    private int width;
    private int height;

    public Board(BoardSquare[][] squares) {
        this.squares = squares;
        this.width = squares.length;
        this.height = squares[0].length;
    }

    public BoardSquare[][] getSquares() {
        return squares;
    }

    public void setSquares(BoardSquare squares, int x, int y) {
        this.squares[x][y] = squares;
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
