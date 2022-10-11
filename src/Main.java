import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int GridSize = 10;

        Grid Map = new Grid(GridSize);

        boolean GameOver = false;

        Scanner Scan = new Scanner(System.in);

        do {
            Map.Print();

            int X = -1;
            int Y = -1;
            do
            {
                try {
                    System.out.println("Please enter X Coordinate:");
                    System.out.print("> ");
                    X = Scan.nextInt();

                    System.out.println("Please enter Y Coordinate:");
                    System.out.print("> ");
                    Y = Scan.nextInt();
                }
                catch (Exception e)
                {
                    System.out.println("There has been an issue please try again");
                    return;
                }
            }
            while(X < 0 && Y < 0);

            Map.UserInput(Y,X);


            if (Map.GameOver())
            {
                System.out.println("\nGAME OVER\n");
                GameOver = true;
            }
        }
        while (!GameOver);
    }

}
