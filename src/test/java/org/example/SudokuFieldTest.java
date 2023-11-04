package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SudokuFieldTest {
    @Test
    void valueIsSetAndGetCorrectly() {
        SudokuField field = new SudokuField();
        field.setValue(5);
        assertEquals(5, field.getValue());
    }
}
