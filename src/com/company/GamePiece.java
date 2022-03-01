package com.company;

import javax.swing.text.Position;
import java.awt.*;

public abstract class GamePiece implements GamePieceInterface {
    Color color;
    Position pos;
    public void movePiece(Position newPos, Board b){

    }

    public Color getColor() {
        return color;
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public Position getPos() {
        return pos;
    }

    private void setPos(Position pos) {
        this.pos = pos;
    }
}
