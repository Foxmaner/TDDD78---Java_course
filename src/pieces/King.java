package pieces;

import java.awt.*;


public class King extends GamePiece {

    public King(Color c, int x, int y, Image picture) {
        super(c, new Point(x, y), picture);
    }

    @Override
    public Point[] validMoves() {
        Point[] validMoves = new Point[8];
        validMoves[0] = new Point(getX() - 1, getY() - 1);
        validMoves[1] = new Point(getX(), getY() - 1);
        validMoves[2] = new Point(getX() + 1, getY() - 1);

        validMoves[3] = new Point(getX() - 1, getY());

        validMoves[4] = new Point(getX() + 1, getY());

        validMoves[5] = new Point(getX() - 1, getY() + 1);
        validMoves[6] = new Point(getX(), getY() + 1);
        validMoves[7] = new Point(getX() + 1, getY() + 1);

        return validMoves;
    }
}
