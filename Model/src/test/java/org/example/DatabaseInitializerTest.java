package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseInitializerTest {

    private final String dbUrl = "jdbc:sqlite:sudoku.db";

    @BeforeEach
    public void setUp() {
        File dbFile = new File("sudoku.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }

        DatabaseInitializer.createNewDatabase();
    }

    @Test
    public void testDatabaseStructureAndContent() throws Exception {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {


            stmt.execute("INSERT INTO boards (name) VALUES ('TestBoard')");
            stmt.execute("INSERT INTO fields (board_id, x, y, value) VALUES (1, 0, 0, 5)");


            try (PreparedStatement ps = conn.prepareStatement("PRAGMA table_info(boards)")) {
                ResultSet rs = ps.executeQuery();
                assertTrue(rs.next(), "Tabela 'boards' nie istnieje lub jest pusta.");
            }


            try (PreparedStatement ps = conn.prepareStatement("PRAGMA table_info(fields)")) {
                ResultSet rs = ps.executeQuery();
                assertTrue(rs.next(), "Tabela 'fields' nie istnieje lub jest pusta.");
            }


            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM boards WHERE name = 'TestBoard'")) {
                ResultSet rs = ps.executeQuery();
                assertTrue(rs.next(), "Nie znaleziono danych w tabeli 'boards'.");
                assertEquals("TestBoard", rs.getString("name"), "Nazwa planszy nie zgadza się.");
            }


            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM fields WHERE board_id = 1")) {
                ResultSet rs = ps.executeQuery();
                assertTrue(rs.next(), "Nie znaleziono danych w tabeli 'fields'.");
                assertEquals(5, rs.getInt("value"), "Wartość w polu nie zgadza się.");
            }
        }
    }

    @AfterEach
    public void tearDown() {

        File dbFile = new File("sudoku.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }
}
