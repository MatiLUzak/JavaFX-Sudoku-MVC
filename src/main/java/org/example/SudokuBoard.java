package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private static final List<Integer> LIST = new ArrayList<>();
    private static  final int GRID_SIZE = 9;

    public static int getGridSize() {
        return GRID_SIZE;
    }

    private int[][] board = new int[GRID_SIZE][GRID_SIZE];

    public int[][] getBoard() {
        int [][] copy = new int[GRID_SIZE][];
        for (int i = 0; i < GRID_SIZE; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    static {
        for (int i = 1; i <= GRID_SIZE; i++) {
            LIST.add(i);
        }
    }

    private void fillArrayWithZero() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    private boolean isNumberInRow(int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInSquare(int number, int column, int row) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int number, int column, int row) {
        return !isNumberInColumn(number, column)
                && !isNumberInRow(number, row)
                && !isNumberInSquare(number, column, row);
    }

        private boolean solveBoard() {
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    if (board[row][column] == 0) {
                        for (Integer numberToTry : LIST) {
                            if (isValid(numberToTry, column, row)) {
                                board[row][column] = numberToTry;
                                if (solveBoard()) {
                                    return true;
                                } else {
                                    board[row][column] = 0;
                                }
                            }
                        }
                    return false;
                }
            }
        }
        return true;
    }

    public  void printArray() {
        for (int i= 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void fillBoard() {
        fillArrayWithZero();
        Collections.shuffle(LIST);
        if (solveBoard()) {
            System.out.println("Sudoku Board has been created");
        } else {
            System.out.println("Error while creating Sudoku Board");
        }
        //printArray();
    }

    /*  public static void main(String[] args) { //main był stworzony na potrzeby wizualnego sprawdzenia rozwiązania
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();
        sudokuBoard.printArray();
    }*/
}
