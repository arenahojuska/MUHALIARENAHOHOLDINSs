package muhaliholdings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;

public class Headless_Runner {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); 
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void loginPhase() throws Exception {
        System.out.println("Running Login (Headless)");
        Login login = new Login(driver, wait);
        login.verify_Login();
    }

    @Test(priority = 2, dependsOnMethods = "loginPhase")
    public void cartPhase() throws Exception {
        System.out.println("Running Cart (Headless)");
        Cartt cart = new Cartt(driver, wait);
        cart.verify_Cart_Functionality();
    }

    @Test(priority = 3, dependsOnMethods = "cartPhase")
    public void pricePhase() throws Exception {
        System.out.println("Running Price Validation (Headless)");
        PurchasePrice priceCalc = new PurchasePrice(driver, wait);
        priceCalc.verify_Cart_Total_Calculation();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Execution finished.");
        }
    }
}