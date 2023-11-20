package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    private SudokuBoard board;
    private SudokuSolver solver;

    @BeforeEach
    void setUp() {
        solver = new BacktrackingSudokuSolver();
        board = new SudokuBoard(solver);
    }
    @Test
    void testConstructorThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SudokuBoard(null);
        });

        String expectedMessage = "Solver cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testToString() {
        String result = board.toString();
        assertNotNull(result);
        assertFalse(result.trim().isEmpty());
    }

    @Test
    void testEquals() {

        assertTrue(board.equals(board), "Board should be equal to itself.");

        SudokuBoard anotherBoard = new SudokuBoard(solver);
        board.set(0, 0, 5);
        anotherBoard.set(0, 0, 5);
        assertTrue(board.equals(anotherBoard), "Boards with the same values should be equal.");

        anotherBoard.set(0, 0, 1);
        assertFalse(board.equals(anotherBoard), "Boards with different values should not be equal.");

        assertFalse(board.equals(null), "Board should not be equal to null.");

        assertFalse(board.equals(new Object()), "Board should not be equal to an object of a different type.");
    }


    @Test
    void testHashCode() {
        SudokuBoard anotherBoard = new SudokuBoard(solver);
        board.set(0, 0, 5);
        anotherBoard.set(0, 0, 5);
        assertEquals(anotherBoard.hashCode(), board.hashCode());
    }

    @Test
    void testEqualsAndHashCodeConsistency() {
        SudokuBoard firstBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard secondBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        for (int row = 0; row < SudokuBoard.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuBoard.GRID_SIZE; col++) {
                int value = row * SudokuBoard.GRID_SIZE + col;
                firstBoard.set(row, col, value);
                secondBoard.set(row, col, value);
            }
        }

        assertTrue(firstBoard.equals(secondBoard), "Both boards should be equal.");

        assertEquals(firstBoard.hashCode(), secondBoard.hashCode(), "Equal objects must have equal hash codes.");

        firstBoard.set(0, 0, 5);
        secondBoard.set(0, 0, 1);

        assertFalse(firstBoard.equals(secondBoard), "Boards with different values should not be equal.");

        assertNotEquals(firstBoard.hashCode(), secondBoard.hashCode(), "Objects that are not equal should not have the same hash code.");
    }




    @Test
    void testGetAndSet() {
        board.set(0, 0, 1);
        assertEquals(1, board.get(0, 0));
    }

    @Test
    public void testCheckBoardFaultyRow() {
        board.solveGame();
        board.set(0, 0, 1);
        board.set(1, 0, 1);
        assertFalse(board.checkBoard());
    }

    @Test
    public void testCheckBoardFaultyColumn() {
        setboardForWrongColumns(board);
        assertFalse(board.checkBoard());
    }
    private void setboardForWrongColumns(SudokuBoard sudokuBoard){

        for(int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            for(int j = 0; j < SudokuBoard.GRID_SIZE; j++){
                sudokuBoard.set(i,j,j+1);
            }
        }
    }

    @Test
    public void testCheckBoardFaultySquare() {
        setboardForWrongSquare(board);
        assertFalse(board.checkBoard());
    }
    private void setboardForWrongSquare(SudokuBoard sudokuBoard){

        for(int j = 0; j < SudokuBoard.GRID_SIZE; j++){
            sudokuBoard.set(0,j,j+1);
        }

        for(int i = 1; i < SudokuBoard.GRID_SIZE; i++) {
            for(int j = 0; j < SudokuBoard.GRID_SIZE; j++){
                sudokuBoard.set(i,j,sudokuBoard.get(i-1,(j+1)%9));
            }
        }
    }

    @Test
    void testgetRow() {
        board.solveGame();
        SudokuRow row = board.getRow(0);
        assertNotNull(row);
        assertTrue(row.verify());
    }

    @Test
    public void testGetRowOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            board.getRow(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board.getRow(9);
        });
    }

    @Test
    void testgetColumn() {
        board.solveGame();
        SudokuColumn column = board.getColumn(0);
        assertNotNull(column);
        assertTrue(column.verify());
    }

    @Test
    public void testGetColumnOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            board.getColumn(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board.getColumn(9);
        });
    }

    @Test
    void testgetBox() {
        board.solveGame();
        SudokuBox box = board.getBox(0, 0);
        assertNotNull(box);
        assertTrue(box.verify());
    }

    @Test
    public void testGetBoxOutOfBounds() {

        assertThrows(IllegalArgumentException.class, () -> board.getBox(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> board.getBox(0, -1));

        assertThrows(IllegalArgumentException.class, () -> board.getBox(SudokuBoard.GRID_SIZE - 2, SudokuBoard.GRID_SIZE));
        assertThrows(IllegalArgumentException.class, () -> board.getBox(SudokuBoard.GRID_SIZE, SudokuBoard.GRID_SIZE - 2));

        assertThrows(IllegalArgumentException.class, () -> board.getBox(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> board.getBox(SudokuBoard.GRID_SIZE, SudokuBoard.GRID_SIZE));

        assertThrows(IllegalArgumentException.class, () -> board.getBox(-1, SudokuBoard.GRID_SIZE));
        assertThrows(IllegalArgumentException.class, () -> board.getBox(SudokuBoard.GRID_SIZE, -1));

        assertThrows(IllegalArgumentException.class, () -> board.getBox(SudokuBoard.GRID_SIZE - 2, SudokuBoard.GRID_SIZE - 2));
        assertThrows(IllegalArgumentException.class, () -> board.getBox(SudokuBoard.GRID_SIZE, SudokuBoard.GRID_SIZE - 3));
        assertThrows(IllegalArgumentException.class, () -> board.getBox(SudokuBoard.GRID_SIZE - 3, SudokuBoard.GRID_SIZE));
    }
    @Test
    void checkBoard() {
        board.solveGame();
        assertTrue(board.checkBoard());
    }

    @Test
    void solveboardtest() {
        board.solveGame();
        assertTrue(checkBoard1());
    }

    public boolean checkBoard1() {
        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            if (!isNumberInRowTest(i) || !isNumberInColumnTest(i)) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isNumberInSquareTest(i * 3,j * 3)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNumberInRowTest(int row) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board.get(row,i) < 1 || board.get(row,i) > 9 || seen[board.get(row,i) - 1]) {
                return false;
            }
            seen[board.get(row,i) - 1] = true;
        }
        return true;
    }

    private boolean isNumberInColumnTest(int column) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (board.get(i,column) < 1 || board.get(i,column) > 9 || seen[board.get(i,column) - 1]) {
                return false;
            }
            seen[board.get(i,column) - 1] = true;
        }
        return true;
    }

    private boolean isNumberInSquareTest(int row, int column) {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = board.get(i + row,j + column);
                if (num < 1 || num > 9 || seen[num - 1]) {
                    return false;
                }
                seen[num - 1] = true;
            }
        }
        return true;
    }

}

