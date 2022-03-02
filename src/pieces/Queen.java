package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Queen extends GamePiece {
    public Queen() {
    }

    @Override
    public Point[] validMoves() {
        return new Point[0];
    }
}
