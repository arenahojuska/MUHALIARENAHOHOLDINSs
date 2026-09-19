package muhaliholdings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import java.time.Duration;

public class Test_Cases {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void loginTest() throws Exception {
        Login login = new Login(driver, wait);
        login.verify_Login();
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void cartTest() throws Exception {
        Cartt cart = new Cartt(driver, wait);
        cart.verify_Cart_Functionality();
    }

    @Test(priority = 3, dependsOnMethods = "cartTest")
    public void priceValidationTest() throws Exception {
        PurchasePrice priceCalc = new PurchasePrice(driver, wait);
        priceCalc.verify_Cart_Total_Calculation();
    }

    @AfterTest
    public void tearDownAndGenerateReport(ITestContext context) {
        // 1. Always shut down the browser window safely first
        if (driver != null) {
            driver.quit();
            System.out.println(">>> Driver closed cleanly.");
        }

        // 2. Extract metrics from the TestNG context and pass them to your Word Writer
        System.err.println(">>> UI SUITE TEARDOWN FIRED - EXTRACTING METRICS");
        try {
            int passed = context.getPassedTests().size();
            int failed = context.getFailedTests().size();
            int skipped = context.getSkippedTests().size();

            System.err.println(">>> Passed=" + passed + " Failed=" + failed + " Skipped=" + skipped);
            
            // This calls your Word generator and passes the metrics to build the document & charts
            WordReportGenerator.generateStatusReport(passed, failed, skipped);

        } catch (Throwable t) {
            System.err.println(">>> CRASH GENERATING WORD REPORT: " + t.getClass().getName());
            System.err.println(">>> MESSAGE: " + t.getMessage());
            t.printStackTrace();
        }
    }
}