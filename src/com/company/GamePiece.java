package com.company;


import java.awt.*;

public abstract class GamePiece implements GamePieceInterface {
    Color color;
    Point pos;
    public void movePiece(Point newPos, Board b){

    }

    public Color getColor() {
        return color;
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public Point getPos() {
        return pos;
    }

    private void setPos(Point pos) {
        this.pos = pos;
    }
}
