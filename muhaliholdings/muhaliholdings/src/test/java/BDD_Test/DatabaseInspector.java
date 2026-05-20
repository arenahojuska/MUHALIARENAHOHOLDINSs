package BDD_Test;

import java.sql.*;

public class DatabaseInspector {

    public static void main(String[] args) {
        // Your specific access details
        String url = "jdbc:postgresql://localhost:5432/AJM";
        String user = "postgres";
        String password = "ARENAHOJUSKA";

        String query = "SELECT * FROM employees";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // 1. Get Metadata to find column names
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("--- Table Structure: employees ---");
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + " [" + metaData.getColumnTypeName(i) + "]\t");
            }
            System.out.println("\n----------------------------------");

            // 2. Print the actual data rows
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
            
            if(rowCount == 0) {
                System.out.println("Connection successful, but the table is empty.");
            }

        } catch (SQLException e) {
            System.err.println("Database Error!");
            // This will tell us if the password is wrong or the database 'AJM' doesn't exist
            e.printStackTrace(); 
        }
    }
}