package API_Testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.sql.*;
import static io.restassured.RestAssured.given;

public class Get {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = TestData.getApiBaseUrl();
    }

    @Test
    public void validateEmployeeDataIndependently() throws SQLException {
        // Target an existing ID in your large database
        int targetEmployeeId = 1; 

        // 1. API GET Request
        Response response = given()
            .when()
                .get("/" + targetEmployeeId)
            .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "API failed to find ID: " + targetEmployeeId);

        var jsonResponse = response.jsonPath();
        String apiFirstName = jsonResponse.getString("firstName");
        String apiEmail = jsonResponse.getString("email");

        // 2. Database Verification
        System.out.println("Independent Check: Verifying against SQL Database AJM...");
        Connection conn = DatabaseConfig.getConnection();
        
        // FIX: Changed 'email' to 'email_id' to match common Hibernate/JPA naming
        String sql = "SELECT first_name, email_id FROM employees WHERE id = ?";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, targetEmployeeId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String dbName = rs.getString("first_name");
            String dbEmail = rs.getString("email_id"); // Update this getter as well

            Assert.assertEquals(apiFirstName, dbName, "First Name mismatch!");
            Assert.assertEquals(apiEmail, dbEmail, "Email mismatch!");
            
            System.out.println("Success! API Name [" + apiFirstName + "] matches DB Name [" + dbName + "]");
        } else {
            Assert.fail("Record ID " + targetEmployeeId + " not found in Database.");
        }

        rs.close();
        pstmt.close();
        conn.close();
    }
}