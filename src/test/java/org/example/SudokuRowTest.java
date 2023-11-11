package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
public class SudokuRowTest {
    @Test
    void constructorShouldThrowExceptionForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuRow(null));
    }
    @Test
    void TestVerifyRow(){
        List<SudokuField> fields = new ArrayList<>();
        for(int i = 0; i < SudokuBoard.GRID_SIZE; i++){
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields.add(field);
        }
        SudokuRow row = new SudokuRow(fields);
        assertTrue(row.verify());
        fields.get(0).setValue(1);
        fields.get(1).setValue(1);
        assertFalse(row.verify());
    }
}
