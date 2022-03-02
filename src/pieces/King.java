package pieces;

import com.company.GamePiece;

import java.awt.*;

public class King extends GamePiece {
    private Color color;
    public King(Color c) {
        color = c;
    }

    @Override
    public Point[] validMoves() {
        return new Point[0];
    }
}
