package org.example;

import org.example.exceptions.InvalidSudokuBoardException;
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
            assertThrows(InvalidSudokuBoardException.class, () -> new SudokuBox(null));
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

    @Test
    void testToString() {
        SudokuStructure structure = new SudokuStructure(fields);
        String toStringResult = structure.toString();
        assertNotNull(toStringResult);
        assertFalse(toStringResult.trim().isEmpty());
    }

    @Test
    void testEqualsAndHashCodeConsistency() {
        List<SudokuField> fields2 = new ArrayList<>();
        for (int i = 0; i < SudokuBoard.GRID_SIZE; i++) {
            SudokuField field = new SudokuField();
            field.setValue(i + 1);
            fields2.add(field);
        }
        SudokuStructure structure1 = new SudokuStructure(fields);
        SudokuStructure structure2 = new SudokuStructure(fields2);

        assertTrue(structure1.equals(structure2), "The two structures should be equal.");
        assertEquals(structure1.hashCode(), structure2.hashCode(), "Equal objects must have equal hash codes.");

        structure2.fields.get(0).setValue(8);

        assertFalse(structure1.equals(structure2), "The two structures should not be equal after changing a value in one of them.");
        assertNotEquals(structure1.hashCode(), structure2.hashCode(), "Equal objects must have equal hash codes.");
    }

    @Test
    void testEqualsWithItself() {
        SudokuStructure structure = new SudokuStructure(fields);
        assertTrue(structure.equals(structure), "An object should be equal to itself.");
    }

    @Test
    void testEqualsWithNull() {
        SudokuStructure structure = new SudokuStructure(fields);
        assertFalse(structure.equals(null), "An object should not be equal to null.");
    }

    @Test
    void testEqualsWithDifferentClass() {
        SudokuStructure structure = new SudokuStructure(fields);
        assertFalse(structure.equals(new Object()), "An object should not be equal to an object of a different class.");
    }

    @Test
    void cloneTest() {
            SudokuStructure originalStructure = new SudokuStructure(fields);
            SudokuStructure clonedStructure = originalStructure.clone();

            assertEquals(originalStructure,clonedStructure);
            assertNotSame(originalStructure, clonedStructure, "Cloned structure should not be the same instance as the original structure.");
    }


    }


