package com.company;

import pieces.*;

import java.awt.*;

public class boardToTextConverter {
    private Board b;

    public boardToTextConverter(Board b) {
        this.b = b;
    }

    public String toStringColour(){
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < b.getHeight(); x++){
            for (int y = 0; y < b.getWidth(); y++){
                BoardSquare squares = b.getSquares(x, y);
                if (squares.getColour() == Color.BLACK) {
                    builder.append("B ");
                } else if (squares.getColour() == Color.WHITE) {
                    builder.append("W ");
                } else {
                    builder.append("Error!");
                }
                if (y == b.getWidth()-1)
                    builder.append("\n");
            }
        }
        return builder.toString();
    }

    public String toStringPiece(){
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < b.getHeight(); x++){
            for (int y = 0; y < b.getWidth(); y++){
                BoardSquare squares = b.getSquares(x, y);


                if (squares.getPieceOnSquare() == null) {
                    builder.append("- ");
                } else if (squares.getPieceOnSquare() instanceof Bishop) {
                    builder.append("B ");
                }else if (squares.getPieceOnSquare() instanceof King) {
                    builder.append("K ");
                }else if (squares.getPieceOnSquare() instanceof Knight) {
                    builder.append("k ");
                }else if (squares.getPieceOnSquare() instanceof Pawn) {
                    builder.append("P ");
                }else if (squares.getPieceOnSquare() instanceof Queen) {
                    builder.append("Q ");
                }else if (squares.getPieceOnSquare() instanceof Rook) {
                    builder.append("R ");
                } else {
                    System.out.println(squares.getPieceOnSquare().getClass());
                    builder.append("Error!");
                }
                if (y == b.getWidth()-1)
                    builder.append("\n");
            }
        }
        return builder.toString();
    }

}
