package API_Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get {

    @BeforeClass
    public void setup() {

        RestAssured.baseURI =
                "https://sfpre.randmutual.co.za";
    }

    @Test
    public void validateRolePlayerTypesAPI() {

        // ============================================================
        // SEND GET REQUEST
        // ============================================================

        Response response = given()

                .queryParam("typeId", 10)
                .queryParam("typeId", 11)
                .queryParam("typeId", 12)
                .queryParam("typeId", 13)
                .queryParam("typeId", 14)
                .queryParam("typeId", 15)
                .queryParam("typeId", 16)
                .queryParam("typeId", 17)
                .queryParam("typeId", 18)
                .queryParam("typeId", 19)
                .queryParam("typeId", 20)
                .queryParam("typeId", 21)
                .queryParam("typeId", 22)
                .queryParam("typeId", 23)
                .queryParam("typeId", 24)
                .queryParam("typeId", 25)
                .queryParam("typeId", 26)
                .queryParam("typeId", 27)
                .queryParam("typeId", 28)
                .queryParam("typeId", 29)
                .queryParam("typeId", 30)
                .queryParam("typeId", 31)
                .queryParam("typeId", 32)
                .queryParam("typeId", 33)
                .queryParam("typeId", 34)
                .queryParam("typeId", 35)
                .queryParam("typeId", 36)
                .queryParam("typeId", 37)
                .queryParam("typeId", 38)
                .queryParam("typeId", 39)
                .queryParam("typeId", 40)
                .queryParam("typeId", 41)
                .queryParam("typeId", 42)

                .when()

                .get("/clc/api/RolePlayer/RolePlayer/GetRolePlayerTypes")

                .then()

                .extract()
                .response();


        // ============================================================
        // PRINT RESPONSE INFORMATION
        // ============================================================

        System.out.println("==============================================");
        System.out.println("API RESPONSE");
        System.out.println("==============================================");

        System.out.println("STATUS CODE: "
                + response.getStatusCode());

        System.out.println();

        System.out.println("HEADERS:");
        System.out.println(response.getHeaders());

        System.out.println();

        System.out.println("CONTENT TYPE:");
        System.out.println(response.getContentType());

        System.out.println();

        System.out.println("RESPONSE BODY:");
        System.out.println(response.asPrettyString());

        System.out.println("==============================================");


        // ============================================================
        // 1. TEST STATUS CODE
        // ============================================================

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected HTTP status code 200"
        );


        // ============================================================
        // 2. TEST RESPONSE BODY IS NOT EMPTY
        // ============================================================

        Assert.assertFalse(
                response.getBody().asString().isEmpty(),
                "Response body should not be empty"
        );


        // ============================================================
        // 3. TEST CONTENT TYPE
        // ============================================================

        Assert.assertTrue(
                response.getContentType().contains("application/json"),
                "Response should be JSON"
        );


        // ============================================================
        // 4. TEST RESPONSE BODY IS A JSON ARRAY
        // ============================================================

        List<String> features =
                response.jsonPath().getList("$");

        Assert.assertNotNull(
                features,
                "Response should contain a JSON array"
        );

        Assert.assertFalse(
                features.isEmpty(),
                "Feature list should not be empty"
        );


        // ============================================================
        // 5. TEST DATA TYPES
        // ============================================================

        for (Object feature : features) {

            Assert.assertTrue(
                    feature instanceof String,
                    "Every feature returned by the API should be a String"
            );
        }


        // ============================================================
        // 6. TEST A SPECIFIC RESPONSE VALUE
        // ============================================================

        Assert.assertTrue(
                features.contains("SendSMSFeature"),
                "SendSMSFeature should be returned"
        );


        // ============================================================
        // 7. TEST RESPONSE HEADER
        // ============================================================

        String contentType =
                response.getHeader("Content-Type");

        Assert.assertNotNull(
                contentType,
                "Content-Type header should be present"
        );

        System.out.println(
                "Content-Type Header: " + contentType
        );


        // ============================================================
        // 8. PRINT NUMBER OF FEATURES
        // ============================================================

        System.out.println(
                "Number of Features Returned: "
                        + features.size()
        );


        // ============================================================
        // 9. PRINT EACH FEATURE
        // ============================================================

        System.out.println();
        System.out.println("FEATURES:");

        for (String feature : features) {

            System.out.println(
                    " - " + feature
            );
        }

        System.out.println("==============================================");
    }
}