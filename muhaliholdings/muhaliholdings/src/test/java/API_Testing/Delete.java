package API_Testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Delete {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = TestData.getApiBaseUrl();
    }

    @Test
    public void deleteEmployeeViaDelete() {
        // ID targeted for deletion
        int deleteId = 5;

        // Execute DELETE request
        Response response = given()
            .header("Content-Type", "application/json")
        .when()
            .delete("/" + deleteId)
        .then()
            .extract().response();

        // Verify status code (200 OK or 204 No Content)
        int statusCode = response.getStatusCode();
        System.out.println("Delete Status Code: " + statusCode);
        
        boolean isSuccess = (statusCode == 200 || statusCode == 204);
        Assert.assertTrue(isSuccess, "Delete failed. Received: " + statusCode);

        // Verify Speed
        double speedSeconds = response.getTime() / 1000.0;
        System.out.println("Deletion Speed: " + speedSeconds + "s");
        Assert.assertTrue(speedSeconds < 5.0, "Delete request exceeded time limit");
    }
}