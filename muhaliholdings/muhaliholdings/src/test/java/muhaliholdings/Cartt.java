package muhaliholdings;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cartt {

    private final WebDriver driver;

    public Cartt(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
    }

    public String clickShopButton() {
        WebElement shopBtn = Utilities.waitForVisibility(driver, ShopLocators.SHOP_LINK, 15);

        if (shopBtn.isDisplayed() && shopBtn.isEnabled()) {
            shopBtn.click();
            System.out.println("Shop button clicked");
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
            return "Shop button not clickable";
        }
    }

    public boolean verifyShopHeaderVisible() {
        WebElement shopHeader = Utilities.waitForVisibility(driver, ShopLocators.SHOP_HEADER, 15);

        if (shopHeader.isDisplayed()) {
            System.out.println("Shop page is visible");
            return true;
        } else {
            Screenshot.takeScreenshot(driver);
            return false;
        }
    }

    public String addProductsToCart(int totalToClick) {
        for (int i = 1; i <= totalToClick; i++) {
            WebElement btn = Utilities.waitForClickable(driver, ShopLocators.getProductAddBtn(i), 15);

            Utilities.scrollToElement(driver, btn);

            if (btn.isDisplayed() && btn.isEnabled()) {
                btn.click();
                System.out.println("Product " + i + " clicked");
            } else {
                Screenshot.takeScreenshot(driver);
                return "Product " + i + " not clickable";
            }
        }
        return null;
    }

    public String clickCartButton() {
        Utilities.scrollToTop(driver);

        WebElement cartBtn = driver.findElement(ShopLocators.CART_ICON);

        if (cartBtn.isDisplayed() && cartBtn.isEnabled()) {
            cartBtn.click();
            System.out.println("Cart button clicked");
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
            return "Cart button not clickable";
        }
    }

    public String getCartItemCountText() {
        WebElement countElement = Utilities.waitForVisibility(driver, ShopLocators.CART_TOTAL_TEXT, 15);
        return countElement.getText();
    }
}