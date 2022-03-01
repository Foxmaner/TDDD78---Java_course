package com.company;

import javax.swing.text.Position;

public interface GamePieceInterface {
    void movePiece(Position newPos, Board b);
    Position[] validMoves();
}
