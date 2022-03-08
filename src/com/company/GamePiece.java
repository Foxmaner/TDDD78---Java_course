package com.company;


import java.awt.*;

public abstract class GamePiece implements GamePieceInterface {
    protected Color color;
    protected Point pos;
    protected String fileName;

    public GamePiece(Color c, Point pos){
        this.color = c;
        this.pos = pos;
    }

    @Override public void movePiece(Point newPos){
        this.pos = newPos;
    }

    @Override public Color getColor() {
        return color;
    }

    @Override public void setColor(Color color) {
        this.color = color;
    }

    @Override public Point getPos() {
        return pos;
    }

    @Override public void setPos(Point pos) {
        this.pos = pos;
    }

    @Override public String getFileName() {return this.fileName;}
}
