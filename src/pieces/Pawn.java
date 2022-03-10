package pieces;


import com.company.GamePiece;

import java.awt.*;


public class Pawn extends GamePiece {

    Boolean firstMove = true;
    int direction;

    public Pawn(Color c, int x, int y, Image picture) {
        super(c, new Point(x, y), picture);
        if (this.color == Color.WHITE) {
            direction = -1;
        } else {
            direction = 1;
        }
    }

    @Override
    public Point[] validMoves() {

        if (firstMove) {
            Point[] validMoves = new Point[2];
            validMoves[0] = new Point((int)this.getPos().getX(), (int)this.getPos().y + 1 * direction);
            validMoves[1] = new Point((int)this.getPos().getX(), (int)this.getPos().y + 2 * direction );
            return validMoves;
        } else {
            Point[] validMoves = new Point[1];
            validMoves[0] = new Point((int)this.getPos().getX(), (int)this.getPos().y + 1 * direction);
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
