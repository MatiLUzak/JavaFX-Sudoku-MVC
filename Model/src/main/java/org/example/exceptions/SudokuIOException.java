package org.example.exceptions;

import java.io.IOException;

public class SudokuIOException extends SudokuException {
    public SudokuIOException(String message, IOException cause) {
        super(message, cause);
    }
}
