package pieces;


import com.company.GamePiece;

import java.awt.*;


public class Pawn extends GamePiece {

    Boolean firstMove;

    public Pawn(Color c, int x, int y, boolean first) {
        super(c, new Point(x, y));
        if (this.color == Color.WHITE) {
            fileName = "wP.png";
        } else {
            fileName = "bP.png";
        }
        firstMove = first;
    }

    @Override
    public Point[] validMoves() {

        int direction = 0;
        if (this.getColor() == Color.WHITE) {
            direction = -1;
        }
        else {
            direction = 1;
        }
        if (firstMove) {

            Point[] validMoves = new Point[2];
            validMoves[0] = new Point((int)this.getPos().getX(), (int)this.getPos().y + direction);
            validMoves[1] = new Point((int)this.getPos().getX(), (int)this.getPos().y + (direction * 2));
            return validMoves;
        } else {
            Point[] validMoves = new Point[2];
            validMoves[0] = new Point((int)this.getPos().getX(), (int)this.getPos().y + direction);
            return validMoves;
        }

    }


}
