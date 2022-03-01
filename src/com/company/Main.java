package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Board b = new Board(8, 8);
        boardToTextConverter converter = new boardToTextConverter(b);
        System.out.println(converter.toStringColour());
    }
}