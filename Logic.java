/**
 * Lazy Chess
 * @author Bryan Lin
 * @version 2002.2.18
 */

import static Information.Tag.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Logic
{
    private Board game;
    private Board future;
    private Player p1;
    private Player p2;
    private Color turn;
    
    public Logic()
    {
        game = new Board();
        future = null;
        turn = Color.WHITE;

        p1 = new Player(Color.WHITE);
        p2 = new Player(Color.BLACK);
    }

    /**
     * Move the Piece at start to end, illegal move will not move the board. 
     * The Special move are handled here
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
        
        //King castle
        if(game.getPiece(start).getType() == Type.KING && start.distance(end) == 2)
        {
            //Determine Rook placement
            int xStart = (end.getX() == 2 ? 3 : 5);
            int xEnd = (xStart == 3 ? 0 : 8);
            int y = (turn == Color.WHITE ? 0 : 8);

            game.movePiece(new Point(xStart, y), new Point(xEnd, y));
        }

        //En Passant
        if(game.getPiece(start).getType() == Type.PAWN)
        {
            double distance = start.distance(end);

            //Setting enPassant
            if(distance == 2)
                game.setEnPassant(end);
            
            //Determine if moving diagonally and without enemy on end
            if(distance != 1 && distance != 2 && (!game.hasPiece(end)))
            {
                game.removePiece(game.getEnPassant());
                game.setEnPassant(null);
            }
        }

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
            if(isChecked(turn))
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
