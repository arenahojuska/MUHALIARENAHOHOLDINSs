package API_Testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = TestData.getApiBaseUrl();
    }

    @Test
    public void getEmployee() {

        // ID of the employee to retrieve
        int employeeId = 5;

        // Execute GET request
        Response response = given()
            .header("Content-Type", "application/json")
        .when()
            .get("/" + employeeId)
        .then()
            .extract().response();

        // Print response details
        System.out.println("GET Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + " ms");
        System.out.println("Response Body:");
        System.out.println(response.getBody().asPrettyString());

        // Verify status code
        Assert.assertEquals(
            response.getStatusCode(),
            200,
            "GET request failed"
        );

        // Verify response body is not empty
        Assert.assertFalse(
            response.getBody().asString().isEmpty(),
            "Response body is empty"
        );

        // Verify speed
        double speedSeconds = response.getTime() / 1000.0;
        System.out.println("Response Speed: " + speedSeconds + "s");

        Assert.assertTrue(
            speedSeconds < 5.0,
            "GET request exceeded time limit"
        );
    }
}
