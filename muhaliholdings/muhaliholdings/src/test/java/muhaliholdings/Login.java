package muhaliholdings;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    private final WebDriver driver;

    public Login(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
    }

    public void navigateToLandingPage() {
        driver.get(TestData.getBaseUrl());
        Screenshot.takeScreenshot(driver);
    }

    public String verifyPageTitle() {
        String actualTitle = driver.getTitle();
        System.out.println("Page title is: " + actualTitle);

        if (actualTitle.equals(TestData.getExpectedTitle())) {
            System.out.println("Title is correct");
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
            return "Title mismatch. Found: " + actualTitle;
        }
    }

    public String enterEmailField() {
        WebElement emailField = driver.findElement(ShopLocators.EMAIL_FIELD);
        if (emailField.isDisplayed() && emailField.isEnabled()) {
            emailField.sendKeys(TestData.getAdminEmail());
            
            if (emailField.getAttribute("value").equals(TestData.getAdminEmail())) {
                System.out.println("Email entered correctly");
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                Screenshot.takeScreenshot(driver);
                return "Email entry failed";
            }
        } else {
            Screenshot.takeScreenshot(driver);
            return "Email field not accessible";
        }
    }

    public String enterPasswordField() {
        WebElement passwordField = driver.findElement(ShopLocators.PASSWORD_FIELD);
        if (passwordField.isDisplayed() && passwordField.isEnabled()) {
            passwordField.sendKeys(TestData.getAdminPassword());
            
            if (passwordField.getAttribute("value").equals(TestData.getAdminPassword())) {
                System.out.println("Password entered correctly");
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                Screenshot.takeScreenshot(driver);
                return "Password entry failed";
            }
        } else {
            Screenshot.takeScreenshot(driver);
            return "Password field not accessible";
        }
    }

    public String clickLoginButton() {
        WebElement loginBtn = driver.findElement(ShopLocators.LOGIN_BTN);
        if (loginBtn.isDisplayed() && loginBtn.isEnabled()) {
            loginBtn.click();
            System.out.println("Login button clicked");
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
            return "Login button not clickable";
        }
    }

    public String verifyLoginSuccessState() {
        try {
            boolean isLoggedIn = Utilities.waitForTextPresent(driver, ShopLocators.BODY_TAG, "Logout", 15);

            if (isLoggedIn) {
                System.out.println("Login successful");
                Screenshot.takeScreenshot(driver);
                return null;
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Login validation failed or Logout button not found.";
        }
        Screenshot.takeScreenshot(driver);
        return "Login validation failed or Logout button not found.";
    }
}