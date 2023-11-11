package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuColumnTest {
    @Test
    void constructorShouldThrowExceptionForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuColumn(null));
    }
    @Test
    void TestVerifyColumn(){
        List<SudokuField> fields = new ArrayList<>();
        for(int i = 0; i < SudokuBoard.GRID_SIZE; i++){
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields.add(field);
        }
        SudokuColumn column = new SudokuColumn(fields);
        assertTrue(column.verify());
        fields.get(0).setValue(1);
        fields.get(1).setValue(1);
        assertFalse(column.verify());
    }
}
