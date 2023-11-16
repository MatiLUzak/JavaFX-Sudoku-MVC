package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
public class SudokuRowTest {
    @Test
    void testSudokuRowCreation() {

        List<SudokuField> fields = new ArrayList<>();
        for(int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields.add(field);
        }

        SudokuRow row = new SudokuRow(fields);

        assertNotNull(row, "SudokuBox should be created and not null");


    }

}
