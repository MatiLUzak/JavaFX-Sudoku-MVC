package org.example;

public class SudokuColumn implements SudokuStructure{
    private SudokuField[] fields;

    public SudokuColumn(SudokuField[] fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Fields array cannot be null");
        }
        this.fields = fields;
    }

    @Override
    public boolean verify() {
        boolean[] seen = new boolean[9];
        for (SudokuField field : fields) {
            int value = field.getValue();
            if (value < 1 || value > 9 || seen[value - 1]) {
                return false;
            }
            seen[value - 1] = true;
        }
        return true;
    }
}
