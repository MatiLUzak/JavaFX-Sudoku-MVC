package org.example.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.example.SudokuBoard;
import org.example.SudokuField;

public class SudokuFieldAdapter {
    private final SudokuBoard board;
    private final int row;
    private final int col;
    private final StringProperty stringValue;

    public SudokuFieldAdapter(SudokuBoard board, int row, int col) {
        this.board = board;
        this.row = row;
        this.col = col;
        this.stringValue = new SimpleStringProperty(getSudokuStringValue());
        this.stringValue.addListener((obs, oldVal, newVal) -> {
            int value = newVal.isEmpty() ? 0 : Integer.parseInt(newVal);
            board.set(row, col, value);
        });
    }

    private String getSudokuStringValue() {
        int value = board.get(row, col);
        return value == 0 ? "" : String.valueOf(value);
    }

    public StringProperty stringValueProperty() {
        return stringValue;
    }
}
