package RMA;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import org.apache.poi.ss.usermodel.*;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class Utilities {

    // ============================================================
    // CREATE WEBDRIVER
    // ============================================================

    public static WebDriver createDriverInstance(boolean headless) {

        WebDriver driver;

        if (headless) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);

        } else {

            ChromeOptions options = new ChromeOptions();

            driver = new ChromeDriver(options);

            // Maximize browser window
            driver.manage().window().maximize();
        }

        // Implicit wait
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }


    // ============================================================
    // WINDOW FOCUS HELPER
    // ============================================================

    /**
     * Finds the Windows application window whose title contains
     * the supplied text and brings it to the foreground.
     *
     * Example:
     *
     * Utilities.WindowFocusHelper.focusWindowByTitle("Chrome");
     */
    public static class WindowFocusHelper {

        public static boolean focusWindowByTitle(String titleContains) {

            final boolean[] found = {false};

            User32.INSTANCE.EnumWindows((hWnd, data) -> {

                char[] buffer = new char[512];

                User32.INSTANCE.GetWindowText(
                        hWnd,
                        buffer,
                        512
                );

                String windowTitle = Native.toString(buffer);

                if (windowTitle != null
                        && windowTitle.contains(titleContains)) {

                    // Restore window if minimized
                    User32.INSTANCE.ShowWindow(
                            hWnd,
                            WinUser.SW_RESTORE
                    );

                    // Bring window to foreground
                    User32.INSTANCE.SetForegroundWindow(hWnd);

                    found[0] = true;

                    // Stop searching
                    return false;
                }

                // Continue searching
                return true;

            }, null);

            return found[0];
        }
    }


    // ============================================================
    // WAIT FOR VISIBILITY
    // ============================================================

    public static WebElement waitForVisibility(
            WebDriver driver,
            By locator,
            int timeoutSeconds) {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(timeoutSeconds)
                );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }


    // ============================================================
    // WAIT FOR PRESENCE
    // ============================================================

    public static WebElement waitForPresence(
            WebDriver driver,
            By locator,
            int timeoutSeconds) {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(timeoutSeconds)
                );

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }


    // ============================================================
    // WAIT FOR CLICKABLE
    // ============================================================

    public static WebElement waitForClickable(
            WebDriver driver,
            By locator,
            int timeoutSeconds) {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(timeoutSeconds)
                );

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }


    // ============================================================
    // WAIT FOR TEXT
    // ============================================================

    public static boolean waitForTextPresent(
            WebDriver driver,
            By locator,
            String text,
            int timeoutSeconds) {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(timeoutSeconds)
                );

        return wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        locator,
                        text
                )
        );
    }


    // ============================================================
    // SCROLL TO ELEMENT
    // ============================================================

    public static void scrollToElement(
            WebDriver driver,
            WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                element
        );
    }


    // ============================================================
    // SCROLL TO TOP
    // ============================================================

    public static void scrollToTop(WebDriver driver) {

        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, 0);"
        );
    }


    // ============================================================
    // JAVASCRIPT CLICK
    // ============================================================

    public static void jsClick(
            WebDriver driver,
            WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }


    // ============================================================
    // READ DATA FROM EXCEL
    // ============================================================

    public static String getExcelData(
            String filePath,
            String sheetName,
            int rowIndex,
            int colIndex) {

        String value = "";

        try (
                FileInputStream fis =
                        new FileInputStream(filePath);

                Workbook workbook =
                        WorkbookFactory.create(fis)
        ) {

            Sheet sheet =
                    workbook.getSheet(sheetName);

            if (sheet != null) {

                Row row =
                        sheet.getRow(rowIndex);

                if (row != null) {

                    Cell cell =
                            row.getCell(colIndex);

                    if (cell != null) {

                        if (cell.getCellType()
                                == CellType.NUMERIC) {

                            value = String.valueOf(
                                    (int) cell.getNumericCellValue()
                            );

                        } else {

                            value = cell.toString();
                        }
                    }
                }
            }

        } catch (IOException e) {

            System.err.println(
                    "Utility Error: Unable to read Excel file at "
                            + filePath
            );

            e.printStackTrace();
        }

        return value;
    }
}