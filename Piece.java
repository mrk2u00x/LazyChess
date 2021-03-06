/**
 * Lazy Chess
 * @author Bryan Lin
 * @version 2002.3.3
 */

import static Information.Tag.*;
import Information.Layout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.Graphics;
import Information.Tag;

/**
 * This is an class inheritied by the pieces with their own type
 * It contains basic and repetitve columns for every pieces, also requiring the other
 * pieces to have the method getLegalMove() to generate moves for each sub-pieces.
 */
public class Piece extends JComponent implements Cloneable
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
    
    public ImageIcon getImage()
    {
        String fileName = (side == Tag.Color.WHITE ? "white" : "black") + "_";
    
        switch(type)
        {
            case PAWN:
                fileName += "pawn";
                break;
            case KNIGHT:
                fileName += "knight";
                break;
            case ROOK:
                fileName += "rook";
                break;
            case BISHOP:
                fileName += "bishop";
                break;
            case KING:
                fileName += "king";
                break;
            case QUEEN:
                fileName += "queen";
                break;
        }

        fileName += ".png";
        
        return new ImageIcon(Piece.class.getResource("/assets/" + fileName));
    }
}
