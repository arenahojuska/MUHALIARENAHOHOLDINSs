package API_Testing;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestData {

    public static String getApiBaseUrl() { 
        return "http://localhost:8080/api/employees"; 
    }

    public static Map<String, Object> generateRandomEmployee() {
        int randomNum = new Random().nextInt(1000);
        Map<String, Object> data = new HashMap<>();
        data.put("firstName", "John" + randomNum);
        data.put("lastName", "Doe" + randomNum);
        data.put("email", "john.doe" + randomNum + "@muhali.com");
        data.put("password", "Pass@123");
        return data;
    }
}