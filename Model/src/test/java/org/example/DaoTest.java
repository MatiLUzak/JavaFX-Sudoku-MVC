package org.example;

import org.example.exceptions.SudokuIOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTest {

    private final String testFileName = "testSudokuBoard.ser";
    private SudokuBoard expectedBoard;

    @BeforeEach
    public void setUp() {
        expectedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        expectedBoard.solveGame();
    }

    @Test
    public void testWriteAndRead() throws SudokuIOException {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(testFileName)) {
            dao.write(expectedBoard);
            SudokuBoard actualBoard = dao.read();

            assertEquals(expectedBoard, actualBoard);
            assertEquals(expectedBoard.hashCode(), actualBoard.hashCode());
        }
    }

    @Test
    public void testSudokuBoardDaoFactoryNotNull(){
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertNotNull(factory);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(testFileName);
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }
}
