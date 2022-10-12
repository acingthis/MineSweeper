import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class FileManager {

    public FileManager()
    {
        try {
            File ScoreBoard = new File("ScoreBoard.txt");
            ScoreBoard.createNewFile();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void WriteToFile(String UserName, int Moves)
    {
        try {
            String Board = PlaceScore(UserName,Moves);
            FileWriter ScoreBoard = new FileWriter("ScoreBoard.txt");
            ScoreBoard.write(Board);
            ScoreBoard.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private String PlaceScore(String UserName, int Moves)
    {
        String[] Scores = new String[10];
        int Counter = 0;
        try
        {
            File ScoreBoard = new File("ScoreBoard.txt");
            Scanner myReader = new Scanner(ScoreBoard);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                Scores[Counter] = data;
                Counter++;
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String Replace = "";
        String Store = "";
        int x;
        for (x = 0; x < Scores.length-1; x++)
        {
            if (Moves < Integer.parseInt(Scores[x].substring(Scores[x].indexOf(":")+2)))
            {
                Replace = Scores[x];
                Scores[x] = UserName + " : " + Moves;
                break;
            }
        }
        for (int i = x+1; x < Scores.length-1;x++)
        {
            if (!Objects.equals(Replace, ""))
            {
                if (i >= Scores.length -2)
                {
                    Store = Scores[i];
                    Scores[i] = Replace;
                    Replace = Store;
                }
            }
        }

        if (!Objects.equals(Replace, ""))
        {
            Scores[Scores.length-1] = Replace;
        }

        String Board = "";
        for (String score:Scores)
        {
            Board = Board + score + "\n";
        }
        return Board;
    }

    public String LeaderBoard()
    {
        String[] Scores = new String[10];
        int Counter = 0;
        try
        {
            File ScoreBoard = new File("ScoreBoard.txt");
            Scanner myReader = new Scanner(ScoreBoard);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                Scores[Counter] = data;
                Counter++;

            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String Board = "Player : Moves";
        for (String score:Scores)
        {
            Board = Board + score + "\n";
        }

        return Board;
    }

}
