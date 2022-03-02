package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Queen extends GamePiece {
    private Color color;

    public Queen(Color c) {
        this.color = c;
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
