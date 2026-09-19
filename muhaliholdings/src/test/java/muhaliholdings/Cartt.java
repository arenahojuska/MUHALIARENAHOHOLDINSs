package muhaliholdings;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.io.IOException;

public class Cartt {

    WebDriver driver;
    WebDriverWait wait;

    public Cartt(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void verify_Cart_Functionality() throws IOException, InterruptedException {

        // Step 1: Navigate to Shop - Using modular locator
        WebElement shopBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.shopLink())
        );

        if (shopBtn.isDisplayed() && shopBtn.isEnabled()) {
            shopBtn.click();
            System.out.println("Shop button clicked");
            Screenshot.takeScreenshot(driver);
        } else {
            Screenshot.takeScreenshot(driver);
            Assert.fail("Shop button is not clickable");
        }

        // Step 2: Verify Shop page loaded - Using modular locator
        WebElement shopHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.shopHeader())
        );

        if (shopHeader.isDisplayed()) {
            System.out.println("Shop page is visible");
        } else {
            Screenshot.takeScreenshot(driver);
            Assert.fail("Shop page failed to load");
        }

        // Step 3: Click products (Loop through items from TestData)
        int totalToClick = Integer.parseInt(TestData.getExpectedCartCount());
        for (int i = 1; i <= totalToClick; i++) {
            
            // Using modular dynamic locator method
            WebElement btn = wait.until(
                    ExpectedConditions.elementToBeClickable(ShopLocators.productAddBtn(i))
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
                System.out.println("Product " + i + " clicked");
            } else {
                Screenshot.takeScreenshot(driver);
                Assert.fail("Product " + i + " is not clickable");
            }
        }

        // Step 4: Navigate to Cart - Using modular locator
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        WebElement cartBtn = driver.findElement(ShopLocators.cartIcon());

        if (cartBtn.isDisplayed() && cartBtn.isEnabled()) {
            cartBtn.click();
            System.out.println("Cart button clicked");
            Screenshot.takeScreenshot(driver);
        } else {
            Screenshot.takeScreenshot(driver);
            Assert.fail("Cart button is not clickable");
        }

        // Step 5: Validate cart count - Using modular locator and data
        WebElement countElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.cartTotalText())
        );

        String actualCount = countElement.getText();
        String expectedCount = TestData.getExpectedCartCount();

        if (actualCount.equals(expectedCount)) {
            System.out.println("Cart validation passed: Found " + expectedCount + " items.");
            Screenshot.takeScreenshot(driver);
        } else {
            Screenshot.takeScreenshot(driver);
            Assert.fail("Expected " + expectedCount + " products but found " + actualCount);
        }
    }
}