package MineSweep;
import java.security.SecureRandom;

public class Grid {

    public Tile[][] mineMap;
    public int mineAmount;
    public int gridSize;

    public int Score;
    public int numOfMoves;

    public boolean gameOver = false;
    public boolean Won = false;

    public Grid(int GridSize)
    {
        this.Score = 0;
        this.numOfMoves = 0;
        mineMap = new Tile[GridSize][GridSize];

        for(int x = 0; x < GridSize; x++)
        {
            for(int i = 0; i < GridSize; i++)
            {
                mineMap[x][i] = new Tile();
            }
        }
        mineAmount = GridSize;
        this.gridSize = GridSize;
        createMines();
    }

    private void createMines()
    {
        SecureRandom rand = new SecureRandom();

        for(int i = 0; i < mineAmount; i++)
        {
            int X = rand.nextInt(gridSize);
            int Y = rand.nextInt(gridSize);
            while (mineMap[X][Y].getMine())
            {
                X = rand.nextInt(gridSize);
                Y = rand.nextInt(gridSize);
            }
            mineMap[X][Y].setMine();
            numberAroundMine(X,Y);
        }
    }

    public void numberAroundMine(int X, int Y)
    {
        createNumbers(X-1,Y);
        createNumbers(X-1,Y-1);
        createNumbers(X,Y-1);
        createNumbers(X+1,Y-1);
        createNumbers(X+1,Y);
        createNumbers(X+1,Y+1);
        createNumbers(X,Y+1);
        createNumbers(X-1,Y+1);
    }

    private void createNumbers(int X, int Y)
    {
        if(X < 0 || X >= gridSize || Y < 0 || Y >= gridSize)
        {
            return;
        }

        if (!mineMap[X][Y].getMine())
        {
            mineMap[X][Y].setValue((char) (1 + (int) mineMap[X][Y].getValue()));
        }
    }

    public void Print()
    {
        //Values
        System.out.print(ConsoleColor.BLUE);
        System.out.println("------------------------");
        System.out.println("Mines: "+ mineAmount + "  Score: " + Score + "/" + ((gridSize * gridSize) - mineAmount));
        System.out.println("------------------------");
        System.out.print(ConsoleColor.RESET);

        //Map
        for(int x = 0; x < gridSize; x++)
        {
            //Coordinates
            if(x >= 10)
            {
                System.out.print(x + "| ");
            }
            else
            {
                if (gridSize <= 10)
                {
                    System.out.print(x + "| ");
                }
                else
                {
                    System.out.print("0" + x + "| ");
                }
            }

            for(int i = 0; i < gridSize; i++)
            {
                if (mineMap[x][i].getFlagged())
                {
                    if (mineMap[x][i].getSelected())
                    {
                        System.out.print(ConsoleColor.GREEN + mineMap[x][i].getValue() + " " + ConsoleColor.RESET);
                    }
                    else
                    {
                        System.out.print(ConsoleColor.BLUE +"F" + " " + ConsoleColor.RESET);
                    }
                }
                else if (!mineMap[x][i].getSelected())
                {
                    System.out.print("#" + " ");
                }
                else
                {
                    if (mineMap[x][i].getMine())
                    {
                        System.out.print(ConsoleColor.RED + "M" + " " + ConsoleColor.RESET);
                    }
                    else
                    {
                        System.out.print(ConsoleColor.GREEN + mineMap[x][i].getValue() + " " + ConsoleColor.RESET);
                    }
                }
            }
            System.out.print("\n");
        }


        //Coordinates outline
        if (gridSize <= 10)
        {
            System.out.print("  ");
        }
        else
        {
            System.out.print("    ");
        }
        for(int i = 0; i < gridSize *2; i++)
        {
            System.out.print("-");
        }

        if (gridSize <= 10)
        {
            System.out.print("\n   ");
        }
        else
        {
            System.out.print("\n    ");
        }
        //Grid values
        for(int i = 0; i < gridSize; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    public boolean gameOver()
    {
        return gameOver;
    }
    public boolean Win() { return Won; }

    int MinesFlagged = 0;

    public void Flag(int X, int Y)
    {
        mineMap[X][Y].setFlagged();
        if (mineMap[X][Y].getMine() && mineMap[X][Y].getFlagged())
        {
            MinesFlagged++;
        }
        if(MinesFlagged == mineAmount && Score == (gridSize * gridSize) - mineAmount)
        {
            Won = true;
        }
    }

    public void userInput(int X, int Y)
    {
        if(mineMap[X][Y].getFlagged())
        {
            System.out.println("You choose a flagged tile. un-flag the tile by flagging it again");
            return;
        }
        //Game Over
        if (mineMap[X][Y].getMine())
        {
            mineMap[X][Y].setSelected();
            gameOver = true;
            return;
        }

        numOfMoves++;
        checkValue(X,Y);

        if(MinesFlagged == mineAmount && Score == (gridSize * gridSize) - mineAmount)
        {
            Won = true;
        }
    }

    private void checkValue(int X, int Y)
    {
        if(X < 0 || X >= gridSize || Y < 0 || Y >= gridSize || mineMap[X][Y].getSelected() || mineMap[X][Y].getFlagged())
        {
            return;
        }

        if (!mineMap[X][Y].getMine())
        {
            mineMap[X][Y].setSelected();
            Score++;
        }

        if (mineMap[X][Y].getValue() == '0')
        {
            checkValue(X-1,Y);
            checkValue(X+1,Y);
            checkValue(X,Y+1);
            checkValue(X,Y-1);
        }

    }

}
