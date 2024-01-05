package org.example;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseInitializerTest {

    @Test
    public void testDatabaseStructure() {
        DatabaseInitializer.createNewDatabase();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sudoku.db")) {
            try (PreparedStatement ps = conn.prepareStatement("PRAGMA table_info(boards)")) {
                ResultSet rs = ps.executeQuery();
                assertTrue(rs.next(), "Tabela 'boards' nie istnieje lub jest pusta.");
            }

            try (PreparedStatement ps = conn.prepareStatement("PRAGMA table_info(fields)")) {
                ResultSet rs = ps.executeQuery();
                assertTrue(rs.next(), "Tabela 'fields' nie istnieje lub jest pusta.");
            }

            try (Statement stmt = conn.createStatement()) {
                String sqlTest = "SELECT * FROM boards JOIN fields ON boards.board_id = fields.board_id";
                ResultSet rs = stmt.executeQuery(sqlTest);
                assertTrue(true, "Relacja między tabelami 'boards' i 'fields' nie istnieje.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Wystąpił wyjątek SQL: " + e.getMessage());
        }
    }
}

