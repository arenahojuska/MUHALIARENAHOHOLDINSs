package RMA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class cartt {

    public static List<WebElement> locateNonTextTypeElements(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);

        return driver.findElements(
            By.cssSelector("form input:not([type='text'])")
        );
    }

    public static List<WebElement> locateContactElements(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);

        return driver.findElements(
            By.cssSelector("form input[name^='contact']")
        );
    }

    public static WebElement findSubmitElement(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);

        return driver.findElement(
            By.cssSelector("form button[type='submit']")
            
        );
    }

    public static List<WebElement> locateIdMissingElements(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);

        return driver.findElements(
            By.cssSelector("form input:not([id])")
        );
    }
}