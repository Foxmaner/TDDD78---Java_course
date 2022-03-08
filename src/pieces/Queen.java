package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Queen extends GamePiece {

    public Queen(Color c, int x, int y) {
        super(c, new Point(x, y));
        if (this.color == Color.WHITE) {
            fileName = "wQ.png";
        } else {
            fileName = "bQ.png";
        }
    }

    @Override
    public Point[] validMoves() {
        Point[] validMoves = new Point[8];
        return validMoves;
    }
}
