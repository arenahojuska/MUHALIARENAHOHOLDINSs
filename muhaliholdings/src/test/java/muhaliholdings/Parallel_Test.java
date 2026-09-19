package muhaliholdings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;

public class Parallel_Test {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void runParallel() throws Exception {
        Login login = new Login(driver, wait);
        login.verify_Login();
        
        Cartt cart = new Cartt(driver, wait);
        cart.verify_Cart_Functionality();

        PurchasePrice priceCalc = new PurchasePrice(driver, wait);
        priceCalc.verify_Cart_Total_Calculation();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}