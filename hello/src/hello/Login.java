package hello;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.time.Duration;

public class Login {

    public static void verify_Login(WebDriver driver) throws IOException, InterruptedException {

        driver.manage().window().maximize();
        driver.get("https://eshopmuhali.netlify.app/");
        Screenshot.takeScreenshot(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Title validation
        String actualTitle = driver.getTitle();
        System.out.println("Page title is: " + actualTitle);

        if (actualTitle.equals("MUHALI E SHOP")) {
            System.out.println("Title is correct");
            Screenshot.takeScreenshot(driver);
        } else {
            System.out.println("Title mismatch");
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Title mismatch");
        }

        // Step 2: Enter email
        WebElement emailField = driver.findElement(By.id("email"));

        if (emailField.isDisplayed() && emailField.isEnabled()) {
            emailField.sendKeys("admin@example.com");

            String enteredEmail = emailField.getAttribute("value");

            if (enteredEmail.equals("admin@example.com")) {
                System.out.println("Email entered correctly");
                Screenshot.takeScreenshot(driver);
            } else {
                System.out.println("Email not entered correctly");
                Screenshot.takeScreenshot(driver);
                throw new AssertionError("Email not entered correctly");
            }

        } else {
            System.out.println("Email field not accessible");
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Email field not accessible");
        }

        // Step 3: Enter password
        WebElement passwordField = driver.findElement(By.id("password"));

        if (passwordField.isDisplayed() && passwordField.isEnabled()) {
            passwordField.sendKeys("Pass@123");

            String enteredPassword = passwordField.getAttribute("value");

            if (enteredPassword.equals("Pass@123")) {
                System.out.println("Password entered correctly");
                Screenshot.takeScreenshot(driver);
            } else {
                System.out.println("Password not entered correctly");
                Screenshot.takeScreenshot(driver);
                throw new AssertionError("Password not entered correctly");
            }

        } else {
            System.out.println("Password field not accessible");
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Password field not accessible");
        }

        // Step 4: Click login button
        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));

        if (loginBtn.isDisplayed() && loginBtn.isEnabled()) {
            loginBtn.click();
            System.out.println("Login button clicked");
            Screenshot.takeScreenshot(driver);
        } else {
            System.out.println("Login button not clickable");
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Login button not clickable");
        }

        // Step 5: Validate login success
        try {
            boolean isLoggedIn = wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.tagName("body"), "Logout")
            );

            if (isLoggedIn) {
                System.out.println("Login successful");
                Screenshot.takeScreenshot(driver);
            } else {
                System.out.println("Login failed");
                Screenshot.takeScreenshot(driver);
                throw new AssertionError("Login failed");
            }

        } catch (Exception e) {
            System.out.println("Login validation failed");
            Screenshot.takeScreenshot(driver);
            throw new AssertionError("Login validation failed");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();

        try {
            verify_Login(driver);
        } finally {
            driver.quit();
        }
    }
}