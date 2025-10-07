package GroupAssignment1.RoenESolution;

import java.util.ArrayList;
import java.util.Scanner;

public class RoenESolution {

    public static final char MINE = '*';

    /**
     * The main method that runs the program.
     * @param args Command-line arguments.
     */
    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String[]> fields = getFields(in);
        printOutput(fields);
    }

    /**
     * Creates and returns an ArrayList of String[] where each
     * String[] is an individual field from the input.
     * @param theIn Input scanner.
     * @return An ArrayList of type String[] with the fields from the input.
     */
    private static ArrayList<String[]> getFields(final Scanner theIn) {
        int n = theIn.nextInt();
        int m = theIn.nextInt();
        theIn.nextLine();
        ArrayList<String[]> fields = new ArrayList<>();
        while (n > 0 && m > 0) {
            String[] field = new String[n];
            for (int i = 0; i < n; i++) {
                field[i] = theIn.nextLine(); // copy input to array
            }
            fields.add(field); // add field to ArrayList of all fields
            n = theIn.nextInt();
            m = theIn.nextInt();
            theIn.nextLine();
        }
        return fields;
    }

    /**
     * Prints the solution to the minesweeper fields.
     * @param theFields the fields to solve and print.
     */
    public static void printOutput(final ArrayList<String[]> theFields) {
        ArrayList<String> solution = new ArrayList<>();
        for (String[] field : theFields) {
            solution.add(solveField(field));
        }
        for (String line : solution) {
            System.out.println(line);
        }
    }

    /**
     * Creates a solution field based on the given input field.
     * @param theField the field from the input.
     * @return a string solution to the input field.
     */
    private static String solveField(final String[] theField) {
        String solution = "";
        for (int i = 0; i < theField.length; i++) {
            String line = theField[i];
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                solution = c == MINE ? solution.concat(String.valueOf(MINE))
                        : solution.concat(String.valueOf(countMines(theField, i, j)));
            }
            solution = solution.concat("\n");
        }
        return solution;
    }

    /**
     * Takes the given field and cell location and checks the surrounding cells for mines.
     * @param theField the input field.
     * @param theI the row value.
     * @param theJ the column value.
     * @return an integer number of mines surrounding the cell.
     */
    private static int countMines(final String[] theField, final int theI, final int theJ) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                boolean valid =
                        !(i == 0 && j == 0)
                        && theI + i >= 0
                        && theI + i < theField.length
                        && theJ + j >= 0
                        && theJ + j < theField[theI].length();
                if (valid && theField[theI + i].charAt(theJ + j) == MINE) {
                    count++;
                }
            }
        }
        return count;
    }
}
