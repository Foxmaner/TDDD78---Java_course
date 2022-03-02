package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Rook extends GamePiece {
    private Color color;
    public Rook(Color c) {
        color = c;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Point[] validMoves() {
        return new Point[0];
    }
}
