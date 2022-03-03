package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Bishop extends GamePiece {

    public Bishop(Color c, int x, int y) {
        super(c, new Point(x, y));
    }

    @Override
    public Point[] validMoves() {
        return new Point[0];
    }
}
