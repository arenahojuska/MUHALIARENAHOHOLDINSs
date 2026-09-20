package BDD_Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;

public class Login {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void verify_Login(String email, String password) {

        try {

            driver.get(TestData.getBaseUrl());

            driver.findElement(ShopLocators.emailField())
                    .sendKeys(email);

            driver.findElement(ShopLocators.passwordField())
                    .sendKeys(password);

            driver.findElement(ShopLocators.loginBtn())
                    .click();

            wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.tagName("body"),
                            "Logout"
                    )
            );

            System.out.println("Login passed");

        } catch (Exception e) {

            System.out.println("Login failed");

            throw new RuntimeException("FAILED");
        }
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }
    }
}