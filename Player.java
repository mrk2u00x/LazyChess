/**
 * Lazy Chess
 * @author Bryan Lin
 * @version 2002.2.11
 */

import Information.Tag.*;

public class Player
{
    private Color side;
    private String name;

    public Player(Color side)
    {
        this.side = side;
        if(side == Color.WHITE)
            name = "Player 1";
        else
            name = "Player 2";
    }

    public Player(Color side, String name)
    {
        this.side = side;
        this.name = name;
    }

    //getters & setters
    public Color getSide() { return side; }
    public void setSide(Color side) { this.side = side; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
