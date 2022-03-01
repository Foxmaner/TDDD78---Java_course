package com.company;

import java.awt.*;

public class BoardSquare {
    Color colour;
    GamePiece pieceOnSquare;

    public BoardSquare(Color colour, GamePiece pieceOnSquare) {
        this.colour = colour;
        this.pieceOnSquare = pieceOnSquare;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public GamePiece getPieceOnSquare() {
        return pieceOnSquare;
    }

    public void setPieceOnSquare(GamePiece pieceOnSquare) {
        this.pieceOnSquare = pieceOnSquare;
    }
}
