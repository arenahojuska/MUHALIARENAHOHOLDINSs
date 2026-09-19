package API_Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Post {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI =
                "https://sfpre.randmutual.co.za";
    }

    @Test
    public void loginAPI() {

        // ============================================================
        // LOGIN ENDPOINT
        // ============================================================

        String endpoint = "/auth/connect/token";


        // ============================================================
        // SEND LOGIN REQUEST
        // ============================================================

        Response response = given()

                // OAuth token endpoint expects form data
                .header(
                        "Content-Type",
                        "application/x-www-form-urlencoded"
                )

                // Login credentials
                .formParam(
                        "username",
                        "nmatumba@randmutual.co.za"
                )

                .formParam(
                        "password",
                        "RMA@Cate01"
                )

                .formParam(
                        "grant_type",
                        "password"
                )

                .formParam(
                        "scope",
                        "openid offline_access"
                )

                .when()

                .post(endpoint)

                .then()

                .extract()
                .response();


        // ============================================================
        // PRINT RESPONSE
        // ============================================================

        System.out.println();
        System.out.println("==============================================");
        System.out.println("LOGIN API RESPONSE");
        System.out.println("==============================================");

        System.out.println(
                "STATUS CODE: "
                        + response.getStatusCode()
        );

        System.out.println();

        System.out.println("HEADERS:");

        System.out.println(
                response.getHeaders()
        );

        System.out.println();

        System.out.println("CONTENT TYPE:");

        System.out.println(
                response.getContentType()
        );

        System.out.println();

        System.out.println("RESPONSE BODY:");

        System.out.println(
                response.asPrettyString()
        );

        System.out.println("==============================================");


        // ============================================================
        // 1. STATUS CODE
        // ============================================================

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Login API should return HTTP 200"
        );


        // ============================================================
        // 2. RESPONSE BODY
        // ============================================================

        Assert.assertFalse(
                response.getBody()
                        .asString()
                        .trim()
                        .isEmpty(),
                "Login response body should not be empty"
        );


        // ============================================================
        // 3. CONTENT TYPE
        // ============================================================

        Assert.assertTrue(
                response.getContentType()
                        .toLowerCase()
                        .contains("json"),
                "Login response should be JSON"
        );


        // ============================================================
        // 4. ACCESS TOKEN
        // ============================================================

        String accessToken =
                response.jsonPath()
                        .getString("access_token");

        Assert.assertNotNull(
                accessToken,
                "access_token should be returned"
        );

        Assert.assertFalse(
                accessToken.trim().isEmpty(),
                "access_token should not be empty"
        );


        // ============================================================
        // 5. TOKEN TYPE
        // ============================================================

        String tokenType =
                response.jsonPath()
                        .getString("token_type");

        Assert.assertNotNull(
                tokenType,
                "token_type should be returned"
        );


        // ============================================================
        // 6. TOKEN DATA TYPE
        // ============================================================

        Assert.assertTrue(
                accessToken instanceof String,
                "access_token should be a String"
        );


        // ============================================================
        // 7. PRINT TOKEN INFORMATION
        // ============================================================

        System.out.println(
                "Token Type: "
                        + tokenType
        );

        System.out.println(
                "Access Token Received: YES"
        );

        System.out.println(
                "Access Token Length: "
                        + accessToken.length()
        );

        System.out.println();

        System.out.println("LOGIN TEST PASSED");
        System.out.println("==============================================");
    }
}