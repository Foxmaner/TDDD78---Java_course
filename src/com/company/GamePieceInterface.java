package com.company;


import java.awt.*;

public interface GamePieceInterface {
    void movePiece(Point newPos, Board b);
    Point[] validMoves();
}
