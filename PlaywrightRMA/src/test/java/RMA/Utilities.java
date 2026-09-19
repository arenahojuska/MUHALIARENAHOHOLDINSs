package RMA;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.poi.ss.usermodel.*;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;

import java.io.FileInputStream;
import java.io.IOException;

public class Utilities {

    // ============================================================
    // CREATE PLAYWRIGHT / PAGE INSTANCE
    // ============================================================

    public static Page createPageInstance(Playwright playwright, boolean headless) {

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        Browser browser = playwright.chromium().launch(options);

        BrowserContext context;
        if (headless) {
            context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080));
        } else {
            // Emulate maximized window in headed mode
            context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(null));
        }

        Page page = context.newPage();

        // Default timeout replacement for Selenium implicit wait
        page.setDefaultTimeout(10000);

        return page;
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

    public static Locator waitForVisibility(
            Page page,
            String selector,
            int timeoutSeconds) {

        Locator locator = page.locator(selector);
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeoutSeconds * 1000.0));

        return locator;
    }


    // ============================================================
    // WAIT FOR PRESENCE
    // ============================================================

    public static Locator waitForPresence(
            Page page,
            String selector,
            int timeoutSeconds) {

        Locator locator = page.locator(selector);
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(timeoutSeconds * 1000.0));

        return locator;
    }


    // ============================================================
    // WAIT FOR CLICKABLE
    // ============================================================

    public static Locator waitForClickable(
            Page page,
            String selector,
            int timeoutSeconds) {

        Locator locator = page.locator(selector);
        // Playwright automatically checks visibility, stability, and clickability before actions.
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeoutSeconds * 1000.0));

        return locator;
    }


    // ============================================================
    // WAIT FOR TEXT
    // ============================================================

    public static boolean waitForTextPresent(
            Page page,
            String selector,
            String text,
            int timeoutSeconds) {

        try {
            Locator locator = page.locator(selector + ":has-text('" + text + "')");
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(timeoutSeconds * 1000.0));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    // ============================================================
    // SCROLL TO ELEMENT
    // ============================================================

    public static void scrollToElement(
            Page page,
            Locator element) {

        element.scrollIntoViewIfNeeded();
    }


    // ============================================================
    // SCROLL TO TOP
    // ============================================================

    public static void scrollToTop(Page page) {

        page.evaluate("window.scrollTo(0, 0);");
    }


    // ============================================================
    // JAVASCRIPT CLICK
    // ============================================================

    public static void jsClick(
            Page page,
            Locator element) {

        element.dispatchEvent("click");
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