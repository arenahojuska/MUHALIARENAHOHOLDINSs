package muhaliholdings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        System.out.println("Starting Parallelized Test Execution Method Thread...");
        
        // Initialize our newly refactored POM layers
        Login login = new Login(driver, wait);
        Cartt cart = new Cartt(driver, wait);
        PurchasePrice priceCalc = new PurchasePrice(driver, wait);

        // ==========================================
        // PHASE 1: LOGIN INTEGRITY FLOW
        // ==========================================
        login.navigateToLandingPage();

        String titleError = login.verifyPageTitle();
        if (titleError != null) Assert.fail(titleError);

        String emailError = login.enterEmailField();
        if (emailError != null) Assert.fail(emailError);

        String passwordError = login.enterPasswordField();
        if (passwordError != null) Assert.fail(passwordError);

        String clickError = login.clickLoginButton();
        if (clickError != null) Assert.fail(clickError);

        String sessionError = login.verifyLoginSuccessState();
        if (sessionError != null) Assert.fail(sessionError);

        // ==========================================
        // PHASE 2: BASKET SELECTION INTERACTION FLOW
        // ==========================================
        String shopClickError = cart.clickShopButton();
        if (shopClickError != null) Assert.fail(shopClickError);

        if (!cart.verifyShopHeaderVisible()) {
            Assert.fail("Shop page failed to load");
        }

        int totalItems = Integer.parseInt(TestData.getExpectedCartCount());
        String loopError = cart.addProductsToCart(totalItems);
        if (loopError != null) Assert.fail(loopError);

        String cartClickError = cart.clickCartButton();
        if (cartClickError != null) Assert.fail(cartClickError);

        String actualCount = cart.getCartItemCountText();
        String expectedCount = TestData.getExpectedCartCount();

        if (actualCount.equals(expectedCount)) {
            System.out.println("Cart validation passed: Found " + expectedCount + " items.");
            Screenshot.takeScreenshot(driver);
        } else {
            Screenshot.takeScreenshot(driver);
            Assert.fail("Expected " + expectedCount + " products but found " + actualCount);
        }

        // ==========================================
        // PHASE 3: MATHEMATICAL PRICING TOTALS FLOW
        // ==========================================
        double calculatedTotal = priceCalc.calculateCumulativeCartTotal(2, 24);
        double uiTotal = priceCalc.getUiTotalAmount();

        String comparisonError = priceCalc.verifyTotalsMatch(calculatedTotal, uiTotal);
        if (comparisonError != null) {
            Assert.fail(comparisonError);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Isolated test method instance thread terminated successfully.");
        }
    }
}