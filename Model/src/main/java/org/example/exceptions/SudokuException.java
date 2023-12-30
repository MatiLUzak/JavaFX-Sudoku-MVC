package org.example.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuException extends RuntimeException {
    public SudokuException(String messageKey) {
        super(ResourceBundle.getBundle("messages", Locale.getDefault()).getString(messageKey));
    }

    public SudokuException(String messageKey, Throwable cause) {
        super(ResourceBundle.getBundle("messages", Locale.getDefault()).getString(messageKey), cause);
    }
}
