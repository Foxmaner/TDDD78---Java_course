package com.company;

public class Main {
    private static final int GAMEBOARD_WIDTH = 8;
    private static final int GAMEBOARD_HEIGHT = 8;

    public static void main(String[] args) {
        Board b = new Board(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);

        GameWindow game = new GameWindow(b);
        game.show();
    }
}