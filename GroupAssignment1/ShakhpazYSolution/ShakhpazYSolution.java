package GroupAssignment1.ShakhpazYSolution;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Minesweeper Solving application given a proper input, as
 * shown in the minesweeper.pdf in the command line.
 *
 * @author Yusuf Shakhpaz
 * @version Autumn 2025
 */
public class ShakhpazYSolution {

    /** Symbol representing a mine cell. */
    private static final char MINE =  '*';

    /** Symbol representing an Empty cell. */
    private static final char EMPTY = '.';

    /** All possible directions for each cell */
    private static final int[][] DIRECTIONS = {
            {-1, -1},
            {-1,  0},
            {-1,  1},
            { 0, -1},
            { 0,  1},
            { 1, -1},
            { 1,  0},
            { 1,  1}
    };

    /**
     * Main entry point for the program.
     *
     * @param args not used; all input is read from System.in
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<char[][]> mineFields = generateMineFields(sc);
        solveFields(mineFields);
        printOutput(mineFields);
    }

    /**
     * Reads multiple minefields from input until a line "0 0" is encountered.
     *
     * @param scanner Scanner reading from System.in (command line)
     *
     * @return a list of minefield grids, each represented as a 2D char array
     */
    public static ArrayList<char[][]> generateMineFields(Scanner scanner) {
        ArrayList<char[][]> mineFields = new ArrayList<>();
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        while (row > 0 && col > 0) {
            scanner.nextLine();
            char[][] mineField = new char[row][col];
            for (int r = 0; r < row; r++) {
                String line = scanner.nextLine();
                for (int c = 0; c < col; c++) {
                    mineField[r][c] = line.charAt(c);
                }
            }
            mineFields.add(mineField);
            row = scanner.nextInt();
            col = scanner.nextInt();
        }

        return mineFields;
    }

    /**
     * Solves each minefield in the list by filling in the mine counts.
     *
     * @param theFields list of minefields to solve
     */
    public static void solveFields(ArrayList<char[][]> theFields) {
        for (char[][] field: theFields) {
            solveField(field);
        }
    }

    /**
     * Solves a single minefield grid by replacing empty cells with counts
     * of adjacent mines.
     *
     * @param theField 2D char array representing the minefield
     */
    public static void solveField(char[][] theField) {
        for (int i = 0; i < theField.length; i++) {
            for (int j = 0; j < theField[i].length; j++) {
                char current = theField[i][j];
                if (current == MINE) {
                    checkSurroundings(i, j, theField);
                }
            }
        }

        checkEmpty(theField);
    }

    /**
     * Increments the counts of all valid neighboring cells surrounding a mine.
     *
     * @param theCurrentRow row index of the mine
     * @param theCurrentCol column index of the mine
     * @param theField      minefield grid
     */
    public static void checkSurroundings(int theCurrentRow, int theCurrentCol, char[][] theField) {
        for (int[] dir : DIRECTIONS) {
            int newRow = theCurrentRow + dir[0];
            int newCol = theCurrentCol + dir[1];
            if (newRow >= 0 && newRow < theField.length &&
                    newCol >= 0 && newCol < theField[0].length &&
                    theField[newRow][newCol] != MINE) {

                if (theField[newRow][newCol] == '.') {
                    theField[newRow][newCol] = '1';
                } else if (theField[newRow][newCol] >= '1') {
                    theField[newRow][newCol]++;
                }

            }
        }
    }

    /**
     * Replaces all remaining empty cells ('.') with '0'
     * to indicate zero adjacent mines. Only call this
     * function after having a solved minefield
     *
     * @param theField minefield grid
     */
    public static void checkEmpty(char[][] theField) {
        for (int i = 0; i < theField.length; i++) {
            for (int j = 0; j < theField[0].length; j++) {
                if (theField[i][j] == EMPTY) {
                    theField[i][j] = '0';
                }
            }
        }
    }

    /**
     * Prints the solved minefields to standard output in the correct format.
     * Each field is preceded by "Field #n:" and separated by a blank line,
     * except no blank line after the last field.
     *
     * @param theMineFields list of solved minefields
     */
    public static void printOutput(ArrayList<char[][]> theMineFields) {
        for (int i = 0; i < theMineFields.size(); i++) {
            System.out.println("Field #" + (i + 1) + ":");
            for (char[] line : theMineFields.get(i)) {
                System.out.println(new String(line));
            }
            if (i != theMineFields.size() - 1) {
                System.out.println();
            }
        }
    }


}
