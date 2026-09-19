package RMA;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class Login {

    private final Page page;

    public Login(Page page) {
        this.page = page;
    }

    public void navigateToLandingPage() {
        page.navigate(TestData.getBaseUrl());
        Screenshot.takeScreenshot(page);
    }

    public String verifyPageTitle() {
        String actualTitle = page.title();
        System.out.println("Page title is: " + actualTitle);

        if (actualTitle.equals(TestData.getExpectedTitle())) {
            System.out.println("Title is correct");
            return null;
        } else {
            return "Title mismatch. Found: " + actualTitle;
        }
    }

    public String enterEmailField() {
        return enterEmailField(this.page, TestData.getAdminEmail());
    }

    public String enterApproveField() {
        return enterEmailField(this.page, TestData.getApproveEmail());
    }

    private String enterEmailField(Page targetPage, String email) {
        Locator emailField = targetPage.locator(ShopLocators.EMAIL_FIELD);
        if (emailField.isVisible() && emailField.isEnabled()) {
            emailField.fill(email);

            if (emailField.inputValue().equals(email)) {
                System.out.println("Email entered correctly");
                return null;
            } else {
                return "Email entry failed";
            }
        } else {
            Screenshot.takeScreenshot(targetPage);
            return "Email field not accessible";
        }
    }

    public String enterPasswordField() {
        return enterPasswordField(this.page);
    }

    private String enterPasswordField(Page targetPage) {
        Locator passwordField = targetPage.locator(ShopLocators.PASSWORD_FIELD);
        if (passwordField.isVisible() && passwordField.isEnabled()) {
            passwordField.fill(TestData.getAdminPassword());

            if (passwordField.inputValue().equals(TestData.getAdminPassword())) {
                System.out.println("Password entered correctly");
                Screenshot.takeScreenshot(targetPage);
                return null;
            } else {
                Screenshot.takeScreenshot(targetPage);
                return "Password entry failed";
            }
        } else {
            Screenshot.takeScreenshot(targetPage);
            return "Password field not accessible";
        }
    }

    public String clickLoginButton() {
        return clickLoginButton(this.page);
    }

    private String clickLoginButton(Page targetPage) {
        Locator loginBtn = targetPage.locator(ShopLocators.LOGIN_BTN);
        if (loginBtn.isVisible() && loginBtn.isEnabled()) {
            loginBtn.click();
            System.out.println("Login button clicked");
            Screenshot.takeScreenshot(targetPage);
            return null;
        } else {
            Screenshot.takeScreenshot(targetPage);
            return "Login button not clickable";
        }
    }

    public String verifyLoginSuccessState() {
        return verifyLoginSuccessState(this.page);
    }

    private String verifyLoginSuccessState(Page targetPage) {
        try {
            boolean isLoggedIn = Utilities.waitForTextPresent(targetPage, ShopLocators.BODY_TAG, "Logout", 30);

            if (isLoggedIn) {
                System.out.println("Login successful");
                Screenshot.takeScreenshot(targetPage);
                return null;
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(targetPage);
            return "Login validation failed or Logout button not found.";
        }
        Screenshot.takeScreenshot(targetPage);
        return "Login validation failed or Logout button not found.";
    }

    public String openNewTabAndLogin() {
        try {
            System.out.println("Opening a brand new browser tab...");

            // 1. Native Playwright tab creation within the current browser context
            Page newPage = page.context().newPage();
            System.out.println("Focused switched to the new tab container.");

            // 2. Navigate and wait for document load state
            newPage.navigate(TestData.getBaseUrl());
            Screenshot.takeScreenshot(newPage);
            newPage.waitForLoadState(LoadState.DOMCONTENTLOADED);

            String actualTitle = newPage.title();
            System.out.println("Page title is: " + actualTitle);
            if (!actualTitle.equals(TestData.getExpectedTitle())) {
                return "New Tab Login Error: Title mismatch. Found: " + actualTitle;
            }

            String emailError = enterEmailField(newPage, TestData.getApproveEmail());
            if (emailError != null) {
                return "New Tab Login Error: " + emailError;
            }

            String passError = enterPasswordField(newPage);
            if (passError != null) {
                return "New Tab Login Error: " + passError;
            }

            String clickError = clickLoginButton(newPage);
            if (clickError != null) {
                Screenshot.takeScreenshot(newPage);
                return "New Tab Login Error: " + clickError;
            }

            String validationError = verifyLoginSuccessState(newPage);
            if (validationError != null) {
                Screenshot.takeScreenshot(newPage);
                return "New Tab Login Error: " + validationError;
            }

            System.out.println("Successfully logged into the secondary tab state.");
            Screenshot.takeScreenshot(newPage);
            return null;

        } catch (Exception e) {
            System.out.println("Exception intercepted inside new tab login pipeline sequence: " + e.getMessage());
            Screenshot.takeScreenshot(page);
            return "Failed to complete the new tab login sequence flow: " + e.getMessage();
        }
    }
}