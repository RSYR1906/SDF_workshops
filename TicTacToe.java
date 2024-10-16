import java.util.Scanner;

public class TicTacToe {
    static char[][] board = { { '_', '_', '_' },
            { '_', '_', '_' },
            { '_', '_', '_' } };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame(scanner);
        scanner.close();
    }

    public static void playGame(Scanner scanner) {
        printBoard();
        while (true) {
            playerMove(scanner);
            if (isGameOver())
                break;
            aiMove();
            if (isGameOver())
                break;
        }
    }

    // Print the game board
    public static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if game is over
    public static boolean isGameOver() {
        if (hasPlayerWon('X')) {
            printBoard();
            System.out.println("AI Wins!");
            return true;
        }
        if (hasPlayerWon('O')) {
            printBoard();
            System.out.println("Player Wins!");
            return true;
        }
        if (isBoardFull()) {
            printBoard();
            System.out.println("It's a tie!");
            return true;
        }
        return false;
    }

    // Check if a player has won
    public static boolean hasPlayerWon(char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;
        return false;
    }

    // Check if the board is full (tie)
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    // Player move
    public static void playerMove(Scanner scanner) {
        int row, col;
        while (true) {
            System.out.println("Enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row < 0 || col < 0 || row > 2 || col > 2 || board[row][col] != '_') {
                System.out.println("This move is not valid");
            } else {
                break;
            }
        }
        board[row][col] = 'O';
        printBoard();
    }

    // AI move using Minimax algorithm
    public static void aiMove() {
        int[] bestMove = minimax(2, 'X'); // Max depth of 2 for simplicity, AI is 'X'
        board[bestMove[0]][bestMove[1]] = 'X';
        printBoard();
    }

    // Minimax algorithm
    public static int[] minimax(int depth, char player) {
        int bestScore = (player == 'X') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int[] bestMove = { -1, -1 };

        if (hasPlayerWon('X'))
            return new int[] { -1, -1, 10 };
        if (hasPlayerWon('O'))
            return new int[] { -1, -1, -10 };
        if (isBoardFull())
            return new int[] { -1, -1, 0 };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = player;
                    if (player == 'X') {
                        currentScore = minimax(depth - 1, 'O')[2];
                        if (currentScore > bestScore) {
                            bestScore = currentScore;
                            bestMove = new int[] { i, j };
                        }
                    } else {
                        currentScore = minimax(depth - 1, 'X')[2];
                        if (currentScore < bestScore) {
                            bestScore = currentScore;
                            bestMove = new int[] { i, j };
                        }
                    }
                    board[i][j] = '_'; // Undo move
                }
            }
        }
        return new int[] { bestMove[0], bestMove[1], bestScore };
    }
}