package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private SudokuSolver solver;
    public static  final int GRID_SIZE = 9;

    private int[][] board;

    public SudokuBoard(SudokuSolver solver){
        this.board = new int[GRID_SIZE][GRID_SIZE];
        this.solver = solver;
    }
    public int[][] getBoard() {
        int [][] copy = new int[GRID_SIZE][];
        for (int i = 0; i < GRID_SIZE; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }
    public int get(int x, int y){
        return board[x][y];
    }

    public void set(int x, int y, int value){
        board[x][y] = value;
    }





}
