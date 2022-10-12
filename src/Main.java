import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int GridSize = 10;

        Grid Map = new Grid(GridSize);

        boolean GameOver = false;
        boolean Win = false;

        Scanner Scan = new Scanner(System.in);
        boolean Flag;

        do {
            Map.Print();

            Flag = false;
            int X = -1;
            int Y = -1;
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
                    System.out.println("There has been an issue please try again");
                    return;
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
                System.out.println("\nGAME OVER\n");
                Map.Print();
                System.out.println("\nGAME OVER\n");
                System.out.println("     _.-^^---....,,--       \n" +
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
                System.out.println("\nGAME OVER\n");
                GameOver = true;
            }
            else if (Map.Win())
            {
                System.out.println("\nYOU WIN\n");
                Map.Print();
                System.out.println("\nYOU WIN\n");
                System.out.println( "                                 _       \n" +
                                    "                                | |      \n" +
                                    "  ___ ___  _ __   __ _ _ __ __ _| |_ ___ \n" +
                                    " / __/ _ \\| '_ \\ / _` | '__/ _` | __/ __|\n" +
                                    "| (_| (_) | | | | (_| | | | (_| | |_\\__ \\\n" +
                                    " \\___\\___/|_| |_|\\__, |_|  \\__,_|\\__|___/\n" +
                                    "                  __/ |                  \n" +
                                    "                 |___/ ");
                System.out.println("\nYOU WIN\n");
                break;
            }
        }
        while (!GameOver);
    }

}
