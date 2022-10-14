import MineSweep.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserInput();
    }

    private static void UserInput()
    {
        int GridSize = 10;

        Grid Map = new Grid(GridSize);

        boolean GameOver = false;

        Scanner Scan = new Scanner(System.in);
        boolean Flag;

        do {
            Map.Print();

            Flag = false;
            int X;
            int Y;
            do
            {
                try {

                    System.out.println("Do you wish to flag something?");
                    System.out.print("> ");
                    String Flagging = Scan.nextLine();

                    Flag = switch (Flagging.toLowerCase()) {
                        case "yes","flag","y","f" -> true;
                        default -> false;
                    };

                    System.out.println("Please enter X Coordinate:");
                    System.out.print("> ");
                    X = Integer.parseInt(Scan.nextLine());

                    System.out.println("Please enter Y Coordinate:");
                    System.out.print("> ");
                    Y = Integer.parseInt(Scan.nextLine());
                }
                catch (Exception e)
                {
                    System.out.println(ConsoleColor.RED);
                    System.out.println("\nThere has been an issue please try again\n");
                    X = -1;
                    Y = -1;
                    System.out.println(ConsoleColor.RESET);
                }

                if (Y >= GridSize || X >= GridSize)
                {
                    System.out.println("An index is out of bounds, the grid goes from 0-" + (GridSize-1) + "\n");
                    X = -1;
                    Y = -1;
                }
            }
            while(X < 0 && Y < 0);

            if (Flag)
            {
                Map.Flag(Y,X);
            }
            else
            {
                Map.UserInput(Y,X);
            }

            if (Map.GameOver())
            {
                String LOSE = "GAME OVER";
                System.out.print(ConsoleColor.RED);
                System.out.println("\n" + LOSE + "\n");
                System.out.print(ConsoleColor.RESET);
                Map.Print();
                System.out.print(ConsoleColor.RED);
                System.out.println("\n" + LOSE + "\n");
                System.out.println( "     _.-^^---....,,--       \n" +
                                    " _--                 ---_  \n" +
                                    "<                        >)\n" +
                                    "|                         | \n" +
                                    " \\._                   _./  \n" +
                                    "    ```--. . , ; .--'''       \n" +
                                    "          | |   |             \n" +
                                    "       .-=||  | |=-.   \n" +
                                    "       `-=#$%&%$#=-'   \n" +
                                    "          | ;  :|     \n" +
                                    " _____.,-#%&$@%#&#~,._____");
                System.out.println("\n" + LOSE + "\n");
                System.out.print(ConsoleColor.RESET);
                GameOver = true;
            }
            else if (Map.Win())
            {
                String WINNING = "YOU WIN";
                System.out.print(ConsoleColor.GREEN);
                System.out.println("\n" + WINNING + "\n");
                Map.Print();
                System.out.print(ConsoleColor.RESET);
                System.out.print(ConsoleColor.GREEN);
                System.out.println("\n" + WINNING + "\n");
                System.out.println( "                                 _       \n" +
                                    "                                | |      \n" +
                                    "  ___ ___  _ __   __ _ _ __ __ _| |_ ___ \n" +
                                    " / __/ _ \\| '_ \\ / _` | '__/ _` | __/ __|\n" +
                                    "| (_| (_) | | | | (_| | | | (_| | |_\\__ \\\n" +
                                    " \\___\\___/|_| |_|\\__, |_|  \\__,_|\\__|___/\n" +
                                    "                  __/ |                  \n" +
                                    "                 |___/ ");
                System.out.println("\n" + WINNING + "\n");
                System.out.println("Your score: " + Map.Score + " Moves Made: " + Map.NumOfMoves);
                System.out.println("\n" + WINNING + "\n");
                System.out.print(ConsoleColor.RESET);
                break;
            }
        }
        while (!GameOver);

        //Scoring
        FileManager ScoreBoard = new FileManager();
        System.out.print(ConsoleColor.BLUE);
        System.out.println(ScoreBoard.LeaderBoard());
        System.out.print(ConsoleColor.RESET);
        if (Map.Win())
        {
            System.out.println("What is your Username?");
            System.out.print("> ");
            String Player = Scan.nextLine();
            ScoreBoard.WriteToFile(Player,Map.NumOfMoves);
        }


        //play again?
        boolean Play;
        System.out.println("Do you want to play again?");
        System.out.print("> ");
        String Again = Scan.nextLine();
        Play = switch (Again.toLowerCase()) {
            case "yes","y" -> true;
            default -> false;
        };

        if (Play)
        {
            UserInput();
        }
    }

}
