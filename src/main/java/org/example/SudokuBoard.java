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

    public  void solveGame(){
        solver.solve(this);
    }
    public void checkBoard(){
        if(testIfBoardIsCorrect()){
            System.out.printf("Board is correct");
        }else{
            System.out.printf("Board is incorrect");
        }
    }

    private boolean testIfBoardIsCorrect() {

        for(int i = 0; i <SudokuBoard.GRID_SIZE ; i++){
            if(!isNumberInRowTest(board,i) || !isNumberInColumnTest(board,i)){
                return false;
            }
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(!isNumberInSquareTest(board,i*3,j*3)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNumberInRowTest(int[][] board,int row) {
        boolean[] seen = new boolean[9];
        for(int i = 0; i < 9; i++){
            if(board[row][i] < 1 || board[row][i] > 9 || seen[board[row][i]-1]){
                return false;
            }
            seen[board[row][i] - 1] = true;
        }
        return true;
    }

    private boolean isNumberInColumnTest(int[][] board,int column) {
        boolean[] seen = new boolean[9];
        for(int i = 0; i < 9; i++){
            if(board[i][column] < 1 || board[i][column] > 9 || seen[board[i][column]-1]){
                return false;
            }
            seen[board[i][column] - 1] = true;
        }
        return true;
    }

    private boolean isNumberInSquareTest(int[][] board,int row, int column) {
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





}
