package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BacktrackingSudokuSolverTest {

    @Test
    void testToString() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        assertNotNull(solver.toString(), "toString should return a non-null string.");
    }

    @Test
    void testEqualsWithItself() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        assertTrue(solver.equals(solver), "Solver should be equal to itself.");
    }

    @Test
    void testHashCodeConsistentWithEquals() {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver solver2 = new BacktrackingSudokuSolver();
        assertTrue(solver1.equals(solver2), "All solvers should be equal because they share the same static list.");
        assertEquals(solver1.hashCode(), solver2.hashCode(), "Hash codes should be the same for all solvers.");
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        assertFalse(solver.equals(null), "equals should return false when compared to null.");
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        assertFalse(solver.equals(new Object()), "equals should return false when compared to an instance of a different class.");
    }

}
