package muhaliholdings;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class Utilities {

    public static WebDriver createDriverInstance(boolean headless) {
        WebDriver driver;
        if (headless) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            driver = new ChromeDriver(options);
        } else {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static WebElement waitForVisibility(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForTextPresent(WebDriver driver, By locator, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public static void jsClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static String getExcelData(String filePath, String sheetName, int rowIndex, int colIndex) {
        String value = "";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            
            if (sheet != null) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    Cell cell = row.getCell(colIndex);
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            value = String.valueOf((int) cell.getNumericCellValue());
                        } else {
                            value = cell.getStringCellValue();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Utility Error: Unable to read excel file at " + filePath);
        }
        return value;
    }
}