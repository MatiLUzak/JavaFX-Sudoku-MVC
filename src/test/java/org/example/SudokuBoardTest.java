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
    void testgetColumn() {
        board.solveGame();
        SudokuColumn column = board.getColumn(0);
        assertNotNull(column);
        assertTrue(column.verify());
    }

    @Test
    void testgetBox() {
        board.solveGame();
        SudokuBox box = board.getBox(0, 0);
        assertNotNull(box);
        assertTrue(box.verify());
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

