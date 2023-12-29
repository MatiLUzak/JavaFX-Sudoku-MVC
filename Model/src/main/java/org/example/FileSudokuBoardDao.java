package org.example;

import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.SudokuIOException;
import org.example.exceptions.SudokuException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private static final Logger logger = LogManager.getLogger(FileSudokuBoardDao.class);
    private final String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws SudokuException {
        logger.info("Reading SudokuBoard from file: " + fileName);
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            SudokuBoard board = (SudokuBoard) inputStream.readObject();
            logger.debug("SudokuBoard read successfully from file: " + fileName);
            return board;
        } catch (IOException e) {
            logger.error("Error reading SudokuBoard from file: " + fileName, e);
            throw new SudokuIOException("Error reading from file", e);
        } catch (ClassNotFoundException e) {
            logger.error("Class not found when reading SudokuBoard from file: " + fileName, e);
            throw new SudokuException("Class not found", e);
        }
    }

    @Override
    public void write(SudokuBoard obj) throws SudokuIOException {
        logger.info("Writing SudokuBoard to file: " + fileName);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(obj);
            logger.debug("SudokuBoard written successfully to file: " + fileName);
        } catch (IOException e) {
            logger.error("Error writing SudokuBoard to file: " + fileName, e);
            throw new SudokuIOException("Error writing to file", e);
        }
    }

    @Override
    public void close() throws SudokuIOException {
        logger.debug("Closing FileSudokuBoardDao for file: " + fileName);

    }
}
