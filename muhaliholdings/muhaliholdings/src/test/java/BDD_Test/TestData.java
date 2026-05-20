package BDD_Test;

import org.testng.annotations.DataProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestData {

    // Database Connection Details
    private static final String URL = "jdbc:postgresql://localhost:5432/AJM";
    private static final String USER = "postgres";
    private static final String PASS = "ARENAHOJUSKA";

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        List<Object[]> data = new ArrayList<>();
        
        // Updated to use the exact column name 'email_id' from your inspection
        String query = "SELECT email_id, password FROM employees"; 

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Fetching the strings from your varchar columns
                String email = rs.getString("email_id");
                String password = rs.getString("password");
                
                // Add to our list of test cases
                data.add(new Object[] { email, password });
            }

        } catch (SQLException e) {
            System.err.println("❌ Database Retrieval Failed!");
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }

    public static String getBaseUrl() { return "https://eshopmuhali.netlify.app/"; }
    public static String getExpectedTitle() { return "MUHALI E SHOP"; }
}