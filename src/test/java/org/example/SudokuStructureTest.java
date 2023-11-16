package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuStructureTest {

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

            SudokuStructure structure = new SudokuStructure(fields);
            assertTrue(structure.verify());
            fields.get(0).setValue(1);
            fields.get(1).setValue(1);
            assertFalse(structure.verify());
        }

        @Test
        void TestVerifyBOXNegativeNumbers() {

            SudokuStructure structure = new SudokuStructure(fields);
            assertTrue(structure.verify());
            fields.get(0).setValue(-1);
            fields.get(1).setValue(-1);
            assertFalse(structure.verify());
        }
        @Test
        void TestVerifyBOXToBigNumbers() {

            SudokuStructure structure = new SudokuStructure(fields);
            assertTrue(structure.verify());
            fields.get(0).setValue(10);
            fields.get(1).setValue(10);
            assertFalse(structure.verify());
        }

    }


