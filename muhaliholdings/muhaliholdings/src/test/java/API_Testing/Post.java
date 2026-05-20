package API_Testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Post {

    @BeforeClass
    public void setup() {
        // Uses centralized URL from TestData
        RestAssured.baseURI = TestData.getApiBaseUrl();
    }

    @Test
    public void createNewEmployeeViaPost() {
        // 1. GENERATE DYNAMIC DATA
        // Independent: Uses the random generator from TestData
        Map<String, Object> requestBody = TestData.generateRandomEmployee();
        System.out.println("Generated Data: " + requestBody);

        // 2. EXECUTE POST REQUEST
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post()
            .then()
                .extract().response();

        // 3. VERIFY STATUS CODE (201 Created)
        Assert.assertEquals(response.getStatusCode(), 201, "Expected 201 but got: " + response.getStatusCode());

        // 4. VERIFY RESPONSE IS JSON
        String contentType = response.header("Content-Type");
        Assert.assertTrue(contentType.contains("application/json"), "Expected application/json but got: " + contentType);

        // 5. VERIFY BODY (Compare response values against randomly generated input)
        var json = response.jsonPath();
        Assert.assertEquals(json.getString("firstName"), requestBody.get("firstName"), "FirstName mismatch");
        Assert.assertEquals(json.getString("lastName"), requestBody.get("lastName"), "LastName mismatch");
        Assert.assertEquals(json.getString("email"), requestBody.get("email"), "Email mismatch");

        // 6. VERIFY DATA TYPES
        Assert.assertTrue(json.get("id") instanceof Integer, "ID should be an Integer");
        Assert.assertTrue(json.get("firstName") instanceof String, "firstName should be a String");
        Assert.assertTrue(json.get("email") instanceof String, "email should be a String");

        // 7. VERIFY SPEED
        double speedSeconds = response.getTime() / 1000.0;
        System.out.println("Creation Speed: " + speedSeconds + "s");
        Assert.assertTrue(speedSeconds < 10.5, "POST request was too slow: " + speedSeconds + "s");

        // Output result for independent verification
        System.out.println("Independent Test Success - Created ID: " + json.getInt("id"));
    }
}