package muhaliholdings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        
        // 1. Trigger base landing navigation
        login.navigateToLandingPage();

        // 2. Step 1: Title matching verification
        String titleError = login.verifyPageTitle();
        if (titleError != null) Assert.fail(titleError);

        // 3. Step 2: Email text inputs validation
        String emailError = login.enterEmailField();
        if (emailError != null) Assert.fail(emailError);

        // 4. Step 3: Password text inputs validation
        String passwordError = login.enterPasswordField();
        if (passwordError != null) Assert.fail(passwordError);

        // 5. Step 4: Submission form button click confirmation
        String clickError = login.clickLoginButton();
        if (clickError != null) Assert.fail(clickError);

        // 6. Step 5: Post-login navigation session confirmation state
        String sessionError = login.verifyLoginSuccessState();
        if (sessionError != null) Assert.fail(sessionError);
    }

    @Test(priority = 2, dependsOnMethods = "loginPhase")
    public void cartPhase() throws Exception {
        System.out.println("Running Cart (Headless)");
        Cartt cart = new Cartt(driver, wait);

        // 1. Click shop interaction verification
        String shopClickError = cart.clickShopButton();
        if (shopClickError != null) Assert.fail(shopClickError);

        // 2. Display verification for the landing headers
        if (!cart.verifyShopHeaderVisible()) {
            Assert.fail("Shop page failed to load");
        }

        // 3. Sequential loops selecting targeted catalog items
        int totalToClick = Integer.parseInt(TestData.getExpectedCartCount());
        String loopError = cart.addProductsToCart(totalToClick);
        if (loopError != null) Assert.fail(loopError);

        // 4. Return scroll positioning and tap checkout links
        String cartClickError = cart.clickCartButton();
        if (cartClickError != null) Assert.fail(cartClickError);

        // 5. Evaluate totals count text elements
        String actualCount = cart.getCartItemCountText();
        String expectedCount = TestData.getExpectedCartCount();

        if (actualCount.equals(expectedCount)) {
            System.out.println("Cart validation passed: Found " + expectedCount + " items.");
            Screenshot.takeScreenshot(driver);
        } else {
            Screenshot.takeScreenshot(driver);
            Assert.fail("Expected " + expectedCount + " products but found " + actualCount);
        }
    }

    @Test(priority = 3, dependsOnMethods = "cartPhase")
    public void pricePhase() throws Exception {
        System.out.println("Running Price Validation (Headless)");
        PurchasePrice priceCalc = new PurchasePrice(driver, wait);

        // 1. Accumulate product item pricing sums (Indices 2 through 24)
        double calculatedTotal = priceCalc.calculateCumulativeCartTotal(2, 24);

        // 2. Extract layout price summary from screen UI
        double uiTotal = priceCalc.getUiTotalAmount();

        // 3. Run validation logic comparisons and screenshots
        String comparisonError = priceCalc.verifyTotalsMatch(calculatedTotal, uiTotal);
        if (comparisonError != null) {
            Assert.fail(comparisonError);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Execution finished.");
        }
    }
}