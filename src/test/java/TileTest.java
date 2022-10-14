import MineSweep.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    Tile testTile = new Tile();

    @Test
    public void testSetValue()
    {
        Assertions.assertEquals('0', testTile.getValue(),"Tile is not getting value right");

        testTile.setValue('M');

        Assertions.assertEquals('M', testTile.getValue(),"Tile is not changing the set value");
    }

    @Test
    public void testSetMine()
    {
        Assertions.assertFalse(testTile.getMine(), "Tile is not setting Mine as false to start");

        testTile.setMine();

        Assertions.assertTrue(testTile.getMine(),"Tile is not changing the state of the mine");
    }

    @Test
    public void testSetSelected()
    {
        Assertions.assertFalse(testTile.getSelected(), "Tile is not setting the tile as unselected to start");

        testTile.setSelected();

        Assertions.assertTrue(testTile.getSelected(),"Tile is not changing the state of the mine");
    }

    @Test
    public void testSetFlagged()
    {
        Assertions.assertFalse(testTile.getFlagged(), "Tile is not setting the tile as un-flagged to start");

        testTile.setFlagged();

        Assertions.assertTrue(testTile.getFlagged(),"Tile is not changing the state of the flag on the tile");

        testTile.setFlagged();

        Assertions.assertFalse(testTile.getFlagged(),"Tile is not toggling the state of the flag on the tile");
    }

}
