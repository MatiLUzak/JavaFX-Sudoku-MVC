package org.example;

public class SudokuBox implements SudokuStructure {
    private  SudokuField[][] fields;

    public SudokuBox(SudokuField[][] fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Fields array cannot be null");
        }
        this.fields = fields;
    }

    @Override
    public boolean verify() {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = fields[i][j].getValue();
                if (value < 1 || value > 9 || seen[value - 1]) {
                    return false;
                }
                seen[value - 1] = true;
            }
        }
        return true;
    }
}
