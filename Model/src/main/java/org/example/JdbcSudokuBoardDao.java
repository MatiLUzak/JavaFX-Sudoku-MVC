package org.example;

import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.SudokuDatabaseException;
import org.example.exceptions.SudokuException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    private static final Logger logger = LogManager.getLogger(JdbcSudokuBoardDao.class);
    private final String boardName;

    public JdbcSudokuBoardDao(String boardName) {
        this.boardName = boardName;
    }

    @Override
    public SudokuBoard read() throws SudokuException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement selectBoard = conn.prepareStatement(
                     "SELECT board_id FROM boards WHERE name = ?");
             PreparedStatement selectFields = conn.prepareStatement(
                     "SELECT x, y, value FROM fields WHERE board_id = ?")) {

            selectBoard.setString(1, this.boardName);
            ResultSet rsBoard = selectBoard.executeQuery();

            if (!rsBoard.next()) {
                throw new SudokuDatabaseException("BoardNotFound");
            }

            long boardId = rsBoard.getLong("board_id");

            selectFields.setLong(1, boardId);
            ResultSet rsFields = selectFields.executeQuery();

            while (rsFields.next()) {
                int x = rsFields.getInt("x");
                int y = rsFields.getInt("y");
                int value = rsFields.getInt("value");
                board.set(x, y, value);
            }

        } catch (SQLException e) {
            logger.error("Error reading SudokuBoard from database", e);
            throw new SudokuDatabaseException("DatabaseReadError", e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws SudokuException {
        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
            PreparedStatement insertBoard = conn.prepareStatement(
                    "INSERT INTO boards (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement insertField = conn.prepareStatement(
                    "INSERT INTO fields (board_id, x, y, value) VALUES (?, ?, ?, ?)");

            conn.setAutoCommit(false);

            insertBoard.setString(1, this.boardName);
            int affectedRows = insertBoard.executeUpdate();

            if (affectedRows == 0) {
                throw new SudokuDatabaseException("DatabaseWriteNoRowsAffected");
            }

            long boardId;
            try (ResultSet generatedKeys = insertBoard.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    boardId = generatedKeys.getLong(1);
                } else {
                    throw new SudokuDatabaseException("DatabaseWriteNoIdObtained");
                }
            }

            for (int x = 0; x < SudokuBoard.GRID_SIZE; x++) {
                for (int y = 0; y < SudokuBoard.GRID_SIZE; y++) {
                    insertField.setLong(1, boardId);
                    insertField.setInt(2, x);
                    insertField.setInt(3, y);
                    insertField.setInt(4, obj.get(x, y));
                    insertField.executeUpdate();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            logger.error("Error writing SudokuBoard to database", e);
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    logger.error("Error on rollback", ex);
                }
            }
            throw new SudokuDatabaseException("DatabaseWriteError", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection", e);
                }
            }
        }
    }

    @Override
    public void close() throws SudokuException {
        // Implementacja zamknięcia zasobów, jeśli to konieczne
    }
}
