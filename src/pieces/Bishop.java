package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Bishop extends GamePiece {
    private Color color;

    public Bishop(Color c) {
        this.color = c;
    }

    @Override
    public Point[] validMoves() {
        return new Point[0];
    }
}
