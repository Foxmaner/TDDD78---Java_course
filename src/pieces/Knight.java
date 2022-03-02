package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Knight extends GamePiece {
    private Color color;
    public Knight(Color c) {
        color = c;
    }

    @Override
    public Point[] validMoves() {
        return new Point[0];
    }
}
