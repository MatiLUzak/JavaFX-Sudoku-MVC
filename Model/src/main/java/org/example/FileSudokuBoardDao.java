package org.example;

import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private static final Logger logger = LogManager.getLogger(FileSudokuBoardDao.class);
    private final String fileName;

    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        logger.info("Reading SudokuBoard from file: " + fileName);
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            logger.debug("SudokuBoard read successfully from file: " + fileName);
            return (SudokuBoard) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error reading SudokuBoard from file: " + fileName, e);
            throw e;
        }
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(obj);
            logger.debug("SudokuBoard written successfully to file: " + fileName);
        }catch (IOException e) {
            logger.error("Error writing SudokuBoard to file: " + fileName, e);
            throw e; // Rethrow the exception to allow the caller to handle it
        }
    }

    @Override
    public void close() throws IOException {
        // Implementacja zamykania zasobów
    }
}
