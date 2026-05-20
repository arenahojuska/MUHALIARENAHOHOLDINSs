package hello;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class B4 {
	 private static int clickCount = 0;
	public static void softwareengineering(WebDriver driver) throws IOException, InterruptedException {

		try {
		    driver.get("https://www.pnet.co.za/candidate/login");
		    driver.manage().window().maximize();
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // Accept cookies
		    WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("ccmgt_explicit_accept")));
		    acceptCookiesButton.click();

		    // Wait until the email input field is visible and then send keys
		    WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		    emailInput.sendKeys("arenahojuska@gmail.com");

		    // Wait until the password input field is visible and then send keys
		    WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		    passwordInput.sendKeys("arenaho5god@@");

		    // Wait until the login button is clickable and then click it
		    WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/main/div/div/div/div[5]/form/div/button")));
		    loginButton.click();

		    Thread.sleep(3000);

		   // WebElement acceptCookiesButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("ccmgt_explicit_accept")));
		    //acceptCookiesButton1.click();

		    // Wait until the element after login is visible and then click it
		    WebElement headerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app-header\"]/div/div/div[1]/a")));
		    headerLink.click();

		    Thread.sleep(4000);
		} catch (Exception e) {
		    System.out.println("An error occurred: " + e.getMessage());
		}
	        
	        
	        
	        
	          driver.findElement(By.xpath("//*[starts-with(@id,'genesis-autocomplete')]")).sendKeys("Cloud");
	        
	        
	        
	        
	
	        //*[@id="app-searchBar"]/div/div/div/div/div/div[2]/div/div[1]/button/span/span
	        
	        
	          Thread.sleep(4000);
	          Actions actions = new Actions(driver);
	          actions.sendKeys(Keys.ENTER).perform();
	      //  driver.findElement(By.xpath("//a[contains(text(),'app-searchBar')]")).click();
	        driver.findElement(By.xpath("//a[contains(text(),'Easy Apply')]")).click();

	        Thread.sleep(5000);
	        driver.findElement(By.xpath("//span[contains(text(),'Relevance')]")).click();
	        driver.findElement(By.xpath("//span[contains(text(),'Date')]")).click();
	        Thread.sleep(3000);
	        
	        // Find all "Connect" buttons
	        List<WebElement> connectButtons = driver.findElements(By.xpath("//*[contains(@id, 'job-item-')]"));
	        //List<WebElement> connectButtons = driver.findElements(By.xpath("//*[contains(@id, ')]"));
	        for (WebElement button : connectButtons) {
	            button.click();
	            

	            Thread.sleep(3000);
	            String originalTab = driver.getWindowHandle();
	            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	            Thread.sleep(5000);
	            
	            try {
	                driver.switchTo().window(tabs.get(tabs.size() - 1));
	                

	                driver.findElement(By.xpath("//*[@id='JobAdContent']//button")).click();

	                
	               // driver.findElement(By.xpath("//a[contains(text(),'[@id=\\\"JobAdContent\\')]")).click();
	             //  driver.findElement(By.xpath("//*[@id=\"JobAdContent\"]/div/div/div/div/div/div/div/div[1]/article/div/div[2]/div/div[1]/button")).click();
	                //driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div/article/div/div[2]/div[1]/div/ul/li[1]/button")).click();
	                
	                //driver.switchTo().window(tabs.get(tabs.size() - 1));
	               Thread.sleep(5000);
	               
	               
	                
	                

	                ((EdgeDriver) driver).executeScript("window.scrollBy(0, 500);");
	                
	                Thread.sleep(4000);
	                driver.findElement(By.xpath("//*[@id='app']/div/div/div/div[1]/article/div/form/div[3]/div/div/div[2]/button")).click();
	                driver.close();
	                driver.findElement(By.xpath("//*[@id='app']//button")).click();
	                driver.findElement(By.xpath("(//*[@id='app']//button)[5]")).click();
	                driver.findElement(By.xpath("//button[.//span[contains(text(),'Send Application')]]")).click();

	                driver.findElement(By.xpath("//button[contains(text(),'Send Application')]")).click();
	                driver.findElement(By.xpath("//button[.//span[contains(text(),'Submit')]]")).click();

	                driver.close();
	                clickCount++;
	                Thread.sleep(4000);
	                
	                for (String tab : tabs) {
	                    if (!tab.equals(originalTab)) {
	                        driver.switchTo().window(tab);
	                        driver.close();
	                    }
	                }
	                
	            } catch (Exception e) {
	                
	                driver.switchTo().window(tabs.get(0)); 
	            }
	            
	            try {
	                Thread.sleep(5000); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        
	        System.out.println("Number of button clicks: " + clickCount);
	    }
   

	public static void main(String[] args) throws IOException, InterruptedException {
	    for (int i = 0; i < 3; i++) {
	        WebDriver driver = null;
	        try {
	            System.out.println("Run #" + (i + 1));
	            driver = new EdgeDriver();
	            driver.manage().window().maximize();
	            B4.softwareengineering(driver);
	            
	        } catch (Exception e) {
	            System.err.println("An error occurred during run #" + (i + 1));
	            e.printStackTrace();
	        } finally {
	            if (driver != null) {
	                driver.quit();
	                System.out.println("Driver closed for run #" + (i + 1));
	            }
	        }
	    }
	}}