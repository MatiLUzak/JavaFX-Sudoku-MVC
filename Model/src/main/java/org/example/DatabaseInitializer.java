package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String DATABASE_URL = "jdbc:sqlite:sudoku.db";

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
            // Wyłączenie auto commit
            conn.setAutoCommit(false);

            try (Statement stmt = conn.createStatement()) {
                String sqlBoards = "CREATE TABLE IF NOT EXISTS boards (" +
                        "board_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL);";

                String sqlFields = "CREATE TABLE IF NOT EXISTS fields (" +
                        "field_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "board_id INTEGER," +
                        "x INTEGER NOT NULL," +
                        "y INTEGER NOT NULL," +
                        "value INTEGER NOT NULL," +
                        "FOREIGN KEY(board_id) REFERENCES boards(board_id));";

                stmt.execute(sqlBoards);
                stmt.execute(sqlFields);

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewDatabase();
    }
}


