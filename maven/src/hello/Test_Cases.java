package hello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_Cases {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();

        try {
            Login.verify_Login(driver);
            Cartt.cart(driver);
        } finally {
            driver.quit();
        }
    }
}																																