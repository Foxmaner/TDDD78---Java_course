package pieces;

import java.awt.*;

public class Queen extends GamePiece {

    public Queen(Color c, int x, int y, Image picture) {
        super(c, new Point(x, y), picture);
    }

    @Override
    public Point[] validMoves() {

        final int MOVE_LENGTH = 7;
        final int DIAGONAL_DIRECTIONS = 4;
        final int STRAIGHT_DIRECTIONS = 2;

        Point[] validMoves = new Point[MOVE_LENGTH * DIAGONAL_DIRECTIONS + MOVE_LENGTH * STRAIGHT_DIRECTIONS];
        int i = 0;
        //diagonals
        //down right
        for (int k = 1; k <= MOVE_LENGTH; k++) {
            validMoves[i] = new Point(pos.x + k, pos.y + k);
            i++;
        }
        //down left
        for (int k = 1; k <= MOVE_LENGTH; k++) {
            validMoves[i] = new Point(pos.x - k, pos.y + k);
            i++;
        }
        //up right
        for (int k = 1; k <= MOVE_LENGTH; k++) {
            validMoves[i] = new Point(pos.x + k, pos.y - k);
            i++;
        }
        //up left
        for (int k = 1; k <= MOVE_LENGTH; k++) {
            validMoves[i] = new Point(pos.x - k, pos.y - k);
            i++;
        }
        // straights
        // x axle
        for (int x = 0; x <= MOVE_LENGTH; x++) {
            if (x == pos.x) {
                continue;
            }
            validMoves[i] = new Point(x, pos.y);
            i++;
        }
        // y axle
        for (int y = 0; y <= MOVE_LENGTH; y++) {
            if (y == pos.y) {
                continue;
            }
            validMoves[i] = new Point(pos.x, y);
            i++;
        }
        return validMoves;
    }
}
