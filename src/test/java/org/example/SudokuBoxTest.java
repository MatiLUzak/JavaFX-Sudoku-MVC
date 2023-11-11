package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
public class SudokuBoxTest {
    @Test
    void constructorShouldThrowExceptionForNullArray() {
        assertThrows(IllegalArgumentException.class, () -> new SudokuBox(null));
    }
    @Test
    void testVerifyBox(){
        List<List<SudokuField>> fields = new ArrayList<>();
        int value = 1;
        for (int i = 0; i < 3; i++) {
            List<SudokuField> row = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                SudokuField field = new SudokuField();
                field.setValue(value++);
                row.add(field);
            }
            fields.add(row);
        }
        SudokuBox box = new SudokuBox(fields);
        assertTrue(box.verify());
        fields.get(0).get(0).setValue(1);
        fields.get(0).get(1).setValue(1);
        assertFalse(box.verify());
    }

}
