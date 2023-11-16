package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
public class SudokuBoxTest {
    @Test
    void testSudokuBoxCreation() {

        List<SudokuField> fields = new ArrayList<>();
        for(int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields.add(field);
        }

        SudokuBox box = new SudokuBox(fields);

        assertNotNull(box, "SudokuBox should be created and not null");


    }

}
