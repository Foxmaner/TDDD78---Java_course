package pieces;

import java.awt.*;


public class Bishop extends GamePiece {

    public Bishop(Color c, int x, int y, Image picture) {
        super(c, new Point(x, y), picture);
    }

    @Override
    public Point[] validMoves() {

        final int MOVE_LENGTH = 7;
        final int DIRECTIONS = 4;

        Point[] validMoves = new Point[MOVE_LENGTH *DIRECTIONS];
        int i = 0;
        //down right
        for (int k = 1; k <= MOVE_LENGTH; k++) {
            validMoves[i] = new Point(getX() + k, getY() + k);
            i++;
        }
        //down left
        for (int k = 1; k <= MOVE_LENGTH; k++) {
            validMoves[i] = new Point(getX() - k, getY() + k);
            i++;
        }
        //up right
        for (int k = 1; k <= MOVE_LENGTH; k++) {
            validMoves[i] = new Point(getX() + k, getY() - k);
            i++;
        }
        //up left
        for (int k = 1; k <= MOVE_LENGTH; k++) {
            validMoves[i] = new Point(getX() - k, getY() - k);
            i++;
        }

        return validMoves;
    }
}
