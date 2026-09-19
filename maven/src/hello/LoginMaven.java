package hello;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Login {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verify_Login() {

        driver.get("https://eshopmuhali.netlify.app/");
        Screenshot.takeScreenshot(driver);

        String actualTitle = driver.getTitle();
        System.out.println("Page title is: " + actualTitle);

        Assert.assertEquals(actualTitle, "MUHALI E SHOP", "Title mismatch");
        Screenshot.takeScreenshot(driver);

        WebElement emailField = driver.findElement(By.id("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email field not visible");

        emailField.sendKeys("admin@example.com");
        Assert.assertEquals(emailField.getAttribute("value"), "admin@example.com");
        Screenshot.takeScreenshot(driver);

        WebElement passwordField = driver.findElement(By.id("password"));
        Assert.assertTrue(passwordField.isDisplayed(), "Password field not visible");

        passwordField.sendKeys("Pass@123");
        Assert.assertEquals(passwordField.getAttribute("value"), "Pass@123");
        Screenshot.takeScreenshot(driver);

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        Assert.assertTrue(loginBtn.isEnabled(), "Login button not clickable");

        loginBtn.click();
        Screenshot.takeScreenshot(driver);

        boolean isLoggedIn = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"), "Logout"
                )
        );

        Assert.assertTrue(isLoggedIn, "Login failed");
        Screenshot.takeScreenshot(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}