package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {

    private SudokuSolver solver;
    public static final int GRID_SIZE = 9;

    private SudokuField[][] board;

    public SudokuBoard(SudokuSolver solver) {
        if (solver == null) {
            throw new IllegalArgumentException("Solver cannot be null");
        }
        this.solver = solver;
        board = new SudokuField[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public int get(int x, int y) {
        return board[x][y].getValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setValue(value);
    }

    public void solveGame() {
        solver.solve(this);
    }

    public SudokuRow getRow(int y) {
        SudokuField[] rowArray = new SudokuField[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            rowArray[i] = board[y][i];
        }
        List<SudokuField> rowList = Arrays.asList(rowArray);
        return new SudokuRow(rowList);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] columnArray = new SudokuField[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            columnArray[i] = board[i][x];
        }
        List<SudokuField> columnList = Arrays.asList(columnArray);
        return new SudokuColumn(columnList);
    }

    public SudokuBox getBox(int row, int col) {
        List<List<SudokuField>> boxFields = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            SudokuField[] boxRowArray = new SudokuField[3];
            for (int j = 0; j < 3; j++) {
                boxRowArray[j] = board[row / 3 * 3 + i][col / 3 * 3 + j];
            }
            List<SudokuField> boxRow = Arrays.asList(boxRowArray);
            boxFields.add(boxRow);
        }
        return new SudokuBox(boxFields);
    }

    public boolean checkBoard() {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
        }
        for (int row = 0; row < GRID_SIZE; row += 3) {
            for (int col = 0; col < GRID_SIZE; col += 3) {
                if (!getBox(row, col).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

}
