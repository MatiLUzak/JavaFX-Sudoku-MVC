package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {

    private SudokuSolver solver;
    public static  final int GRID_SIZE = 9;

    private List<List<SudokuField>> board;

    public SudokuBoard(SudokuSolver solver) {
        if (solver == null) {
            throw new IllegalArgumentException("Solver cannot be null");
        }
        this.solver = solver;
        board = new ArrayList<>(GRID_SIZE);
        for (int i = 0; i < GRID_SIZE; i++) {
            List<SudokuField> row = new ArrayList<>();
            for (int j = 0; j < GRID_SIZE; j++) {
                row.add(new SudokuField());
            }
            board.add(Collections.unmodifiableList(row));
        }
    }

    public int get(int x, int y) {

        return board.get(x).get(y).getValue();
    }

    public void set(int x, int y, int value) {

        board.get(x).get(y).setValue(value);
    }

    public  void solveGame() {
        solver.solve(this);
    }

    public SudokuRow getRow(int y) {
        return new SudokuRow(new ArrayList<>(board.get(y)));
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> column = new ArrayList<>(GRID_SIZE);
        for (int y = 0; y < GRID_SIZE; y++) {
            column.add(board.get(y).get(x));
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int row, int col) {
        List<List<SudokuField>> boxFields = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            List<SudokuField> boxRow = new ArrayList<>(3);
            for (int j = 0; j < 3; j++) {
                boxRow.add(board.get(row + i).get(col + j));
            }
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
