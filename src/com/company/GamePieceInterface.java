package com.company;


import java.awt.*;

public interface GamePieceInterface {

    public void movePiece(Point newPos, Board b);

    public Point[] validMoves();

    public Color getColor();
    public void setColor(Color color);

    public Point getPos();
    public void setPos(Point pos);
}
