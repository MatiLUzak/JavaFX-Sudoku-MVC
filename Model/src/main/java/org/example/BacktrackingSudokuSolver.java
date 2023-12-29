package org.example;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver,Serializable {

    private static final long serialVersionUID = 1L;
    public static final int GRID_SIZE = 9;

    private static final List<Integer> LIST = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(BacktrackingSudokuSolver.class);

    static {
        for (int i = 1; i <= GRID_SIZE; i++) {
            LIST.add(i);
        }
    }

    @Override
    public void solve(SudokuBoard board) {
        fillBoard(board);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("LIST", LIST)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        //BacktrackingSudokuSolver that = (BacktrackingSudokuSolver) obj;
        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .toHashCode();
    }

    private void fillArrayWithZero(SudokuBoard board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board.set(i, j, 0);
            }
        }
    }

    private boolean isNumberInRow(SudokuBoard board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board.get(row, i) == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(SudokuBoard board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board.get(i, column) == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInSquare(SudokuBoard board, int number, int column, int row) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board.get(i, j) == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(SudokuBoard board, int number, int column, int row) {
        return !isNumberInColumn(board, number, column)
                && !isNumberInRow(board, number, row)
                && !isNumberInSquare(board, number, column, row);
    }

    private boolean solveBoard(SudokuBoard board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board.get(row, column) == 0) {
                    logger.debug("Attempting to sort board at row: " + row + ", column: " + column);
                    for (Integer numberToTry : LIST) {
                        if (isValid(board, numberToTry, column, row)) {
                            board.set(row, column, numberToTry);
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board.set(row, column, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void fillBoard(SudokuBoard board) {
        fillArrayWithZero(board);
        Collections.shuffle(LIST);
        solveBoard(board);
    }
}