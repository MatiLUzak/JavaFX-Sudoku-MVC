package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SudokuRowTest {
    @Test
    void constructorShouldThrowExceptionForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuRow(null));
    }
    @Test
    void TestVerifyRow(){
        SudokuField[] fields = new SudokuField[SudokuBoard.GRID_SIZE];
        for(int i = 0; i < fields.length; i++){
            fields[i]=new SudokuField();
            fields[i].setValue(i+1);
        }
        SudokuRow row = new SudokuRow(fields);
        assertTrue(row.verify());
        fields[0].setValue(0);
        fields[1].setValue(0);
        assertFalse(row.verify());
    }
}
