package MineSweep;

public class Tile {

    private char Value;
    private boolean Mine;
    private boolean Flagged;
    private boolean selected;

    public Tile()
    {
        Value = '0';
        Mine = false;
        Flagged = false;
        selected = false;
    }

    public boolean getSelected()
    {
        return selected;
    }

    public void setSelected()
    {
        selected = true;
    }

    public boolean getMine()
    {
        return Mine;
    }

    public void setMine()
    {
        Mine = true;
    }

    public char getValue()
    {
        return Value;
    }

    public void setValue(char Value)
    {
        this.Value = Value;
    }

    public void setFlagged() { this.Flagged = !this.Flagged; }

    public boolean getFlagged() { return Flagged; }
}
