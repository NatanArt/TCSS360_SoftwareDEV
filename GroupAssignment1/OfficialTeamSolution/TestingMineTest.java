package GroupAssignment1.OfficialTeamSolution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit testing for the Mine fields
 *
 * @author Team
 * @version Autumn 2025
 */
public class TestingMineTest {

    /**
     * Tests that checkEmpty correctly replaces
     * all '.' empty cells with '0' while leaving mines and
     * existing numbers unchanged.
     */
    @Test
    void testCheckEmpty() {
        char[][] field = {
                {'.', '*'},
                {'.', '.'}
        };

        OfficialTeamSolution.checkEmpty(field);

        assertEquals('0', field[0][0]);
        assertEquals('*', field[0][1]);
        assertEquals('0', field[1][0]);
        assertEquals('0', field[1][1]);
    }

    /**
     * Tests checkSurroundings when the mine is located at
     * the top-left corner (edge case).
     * Ensures only valid neighbors are incremented and the mine remains unchanged.
     */
    @Test
    void testCheckSurroundingsEdge() {
        int currentRow = 0;
        int currentCol = 0;
        char[][] field = {
                {'*', '.'},
                {'.', '.'}
        };
        OfficialTeamSolution.checkSurroundings(currentRow, currentCol, field);

        assertEquals('*', field[0][0]);
        assertEquals('1', field[0][1]);
        assertEquals('1', field[1][1]);
        assertEquals('1', field[1][0]);
    }

    /**
     * Tests checkSurroundings when the mine is located in the middle of the grid.
     * All 8 neighbors should be incremented to '1'.
     */
    @Test
    void testCheckSurroundingMiddle() {
        int currentRow = 1;
        int currentCol = 1;
        char[][] field = {
                {'.', '.', '.'},
                {'.', '*', '.'},
                {'.', '.', '.'}
        };
        OfficialTeamSolution.checkSurroundings(currentRow, currentCol, field);

        assertEquals('*', field[1][1]);
        assertEquals('1', field[0][1]);
        assertEquals('1', field[0][2]);
        assertEquals('1', field[1][0]);
        assertEquals('1', field[1][2]);
        assertEquals('1', field[2][0]);
        assertEquals('1', field[2][1]);
        assertEquals('1', field[2][2]);

    }

    /**
     * Tests checkSurroundings when there are two adjacent mines
     * whose areas of influence intersect.
     * Neighboring cells should accumulate counts correctly ('2').
     */
    @Test
    void testCheckSurroundingIntersection() {
        int firstRow = 1;
        int firstCol = 1;
        int secondRow = 2;
        int secondCol = 1;
        char[][] field = {
                {'.', '.', '.'},
                {'.', '*', '.'},
                {'.', '*', '.'}
        };
        OfficialTeamSolution.checkSurroundings(firstRow, firstCol, field);
        OfficialTeamSolution.checkSurroundings(secondRow, secondCol, field);

        assertEquals('*', field[1][1]);
        assertEquals('1', field[0][1]);
        assertEquals('1', field[0][2]);
        assertEquals('2', field[1][0]);
        assertEquals('2', field[1][2]);
        assertEquals('2', field[2][0]);
        assertEquals('*', field[2][1]);
        assertEquals('2', field[2][2]);

    }

    /**
     * Tests that checkEmpty only replaces '.' empty cells with '0'
     * while leaving numeric counts and mines untouched.
     */
    @Test
    void checkEmpty() {
        char[][] field = {
                {'1', '*', '1'},
                {'1', '1', '1'},
                {'.', '.', '.'}
        };

        OfficialTeamSolution.checkEmpty(field);

        assertEquals('1', field[0][0]);
        assertEquals('*', field[0][1]);
        assertEquals('1', field[0][2]);
        assertEquals('1', field[1][0]);
        assertEquals('1', field[1][1]);
        assertEquals('1', field[1][2]);
        assertEquals('0', field[2][0]);
        assertEquals('0', field[2][1]);
        assertEquals('0', field[2][2]);
    }
}
