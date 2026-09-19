package RMA;
import org.openqa.selenium.WindowType;

import java.time.Duration;

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
           
            return null;
        } else {
       
            return "Title mismatch. Found: " + actualTitle;
        }
    }

    public String enterEmailField() {
        WebElement emailField = driver.findElement(ShopLocators.EMAIL_FIELD);
        if (emailField.isDisplayed() && emailField.isEnabled()) {
            emailField.sendKeys(TestData.getAdminEmail());
            
            if (emailField.getAttribute("value").equals(TestData.getAdminEmail())) {
                System.out.println("Email entered correctly");
                
                return null;
            } else {
              
                return "Email entry failed";
            }
        } else {
            
            return "Email field not accessible";
        }
    }
    public String enterApproveField() {
        WebElement emailField = driver.findElement(ShopLocators.EMAIL_FIELD);
        if (emailField.isDisplayed() && emailField.isEnabled()) {
            emailField.sendKeys(TestData.getApproveEmail());
            
            if (emailField.getAttribute("value").equals(TestData.getApproveEmail())) {
                System.out.println("Email entered correctly");
              
                return null;
            } else {
              
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
            boolean isLoggedIn = Utilities.waitForTextPresent(driver, ShopLocators.BODY_TAG, "Logout", 60);

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


    public String openNewTabAndLogin() throws InterruptedException {
    
            try {
                System.out.println("Opening a brand new browser tab via JavaScript execution...");
                
                // 1. Get the current original window handle ID so we can track tabs
                String originalTab = driver.getWindowHandle();
                
                // 2. Run a JavaScript command to open a blank window tab context
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
                Thread.sleep(1500); // Allow window layer initialization buffer
                
                // 3. Switch context loop to find the new window handle identity target
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!originalTab.equals(windowHandle)) {
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }
                System.out.println("Focused switched to the new tab container.");
                
                // 4. Proceed with standard login sequence execution actions
                navigateToLandingPage();
                
                // 🔄 Synchronize on document readiness state inside the newly focused window context
                WebDriverWait tabWait = new WebDriverWait(driver, Duration.ofSeconds(30));
                tabWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                    .executeScript("return document.readyState").equals("complete"));
                
                String titleError = verifyPageTitle();
                if (titleError != null) {
                  
                    return "New Tab Login Error: " + titleError;
                }
                
                String emailError = enterApproveField();
                if (emailError != null) {
                   
                    return "New Tab Login Error: " + emailError;
                }
                
                String passError = enterPasswordField();
                if (passError != null) {
                  
                    return "New Tab Login Error: " + passError;
                }
                
                String clickError = clickLoginButton();
                if (clickError != null) {
                    Screenshot.takeScreenshot(driver);
                    return "New Tab Login Error: " + clickError;
                }
                
                String validationError = verifyLoginSuccessState();
                if (validationError != null) {
                    Screenshot.takeScreenshot(driver);
                    return "New Tab Login Error: " + validationError;
                }
                
                System.out.println("Successfully logged into the secondary tab state.");
                Screenshot.takeScreenshot(driver); // 📸 Capture final successful authentication layout state
                return null;
                
            } catch (Exception e) {
                System.out.println("Exception intercepted inside new tab login pipeline sequence: " + e.getMessage());
                Screenshot.takeScreenshot(driver);
                return "Failed to complete the new tab login sequence flow: " + e.getMessage();
            }
        }
}