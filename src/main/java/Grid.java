import java.lang.Math;

public class Grid {

    Tile[][] MineMap;
    int MineAmount;
    int GridSize;

    int Score;
    int NumOfMoves;

    boolean GameOver = false;
    boolean Won = false;

    public Grid(int GridSize)
    {
        this.Score = 0;
        this.NumOfMoves = 0;
        MineMap = new Tile[GridSize][GridSize];

        for(int x = 0; x < GridSize; x++)
        {
            for(int i = 0; i < GridSize; i++)
            {
                MineMap[x][i] = new Tile();
            }
        }
        MineAmount = GridSize;
        this.GridSize = GridSize;
        CreateMines();
    }

    private void CreateMines()
    {
        for(int i = 0; i < MineAmount; i++)
        {
            int X = (int)(Math.random() * GridSize);
            int Y = (int)(Math.random() * GridSize);
            while (MineMap[X][Y].getMine())
            {
                X = (int)(Math.random() * GridSize);
                Y = (int)(Math.random() * GridSize);
            }
            MineMap[X][Y].setMine();
            numberAroundMine(X,Y);
        }
    }

    private void numberAroundMine(int X, int Y)
    {
        CreateNumbers(X-1,Y);
        CreateNumbers(X-1,Y-1);
        CreateNumbers(X,Y-1);
        CreateNumbers(X+1,Y-1);
        CreateNumbers(X+1,Y);
        CreateNumbers(X+1,Y+1);
        CreateNumbers(X,Y+1);
        CreateNumbers(X-1,Y+1);
    }

    private void CreateNumbers(int X, int Y)
    {
        if(X < 0 || X >= GridSize || Y < 0 || Y >= GridSize)
        {
            return;
        }

        if (!MineMap[X][Y].getMine())
        {
            MineMap[X][Y].setValue((char) (1 + (int) MineMap[X][Y].getValue()));
        }
    }

    public void Print()
    {
        //Values
        System.out.print(ConsoleColor.BLUE);
        System.out.println("------------------------");
        System.out.println("Mines: "+ MineAmount + "  Score: " + Score + "/" + ((GridSize * GridSize) - MineAmount));
        System.out.println("------------------------");
        System.out.print(ConsoleColor.RESET);

        //Map
        for(int x = 0; x < GridSize; x++)
        {
            //Coordinates
            if(x >= 10)
            {
                System.out.print(x + "| ");
            }
            else
            {
                if (GridSize <= 10)
                {
                    System.out.print(x + "| ");
                }
                else
                {
                    System.out.print("0" + x + "| ");
                }
            }

            for(int i = 0; i < GridSize; i++)
            {
                if (MineMap[x][i].getFlagged())
                {
                    if (MineMap[x][i].getSelected())
                    {
                        System.out.print(ConsoleColor.GREEN + MineMap[x][i].getValue() + " " + ConsoleColor.RESET);
                    }
                    else
                    {
                        System.out.print(ConsoleColor.BLUE +"F" + " " + ConsoleColor.RESET);
                    }
                }
                else if (!MineMap[x][i].getSelected())
                {
                    System.out.print("#" + " ");
                }
                else
                {
                    if (MineMap[x][i].getMine())
                    {
                        System.out.print(ConsoleColor.RED + "M" + " " + ConsoleColor.RESET);
                    }
                    else
                    {
                        System.out.print(ConsoleColor.GREEN + MineMap[x][i].getValue() + " " + ConsoleColor.RESET);
                    }
                }
            }
            System.out.print("\n");
        }


        //Coordinates outline
        if (GridSize <= 10)
        {
            System.out.print("  ");
        }
        else
        {
            System.out.print("    ");
        }
        for(int i = 0; i < GridSize*2; i++)
        {
            System.out.print("-");
        }

        if (GridSize <= 10)
        {
            System.out.print("\n   ");
        }
        else
        {
            System.out.print("\n    ");
        }
        //Grid values
        for(int i = 0; i < GridSize; i++)
        {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    public boolean GameOver()
    {
        return GameOver;
    }

    int MinesFlagged = 0;

    public void Flag(int X, int Y)
    {
        MineMap[X][Y].setFlagged();
        if (MineMap[X][Y].getMine() && MineMap[X][Y].getFlagged())
        {
            MinesFlagged++;
        }
        if(MinesFlagged == MineAmount && Score == (GridSize * GridSize) - MineAmount)
        {
            Won = true;
        }
    }

    public boolean Win()
    {
        return Won;
    }

    public void UserInput(int X, int Y)
    {
        if(MineMap[X][Y].getFlagged())
        {
            System.out.println("You choose a flagged tile. un-flag the tile by flagging it again");
            return;
        }
        //Game Over
        if (MineMap[X][Y].getMine())
        {
            MineMap[X][Y].setSelected();
            GameOver = true;
            return;
        }

        NumOfMoves++;
        CheckValue(X,Y);

        if(MinesFlagged == MineAmount && Score == (GridSize * GridSize) - MineAmount)
        {
            Won = true;
        }
    }

    private void CheckValue(int X, int Y)
    {
        if(X < 0 || X >= GridSize)
        {
            return;
        }
        else if (Y < 0 || Y >= GridSize)
        {
            return;
        }
        else if (MineMap[X][Y].getSelected())
        {
            return;
        }
        else if (MineMap[X][Y].getFlagged())
        {
            return;
        }

        if (!MineMap[X][Y].getMine())
        {
            MineMap[X][Y].setSelected();
            Score++;
        }

        if (MineMap[X][Y].getValue() == '0')
        {
            CheckValue(X-1,Y);
            CheckValue(X+1,Y);
            CheckValue(X,Y+1);
            CheckValue(X,Y-1);
        }

    }

}
