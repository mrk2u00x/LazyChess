/**
 * Lazy Chess
 * @author Bryan Lin
 * @version 2002.2.22
 */

import static Information.Tag.*;

/**
 * This is an class inheritied by the pieces with their own type
 * It contains basic and repetitve columns for every pieces, also requiring the other
 * pieces to have the method getLegalMove() to generate moves for each sub-pieces.
 */
public class Piece implements Cloneable
{

    /**
     * Point position have values from 0 ~ 8 for both x and y,
     * where (0, 0) being bottom left
     */
    //protected Point position;
    //protected boolean alive;
    private Color side;
    private Type type;
    
    /**
     * Indicates if the Chess type has special first move
     * False means having special first move and haven't moved
     */
    private boolean moved;

    public Piece(Color side, Type type)
    {
        this.side = side;
        this.type = type;
        
        switch(type)
        {
            case KING:
            case ROOK:
            case PAWN:
                moved = false;
                break;
            default:
                moved = true;
        }
    }
    
    //getters & setters
    public Color getSide() { return side; }
    public void setSide(Color side) { this.side = side; }

    public boolean isMoved() { return moved; }
    public void moved() { moved = true; }

    public Type getType() { return type; }


    public boolean equals(Piece other)
    {
        return this.side == other.side
            && this.type == other.type
            && this.moved == other.moved;
    }

    public Piece clone()
    {
        return new Piece(side, type);
    }

}
