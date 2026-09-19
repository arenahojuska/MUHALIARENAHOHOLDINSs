package muhaliholdings;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.io.IOException;

public class PurchasePrice {
    WebDriver driver;
    WebDriverWait wait;

    public PurchasePrice(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void verify_Cart_Total_Calculation() throws IOException {
        double calculatedTotal = 0.0;

        // Loop through product indices 2 to 24 (matching your Robot script range)
        for (int i = 2; i <= 24; i++) {
            try {
                // Step 1: Get raw text from the price locator
                String rawText = driver.findElement(ShopLocators.productPrice(i)).getText();
                System.out.println("Index " + i + " raw text: " + rawText);

                // Step 2: Clean the string (Remove 'R' and non-numeric characters)
                String cleanValue = rawText.replaceAll("[^0-9.]", "");
                
                // Step 3: Validation and Addition
                if (!cleanValue.isEmpty() && cleanValue.contains(".")) {
                    double price = Double.parseDouble(cleanValue);
                    calculatedTotal += price;
                    System.out.println("✅ Adding price: " + cleanValue);
                } else {
                    System.out.println("❌ Skipping: " + rawText + " (Cleaned: " + cleanValue + ")");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Element at index " + i + " not found or could not be processed.");
            }
        }

        // Format calculated total to 2 decimal places for comparison
        String formattedCalculated = String.format("%.2f", calculatedTotal);
        System.out.println("==============================");
        System.out.println("🏁 Final Calculated Total: R" + formattedCalculated);
        System.out.println("==============================");

        // --- Get and Clean UI Cart Total ---
        String uiTotalRaw = driver.findElement(ShopLocators.cartUiTotal()).getText();
        System.out.println("Cart UI shows: " + uiTotalRaw);
        
        String uiClean = uiTotalRaw.replaceAll("[^0-9.]", "");
        double uiValue = Double.parseDouble(uiClean);
        String formattedUi = String.format("%.2f", uiValue);

        System.out.println("Formatted Cart UI Total: R" + formattedUi);

        // --- Comparison Logic with Screenshots ---
        if (formattedCalculated.equals(formattedUi)) {
            System.out.println("🟢 Cart total matches UI (both R" + formattedCalculated + ")");
            Screenshot.takeScreenshot(driver);
        } else {
            System.out.println("🔴 Cart total mismatch! Expected UI: R" + formattedUi + ", Calculated: R" + formattedCalculated);
            Screenshot.takeScreenshot(driver);
            Assert.fail("Cart total did not match UI. Expected: " + formattedUi + " but calculated: " + formattedCalculated);
        }
    }
}