package LevinNSolution;
import java.util.Scanner;

/**
 * A minesweeper translation application.
 * Translates user text input "minesweeper fields" into text "field" outputs
 * with mines and cells with counts of mines nearby.
 *
 * @author nlevin11
 * @version 10/8/25
 */
public class LevinNSolution {
    /** A constant char for mine cells. **/
    public static final char MINE = '*';
    /** A field array for each field to build on. **/
    static Cell[][] myField;
    /** A main method to compile user input into a minefield. **/
    public static void main(final String[] args){
        Scanner in = new Scanner(System.in);
        int fieldNumber = 1;
        while(setField(in, fieldNumber)){
            convertField();
            printField();
            fieldNumber++;
        }
    }

    /**
     * A method to convert user input into an array of cells.
     * Stops running once termination line "0 0" is reached
     * @param theScan   A scanner for user input conversion.
     * @param theFieldNumber    A counter for the number of inputted fields.
     * @return  Returns true in all cases except for when the termination line is found.
     */
    public static boolean setField(Scanner theScan, int theFieldNumber) {
        int row = theScan.nextInt();
        int col = theScan.nextInt();
        /* Check for final row. */
        if (row == 0 || col == 0){
            return false;
        }
        /* Check for non-first row spacing. */
        if (theFieldNumber != 1){
            System.out.println();
        }
        System.out.println("Field # " + theFieldNumber + ":");
        myField = new Cell[row][col];
        theScan.nextLine();
        /* Builds field from input by placing mines and empty cells. */
        for(int r = 0; r < row; r++){
            String fieldLine = theScan.nextLine();
            for(int c = 0; c < col; c++){
                Cell temp = new Cell();
                if (fieldLine.charAt(c) == MINE) {
                    temp.setMine();
                }
                myField[r][c] = temp;
            }
        }
        return true;    }
    /** A method to convert a prepared field of mines and empty cells into mines and counted mine cells. **/
    public static void convertField() {
        int rowTotal = myField.length;
        int colTotal = myField[0].length;
        for(int r = 0; r < rowTotal; r++){
            for(int c = 0; c < colTotal; c++){
                /* Checks for mines and adds to the counters of the surrounding cells. */
                if (myField[r][c].isMine()){
                    /* Checks that surrounding cells are not out of bounds before updating mine count. */
                    for (int neighborRow = r-1; neighborRow <= r+1; neighborRow++){
                        for (int neighborCol = c-1; neighborCol <= c+1; neighborCol++){
                            boolean outOfBounds = neighborRow < 0 || neighborRow >= rowTotal ||
                                    neighborCol < 0 || neighborCol >= colTotal;
                            boolean isCenterCell = neighborRow == r && neighborCol == c;
                            if(!outOfBounds && !isCenterCell){
                                myField[neighborRow][neighborCol].addMineNearby();
                            }
                        }

                    }
                }
            }
        }
    }
    /** Prints finished field with mines and counted mine cells. **/
    public static void printField() {
        int colTotal = myField[0].length;
        for (Cell[] cells : myField) {
            for (int c = 0; c < colTotal; c++) {
                if (!cells[c].isMine()) {
                    System.out.print(cells[c].getMinesNearby());
                } else {
                    System.out.print(MINE);
                }
            }
            System.out.println();
        }
    }
}

class Cell {
    /** A boolean to define whether the cell is a mine. **/
    private  boolean isMine = false;
    /** An int to track the number mines in the vicinity of the cell. **/
    private int myNearbyMineCount;
    /** Sets the cell to a mine cell. **/
    public void setMine(){
        isMine = true;
    }
    /** Adds an instance of another mine nearby. **/
    public void addMineNearby(){
        myNearbyMineCount++;
    }

    /**
     * A method to access whether a cell is a mine.
     * @return  Returns a boolean value for whether the cell is a mine.
     */
    public boolean isMine() {
        return isMine;
    }

    /**
     * A method to store the number of mines nearby.
     * @return  Returns the number of found mines nearby.
     */
    public int getMinesNearby() {
        return myNearbyMineCount;
    }
}