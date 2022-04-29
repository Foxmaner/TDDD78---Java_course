package pieces;


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
            validMoves[0] = new Point(this.getX(), this.getY() + direction);
            validMoves[1] = new Point(this.getX(), this.getY() + 2 * direction );
            return validMoves;
        } else {
            Point[] validMoves = new Point[1];
            validMoves[0] = new Point(this.getX(), this.getY() + direction);
            return validMoves;
        }
    }

    @Override
    public void movePiece(Point newPos){
        this.pos = newPos;
        this.firstMove = false;
    }

    public Point getRightAttackSquare() {
        return new Point(this.getX() + 1, this.getY() + this.getDirection());
    }

    public Point getPointInFront() {
        return new Point(this.getX(), this.getY() + this.getDirection());
    }

    public Point getLeftAttackSquare() {
        return new Point(this.getX() - 1, this.getY() + this.getDirection());
    }

    public Boolean getFirstMove() {
        return this.firstMove;
    }

    public int getDirection() {
        return this.direction;
    }


}
