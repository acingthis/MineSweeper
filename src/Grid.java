public class Grid {

    Tile[][] MineMap;
    int MineAmount;
    int GridSize;

    boolean GameOver = false;

    public Grid(int GridSize)
    {
        MineMap = new Tile[GridSize][GridSize];

        for(int x = 0; x < GridSize; x++)
        {
            for(int i = 0; i < GridSize; i++)
            {
                MineMap[x][i] = new Tile();
            }
        }
        MineAmount = GridSize - (int)(Math.random() * 2);
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
        if(X < 0 || X >= GridSize)
        {
            return;
        }
        else if (Y < 0 || Y >= GridSize)
        {
            return;
        }

        if (MineMap[X][Y].getMine())
        {
            return;
        }
        else
        {
            MineMap[X][Y].setValue((char) (1 + (int) MineMap[X][Y].getValue()));
        }
    }

    public void Print()
    {
        for(int x = 0; x < GridSize; x++)
        {
            //Coordinates
            System.out.print(x + "| ");
            for(int i = 0; i < GridSize; i++)
            {
                if (!MineMap[x][i].getSelected())
                {
                    System.out.print("#" + " ");
                }
                else
                {
                    if (MineMap[x][i].getMine())
                    {
                        System.out.print("M" + " ");
                    }
                    else
                    {
                        System.out.print(MineMap[x][i].getValue() + " ");
                    }
                }
            }
            System.out.print("\n");
        }


        //Coordinates outline
        System.out.print("   ");
        for(int i = 0; i < GridSize; i++)
        {
            System.out.print("- ");
        }
        System.out.print("\n   ");
        //Grid values
        for(int i = 0; i < GridSize; i++)
        {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    public boolean GameOver()
    {
        return GameOver;
    }

    public void UserInput(int X, int Y)
    {
        //Game Over
        if (MineMap[X][Y].getMine())
        {
            GameOver = true;
            return;
        }


    }

}
