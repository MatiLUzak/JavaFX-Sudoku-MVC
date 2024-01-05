package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SudokuBoardDaoFactory {

    private static final Logger logger = LogManager.getLogger(SudokuBoardDaoFactory.class);

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        logger.debug("Creating FileSudokuBoardDao for file: " + fileName);
        return new FileSudokuBoardDao(fileName);
    }
    public static Dao<SudokuBoard> getJdbcDao(String boardName) {
        return new JdbcSudokuBoardDao(boardName);
    }
}
