import MineSweep.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {

    Grid testGrid = new Grid(10);

    @Test
    public void testGridCreation()
    {
        Assertions.assertEquals(10,testGrid.MineAmount, "The Amount of mines is incorrect");
        Assertions.assertEquals(10,testGrid.GridSize, "The Grid size is incorrect");
        Assertions.assertEquals(0,testGrid.Score, "The Score doesn't start at 0");
        Assertions.assertEquals(0,testGrid.NumOfMoves, "The number Of moves doesn't start at 0");

        Assertions.assertFalse(testGrid.GameOver, "The Game over state doesn't start as false");
        Assertions.assertFalse(testGrid.Won, "The Winning state doesn't start as false");

        for (int X = 0; X < testGrid.GridSize; X++)
        {
            for (int Y = 0; Y < testGrid.GridSize;Y++)
            {
                Assertions.assertFalse(testGrid.MineMap[X][Y].getSelected(), "The Tile " + X + "," + Y + " is already unselected");
                Assertions.assertFalse(testGrid.MineMap[X][Y].getFlagged(), "The Tile " + X + "," + Y + " is already flagged");
            }
        }
    }

    @Test
    public void testMineMapNumber()
    {
        int NumOfMines = 0;

        for (int X = 0; X < testGrid.GridSize; X++)
        {
            for (int Y = 0; Y < testGrid.GridSize;Y++)
            {
                if (testGrid.MineMap[X][Y].getMine())
                {
                    NumOfMines++;
                }
            }
        }
        Assertions.assertEquals(NumOfMines,testGrid.MineAmount, "The Amount of mines is incorrect within the map");
    }

    @Test
    public void testAroundMine()
    {
        Grid newTestGrid = new Grid(10);
        for (int X = 0; X < testGrid.GridSize; X++)
        {
            for (int Y = 0; Y < testGrid.GridSize;Y++)
            {
                newTestGrid.MineMap[X][Y] = new Tile();
            }
        }

        Assertions.assertEquals('0',newTestGrid.MineMap[5][6].getValue(), "The Value has been changed already");
        Assertions.assertEquals('0',newTestGrid.MineMap[5][4].getValue(), "The Value has been changed already");
        Assertions.assertEquals('0',newTestGrid.MineMap[6][5].getValue(), "The Value has been changed already");
        Assertions.assertEquals('0',newTestGrid.MineMap[4][5].getValue(), "The Value has been changed already");

        newTestGrid.MineMap[5][5].setMine();
        newTestGrid.numberAroundMine(5,5);

        Assertions.assertEquals('1',newTestGrid.MineMap[5][6].getValue(), "The Value around the mine is incorrect");
        Assertions.assertEquals('1',newTestGrid.MineMap[5][4].getValue(), "The Value around the mine is incorrect");
        Assertions.assertEquals('1',newTestGrid.MineMap[6][5].getValue(), "The Value around the mine is incorrect");
        Assertions.assertEquals('1',newTestGrid.MineMap[4][5].getValue(), "The Value around the mine is incorrect");
    }

    @Test
    public void testGameOver()
    {
        testGrid.GameOver = false;

        Assertions.assertFalse(testGrid.GameOver(), "Game Over getting doesn't return false");

        testGrid.GameOver = true;

        Assertions.assertTrue(testGrid.GameOver(),"Game Over getting doesn't return True");
    }

    @Test
    public void testWin()
    {
        testGrid.Won = false;

        Assertions.assertFalse(testGrid.Win(), "Win state getter doesn't return false");

        testGrid.Won = true;

        Assertions.assertTrue(testGrid.Win(),"Win state getter doesn't return True");
    }

    @Test
    public void testFlag()
    {
        Assertions.assertFalse(testGrid.MineMap[0][0].getFlagged(), "The Tile is getting flagged right away, without a prompt");

        testGrid.Flag(0,0);

        Assertions.assertTrue(testGrid.MineMap[0][0].getFlagged(), "The Tile is not getting flagged");

        testGrid.Flag(0,0);

        Assertions.assertFalse(testGrid.MineMap[0][0].getFlagged(), "The Tile is not getting un-flagged");
    }

    @Test
    public void testUserInput()
    {
        Assertions.assertFalse(testGrid.MineMap[5][5].getSelected(), "The Tile is select when it shouldn't be");

        testGrid.UserInput(5,5);

        Assertions.assertTrue(testGrid.MineMap[5][5].getSelected(), "The Tile is not getting Selected");
    }

}
