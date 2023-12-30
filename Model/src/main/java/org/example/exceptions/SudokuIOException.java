package org.example.exceptions;

import java.io.IOException;

public class SudokuIOException extends SudokuException {
    public SudokuIOException(String messageKey, IOException cause) {
        super(messageKey, cause);
    }
}
