import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This program allows for two human players to play Tic-Tac-Toe
 * by choosing positions 1-9 through input
 *
 * @author Matt Wurl
 */
public class TicTacToe {
    static ArrayList<Integer> positionsPlayer1 = new ArrayList<Integer>();
    static ArrayList<Integer> positionsPlayer2 = new ArrayList<Integer>();

    /**
     * Run the game of Tic-Tac-Toe
     * @param args command line arguments
     */
    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        while(true) {
            printBoard(gameBoard);

            Scanner in = new Scanner(System.in);

            // Player 1's turn
            System.out.print("Player 1 (X), enter a position (1-9): ");
            int posPlayer1 = in.nextInt();

            // Check for valid move for player 1
            while(positionsPlayer2.contains(posPlayer1) || positionsPlayer1.contains(posPlayer1)
            || posPlayer1 < 0 || posPlayer1 > 9) {
                System.out.print("Invalid position. Enter another: ");
                posPlayer1 = in.nextInt();
            }

            placePiece(gameBoard, posPlayer1, "player1");
            printBoard(gameBoard);

            // Check for game over after player 1's turn
            String result = checkGameOver();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            // Player 2's turn
            System.out.print("Player 2 (O), enter a position (1-9): ");
            int posPlayer2 = in.nextInt();

            // Check for valid move for player 2
            while (positionsPlayer1.contains(posPlayer2) || positionsPlayer2.contains(posPlayer2)
            || posPlayer2 < 0 || posPlayer2 > 9) {
                System.out.print("Invalid position. Enter another: ");
                posPlayer2 = in.nextInt();
            }

            placePiece(gameBoard, posPlayer2, "player2");

            // Check for game over after player 2's turn
            result = checkGameOver();
            if(result.length() > 0) {
                printBoard(gameBoard);
                System.out.println(result);
                break;
            }
        }
    }

    /**
     * Print the formatted game board
     * @param gameBoard the two-dimensional array of chars that represents the board
     * @throws java.util.InputMismatchException input is non-integer type
     */
    public static void printBoard(char[][] gameBoard) {
        System.out.println(); // Empty line before board
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println(); // Empty line after board
    }

    /**
     * Add the symbol pieces 'X' and 'O' on the game board array
     * @param gameBoard the two-dimensional array that represents the board
     * @param pos the position the player chooses
     * @param player the player of the current turn
     */
    public static void placePiece(char[][] gameBoard, int pos, String player) {
        char symbol = ' ';

        if (player.equals("player1")) {
            symbol = 'X';
            positionsPlayer1.add(pos); // Add the placed piece to the player 1 position ArrayList
        } else {
            symbol = 'O';
            positionsPlayer2.add(pos); // Add the placed piece to the player 2 position ArrayList
        }

        // Set the symbol in the gameBoard array
        // (Symbols can be placed at rows 0, 2, 4 and columns 0, 2, 4)
        // We can use the following expression instead of a long switch statement
        gameBoard[2 * ((pos - 1) / 3)][2 * ((pos - 1) % 3)] = symbol;
    }

    /**
     * Check if the game is over (if someone has won or the board is full)
     * @return the game result string (empty if game is not over)
     */
    public static String checkGameOver() {
        // Define winning positions
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List leftCross = Arrays.asList(1, 5, 9);
        List rightCross = Arrays.asList(7, 5, 3);

        // Create array list of winning conditions
        List<List> winConditions = new ArrayList<List>();

        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(botRow);
        winConditions.add(leftCol);
        winConditions.add(midCol);
        winConditions.add(rightCol);
        winConditions.add(leftCross);
        winConditions.add(rightCross);

        // Check if either player position array list contains the win conditions
        for (List list : winConditions) {
            if (positionsPlayer1.containsAll(list)) {
                return "Player 1 (X) wins!";
            } else if (positionsPlayer2.containsAll(list)) {
                return "Player 2 (O) wins!";
            }
        }

        // Check for full board (9 placed symbols)
        if (positionsPlayer1.size() + positionsPlayer2.size() == 9) {
            return "It's a tie!";
        }

        // Return string with length 0 if game is not over (none of the above conditions are met)
        return "";
    }

}