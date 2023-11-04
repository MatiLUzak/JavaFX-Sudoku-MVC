package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SudokuBoxTest {
    @Test
    void constructorShouldThrowExceptionForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuBox(null));
    }
    @Test
    void testVerifyBox(){
        SudokuField fields[][]=new SudokuField[3][3];
        int value = 1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                fields[i][j] = new SudokuField();
                fields[i][j].setValue(value++);
            }
        }
        SudokuBox box = new SudokuBox(fields);
        assertTrue(box.verify());
        fields[0][0].setValue(1);
        fields[0][1].setValue(1);
        assertFalse(box.verify());
    }

}
