package BDD_Test;
import org.openqa.selenium.By;

public class ShopLocators {
    public static By emailField() { return By.id("email"); }
    public static By passwordField() { return By.id("password"); }
    public static By loginBtn() { return By.xpath("//button[@type='submit']"); }
    public static By bodyTag() { return By.tagName("body"); }
    
public static By shopLink() { return By.cssSelector("a[href='/shop']"); }
    
    public static By shopHeader() { return By.xpath("//*[@id='root']/div/h2"); }
    
    public static By productAddBtn(int index) { 
        return By.xpath("//*[@id='root']/div/div[3]/div[" + index + "]/div[2]/button"); 
    }
    
    public static By cartIcon() { return By.xpath("//*[@id='root']/nav/div[1]/div[3]/a/span"); }
    
    public static By cartTotalText() { return By.xpath("//*[@id='root']/div/div/div[25]/div/div/div[1]/span[2]"); }
    

    public static By productPrice(int index) { 
        return By.xpath("//*[@id='root']/div/div/div[" + index + "]/p"); 
    }

    
    public static By cartUiTotal() { 
        return By.xpath("//*[@id='root']/div/div/div[25]/div/div/div[2]/span[2]"); 
    }
}