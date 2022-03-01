package com.company;

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
}
