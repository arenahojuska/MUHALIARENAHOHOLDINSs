package hello;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.time.Duration;

public class Cartt {

    public static void cart(WebDriver driver) throws IOException, InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1: Navigate to Shop
        WebElement shopBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a[href='/shop']")
                )
        );

        if (shopBtn.isDisplayed() && shopBtn.isEnabled()) {
            shopBtn.click();
            System.out.println("Shop button clicked");
            Screenshot.takeScreenshot(driver);
        } else {
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Shop button is not clickable");
        }

        // Step 2: Verify Shop page loaded
        try {
            WebElement shopHeader = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[@id='root']/div/h2")
                    )
            );

            if (shopHeader.isDisplayed()) {
                System.out.println("Shop page is visible");
                Screenshot.takeScreenshot(driver);
            } else {
                Screenshot.takeScreenshot(driver);
                throw new AssertionError("Shop header not visible");
            }

        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Shop page failed to load");
        }

        // Step 3: Click products
        for (int i = 1; i <= 23; i++) {

            String btnXpath = "//*[@id='root']/div/div[3]/div[" + i + "]/div[2]/button";

            try {
                WebElement btn = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath(btnXpath))
                );

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView(true);", btn);

                if (btn.isDisplayed() && btn.isEnabled()) {
                    btn.click();
                    System.out.println("Product " + i + " clicked");
                    Screenshot.takeScreenshot(driver);
                } else {
                    Screenshot.takeScreenshot(driver);
                    throw new AssertionError("Product " + i + " is not clickable");
                }

            } catch (Exception e) {
                Screenshot.takeScreenshot(driver);
                throw new AssertionError("Failed to click product " + i);
            }
        }

        System.out.println("All products clicked successfully");
        Screenshot.takeScreenshot(driver);

        // Step 4: Navigate to Cart
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        WebElement cartBtn = driver.findElement(
                By.xpath("//*[@id='root']/nav/div[1]/div[3]/a/span")
        );

        if (cartBtn.isDisplayed() && cartBtn.isEnabled()) {
            cartBtn.click();
            System.out.println("Cart button clicked");
            Screenshot.takeScreenshot(driver);
        } else {
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Cart button is not clickable");
        }

        // Step 5: Scroll down
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");

        Screenshot.takeScreenshot(driver);

        // Step 6: Validate cart count
        WebElement countElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id='root']/div/div/div[25]/div/div/div[1]/span[2]")
                )
        );

        String actualCount = countElement.getText();
        System.out.println("Cart count found: " + actualCount);

        if (actualCount.equals("23")) {
            System.out.println("Cart validation passed");
            Screenshot.takeScreenshot(driver);
        } else {
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Cart count mismatch. Expected 23 but got " + actualCount);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            Login.verify_Login(driver);
            cart(driver);

        } finally {
            driver.quit();
        }
    }
}