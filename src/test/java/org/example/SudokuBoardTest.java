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

}
