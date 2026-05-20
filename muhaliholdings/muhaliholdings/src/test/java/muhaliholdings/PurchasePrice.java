package muhaliholdings;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchasePrice {
    private final WebDriver driver;

    public PurchasePrice(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
    }

    public double calculateCumulativeCartTotal(int startIdx, int endIdx) {
        double calculatedTotal = 0.0;

        for (int i = startIdx; i <= endIdx; i++) {
            try {
                WebElement element = Utilities.waitForVisibility(driver, ShopLocators.getProductPrice(i), 15);
                String rawText = element.getText();
                System.out.println("Index " + i + " raw text: " + rawText);

                String cleanValue = rawText.replaceAll("[^0-9.]", "");
                
                if (!cleanValue.isEmpty() && cleanValue.contains(".")) {
                    double price = Double.parseDouble(cleanValue);
                    calculatedTotal += price;
                    System.out.println("Adding price: " + cleanValue);
                } else {
                    System.out.println("Skipping: " + rawText + " (Cleaned: " + cleanValue + ")");
                }
            } catch (Exception e) {
                System.out.println("Element at index " + i + " not found or could not be processed.");
            }
        }
        return calculatedTotal;
    }

    public double getUiTotalAmount() {
    
        WebElement totalElement = Utilities.waitForVisibility(driver, ShopLocators.CART_UI_TOTAL, 15);
        String uiTotalRaw = totalElement.getText();
        System.out.println("Cart UI shows: " + uiTotalRaw);
        
        String uiClean = uiTotalRaw.replaceAll("[^0-9.]", "");
        return Double.parseDouble(uiClean);
    }

    public String verifyTotalsMatch(double calculatedTotal, double uiValue) {
        String formattedCalculated = String.format("%.2f", calculatedTotal);
        String formattedUi = String.format("%.2f", uiValue);

        System.out.println("==============================");
        System.out.println("Final Calculated Total: R" + formattedCalculated);
        System.out.println("Formatted Cart UI Total: R" + formattedUi);
        System.out.println("==============================");

        if (formattedCalculated.equals(formattedUi)) {
            System.out.println("Cart total matches UI (both R" + formattedCalculated + ")");
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            System.out.println("Cart total mismatch! Expected UI: R" + formattedUi + ", Calculated: R" + formattedCalculated);
            Screenshot.takeScreenshot(driver);
            return "Cart total did not match UI. Expected: " + formattedUi + " but calculated: " + formattedCalculated;
        }
    }
}