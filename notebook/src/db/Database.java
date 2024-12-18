package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;
    private Connection connection;
    private final String URL = "jdbc:sqlite:notebook.db"; // SQLite veritabanı dosyasının yolu

    private Database() {
        try {
            Class.forName("org.sqlite.JDBC");  // Java 9 ve sonrası için
            connection = DriverManager.getConnection(URL);
            System.out.println("Connected to SQLite database!");

            // Tabloyu oluştur
            createTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Database connection failed!");
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Not eklemek için
    public void addNote(String title, String content) {
        String sql = "INSERT INTO notes(title, content) VALUES(?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.executeUpdate();
            System.out.println("Note added!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding note.");
        }
    }

    // Notları okumak için
    public List<String> getNotes() {
        List<String> notes = new ArrayList<>();
        String sql = "SELECT * FROM notes";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                notes.add("ID: " + rs.getInt("id") + ", Title: " + rs.getString("title") + ", Content: " + rs.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error reading notes.");
        }

        return notes;
    }

    // Not güncellemek için
    public void updateNote(int id, String newTitle, String newContent) {
        String sql = "UPDATE notes SET title = ?, content = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            pstmt.setString(2, newContent);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Note updated!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating note.");
        }
    }

    // Not silmek için
    public void deleteNote(int id) {
        String sql = "DELETE FROM notes WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Note deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting note.");
        }
    }

    // Veritabanı tablosunu oluşturur
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS notes (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "title TEXT NOT NULL," +
                     "content TEXT NOT NULL);";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'notes' created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating table.");
        }
    }

    // Veritabanı bağlantısını kapatmak için metod
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to close the connection.");
        }
    }
}
