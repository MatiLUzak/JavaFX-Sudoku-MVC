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
        sudokuBoard1.fillBoard();
        int[][] borad = sudokuBoard1.getBoard();
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
