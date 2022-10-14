import MineSweep.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {

    Grid testGrid = new Grid(10);

    @Test
    public void testGridCreation()
    {
        testGrid = new Grid(10);

        Assertions.assertEquals(10,testGrid.mineAmount, "The Amount of mines is incorrect");
        Assertions.assertEquals(10,testGrid.gridSize, "The Grid size is incorrect");
        Assertions.assertEquals(0,testGrid.Score, "The Score doesn't start at 0");
        Assertions.assertEquals(0,testGrid.numOfMoves, "The number Of moves doesn't start at 0");

        Assertions.assertFalse(testGrid.gameOver, "The Game over state doesn't start as false");
        Assertions.assertFalse(testGrid.Won, "The Winning state doesn't start as false");

        for (int X = 0; X < testGrid.gridSize; X++)
        {
            for (int Y = 0; Y < testGrid.gridSize; Y++)
            {
                Assertions.assertFalse(testGrid.mineMap[X][Y].getSelected(), "The Tile " + X + "," + Y + " is already unselected");
                Assertions.assertFalse(testGrid.mineMap[X][Y].getFlagged(), "The Tile " + X + "," + Y + " is already flagged");
            }
        }
    }

    @Test
    public void testMineMapNumber()
    {
        int NumOfMines = 0;

        for (int X = 0; X < testGrid.gridSize; X++)
        {
            for (int Y = 0; Y < testGrid.gridSize; Y++)
            {
                if (testGrid.mineMap[X][Y].getMine())
                {
                    NumOfMines++;
                }
            }
        }
        Assertions.assertEquals(NumOfMines,testGrid.mineAmount, "The Amount of mines is incorrect within the map");
    }

    @Test
    public void testAroundMine()
    {
        Grid newTestGrid = new Grid(10);
        for (int X = 0; X < testGrid.gridSize; X++)
        {
            for (int Y = 0; Y < testGrid.gridSize; Y++)
            {
                newTestGrid.mineMap[X][Y] = new Tile();
            }
        }

        Assertions.assertEquals('0',newTestGrid.mineMap[5][6].getValue(), "The Value has been changed already");
        Assertions.assertEquals('0',newTestGrid.mineMap[5][4].getValue(), "The Value has been changed already");
        Assertions.assertEquals('0',newTestGrid.mineMap[6][5].getValue(), "The Value has been changed already");
        Assertions.assertEquals('0',newTestGrid.mineMap[4][5].getValue(), "The Value has been changed already");

        newTestGrid.mineMap[5][5].setMine();
        newTestGrid.numberAroundMine(5,5);

        Assertions.assertEquals('1',newTestGrid.mineMap[5][6].getValue(), "The Value around the mine is incorrect");
        Assertions.assertEquals('1',newTestGrid.mineMap[5][4].getValue(), "The Value around the mine is incorrect");
        Assertions.assertEquals('1',newTestGrid.mineMap[6][5].getValue(), "The Value around the mine is incorrect");
        Assertions.assertEquals('1',newTestGrid.mineMap[4][5].getValue(), "The Value around the mine is incorrect");
    }

    @Test
    public void testGameOver()
    {
        testGrid.gameOver = false;

        Assertions.assertFalse(testGrid.gameOver(), "Game Over getting doesn't return false");

        testGrid.gameOver = true;

        Assertions.assertTrue(testGrid.gameOver(),"Game Over getting doesn't return True");
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
        Assertions.assertFalse(testGrid.mineMap[0][0].getFlagged(), "The Tile is getting flagged right away, without a prompt");

        testGrid.Flag(0,0);

        Assertions.assertTrue(testGrid.mineMap[0][0].getFlagged(), "The Tile is not getting flagged");

        testGrid.Flag(0,0);

        Assertions.assertFalse(testGrid.mineMap[0][0].getFlagged(), "The Tile is not getting un-flagged");
    }

    @Test
    public void testUserInput()
    {
        Assertions.assertFalse(testGrid.mineMap[5][5].getSelected(), "The Tile is select when it shouldn't be");

        testGrid.userInput(5,5);

        Assertions.assertTrue(testGrid.mineMap[5][5].getSelected(), "The Tile is not getting Selected");
    }

    @Test
    public void testPrint()
    {

    }

}
