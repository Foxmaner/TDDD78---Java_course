package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Board b = new Board(8, 8);
        BoardToTextConverter converter = new BoardToTextConverter(b);
        System.out.println(converter.toStringColour());
        System.out.println(converter.toStringPiece());

        GameWindow game = new GameWindow(b);
        game.show();
    }
}