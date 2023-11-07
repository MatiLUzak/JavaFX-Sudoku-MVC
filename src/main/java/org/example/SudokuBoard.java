package org.example;



public class SudokuBoard {

    private SudokuSolver solver;
    public static  final int GRID_SIZE = 9;

    private SudokuField[][] board;

    public SudokuBoard(SudokuSolver solver) {
        if (solver == null) {
            throw new IllegalArgumentException("Solver cannot be null");
        }
        this.solver = solver;
        board = new SudokuField[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                board[row][col] = new SudokuField();
            }
        }
    }

    public int get(int x, int y) {
        return board[x][y].getValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setValue(value);
    }

    public  void solveGame() {
        solver.solve(this);
    }

    public SudokuRow getRow(int y) {
        return new SudokuRow(board[y]);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] column = new SudokuField[GRID_SIZE];
        for (int y = 0; y < GRID_SIZE; y++) {
            column[y] = board[y][x];
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int row, int col) {
        SudokuField[][] box = new SudokuField[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box[i][j] = board[row + i][col + j];
            }
        }
        return new SudokuBox(box);
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
