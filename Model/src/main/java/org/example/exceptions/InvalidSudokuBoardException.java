package org.example.exceptions;

public class InvalidSudokuBoardException extends SudokuException {
    public InvalidSudokuBoardException(String message) {
        super(message);
    }

    public InvalidSudokuBoardException(String message, Throwable cause) {
        super(message, cause);
    }
}