/**
 * Lazy Chess
 * @author Bryan Lin
 * @version 2002.2.18
 */

package Information;


public class Tag
{
    /**
     * To to confuse with java.awt.Color, this Color
     * is an enum helping to determine the side of the player.
     * The two possible choice of the enum is BLACK and WHITE.
     */
    public static enum Color{WHITE, BLACK};

    /**
     * The Moving pattern for Piece, having special when they own 
     * a moving pattern other than the previous three
     */
    public static enum MoveSet{AROUND, DIAGONAL, STRAIGHT, SPECIAL};

    /** The type of the Pieces */
    public static enum Type{KING, QUEEN, BISHOP, ROOK, KNIGHT, PAWN};

    public static enum Status{ACTIVE, CHECK, CHECKMATE, STAKEMATE, SURRENDER};
}
