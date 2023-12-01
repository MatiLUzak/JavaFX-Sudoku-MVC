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

    @Test
    void testToString() {
        SudokuField field = new SudokuField();
        field.setValue(5);
        assertNotNull(field.toString(), "toString should return a non-null string.");
    }

    @Test
    void testEqualsSelf() {
        SudokuField field = new SudokuField();
        assertTrue(field.equals(field), "A field should be equal to itself.");
    }

    @Test
    void testEqualsDifferentValue() {
        SudokuField field1 = new SudokuField();
        field1.setValue(5);
        SudokuField field2 = new SudokuField();
        field2.setValue(6);
        assertFalse(field1.equals(field2), "Fields with different values should not be equal.");
    }

    @Test
    void testEqualsNull() {
        SudokuField field = new SudokuField();
        assertFalse(field.equals(null), "A field should not be equal to null.");
    }

    @Test
    void testEqualsDifferentClass() {
        SudokuField field = new SudokuField();
        assertFalse(field.equals(new Object()), "A field should not be equal to an object of a different class.");
    }

    @Test
    void testHashCodeConsistentWithEquals() {
        SudokuField field1 = new SudokuField();
        field1.setValue(5);
        SudokuField field2 = new SudokuField();
        field2.setValue(5);
        assertTrue(field1.equals(field2), "A field should be equal to itself.");
        assertEquals(field1.hashCode(), field2.hashCode(), "Hash codes should be the same for equal objects.");
    }
    @Test
    void testClone() throws CloneNotSupportedException {
        SudokuField original = new SudokuField();
        original.setValue(5);
        SudokuField cloned = original.clone();

        assertNotSame(original, cloned, "Cloned object should not be the same instance.");
        assertEquals(original, cloned, "Cloned object should be equal to the original object.");
        assertEquals(original.getValue(), cloned.getValue(), "Cloned object's value should be equal to the original object's value.");
    }

    @Test
    void testCompareTo() {
        SudokuField field1 = new SudokuField();
        field1.setValue(5);
        SudokuField field2 = new SudokuField();
        field2.setValue(6);
        SudokuField field3 = new SudokuField();
        field3.setValue(5);

        assertTrue(field1.compareTo(field2) < 0, "field1 should be considered less than field2.");
        assertTrue(field2.compareTo(field1) > 0, "field2 should be considered greater than field1.");
        assertEquals(0, field1.compareTo(field3), "field1 should be considered equal to field3.");
    }
}
