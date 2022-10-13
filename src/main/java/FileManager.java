import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class FileManager {

    String fileName = "ScoreBoard.txt";

    public FileManager()
    {
        try {
            File ScoreBoard = new File(fileName);
            if (ScoreBoard.createNewFile())
            {
                System.out.println("Scoreboard has been made");
            }
        }
        catch (IOException e)
        {
            System.out.println("ERROR making Scoreboard file");
            e.printStackTrace();
        }
    }

    public final void WriteToFile(String UserName, int Moves)
    {
        try {
            String Board = PlaceScore(UserName,Moves+1);
            FileWriter ScoreBoard = new FileWriter(fileName);
            ScoreBoard.write(Board);
            ScoreBoard.close();
        } catch (IOException e) {
            System.out.println("ERROR writing to Scoreboard");
            e.printStackTrace();
        }
    }

    private String PlaceScore(String UserName, int Moves)
    {
        String[] Scores = new String[10];
        int Counter = 0;
        try
        {
            File ScoreBoard = new File(fileName);
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
            System.out.println("ERROR problem reading scoreboard file");
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
        for (int i = x; i < Scores.length-1;i++)
        {
            if (!Objects.equals(Replace, ""))
            {
                Store = Scores[i];
                Scores[i] = Replace;
                Replace = Store;
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
            File ScoreBoard = new File(fileName);
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
            System.out.println("ERROR reading scoreboard");
            e.printStackTrace();
        }

        String Board =  "Player : Moves\n" +
                        "---------------\n";
        for (String score:Scores)
        {
            if (score == null)
            {
                return Board;
            }
            Board = Board + score + "\n";
        }

        return Board;
    }

}
