package Practice;
import java.util.*;

public class BasicTicTacToe {

    static char[][] tttboard = { { ' ', '|', ' ', '|', ' ' },
            { '-', '+', '-', '+', '-' },
            { ' ', '|', ' ', '|', ' ' },
            { '-', '+', '-', '+', '-' },
            { ' ', '|', ' ', '|', ' ' }
    };

    public static void main(String[] args) {

        printBoard(tttboard);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your placement (from 1-9): ");
            int playerPlacement = scanner.nextInt();

            while (!isValidMove(playerPlacement)) {
                System.out.println("Position taken. Please enter another number");
                playerPlacement = scanner.nextInt();
            }
            placePiece(playerPlacement, tttboard, "player");
            printBoard(tttboard);

            if (hasWon("player")) {
                printBoard(tttboard);
                System.out.println("Player won!");
                break;
            }
            Random rand = new Random();
            int AIplacement = rand.nextInt(9) + 1;

            while (!isValidMove(AIplacement)) {
                AIplacement = rand.nextInt(9) + 1;
            }
            placePiece(AIplacement, tttboard, "AI");
            printBoard(tttboard);

            if (hasWon("AI")) {
                printBoard(tttboard);
                System.out.println("AI won!");
                break;
            }

            if (isBoardFull()) {
                printBoard(tttboard);
                System.out.println("Draw!");
                break;
            }
        }
        scanner.close();

    }

    public static void printBoard(char[][] tttboard) {
        for (char[] row : tttboard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(int placement, char[][] tttboard, String user) {

        char symbol = user.equals("player") ? 'X' : 'O';

        switch (placement) {
            case 1:
                tttboard[0][0] = symbol;
                break;
            case 2:
                tttboard[0][2] = symbol;
                break;
            case 3:
                tttboard[0][4] = symbol;
                break;
            case 4:
                tttboard[2][0] = symbol;
                break;
            case 5:
                tttboard[2][2] = symbol;
                break;
            case 6:
                tttboard[2][4] = symbol;
                break;
            case 7:
                tttboard[4][0] = symbol;
                break;
            case 8:
                tttboard[4][2] = symbol;
                break;
            case 9:
                tttboard[4][4] = symbol;
                break;
            default:
                System.out.println("Please enter a number from 1-9");
                break;
        }
        printBoard(tttboard);
    }

    public static boolean hasWon(String user) {

        char symbol = user.equals("player") ? 'X' : 'O';

        for (int i = 0; i < 5; i += 2) {
            if (tttboard[i][0] == symbol && tttboard[i][2] == symbol && tttboard[i][4] == symbol) {
                return true;
            }
        }

        for (int j = 0; j < 5; j += 2) {
            if (tttboard[0][j] == symbol && tttboard[2][j] == 'X' && tttboard[4][j] == symbol) {
                return true;
            }
        }

        if (tttboard[0][0] == symbol && tttboard[2][2] == symbol && tttboard[4][4] == symbol) {
            return true;
        }
        if (tttboard[0][4] == symbol && tttboard[2][2] == symbol && tttboard[4][4] == symbol) {
            return true;
        }
        return false;
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 5; j += 2) {
                if (tttboard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidMove(int position) {
        switch (position) {
            case 1:
                return tttboard[0][0] == ' ';
            case 2:
                return tttboard[0][2] == ' ';
            case 3:
                return tttboard[0][4] == ' ';
            case 4:
                return tttboard[2][0] == ' ';
            case 5:
                return tttboard[2][2] == ' ';
            case 6:
                return tttboard[2][4] == ' ';
            case 7:
                return tttboard[4][0] == ' ';
            case 8:
                return tttboard[4][2] == ' ';
            case 9:
                return tttboard[4][4] == ' ';
            default:
                return false;
        }
    }
}