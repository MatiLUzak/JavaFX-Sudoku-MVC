package org.example;

import java.util.List;

public class SudokuBox  {
    private  List<List<SudokuField>> fields;

    public SudokuBox(List<List<SudokuField>> fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Fields array cannot be null");
        }
        this.fields = fields;
    }

    public boolean verify() {
        boolean[] seen = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = fields.get(i).get(j).getValue();
                if (value < 1 || value > 9 || seen[value - 1]) {
                    return false;
                }
                seen[value - 1] = true;
            }
        }
        return true;
    }
}
