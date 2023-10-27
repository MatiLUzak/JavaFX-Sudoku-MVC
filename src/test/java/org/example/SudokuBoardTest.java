package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void testCheckBoardFaultyRow() {
        sudokuBoard1.solveGame();
        sudokuBoard1.set(0, 0, 1);
        sudokuBoard1.set(1, 0, 1);
        assertFalse(sudokuBoard1.checkBoard());
    }

    @Test
    public void testCheckBoardFaultyColumn() {
        setboardForWrongColumns(sudokuBoard1);
        assertFalse(sudokuBoard1.checkBoard());
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
        setboardForWrongSquare(sudokuBoard1);
        assertFalse(sudokuBoard1.checkBoard());
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
    public void testFaultyRow() {
        sudokuBoard1.solveGame();
        sudokuBoard1.set(0, 0, 1);
        sudokuBoard1.set(1, 0, 1);
        assertFalse(testIfBoardIsCorrect(sudokuBoard1));
    }

    @Test
    public void testFaultyColumn() {
        sudokuBoard1.solveGame();
        sudokuBoard1.set(0, 0, 1);
        sudokuBoard1.set(0, 1, 1);
        assertFalse(testIfBoardIsCorrect(sudokuBoard1));
    }

    @Test
    public void testFaultySquare() {
        sudokuBoard1.solveGame();
        sudokuBoard1.set(0,0,0);
        sudokuBoard1.set(1, 1, 0);
        assertFalse(testIfBoardIsCorrect(sudokuBoard1));
    }
    private boolean testIfBoardIsCorrect(SudokuBoard sudokuBoard) {
        int[][] borad = sudokuBoard.getBoard();
        for(int i = 0; i <SudokuBoard.GRID_SIZE ; i++){
            if(!isNumberInRowTest(borad,i) || !isNumberInColumnTest(borad,i)){
                return false;
            }
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(!isNumberInSquareTest(borad,i*3,j*3)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNumberInRowTest(int board[][],int row) {
        boolean[] seen = new boolean[9];
        for(int i = 0; i < 9; i++){
            if(board[row][i] < 1 || board[row][i] > 9 || seen[board[row][i]-1]){
                return false;
            }
            seen[board[row][i] - 1] = true;
        }
        return true;
    }

    private boolean isNumberInColumnTest(int board[][],int column) {
        boolean[] seen = new boolean[9];
        for(int i = 0; i < 9; i++){
            if(board[i][column] < 1 || board[i][column] > 9 || seen[board[i][column]-1]){
                return false;
            }
            seen[board[i][column] - 1] = true;
        }
        return true;
    }

    private boolean isNumberInSquareTest(int board[][],int row, int column) {
        boolean[] seen = new boolean[9];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int num = board[i+row][j+column];
                if(num < 1 || num > 9 || seen[num-1]){
                    return false;
                }
                seen[num - 1] = true;
            }
        }
        return true;
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
