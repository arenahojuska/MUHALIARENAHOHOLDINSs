package muhaliholdings;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.io.IOException;

public class Login {

    WebDriver driver;
    WebDriverWait wait;

    public Login(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void verify_Login() throws IOException, InterruptedException {

        // Use modular URL from TestData
        driver.get(TestData.getBaseUrl());
        Screenshot.takeScreenshot(driver);

        // Step 1: Title validation
        String actualTitle = driver.getTitle();
        System.out.println("Page title is: " + actualTitle);

        if (actualTitle.equals("MUHALI E SHOP")) {
            System.out.println("Title is correct");
        } else {
            Screenshot.takeScreenshot(driver);
            Assert.fail("Title mismatch. Found: " + actualTitle);
        }

        // Step 2: Enter email - Using modular locators and data
        WebElement emailField = driver.findElement(ShopLocators.emailField());
        if (emailField.isDisplayed() && emailField.isEnabled()) {
            emailField.sendKeys(TestData.getAdminEmail());
            Assert.assertEquals(emailField.getAttribute("value"), TestData.getAdminEmail(), "Email entry failed");
            System.out.println("Email entered correctly");
        } else {
            Assert.fail("Email field not accessible");
        }

        // Step 3: Enter password - Using modular locators and data
        WebElement passwordField = driver.findElement(ShopLocators.passwordField());
        if (passwordField.isDisplayed() && passwordField.isEnabled()) {
            passwordField.sendKeys(TestData.getAdminPassword());
            Assert.assertEquals(passwordField.getAttribute("value"), TestData.getAdminPassword(), "Password entry failed");
            System.out.println("Password entered correctly");
        } else {
            Assert.fail("Password field not accessible");
        }

        // Step 4: Click login button - Using modular locator
        WebElement loginBtn = driver.findElement(ShopLocators.loginBtn());
        if (loginBtn.isDisplayed() && loginBtn.isEnabled()) {
            loginBtn.click();
            System.out.println("Login button clicked");
        } else {
            Assert.fail("Login button not clickable");
        }

        // Step 5: Validate login success
        try {
            boolean isLoggedIn = wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            ShopLocators.bodyTag(), "Logout")
            );

            if (isLoggedIn) {
                System.out.println("Login successful");
                Screenshot.takeScreenshot(driver);
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            Assert.fail("Login validation failed or Logout button not found.");
        }
    }
}