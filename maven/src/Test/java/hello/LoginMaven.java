package hello;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class LoginMaven {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void takeScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File("screenshots/" + name + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void verify_Login() {

        driver.get("https://eshopmuhali.netlify.app/");
        takeScreenshot("page");

        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);

        Assert.assertEquals(actualTitle, "MUHALI E SHOP");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("admin@example.com");
        takeScreenshot("email");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("Pass@123");
        takeScreenshot("password");

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();
        takeScreenshot("login");

        boolean loggedIn = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"), "Logout"
                )
        );

        Assert.assertTrue(loggedIn);
        takeScreenshot("success");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}