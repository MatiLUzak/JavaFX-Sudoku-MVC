package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    private SudokuBoard sudokuBoard1;

    @BeforeEach
    void setUp() {
        SudokuSolver solver=new BacktrackingSudokuSolver();
        sudokuBoard1 = new SudokuBoard(solver);
    }

    @Test
    public void testSetGet(){
        sudokuBoard1.set(0,0,1);
        assertEquals(1,sudokuBoard1.get(0,0));
    }
    @Test
    public void testCheckBoard(){

        sudokuBoard1.solveGame();
        assertTrue(sudokuBoard1.checkBoard());

        sudokuBoard1.set(0,0,0);
        assertFalse(sudokuBoard1.checkBoard());

    }
    @Test
    public void testFaultyRow() {
        sudokuBoard1.solveGame();
        sudokuBoard1.set(0, 0, 1);
        sudokuBoard1.set(1, 0, 1);
        assertFalse(sudokuBoard1.checkBoard());
    }

    @Test
    public void testFaultyColumn() {
        sudokuBoard1.solveGame();
        sudokuBoard1.set(0, 0, 1);
        sudokuBoard1.set(0, 1, 1);
        assertFalse(sudokuBoard1.checkBoard());
    }

    @Test
    public void testFaultySquare() {
        sudokuBoard1.solveGame();
        sudokuBoard1.set(0,0,0);
        sudokuBoard1.set(1, 1, 0);
        assertFalse(sudokuBoard1.checkBoard());
    }


    @Test
    public void testcopyArray(){
        sudokuBoard1.set(0,0,1);
        int [][] tab = sudokuBoard1.getBoard();
        assertEquals(1,tab[0][0]);

        tab[0][0]=2;
        int [][] tab2 = sudokuBoard1.getBoard();
        assertNotEquals(2,tab2[0][0]);
    }

}
