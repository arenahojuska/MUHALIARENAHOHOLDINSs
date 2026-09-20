package API_Testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // Connection details for AJM Postgres
    private static final String URL = "jdbc:postgresql://localhost:5432/AJM";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ARENAHOJUSKA"; //

    public static Connection getConnection() throws SQLException {
        try {
            // Register the PostgreSQL Driver
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found. Add the Maven dependency.");
        }
    }
}