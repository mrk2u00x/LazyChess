/**
 * Lazy Chess
 * @version 2002.2.8
 * @author Bryan Lin
 */

import java.awt.geom.Point2D;

/**
 * This is an abstract class inheritied by the pieces with their own type
 * It contains basic and repetitve columns for every pieces, also requiring the other
 * pieces to have the method getLegalMove() to generate moves for each sub-pieces.
 */
abstract class Piece
{
    /**
     * To to confuse with java.awt.Color, this Color
     * is an enum helping to determine the side of the player.
     * The two possible choice of the enum is BLACK and WHITE.
     */
    public static enum Color{BLACK, WHITE};

    private Point2D position;
    private boolean alive;
    private Color side;

    //getters & setters
    public Point2D getPosition() { return position; }
    public void setPosition(Point2D position) { this.position = position; }

    public boolean isAlive() { return this.alive; }
    public boolean getAlive() { return this.alive; }
    public void setAlive(boolean alive) { this.alive = alive; }

    public Color getSide() { return this.side; }
    public void setSide(Color side) { this.side = side; }

    /**
     * This method will output the legal moves without any other pieces on the board,
     * the Point2D array will sent to game logic for legal moves with the pieces on board.
     * @return Point2D array for every availible position without interuption from other pieces
     */
    abstract Point2D[] getLegalMove();
    
}
