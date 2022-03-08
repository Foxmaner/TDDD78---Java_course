package pieces;


import com.company.GamePiece;

import java.awt.*;


public class Pawn extends GamePiece {

    Boolean firstMove;
    int direction;

    public Pawn(Color c, int x, int y, boolean first) {
        super(c, new Point(x, y));
        if (this.color == Color.WHITE) {
            fileName = "wP.png";
            direction = -1;
        } else {
            fileName = "bP.png";
            direction = 1;
        }
        firstMove = first;
    }

    @Override
    public Point[] validMoves() {

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

    @Override
    public void movePiece(Point newPos){
        this.pos = newPos;
        this.firstMove = false;
    }

    public Boolean getFirstMove() {
        return this.firstMove;
    }

    public int getDirection() {
        return this.direction;
    }


}
