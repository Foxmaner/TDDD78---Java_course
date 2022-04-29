package pieces;

import java.awt.*;

public class Rook extends GamePiece {




    public Rook(Color c, int x, int y, Image picture) {
        super(c, new Point(x, y), picture);
    }

    @Override
    public Point[] validMoves() {

        final int MOVE_LENGTH = 7;
        final int DIRECTIONS = 2;

        Point[] validMoves = new Point[MOVE_LENGTH *DIRECTIONS];
        int i = 0;
        // x axle
        for (int x = 0; x <= MOVE_LENGTH; x++) {
            if (x == this.getX()) {
                continue;
            }
            validMoves[i] = new Point(x, this.getY());
            i++;
        }
        // y axle
        for (int y = 0; y <= MOVE_LENGTH; y++) {
            if (y == this.getY()) {
                continue;
            }
            validMoves[i] = new Point(this.getX(), y);
            i++;
        }

        return validMoves;
    }
}
