package muhaliholdings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        System.out.println("Executing Login Test Case Phase...");
        Login login = new Login(driver, wait);
        
        // 1. Initial Page Navigation
        login.navigateToLandingPage();

        // 2. Step 1: Title Validation
        String titleError = login.verifyPageTitle();
        if (titleError != null) Assert.fail(titleError);

        // 3. Step 2: Email Processing Validation
        String emailError = login.enterEmailField();
        if (emailError != null) Assert.fail(emailError);

        // 4. Step 3: Password Processing Validation
        String passwordError = login.enterPasswordField();
        if (passwordError != null) Assert.fail(passwordError);

        // 5. Step 4: Login Submission Verification
        String clickError = login.clickLoginButton();
        if (clickError != null) Assert.fail(clickError);

        // 6. Step 5: Landing/Session Verification State
        String sessionError = login.verifyLoginSuccessState();
        if (sessionError != null) Assert.fail(sessionError);
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void cartTest() throws Exception {
        System.out.println("Executing Cart Interaction Test Case Phase...");
        Cartt cart = new Cartt(driver, wait);


        String shopClickError = cart.clickShopButton();
        if (shopClickError != null) Assert.fail(shopClickError);

        // 2. Main Page Header Verification
        if (!cart.verifyShopHeaderVisible()) {
            Assert.fail("Shop page failed to load");
        }

        // 3. Automated Sequential Product Loop Processing
        int totalItems = Integer.parseInt(TestData.getExpectedCartCount());
        String loopError = cart.addProductsToCart(totalItems);
        if (loopError != null) Assert.fail(loopError);

        // 4. Back-To-Top Scrolling and Checkout Basket Transition Link Click
        String cartClickError = cart.clickCartButton();
        if (cartClickError != null) Assert.fail(cartClickError);

        // 5. End Quantity Text Match Verification
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

    @Test(priority = 3, dependsOnMethods = "cartTest")
    public void priceValidationTest() throws Exception {
        System.out.println("Executing Mathematical Pricing Total Verification Phase...");
        PurchasePrice priceCalc = new PurchasePrice(driver, wait);

        // 1. Extract aggregated items pricing data across loop limits (Indices 2 through 24)
        double calculatedTotal = priceCalc.calculateCumulativeCartTotal(2, 24);

        // 2. Grabs summary calculation text currently displayed on UI layout
        double uiTotal = priceCalc.getUiTotalAmount();

        // 3. Process direct visual summary comparisons and error checking operations
        String comparisonError = priceCalc.verifyTotalsMatch(calculatedTotal, uiTotal);
        if (comparisonError != null) {
            Assert.fail(comparisonError);
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser context cleared out cleanly.");
        }
    }
}