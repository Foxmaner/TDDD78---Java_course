package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Queen extends GamePiece {

    public Queen(Color c, int x, int y, Image picture) {
        super(c, new Point(x, y), picture);
    }

    @Override
    public Point[] validMoves() {
        Point[] validMoves = new Point[7*4 + 8*2];
        int i = 0;
        //diagonals
        //down right
        for (int k = 1; k <= 7; k++) {
            validMoves[i] = new Point(pos.x + k, pos.y + k);
            i++;
        }
        //down left
        for (int k = 1; k <= 7; k++) {
            validMoves[i] = new Point(pos.x - k, pos.y + k);
            i++;
        }
        //up right
        for (int k = 1; k <= 7; k++) {
            validMoves[i] = new Point(pos.x + k, pos.y - k);
            i++;
        }
        //up left
        for (int k = 1; k <= 7; k++) {
            validMoves[i] = new Point(pos.x - k, pos.y - k);
            i++;
        }
        // straights
        // x axle
        for (int x = 0; x <= 8; x++) {
            if (x == pos.x) {
                continue;
            }
            validMoves[i] = new Point(x, pos.y);
            i++;
        }
        // y axle
        for (int y = 0; y <= 8; y++) {
            if (y == pos.y) {
                continue;
            }
            validMoves[i] = new Point(pos.x, y);
            i++;
        }
        return validMoves;
    }
}
