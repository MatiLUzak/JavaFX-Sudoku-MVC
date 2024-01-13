package org.example;

import org.example.exceptions.SudokuDatabaseException;
import org.example.exceptions.SudokuException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcSudokuBoardDaoTest {

    private final String testBoardName = "testSudokuBoard";
    private SudokuBoard expectedBoard;

    @BeforeEach
    public void setUp() {
        File dbFile = new File("sudoku.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }

        DatabaseInitializer.createNewDatabase();

        expectedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        expectedBoard.solveGame();
    }

    @Test
    public void testWriteAndRead() throws SudokuException {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao(testBoardName)) {
            dao.write(expectedBoard);
            SudokuBoard actualBoard = dao.read();

            assertNotNull(actualBoard);
            assertEquals(expectedBoard, actualBoard);
            assertEquals(expectedBoard.hashCode(), actualBoard.hashCode());
        }
    }

    @Test
    public void testExceptionWhenBoardNotFound() {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("nonexistentBoard")) {
            assertThrows(SudokuDatabaseException.class, dao::read);
        }
    }

    @Test
    public void testWriteAndReadTransaction() throws SudokuException {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao(testBoardName)) {
            dao.write(expectedBoard);
        } catch (SudokuDatabaseException e) {
            fail("Nie powinno być wyjątku podczas zapisu: " + e.getMessage());
        }

        SudokuBoard actualBoard = null;
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao(testBoardName)) {
            actualBoard = dao.read();
        } catch (SudokuDatabaseException e) {
            fail("Nie powinno być wyjątku podczas odczytu: " + e.getMessage());
        }

        assertNotNull(actualBoard, "Odczytana plansza nie powinna być null.");
        assertEquals(expectedBoard, actualBoard, "Odczytana plansza powinna być równa zapisanej.");
    }



    @AfterEach
    public void tearDown() {
        File dbFile = new File("sudoku.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }
}
