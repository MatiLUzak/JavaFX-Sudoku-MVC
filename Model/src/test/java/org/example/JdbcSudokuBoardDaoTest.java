package org.example;

import org.example.exceptions.SudokuDatabaseException;
import org.example.exceptions.SudokuException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcSudokuBoardDaoTest {

    private final String testBoardName = "testSudokuBoard";
    private SudokuBoard expectedBoard;

    @BeforeEach
    public void setUp() throws Exception {
        expectedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        expectedBoard.solveGame();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sudoku.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM boards WHERE name = '" + testBoardName + "'");
            stmt.execute("DELETE FROM fields WHERE board_id IN (SELECT board_id FROM boards WHERE name = '" + testBoardName + "')");
        }
    }

    @Test
    public void testWriteAndRead() throws SudokuException {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao(testBoardName)) {
            dao.write(expectedBoard);
            SudokuBoard actualBoard = dao.read();

            assertNotNull(actualBoard);
            assertEquals(expectedBoard, actualBoard);
            assertEquals(expectedBoard.hashCode(), actualBoard.hashCode()); //zxcxczczxczxxcc
        }
    }

    @Test
    public void testExceptionWhenBoardNotFound() {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getJdbcDao("nonexistentBoard")) {
            assertThrows(SudokuDatabaseException.class, dao::read);
        }
    }


    @AfterEach
    public void tearDown() throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sudoku.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM boards WHERE name = '" + testBoardName + "'");
            stmt.execute("DELETE FROM fields WHERE board_id IN (SELECT board_id FROM boards WHERE name = '" + testBoardName + "')");
        }
    }
}
