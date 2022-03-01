package com.company;

import java.awt.*;

public abstract class GamePiece{
    Color color;
    abstract void movePiece(int newX, int newY, Board b);
}
