package muhaliholdings;

import org.openqa.selenium.By;

public class ShopLocators {
    // Login Elements
    public static final By EMAIL_FIELD = By.id("email");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BTN = By.xpath("//button[@type='submit']");
    public static final By BODY_TAG = By.tagName("body");
    
    // Shop Navigation & Headers
    public static final By SHOP_LINK = By.cssSelector("a[href='/shop']");
    public static final By SHOP_HEADER = By.xpath("//*[@id='root']/div/h2");
    public static final By CART_ICON = By.xpath("//*[@id='root']/nav/div[1]/div[3]/a/span");
    
    // Cart & Totals
    public static final By CART_TOTAL_TEXT = By.xpath("//*[@id='root']/div/div/div[25]/div/div/div[1]/span[2]");
    public static final By CART_UI_TOTAL = By.xpath("//*[@id='root']/div/div/div[25]/div/div/div[2]/span[2]");

    // Dynamic Locator Builders (Kept pure, no logic)
    public static By getProductAddBtn(int index) { 
        return By.xpath("//*[@id='root']/div/div[3]/div[" + index + "]/div[2]/button"); 
    }
    
    public static By getProductPrice(int index) { 
        return By.xpath("//*[@id='root']/div/div/div[" + index + "]/p"); 
    }
}