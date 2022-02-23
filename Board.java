/**
 * Lazy Chess
 * @author Bryan Lin
 * @version 2002.2.21
 */

import static Information.Tag.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Board implements Cloneable
{
    
    private HashMap<Point, Piece> board;
    private Player p1;
    private Player p2;
    
    /**
     * Will record the point that make two step as the first move, 
     * detected by other pawn for the move. The default for enPassant 
     * should be null or a Point only for the opponent's turn after the move.
     */
    private Point enPassant;

    /**
     * Creates empty board without any pieces,
     * used by clone for easier copy of another board
     */
    private Board()
    {   
        board = new HashMap<>();
        enPassant = null;

        initizeBoard();
    }

    /**
     * Constructor for Board
     * @param name1 Player 1's name
     * @param name2 Player 2's name
     */
    public Board(String name1, String name2)
    {
        new Board();
        p1 = new Player(Color.WHITE, name1);
        p2 = new Player(Color.BLACK, name2);
    }


    /**
     * Insert all the pieces to it's default position
     */
    public void initizeBoard()
    {
        //Generate Pawn
        for(int i = 0; i < 8; i++)
        {
            board.put(new Point(i, 1), new Piece(Color.WHITE, Type.PAWN));
            board.put(new Point(i, 6), new Piece(Color.BLACK, Type.PAWN));
        }

        //Generate Knight
        board.put(new Point(1, 0), new Piece(Color.WHITE, Type.KNIGHT));
        board.put(new Point(6, 0), new Piece(Color.WHITE, Type.KNIGHT));
        board.put(new Point(1, 7), new Piece(Color.BLACK, Type.KNIGHT));
        board.put(new Point(6, 7), new Piece(Color.BLACK, Type.KNIGHT));

        //Generate Rook
        board.put(new Point(0, 0), new Piece(Color.WHITE, Type.ROOK));
        board.put(new Point(7, 0), new Piece(Color.WHITE, Type.ROOK));
        board.put(new Point(0, 7), new Piece(Color.BLACK, Type.ROOK));
        board.put(new Point(7, 7), new Piece(Color.BLACK, Type.ROOK));

        //Generate Bishop
        board.put(new Point(2, 5), new Piece(Color.WHITE, Type.BISHOP));
        board.put(new Point(5, 0), new Piece(Color.WHITE, Type.BISHOP));
        board.put(new Point(2, 7), new Piece(Color.BLACK, Type.BISHOP));
        board.put(new Point(5, 7), new Piece(Color.BLACK, Type.BISHOP));
        
        //Generate Queen
        board.put(new Point(3, 0), new Piece(Color.WHITE, Type.QUEEN));
        board.put(new Point(3, 7), new Piece(Color.BLACK, Type.QUEEN));

        //Generate King
        board.put(new Point(4, 0), new Piece(Color.WHITE, Type.KING));
        board.put(new Point(4, 7), new Piece(Color.BLACK, Type.KING));
        
    }
    
    /**
     * Resets the board to default
     */
    public void resetBoard()
    {
        board.clear();
        initizeBoard();
    }

    //getters & setters
    public Piece getPiece(Point target) { return board.get(target); }
    public Piece removePiece(Point target) { return board.remove(target); }
    public boolean hasPiece(Point target) { return board.containsKey(target); }
    public void setEnPassant(Point enPassant) { this.enPassant = new Point(enPassant); }
    public Point getEnPassant() { return enPassant; }
    public String getPlayer1Name() { return p1.getName(); }
    public String getPlayer2Name() { return p2.getName(); }


    /**
     * Returns all pieces on the board
     * @return every Piece in HashMap board
     */
    public HashMap<Point, Piece> getAllPiece()
    {
        HashMap<Point, Piece> hold = new HashMap<>();

        for(Entry<Point, Piece> e : board.entrySet())
            hold.put(new Point(e.getKey()), e.getValue().clone());

        return hold;
    }

    /**
     * Get all the pieces from the Color side
     * @param side Color of the Pieces to obtain
     * @return HashMap of all Pieces with Color side
     */
    public HashMap<Point, Piece> getAllyPiece(Color side)
    {
        HashMap<Point, Piece> hold = new HashMap<>();

        for(Entry<Point, Piece> e : board.entrySet())
            if(e.getValue().getSide() == side)
                hold.put(e.getKey(), e.getValue());

        return hold;
    }

    /**
     * Similiar to getAllyPiece(Color), but also specify on the type of pieces
     * @param side Color of Pieces to Obtain
     * @param type Type of Pieces to Obtain
     * @return HashMap of all Pieces qualify after filter
     */
    public HashMap<Point, Piece> getAllyTypePiece(Color side, Type type)
    {
        HashMap<Point, Piece> hold = new HashMap<>();

        for(Entry<Point, Piece> e : board.entrySet())
            if(e.getValue().getSide() == side && e.getValue().getType() == type)
                hold.put(e.getKey(), e.getValue());

        return hold;
    }

    /**
     * Moving the Piece at start to end
     * @param start Point where the Piece is
     * @param end Where to move the Piece to
     * @return True when Piece is moved
     */
    public boolean movePiece(Point start, Point end)
    {
        if(hasPiece(start) && (!inBound(end)))
            return false;

        //King castle
        if(getPiece(start).getType() == Type.KING && start.distance(end) == 2)
        {
            //Determine Rook placement
            int xStart = (end.getX() == 2 ? 0 : 7);
            int xEnd = (xStart == 0 ? 3 : 5);
            int y = (int)start.getY();

            movePiece(new Point(xStart, y), new Point(xEnd, y));
        }

        //En Passant
        if(getPiece(start).getType() == Type.PAWN)
        {
            double distance = start.distance(end);

            //Reset En Passant
            if(distance == 1)
                enPassant = null;
            
            //Setting En Passant
            else if(distance == 2)
                enPassant = new Point(end);

            //Determine if moving diagonally and without enemy on end
            else if(!hasPiece(end))
            {
                removePiece(enPassant);
                enPassant = null;
            }
        }
        else
            enPassant = null;
        
        board.put(new Point(end), board.get(start));
        getPiece(end).moved();
        board.remove(start);

        return true;
    }

    public Board clone()
    {
        Board rtn = new Board();
        
        //Copying data
        for(Entry<Point, Piece> e : board.entrySet())
            rtn.board.put(new Point(e.getKey()), e.getValue().clone());

        return rtn;
    }

    /**
     * Process the moves able to do without getting block by other pieces, 
     * note that this arrays of points doesn't consider check
     * @param target
     * @return moves able to make without getting blocked
     */
    public Point[] getMove(Point target)
    {
        Piece temp = getPiece(target);
        HashSet<Point> moves = new HashSet<>();

        //Select the legal moves for different type
        switch(temp.getType())
        {
            case KING:
                for(Point p : getAroundMove(target))
                    moves.add(new Point(p));
                for(Point p : getSpecialMove(target))
                    moves.add(new Point(p));
                break;

            case QUEEN:
                for(Point p : getStraightMove(target))
                    moves.add(new Point(p));
                for(Point p : getDiagonalMove(target))
                    moves.add(new Point(p));
                break;
                
            case BISHOP:
                for(Point p : getDiagonalMove(target))
                    moves.add(new Point(p));
                break;
                
            case ROOK:
                for(Point p : getStraightMove(target))
                    moves.add(new Point(p));
                break;

            case KNIGHT:
                for(Point p : getSpecialMove(target))
                    moves.add(new Point(p));
                break;

            case PAWN:
                for(Point p : getSpecialMove(target))
                    moves.add(new Point(p));
        }

        return (Point[])moves.toArray();
    }

    /**
     * Retrieve diagonal moves available without getting blocked, not cosidering check
     * @param target Point where the selected Piece is located
     * @return moves able to make without getting blocked
     */
    public Point[] getDiagonalMove(Point target)
    {
        HashSet<Point> moves = new HashSet<>();

        for(int i = 0; i < 4; i++)
            for(Point p : helperMove(target, i, MoveSet.DIAGONAL))
                moves.add(p);

        return (Point[])moves.toArray();   
    }

    /**
     * Retrieve straight moves available without getting blocked, not cosidering check
     * @param target Point where the selected Piece is located
     * @return moves able to make without getting blocked
     */
    public Point[] getStraightMove(Point target)
    {
        HashSet<Point> moves = new HashSet<>();

        for(int i = 0; i < 4; i++)
            for(Point p : helperMove(target, i, MoveSet.STRAIGHT))
                moves.add(p);

        return (Point[])moves.toArray();
    }

    /**
     * Retrieve around moves available without getting blocked, not cosidering check
     * @param target Point where the selected Piece is located
     * @return moves able to make without getting blocked
     */
    public Point[] getAroundMove(Point target)
    {
        int cX = (int)target.getX();
        int cY = (int)target.getY();
        Point location;
        HashSet<Point> moves = new HashSet<>();

        for(int y = -1; y < 2; y++)
            for(int x = -1; x < 2; x++)
            {    
                location = new Point(cX + x, cY + y);
                if(inBound(location))
                    if(!hasPiece(location)) //Case Empty Space
                        moves.add(new Point(location));
                    else if(getPiece(location).getType() != getPiece(target).getType()) // Case Enemy Occupied
                        moves.add(new Point(location));
            }
        
        return (Point[])moves.toArray();
    }
    
    /**
     * Retrieve special moves, by Pawn, King, or Knight, available without getting blocked, 
     * not cosidering check
     * @param target Point where the selected Piece is located
     * @return moves able to make without getting blocked
     */
    public Point[] getSpecialMove(Point target)
    {
        if(getPiece(target).getType() == Type.KNIGHT)    
            return getKnightMove(target);
        if(getPiece(target).getType() == Type.KING)
            return getKingMove(target);
        if(getPiece(target).getType() == Type.PAWN)
            return getPawnMove(target);

        return null;
    }

    /**
     * Helper method for getSpecialMove(Point), retrive moves for Knight
     * @param target Point where the selected Piece is located
     * @return moves able to make without getting blocked
     */
    public Point[] getKnightMove(Point target)
    {
        int[] xShift = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] yShift = {2, 1, -1, -2, -2, -1, 1, 2};
        
        int x = (int)target.getX();
        int y = (int)target.getY();

        Point location = new Point(target);
        HashSet<Point> moves = new HashSet<>();
        
        for(int i = 0; i < 8; i++)
        {
            location.move(x + xShift[i], y + yShift[i]);
            if(inBound(location))
                if(!hasPiece(location)) //Case Empty Space
                    moves.add(new Point(location));
                else if(getPiece(location).getSide() != getPiece(target).getSide()) //Case Enemy Occupied
                    moves.add(new Point(location));
        }

        return (Point[])moves.toArray();
    }
     
    /**
     * Helper method for getSpecialMove(Point), retrive moves for King
     * @param target Point where the selected Piece is located
     * @return moves able to make without getting blocked
     */
    private Point[] getKingMove(Point target)
    {
        HashSet<Point> moves = new HashSet<>();
        Piece selected = getPiece(target);

        //Standard Move
        for(Point p : getAroundMove(target))
            moves.add(new Point(p));

        //Castle move
        if(!selected.isMoved())
        {
            int y = (selected.getSide() == Color.WHITE ? 0 : 7);

            //Left Side Rook haven't moved && empty between them
            Point location = new Point(0, y);                
            if(hasPiece(location) && (!getPiece(location).isMoved()))
                if(!(hasPiece(new Point(1, y)) || hasPiece(new Point(2, y))
                ||   hasPiece(new Point(3, y)) ))
                    moves.add(new Point(2, y));
            
            //Right side
            location.setLocation(7, y);
            if(hasPiece(location) && (!getPiece(location).isMoved()))
                if(!(hasPiece(new Point(5, y)) || hasPiece(new Point(6, y)) ))
                    moves.add(new Point(6, y));
        }
        

        return (Point[])moves.toArray();
    }

    /**
     * Helper method for getSpecialMove(Point), retrive moves for Pawn
     * @param target Point where the selected Piece is located
     * @return moves able to make without getting blocked
     */
    private Point[] getPawnMove(Point target)
    {
        HashSet<Point> moves = new HashSet<>();
        Piece selected = getPiece(target);
        int direction = (selected.getSide() == Color.WHITE) ? 1 : -1;
        Point location = new Point(target);
        location.translate(0, direction);

        //Two squares in front
        if(inBound(location))
            if(!hasPiece(location))
            {
                moves.add(new Point(location));
                location.translate(0, direction);
                if( (!hasPiece(location)) && (!selected.isMoved()) )
                    moves.add(new Point(location));
            }

        //Steps for taking diagonal enemy
        location.setLocation(target);
        location.translate(-1, direction);
        for(int i = 0; i < 2; i++)
        {
            if(inBound(location) && hasPiece(location))
                if(getPiece(location).getSide() != selected.getSide())
                    moves.add(new Point(location));

            location.translate(2, 0);
        }

        //En Passant
        if(enPassant != null)
        {
            location.setLocation(target);
            location.translate(-1, 0);
            if(enPassant.equals(location))
                moves.add(new Point(enPassant));
            else
            {
                location.translate(2, 0);
                if(enPassant.equals(location))
                    moves.add(new Point(enPassant));
            }
        }



        return (Point[])moves.toArray();
            
    }

    /**
     * Helper method for Normal moves such as diagonal, retrive their move to 
     * the corresponding move method
     * @param target Point where the selected Piece is located
     * @return moves able to make without getting blocked
     */
    private Point[] helperMove(Point target, int num, MoveSet moveSet)
    {
        int xShift = 0, yShift = 0;
        
        //Determine how to shift to follow pattern
        if(moveSet == MoveSet.DIAGONAL)
            switch(num)
            {
                case 0:
                    xShift = 1;
                    yShift = 1;
                    break;
                case 1:
                    xShift = 1;
                    yShift = -1;
                    break;
                case 2:
                    xShift = -1;
                    yShift = -1;
                    break;
                case 3:
                    xShift = -1;
                    yShift = 1;
                    break;
            }
        else if(moveSet == MoveSet.STRAIGHT)
            switch(num)
            {
                case 0:
                    yShift = 1;
                    break;
                case 1:
                    xShift = 1;
                    break;
                case 2:
                    yShift = -1;
                    break;
                case 3:
                    xShift = -1;
                    break;
            }

        HashSet<Point> moves = new HashSet<>();
        Point location = new Point(target);
        location.translate(xShift, yShift);

        while(inBound(location))
        {
            if(!hasPiece(location)) //Case Empty Space
            {
                moves.add(new Point(location));
                location.translate(xShift, yShift);
            }
            else if(getPiece(location).getSide() != getPiece(target).getSide()) //Case Enemy Occupied
            {
                moves.add(new Point(location));
                break;
            }
            else //Case Ally Occupied
                break;
        }

        return (Point[])moves.toArray();
    }

    /**
     * Determine of the paraneter is a space on the board
     * @param location
     * @return boolean determine if the location is in-bound or not
     */
    private boolean inBound(Point location)
    {
        int x = (int)location.getX();
        int y = (int)location.getY();

        return x >= 0 && x < 8
            && y >= 0 && y < 8;
    }

}
