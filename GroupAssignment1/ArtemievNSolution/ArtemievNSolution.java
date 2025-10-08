/*
 * Assignment: Group Assignment 1
 * Class: TCSS360
 * Term: Fall 2025
 * File: ArtemievNSolution
 */
package GroupAssignment1.ArtemievNSolution;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A minesweeper solver application that processes one or more minesweeper fields
 * from standard input, solves them, and prints the results to standard output.
 * <p>
 * The program reads the dimensions and grid of each field, then calculates the
 * number of adjacent mines for each non-mine square.
 * The processing continues until a field with zero dimensions (0 0) is encountered.
 * The solved fields are then printed to the console.
 *
 * @author ArtemievNSolution (The code author is assumed to be part of the package name)
 * @version 10/8/2025
 */
public class ArtemievNSolution {

    /**
     * The main method and entry point of the application.
     * <p>
     * It orchestrates the process by:
     * <ol>
     *     <li>Creating a {@link Scanner} to read from standard input.</li>
     *     <li>Calling {@link #getFields(Scanner)} to read and solve all fields.</li>
     *     <li>Calling {@link #printFields(ArrayList)} to display the results.</li>
     * </ol>
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<ArrayList<String[]>> fields = getFields(scan);
        printFields(fields);
    }

    /**
     * Reads minesweeper fields from a scanner, solves each one, and stores the results.
     * <p>
     * The input format is as follows: The first line of each field contains two
     * integers, `n` and `m`, for the number of rows and columns, respectively.
     * This is followed by `n` lines, each containing a string of ' * ' and ' . '
     * characters representing the minefield. The input is terminated by a field
     * with dimensions 0 0.
     *
     * @param theScan The {@link Scanner} object used to read from the input source.
     * @return A list of solved minesweeper fields, where each field is represented
     *         as a list of string arrays.
     */
    private static ArrayList<ArrayList<String[]>> getFields(final Scanner theScan) {
        int n;
        int m;
        ArrayList<ArrayList<String[]>> fields = new ArrayList<>();
        while(theScan.hasNextLine()) {
            if(theScan.hasNextInt()) {
                n = theScan.nextInt();
                m = theScan.nextInt();
                if(n != 0) {
                    ArrayList<String[]> field = new ArrayList<>();
                    for(int i = 0; i < n; i++) {
                        String line =  theScan.nextLine();
                        while(line.isEmpty()) {
                            line = theScan.nextLine();
                        }
                        field.add(line.split(""));
                    }
                    solveField(field, n, m);
                    fields.add(field);
                }
            }
            else {
                theScan.nextLine();
            }
        }
        return fields;
    }

    /**
     * Solves a single minesweeper field by calculating the number of adjacent mines.
     * <p>
     * For each cell that does not contain a mine ('*'), this method counts the
     * number of neighboring cells that contain a mine. The original ' . ' character
     * is then replaced with the calculated count as a string.
     *
     * @param theField The minesweeper field to be solved, represented as a list of
     *                 string arrays. This list is modified directly.
     * @param theN     The number of rows in the field.
     * @param theM     The number of columns in the field.
     */
    private static void solveField(ArrayList<String[]> theField, final int theN, final int theM) {
        for(int i = 0;  i < theN; i++) {
            for(int j = 0; j < theM; j++) {
                if(!theField.get(i)[j].equals("*")) {
                    int mineCount = 0;
                    for(int k = i - 1;  k <= i + 1 ; k++) {
                        for(int l = j - 1; l <= j + 1; l++) {
                            if(k < 0 || l < 0 || k >= theN || l >= theM) {
                                continue;
                            }
                            if(theField.get(k)[l].equals("*")) {
                                mineCount++;
                            }
                        }
                    }
                    theField.get(i)[j] = Integer.toString(mineCount);
                }
            }
        }
    }

    /**
     * Prints a list of solved minesweeper fields to the console.
     * <p>
     * Each field is printed with a header (e.g., "Field 1:") followed by its
     * grid content. A blank line is added after each field for readability.
     *
     * @param theFields A list of solved minesweeper fields, where each field is a
     *                  list of string arrays.
     */
    public static void printFields(final ArrayList<ArrayList<String[]>> theFields) {
        for (int i = 0; i < theFields.size(); i++) {
            System.out.println("Field " + (i+1) + ":");
            for(int j = 0; j < theFields.get(i).size(); j++) {
                for(int k = 0; k < theFields.get(i).get(j).length; k++) {
                    System.out.print(theFields.get(i).get(j)[k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
