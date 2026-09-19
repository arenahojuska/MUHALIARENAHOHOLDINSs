package RMA;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.LoadState;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClaimCareReporting {

    private final Page page;
    private static final double SLOW_PAGE_TIMEOUT = 60000; 

    public ClaimCareReporting(Page page) {
        this.page = page;
    }

    public String clickClaimCareButton() {
        try {
            System.out.println("Waiting for ClaimCare option button control layout to appear...");

            Locator claimCareBtn = page.locator(ShopLocators.ClaimCare_button).first();

            claimCareBtn.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));

            claimCareBtn.click(new Locator.ClickOptions().setTimeout(SLOW_PAGE_TIMEOUT));
            System.out.println("ClaimCare interface action button clicked successfully.");

            page.waitForLoadState(LoadState.LOAD, new Page.WaitForLoadStateOptions().setTimeout(SLOW_PAGE_TIMEOUT));
            page.waitForTimeout(2000); 
            Screenshot.takeScreenshot(page); 
            return null;

        } catch (Exception e) {
            System.out.println("Exception handled in ClaimCare selection process flow: " + e.getMessage());
            Screenshot.takeScreenshot(page); 
            return "Failed to complete ClaimCare action invocation sequence: " + e.getMessage();
        }
    }

    public String clickReportsManagerButton() {

        try {

            System.out.println("Waiting for Reports Manager option button control layout to appear...");

            Locator reportsManagerBtn = page.locator(
                    ShopLocators.Reports_Manager_button
            ).nth(1);

            reportsManagerBtn.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));

            reportsManagerBtn.scrollIntoViewIfNeeded();

            reportsManagerBtn.click(new Locator.ClickOptions()
                    .setTimeout(SLOW_PAGE_TIMEOUT)
                    .setForce(true));

            System.out.println("Reports Manager interface action button clicked successfully.");

            page.waitForLoadState(
                    LoadState.LOAD,
                    new Page.WaitForLoadStateOptions()
                            .setTimeout(SLOW_PAGE_TIMEOUT));

            page.waitForTimeout(2000);

            Screenshot.takeScreenshot(page);

            return null;

        } catch (Exception e) {

            System.out.println(
                    "Exception handled in Reports Manager selection process flow: "
                            + e.getMessage());

            Screenshot.takeScreenshot(page);

            return "Failed to complete Reports Manager action invocation sequence: "
                    + e.getMessage();
        }
    }

    public List<String> getDropdownOptions() {
        List<String> optionsText = new ArrayList<>();
        try {
            System.out.println("Waiting for dropdown trigger to be visible...");

            Locator dropdownTrigger = page.locator(ShopLocators.Dynamic_Dropdown_Trigger).first();
            dropdownTrigger.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));

            dropdownTrigger.scrollIntoViewIfNeeded();
            dropdownTrigger.click(new Locator.ClickOptions().setTimeout(SLOW_PAGE_TIMEOUT));
            System.out.println("Dropdown clicked. Waiting for Angular Material overlay options...");

            // Use the direct mat-option locator
            Locator optionsLocator = page.locator(ShopLocators.Mat_Options_List);
            
            optionsLocator.first().waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));

            int count = optionsLocator.count();
            for (int i = 0; i < count; i++) {
                String optionText = optionsLocator.nth(i).innerText().trim();
                if (!optionText.isEmpty()) {
                    optionsText.add(optionText);
                }
            }

            System.out.println("Retrieved " + optionsText.size() + " unique options: " + optionsText);
            Screenshot.takeScreenshot(page); 
            return optionsText;

        } catch (Exception e) {
            System.out.println("Failed to fetch dropdown options: " + e.getMessage());
            Screenshot.takeScreenshot(page); 
            return optionsText;
        }
    }
    
   

    public String generateActuarialReport(int daysToSubtract) {
        try {
            // 1. Select 'Actuarial Report'
            System.out.println("Step 1: Selecting 'Actuarial Report' option...");
            Locator option = page.locator(ShopLocators.Actuarial_Report_Option).first();
            option.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));
            option.click();

            // 2. Calculate exact target date
            LocalDate targetDate = LocalDate.now().minusDays(daysToSubtract);
            String targetYear = String.valueOf(targetDate.getYear());
            String targetMonth = targetDate.format(DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH)).toUpperCase(); // e.g., "AUG"
            int targetDay = targetDate.getDayOfMonth();

            System.out.println("Target Date calculated: Year=" + targetYear + ", Month=" + targetMonth + ", Day=" + targetDay);

            // 3. Open Calendar Widget
            System.out.println("Step 2: Opening datepicker popup...");
            Locator calendarBtn = page.locator(ShopLocators.Calendar_Toggle).first();
            calendarBtn.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));
            calendarBtn.click();

            // 4. Inspect current visible period button text (e.g., "SEP 2026")
            Locator periodBtn = page.locator(ShopLocators.Calendar_Period_Button).first();
            periodBtn.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));
            
            String currentPeriodText = periodBtn.innerText().toUpperCase();
            System.out.println("Calendar currently displaying: " + currentPeriodText);

            // If target month/year isn't visible in the header, navigate via the Period Selector
            if (!currentPeriodText.contains(targetMonth) || !currentPeriodText.contains(targetYear)) {
                System.out.println("Target month/year (" + targetMonth + " " + targetYear + ") differs from current view. Navigating...");

                // Click period button to switch to multi-year/month grid view
                periodBtn.click();

                // Select Year
                Locator yearTile = page.locator("//mat-calendar//div[contains(@class, 'mat-calendar-body-cell-content') and normalize-space(text())='" + targetYear + "']").first();
                yearTile.waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(SLOW_PAGE_TIMEOUT));
                yearTile.click();

                // Select Month
                Locator monthTile = page.locator("//mat-calendar//div[contains(@class, 'mat-calendar-body-cell-content') and normalize-space(text())='" + targetMonth + "']").first();
                monthTile.waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(SLOW_PAGE_TIMEOUT));
                monthTile.click();
            }

            // 5. Select Target Day Tile (e.g. 28)
            System.out.println("Step 3: Selecting day tile: " + targetDay);
            Locator dayTile = page.locator("//mat-calendar//div[contains(@class, 'mat-calendar-body-cell-content') and normalize-space(text())='" + targetDay + "'] | " +
                                           "//mat-calendar//button[normalize-space(text())='" + targetDay + "']").first();
            dayTile.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));
            dayTile.click();

            // 6. Dismiss calendar backdrop overlay
            page.keyboard().press("Escape");
            page.waitForTimeout(500);

            // 7. Click 'View' Button
            System.out.println("Step 4: Clicking 'View' button...");
            Locator viewBtn = page.locator(ShopLocators.View_Button).first();
            viewBtn.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(SLOW_PAGE_TIMEOUT));

            viewBtn.click();
            System.out.println("'View' button clicked successfully.");

            page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(SLOW_PAGE_TIMEOUT));
            page.waitForTimeout(2000);

            Screenshot.takeScreenshot(page);
            return null;

        } catch (Exception e) {
            System.out.println("Failed during Actuarial Report generation workflow: " + e.getMessage());
            Screenshot.takeScreenshot(page);
            return "Failed during Actuarial Report generation sequence: " + e.getMessage();
            
        }
    }
    
    public String downloadAndVerifyPDF() {
        try {
            System.out.println("PDF is already rendered in the viewer — downloading directly from PDF viewer...");
            page.waitForTimeout(3000);

            // DEBUG — prints all elements with aria-label so we can see exactly what's on the page
            Object debugResult = page.evaluate(
                "() => {" +
                "  const results = [];" +
                "  document.querySelectorAll('[aria-label]').forEach(el => {" +
                "    results.push(el.tagName + '#' + el.id + ' aria-label=' + el.getAttribute('aria-label'));" +
                "  });" +
                "  return results.join('\\n');" +
                "}"
            );
            System.out.println("=== DEBUG: Elements with aria-label on main page ===\n" + debugResult);

            // Also check all frames for the save button
            for (Frame frame : page.frames()) {
                System.out.println("Found frame URL: " + frame.url());
                try {
                    Object frameDebug = frame.evaluate(
                        "() => {" +
                        "  const btn = document.querySelector('cr-icon-button#save') || document.querySelector('#save');" +
                        "  return btn ? btn.tagName + '#' + btn.id + ' found in this frame' : 'not found in this frame';" +
                        "}"
                    );
                    System.out.println("  -> cr-icon-button#save in this frame: " + frameDebug);
                } catch (Exception ignored) {
                    System.out.println("  -> Could not evaluate in this frame: " + frame.url());
                }
            }
            System.out.println("=== END DEBUG ===");

            // Try clicking on the main page first
            Download download = page.waitForDownload(() -> {
                System.out.println("Attempting to click PDF download button...");

                // Try main page first
                Object mainPageResult = page.evaluate(
                    "() => {" +
                    "  const btn = document.querySelector('cr-icon-button#save') ||" +
                    "               document.querySelector('[aria-label=\"Download\"]') ||" +
                    "               document.querySelector('#save');" +
                    "  if (btn) { btn.click(); return 'clicked: ' + btn.tagName + '#' + btn.id; }" +
                    "  return 'not found on main page';" +
                    "}"
                );
                System.out.println("Main page click result: " + mainPageResult);

                // If not found on main page, try every frame
                if (mainPageResult.toString().contains("not found")) {
                    for (Frame frame : page.frames()) {
                        try {
                            Object frameResult = frame.evaluate(
                                "() => {" +
                                "  const btn = document.querySelector('cr-icon-button#save') ||" +
                                "               document.querySelector('[aria-label=\"Download\"]') ||" +
                                "               document.querySelector('#save');" +
                                "  if (btn) { btn.click(); return 'clicked: ' + btn.tagName + '#' + btn.id; }" +
                                "  return 'not found';" +
                                "}"
                            );
                            System.out.println("Frame [" + frame.url() + "] click result: " + frameResult);
                            if (!frameResult.toString().contains("not found")) {
                                break; // found and clicked — stop trying frames
                            }
                        } catch (Exception ignored) {
                            System.out.println("Could not evaluate in frame: " + frame.url());
                        }
                    }
                }
            });

            // Save the downloaded PDF locally
            String fileName = download.suggestedFilename();
            Path targetPath = Paths.get("target/downloads/" + fileName);
            download.saveAs(targetPath);
            System.out.println("PDF successfully downloaded to: " + targetPath.toAbsolutePath());

            // Take screenshot of the open PDF viewer
            Screenshot.takeScreenshot(page);

            return targetPath.toAbsolutePath().toString();

        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            throw new RuntimeException("PDF download failed: " + e.getMessage(), e);
        }
    }}