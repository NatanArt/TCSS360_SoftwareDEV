package GroupAssignment1.OfficialTeamSolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Program to generate test input for the Minesweeper problem
 * and direct the output to a file named "minefield_inputs.txt".
 */
public class MinesweeperTestGenerator {

    private static final int MAX_SIZE = 100;
    private static final Random random = new Random();

    // Field to hold the PrintWriter object for file output
    private static PrintWriter writer;
    private static final String OUTPUT_DIRECTORY = "C:\\Users\\njart\\UWT\\TCSS360\\TCSS360_SoftwareDEV\\GroupAssignment1\\OfficialTeamSolution\\";
    private static final String OUTPUT_FILENAME = OUTPUT_DIRECTORY + "minefield_inputs.txt";

    /**
     * Generates an n x m Minesweeper field with a specified mine density.
     */
    private static String[] generateField(int n, int m, double mineDensity) {
        String[] field = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder(m);
            for (int j = 0; j < m; j++) {
                if (random.nextDouble() < mineDensity) {
                    row.append('*');
                } else {
                    row.append('.');
                }
            }
            field[i] = row.toString();
        }
        return field;
    }

    /**
     * Prints a generated field to the output file in the required input format.
     */
    private static void printField(int n, int m, String[] field) {
        // Use the writer object instead of System.out.println
        writer.println(n + " " + m);
        for (String row : field) {
            writer.println(row);
        }
    }

    public static void main(String[] args) {
        try {
            // Initialize the PrintWriter to write to the specified file
            writer = new PrintWriter(new File(OUTPUT_FILENAME));
            System.out.println("Generating test cases and writing to " + OUTPUT_FILENAME + "...");

            // --- Test Case 1: Small Field ---
            int n1 = 4, m1 = 4;
            String[] field1 = generateField(n1, m1, 0.20);
            printField(n1, m1, field1);

            // --- Test Case 2: Maximum size field (100x100) ---
            int n2 = MAX_SIZE, m2 = MAX_SIZE;
            String[] field2 = generateField(n2, m2, 0.10);
            printField(n2, m2, field2);

            // --- Test Case 3: Minimum size field (1x1) ---
            int n3 = 1, m3 = 1;
            String[] field3 = generateField(n3, m3, 0.50);
            printField(n3, m3, field3);

            // --- Test Case 4: Rectangular field, high mine density ---
            int n4 = 10, m4 = 30;
            String[] field4 = generateField(n4, m4, 0.45);
            printField(n4, m4, field4);

            // --- Termination Signal ---
            // The first field line where n=m=0 represents the end of input
            writer.println("0 0");

        } catch (FileNotFoundException e) {
            // Handle the case where the file cannot be created or opened for writing
            System.err.println("Error: Could not open file for writing: " + e.getMessage());
        } finally {
            // Important: Close the PrintWriter to ensure all data is written to the file
            if (writer != null) {
                writer.close();
            }
            System.out.println("Test cases successfully generated and file closed.");
        }
    }
}