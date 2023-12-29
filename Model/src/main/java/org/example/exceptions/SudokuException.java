package org.example.exceptions;

import java.util.ResourceBundle;
import java.util.Locale;

public class SudokuException extends RuntimeException {
    public SudokuException(String messageKey, Object... args) {
        super(String.format(ResourceBundle.getBundle("messages", Locale.getDefault()).getString(messageKey), args));
    }

    public SudokuException(String message, Throwable cause) {

        super(message, cause);
    }
}
