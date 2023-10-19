package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    private SudokuBoard sudokuBoard1;

    @BeforeEach
    void setUp() {
        sudokuBoard1 = new SudokuBoard();
    }

    @Test
    void testSudokuBoardInitialization() {
        assertNotNull(sudokuBoard1, "SudokuBoard should be initialized");
    }

    @Test
    void testIsBoardFilled() {
        sudokuBoard1.fillBoard();
        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.GRID_SIZE; j++) {
                assertTrue(sudokuBoard1.getBoard()[i][j] > 0, "Not every cell is filled");
            }
        }
    }

    @Test
    void testBoard() {
        assertTrue(testIfBoardIsCorrect(), "Sudoku board is not working");
    }

    private boolean testIfBoardIsCorrect() {
        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.GRID_SIZE; j++) {
                int number = sudokuBoard1.getBoard()[i][j];
                if (number != 0) {
                    if (!isValidTest(number, j, i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isNumberInRowTest(int number, int row, int currentColumn) {
        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            if (i != currentColumn && sudokuBoard1.getBoard()[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumnTest(int number, int column, int currentRow) {
        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            if (i != currentRow && sudokuBoard1.getBoard()[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInSquareTest(int number, int column, int row, int currentColumn, int currentRow) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if ((i != currentRow || j != currentColumn) && sudokuBoard1.getBoard()[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidTest(int number, int column, int row) {
        return !isNumberInColumnTest(number, column, row)
                && !isNumberInRowTest(number, row, column)
                && !isNumberInSquareTest(number, column, row, column, row);
    }

    @Test
    void differentArraysTest() {
        sudokuBoard1.fillBoard();
        int[][] firstArray = copyArray(sudokuBoard1.getBoard());
        sudokuBoard1.fillBoard();
        int[][] secondArray = copyArray(sudokuBoard1.getBoard());

        assertNotEquals(firstArray, secondArray, "fillBoard method generated the same arrays");
    }

    private int[][] copyArray(int[][] org) {
        int[][] copy = new int[org.length][];
        for (int i = 0; i < org.length; i++) {
            copy[i] = org[i].clone();
        }
        return copy;
    }
}
