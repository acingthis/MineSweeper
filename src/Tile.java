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

    public void setSelected(boolean select)
    {
        selected = select;
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
}