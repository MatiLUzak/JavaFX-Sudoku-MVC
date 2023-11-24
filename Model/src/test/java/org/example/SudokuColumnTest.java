package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuColumnTest {
    @Test
    void testSudokuColumnCreation() {

        List<SudokuField> fields = new ArrayList<>();
        for(int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields.add(field);
        }

        SudokuColumn column = new SudokuColumn(fields);

        assertNotNull(column, "SudokuBox should be created and not null");


    }
}
