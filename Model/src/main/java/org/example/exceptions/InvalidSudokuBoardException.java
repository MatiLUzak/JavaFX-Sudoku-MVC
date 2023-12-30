package org.example.exceptions;

public class InvalidSudokuBoardException extends SudokuException {
    public InvalidSudokuBoardException(String messageKey) {
        super(messageKey);
    }

    public InvalidSudokuBoardException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }
}
