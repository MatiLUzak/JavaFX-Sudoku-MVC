package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuColumnTest {
    @Test
    void constructorShouldThrowExceptionForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuColumn(null));
    }
    @Test
    void TestVerifyRow(){
        SudokuField[] fields = new SudokuField[SudokuBoard.GRID_SIZE];
        for(int i = 0; i < fields.length; i++){
            fields[i]=new SudokuField();
            fields[i].setValue(i+1);
        }
        SudokuColumn column = new SudokuColumn(fields);
        assertTrue(column.verify());
        fields[0].setValue(0);
        fields[1].setValue(0);
        assertFalse(column.verify());
    }
}
