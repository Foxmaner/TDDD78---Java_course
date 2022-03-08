package pieces;

import com.company.GamePiece;

import java.awt.*;


public class King extends GamePiece {

    public King(Color c, int x, int y) {
        super(c, new Point(x, y));
        if (this.color == Color.WHITE) {
            fileName = "wK.png";
        } else {
            fileName = "bK.png";
        }
    }

    @Override
    public Point[] validMoves() {
        Point[] validMoves = new Point[8];
        validMoves[0] = new Point(pos.x - 1, pos.y - 1);
        validMoves[1] = new Point(pos.x, pos.y - 1);
        validMoves[2] = new Point(pos.x + 1, pos.y - 1);

        validMoves[3] = new Point(pos.x - 1, pos.y);

        validMoves[4] = new Point(pos.x + 1, pos.y);

        validMoves[5] = new Point(pos.x - 1, pos.y + 1);
        validMoves[6] = new Point(pos.x, pos.y + 1);
        validMoves[7] = new Point(pos.x + 1, pos.y + 1);

        return validMoves;
    }
}
