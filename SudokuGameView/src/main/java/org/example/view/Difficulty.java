package org.example.view;
import org.example.SudokuBoard;
public enum Difficulty {
    EASY(10),
    MEDIUM(20),
    HARD(30);

    private final int fieldsToRemove;

    Difficulty(int fieldsToRemove) {
        this.fieldsToRemove = fieldsToRemove;
    }

    public void apply(SudokuBoard board) {
        int removedFields = 0;
        while (removedFields < this.fieldsToRemove) {
            int x = (int) (Math.random() * SudokuBoard.GRID_SIZE);
            int y = (int) (Math.random() * SudokuBoard.GRID_SIZE);
            if (board.get(x, y) != 0) {
                board.set(x, y, 0);
                removedFields++;
            }
        }
    }
}
