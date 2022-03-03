package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Rook extends GamePiece {

    public Rook(Color c, int x, int y) {
        super(c, new Point(x, y));
    }

    @Override
    public Point[] validMoves() {
        return new Point[0];
    }
}
