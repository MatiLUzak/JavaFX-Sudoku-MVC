package org.example;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard implements Serializable,Cloneable {

    private SudokuSolver solver;
    public static final int GRID_SIZE = 9;

    private static final long serialVersionUID = 1L;


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

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
        for (SudokuField[] row : board) {
            toStringBuilder.append(Arrays.toString(row));
        }
        return toStringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) obj;
        return new EqualsBuilder()
                .append(solver, that.solver)
                .append(GRID_SIZE, that.GRID_SIZE)
                .append(board, that.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(solver)
                .append(GRID_SIZE)
                .append(board)
                .toHashCode();
    }

    @Override
    public SudokuBoard clone() {
        try {
            SudokuBoard cloned = (SudokuBoard) super.clone();
            // cloned.solver = this.solver.clone();  tu nie wiem bo solver ma nie być clonable
            cloned.board = new SudokuField[GRID_SIZE][GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    cloned.board[i][j] = this.board[i][j].clone();
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Class not cloneable", e);
        }
    }

    public void removeFields(Difficulty difficulty) {
        int fieldsToRemove = difficulty.getFieldsToRemove();
        int removedFields = 0;
        while (removedFields < fieldsToRemove) {
            int x = (int) (Math.random() * GRID_SIZE);
            int y = (int) (Math.random() * GRID_SIZE);
            if (get(x, y) != 0) {
                set(x, y, 0);
                removedFields++;
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
        if (y < 0 || y >= GRID_SIZE) {
            throw new IllegalArgumentException("Row index out of bounds");
        }
        SudokuField[] rowArray = new SudokuField[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            rowArray[i] = board[y][i];
        }
        List<SudokuField> rowList = Arrays.asList(rowArray);
        return new SudokuRow(rowList);
    }

    public SudokuColumn getColumn(int x) {
        if (x < 0 || x >= GRID_SIZE) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        SudokuField[] columnArray = new SudokuField[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            columnArray[i] = board[i][x];
        }
        List<SudokuField> columnList = Arrays.asList(columnArray);
        return new SudokuColumn(columnList);
    }

    public SudokuBox getBox(int row, int col) {
        if (row < 0 || row > GRID_SIZE - 3 || col < 0 || col > GRID_SIZE - 3) {
            throw new IllegalArgumentException("Box index out of bounds");
        }
        SudokuField[] boxRowArray = new SudokuField[GRID_SIZE];
        int x = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    boxRowArray[x] = board[row / 3 * 3 + i][col / 3 * 3 + j];
                    x++;
                }
            }
        List<SudokuField> boxList = Arrays.asList(boxRowArray);
        return new SudokuBox(boxList);
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
