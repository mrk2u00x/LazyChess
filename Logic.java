/**
 * Lazy Chess
 * @author Bryan Lin
 * @version 2002.2.21
 */

import static Information.Tag.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Logic
{
    private Board game;
    private Board future;
    private Color turn;
    
    public Logic(String name1, String name2)
    {
        game = new Board(name1, name2);
        game.initizeBoard();
        future = null;
        turn = Color.WHITE;

    }

    /**
     * Move the Piece at start to end, illegal move will not move the board. 
     * @param start
     * @param end
     * @return true if the piece is moved to end
     */
    public boolean move(Point start, Point end)
    {
        //No Piece
        if(!game.hasPiece(start))
            return false;
        
        //Not the player's piece
        if(game.getPiece(start).getSide() != turn)
            return false;
        
        //Illegal Move
        if(!getLegalMove(start).contains(end))
            return false;
    

        game.movePiece(start, end);

        //Setting for next round
        turn = (turn == Color.WHITE ? Color.BLACK : Color.WHITE);
        future = game.clone();

        return true;
    }

    /**
     * It finds the legal move based on the unprocessed move from 
     * getMove() in Board class
     * @param target Piece at that point
     * @return ArrayList<Point> of legal move from the Piece at target
     */
    public ArrayList<Point> getLegalMove(Point target)
    {
        Point[] unprocessMove = game.getMove(target);
        ArrayList<Point> processedMove = new ArrayList<>();

        for(Point p : unprocessMove)
        {
            future = game.clone();
            future.movePiece(target, p);
            if(!isChecked(turn))
                processedMove.add(new Point(p));
        }

        return processedMove;
    }

    /**
     * Determine if the future board is in check
     * @param side which side's king to be checked
     * @return True when King is checked on Color side
     */
    public boolean isChecked(Color side)
    {
        Point king = null;

        //Find king
        for(Entry<Point, Piece> e : future.getAllyTypePiece(side, Type.KING).entrySet())
            king = new Point(e.getKey());
        
        //Checks if king's gettign checked
        if(checkDiagonal(king, side) || checkStraight(king, side) 
        || checkAround  (king, side) || checkKnight  (king, side))
            return true;
        
        //Check if pawn checks king
        int direction = side == Color.WHITE ? 1 : -1;
        Point location = new Point(king);
        location.translate(-1, direction);

        if(future.hasPiece(location) && future.getPiece(location).getType() == Type.PAWN)
            return true;
        
        location.translate(2, 0);
        if(future.hasPiece(location) && future.getPiece(location).getType() == Type.PAWN)
            return true;

        return false;
    }

    /**
     * Checks if the Color side any any available moves
     * @param side
     * @return True if there's moves availble for side
     */
    public boolean hasLegalMove(Color side)
    {
        HashMap<Point, Piece> ally = game.getAllyPiece(side);
        for(Entry<Point, Piece> e : ally.entrySet())
            if(!getLegalMove(e.getKey()).isEmpty())
                return true;
        
        return false;
    }

    /**
     * Helper method for isChecked
     * @param king where the king is
     * @param side Color of king
     * @return True if checked diagonally
     */
    private boolean checkDiagonal(Point king, Color side)
    {
        Point[] unprocessMoved = future.getDiagonalMove(king);
        Piece selected = null;
        
        for(Point p : unprocessMoved)
            if(future.hasPiece(p))
            {
                selected = future.getPiece(p);
                if(selected.getSide() != side)
                {
                    Type type = selected.getType();
                    if(type == Type.BISHOP || type == Type.QUEEN)
                        return true;
                }
            }

        return false;
    }

    /**
     * Helper method for isChecked
     * @param king where the king is
     * @param side Color of king
     * @return True if checked in straight line
     */
    private boolean checkStraight(Point king, Color side)
    {
        Point[] unprocessMoved = future.getStraightMove(king);
        Piece selected = null;
        
        for(Point p : unprocessMoved)
            if(future.hasPiece(p))
            {
                selected = future.getPiece(p);
                if(selected.getSide() != side)
                {
                    Type type = selected.getType();
                    if(type == Type.ROOK || type == Type.QUEEN)
                        return true;
                }
            }


        return false;
    }

    /**
     * Helper method for isChecked
     * @param king where the king is
     * @param side Color of king
     * @return True if checked around
     */
    private boolean checkAround(Point king, Color side)
    {
        Point[] unprocessMoved = future.getAroundMove(king);
        Piece selected = null;
        
        for(Point p : unprocessMoved)
            if(future.hasPiece(p))
            {
                selected = future.getPiece(p);
                if(selected.getSide() != side)
                {
                    Type type = selected.getType();
                    if(type == Type.KING)
                        return true;
                }
            }


        return false;
    }

    /**
     * Helper method for isChecked
     * @param king where the king is
     * @param side Color of king
     * @return True if checked by enemy knight
     */
    private boolean checkKnight(Point king, Color side)
    {
        Point[] unprocessMoved = future.getKnightMove(king);
        Piece selected = null;
        
        for(Point p : unprocessMoved)
            if(future.hasPiece(p))
            {
                selected = future.getPiece(p);
                if(selected.getSide() != side)
                {
                    Type type = selected.getType();
                    if(type == Type.KNIGHT)
                        return true;
                }
            }


        return false;
    }
}
