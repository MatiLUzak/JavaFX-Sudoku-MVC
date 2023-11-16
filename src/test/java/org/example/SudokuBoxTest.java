package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
public class SudokuBoxTest {
    private List<SudokuField> fields;
    @BeforeEach
    void setUp() {
        fields = new ArrayList<>();
        for(int i = 0; i < SudokuBoard.GRID_SIZE; i++){
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields.add(field);
        }
    }
    @Test
    void constructorShouldThrowExceptionForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuBox(null));
    }
    @Test
    void TestVerifyBOXSeenNumbers() {

        SudokuBox row = new SudokuBox(fields);
        assertTrue(row.verify());
        fields.get(0).setValue(1);
        fields.get(1).setValue(1);
        assertFalse(row.verify());
    }

    @Test
    void TestVerifyBOXNegativeNumbers() {

        SudokuBox row = new SudokuBox(fields);
        assertTrue(row.verify());
        fields.get(0).setValue(-1);
        fields.get(1).setValue(-1);
        assertFalse(row.verify());
    }
    @Test
    void TestVerifyBOXToBigNumbers() {

        SudokuBox row = new SudokuBox(fields);
        assertTrue(row.verify());
        fields.get(0).setValue(10);
        fields.get(1).setValue(10);
        assertFalse(row.verify());
    }

}
