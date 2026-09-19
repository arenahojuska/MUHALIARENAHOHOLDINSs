package APITESTING;

import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ApiTest {

    @Test
    public void e2eAuthAndFetchDomainsTest() throws Exception {
        Playwright playwright = Playwright.create();
        APIRequestContext apiRequest = playwright.request().newContext();

        // 1. Build OAuth token request body matching DevTools
        FormData formData = FormData.create()
                .set("username", "nmatumba@randmutual.co.za")
                .set("password", "RMA@Cate01")
                .set("grant_type", "password")
                .set("scope", "openid offline_access");

        // 2. Execute Authentication POST
        APIResponse tokenResponse = apiRequest.post(
            "https://sfpre.randmutual.co.za/auth/connect/token", 
            RequestOptions.create().setForm(formData)
        );

        System.out.println("Token Request Status: " + tokenResponse.status());
        System.out.println("Token Response Body: " + tokenResponse.text());

        // Assert Token Call Succeeded (HTTP 200)
        assertThat(tokenResponse).isOK();

        // 3. Extract access_token from response JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(tokenResponse.text());
        String accessToken = jsonNode.get("access_token").asText();
        System.out.println("\nSuccessfully extracted Access Token!");

        // 4. Use Bearer token for GetTopHundredDomains GET request
        APIResponse domainResponse = apiRequest.get(
            "https://sfpre.randmutual.co.za/mdm/api/ContactValidation/GetTopHundredDomains",
            RequestOptions.create()
                    .setHeader("Authorization", "Bearer " + accessToken)
                    .setHeader("Accept", "application/json")
        );

        System.out.println("\nDomains Endpoint Status: " + domainResponse.status());
        System.out.println("Domains Response Body: " + domainResponse.text());

        // Assert API Call Succeeded
        assertThat(domainResponse).isOK();

        // Clean up resources
        apiRequest.dispose();
        playwright.close();
    }
}