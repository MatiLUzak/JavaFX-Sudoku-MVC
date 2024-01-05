package org.example.exceptions;

import java.sql.SQLException;

public class SudokuDatabaseException extends SudokuException {
    public SudokuDatabaseException(String messageKey) {
        super(messageKey);
    }

    public SudokuDatabaseException(String messageKey, SQLException cause) {
        super(messageKey, cause);
    }
}
