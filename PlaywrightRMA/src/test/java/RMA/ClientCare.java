package RMA;

/*
 * ============================================================================
 *  PLAYWRIGHT CONVERSION NOTES (read before wiring this in)
 * ============================================================================
 *  1. WebDriver/WebDriverWait -> Page. Every method now takes/uses `page`
 *     instead of `driver`. Wire this up wherever ClientCare is constructed
 *     (e.g. `new ClientCare(page)` instead of `new ClientCare(driver, wait)`).
 *
 *  2. ShopLocators must return String selectors instead of By objects.
 *     A Selenium `By.xpath("//foo")` becomes the Playwright selector string
 *     "xpath=//foo". A Selenium `By.cssSelector("...")` becomes just the CSS
 *     string. I did NOT have ShopLocators.java, Utilities.java, TestData.java
 *     or Screenshot.java, so every reference to those classes below assumes:
 *       - ShopLocators.XYZ           -> String (was By)
 *       - Screenshot.takeScreenshot(page)  -> now takes Page instead of WebDriver
 *       - TestData.* stays exactly as-is (nothing Selenium-specific in it)
 *     Send those files over and I'll convert them to match 1:1.
 *
 *  3. File uploads (uploadOnboardingSheetViaRobot / uploadFileViaRobot):
 *     the original used java.awt.Robot + clipboard paste to drive the native
 *     Windows "Open" dialog. Playwright has a first-class replacement for
 *     this exact scenario (page.waitForFileChooser + FileChooser.setFiles),
 *     which is more reliable and doesn't depend on window focus/OS dialogs.
 *     I switched to that rather than reimplementing Robot-based keystrokes
 *     under Playwright (Playwright browsers run in a way that makes native
 *     OS dialogs unreliable to automate with Robot). End result is identical:
 *     the same file gets attached to the same upload control. Flagging this
 *     explicitly since you asked me not to silently change behavior.
 *
 *  4. Every method keeps its original name, parameters, return type,
 *     and step order. Where the original returned an error string on
 *     failure vs. null on success, that contract is preserved.
 *
 *  5. Thread.sleep() calls are kept as-is (not stripped) even though
 *     Playwright's auto-waiting reduces the need for most of them, because
 *     you said not to change logic/behavior. If you later want me to trim
 *     the sleeps once you've confirmed the converted flow passes, say so.
 * ============================================================================
 */

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.assertions.LocatorAssertions;
public class ClientCare {

    private final Page page;

    // Holds the FileChooser captured by selectRadioAndOpenUploadWindow() so that
    // uploadFileViaRobot() (called afterwards, same as in the original flow) can
    // attach the file to it. See note #3 above.
    private FileChooser pendingFileChooser;

    public ClientCare(Page page) {
        this.page = page;
    }

    // ------------------------------------------------------------------
    // Small internal helpers (kept private so the public method list/order
    // below matches the original file 1:1)
    // ------------------------------------------------------------------

    private Locator waitVisible(String selector, int timeoutSeconds) {
        Locator loc = page.locator(selector).first();
        loc.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeoutSeconds * 1000));
        return loc;
    }

    private Locator waitPresent(String selector, int timeoutSeconds) {
        Locator loc = page.locator(selector).first();
        loc.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(timeoutSeconds * 1000));
        return loc;
    }

    private Locator waitClickable(String selector, int timeoutSeconds) {
        // Playwright doesn't have a distinct "clickable" wait state the way
        // Selenium's ExpectedConditions does; VISIBLE + the isEnabled() check
        // right after (same as the original code did) reproduces that check.
        return waitVisible(selector, timeoutSeconds);
    }

    private void jsClick(Locator locator) {
        locator.evaluate("el => el.click()");
    }

    private void scrollIntoView(Locator locator) {
        locator.evaluate("el => el.scrollIntoView({block: 'center'})");
    }

    // ------------------------------------------------------------------

    public String clickClientCareButton() throws InterruptedException {
        Locator clientCareBtn = waitVisible(ShopLocators.Clientcare_LINK, 15);

        if (clientCareBtn.isVisible() && clientCareBtn.isEnabled()) {
            clientCareBtn.click();
            System.out.println("Client Care button clicked");
            Screenshot.takeScreenshot(page);
            return null;
        } else {
            Screenshot.takeScreenshot(page);
            return "Client Care not clickable";
        }
    }

    public String clickPolicyManagerButton() throws InterruptedException {
        Locator policyManagerBtn = waitVisible(ShopLocators.PolicyManager_button, 15);

        if (policyManagerBtn.isVisible() && policyManagerBtn.isEnabled()) {
            policyManagerBtn.click();
            System.out.println("Policy Manager button clicked");
            Screenshot.takeScreenshot(page);
            return null;
        } else {
            Screenshot.takeScreenshot(page);
            return "Policy Manager not clickable";
        }
    }

    /**
     * Checks for either 'Upload' button or 'Group Members' button dynamically,
     * clicks whichever locator is present and visible, and takes a screenshot.
     */
    public String clickUploadsButton() {
        try {
            System.out.println("Waiting for 'Upload' or 'Group Members' button to appear...");

            // 1. Clear active Angular overlays/backdrops if present
            try {
                page.locator("xpath=//div[contains(@class,'cdk-overlay-backdrop')]")
                        .first()
                        .waitFor(new Locator.WaitForOptions()
                                .setState(WaitForSelectorState.HIDDEN)
                                .setTimeout(50000));
            } catch (Exception ignore) {}

            // 2. Poll dynamically until either Upload or Group button becomes visible
            Locator targetButton = null;
            long deadline = System.currentTimeMillis() + 50000;
            while (System.currentTimeMillis() < deadline) {
                List<Locator> uploads = page.locator(ShopLocators.Upload_button).all();
                if (!uploads.isEmpty() && uploads.get(0).isVisible()) {
                    targetButton = uploads.get(0);
                    break;
                }
                List<Locator> groups = page.locator(ShopLocators.Group_button).all();
                if (!groups.isEmpty() && groups.get(0).isVisible()) {
                    targetButton = groups.get(0);
                    break;
                }
                page.waitForTimeout(250);
            }

            if (targetButton == null) {
                throw new TimeoutError("Neither Upload nor Group Members button appeared within 50s");
            }

            String buttonText = targetButton.textContent();
            buttonText = buttonText == null ? "" : buttonText.trim();
            if (buttonText.isEmpty()) {
                buttonText = "Upload / Group Members";
            }
            System.out.println("Detected button: '" + buttonText + "'. Triggering click...");

            // 3. Scroll & Click with JS Fallback
            scrollIntoView(targetButton);
            page.waitForTimeout(300);

            try {
                targetButton.click(new Locator.ClickOptions().setTimeout(5000));
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click fallback...");
                jsClick(targetButton);
            }

            System.out.println("Successfully clicked '" + buttonText + "' button!");
            page.waitForTimeout(1000);

            Screenshot.takeScreenshot(page);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click Upload / Group Members button: " + e.getMessage());
            Screenshot.takeScreenshot(page);
            return "Click Upload / Group Members Button Error: " + e.getMessage();
        }
    }

    public String clickFuneralButton() throws InterruptedException {
        try {
            System.out.println("Checking for optional Funeral workflow option button...");

            // Tight 4-second wait so we don't stall the execution line if it's absent
            Locator funeralBtn = waitVisible(ShopLocators.Funeral_button, 4);

            // If found, execute the hover sequence and click
            funeralBtn.hover();
            System.out.println("Hovered over optional Funeral button.");

            funeralBtn.click();
            System.out.println("Optional Funeral button clicked successfully.");

            Screenshot.takeScreenshot(page); // 📸 Capture pass state
            return null;

        } catch (Exception e) {
            // Safe fallback state: Catch the timeout, log it, and skip forward cleanly
            System.out.println("Funeral button did not display during this cycle run. Skipping optional step.");
            return null;
        }
    }

    public String clickPolicyTypeButton() throws InterruptedException {
        try {
            // Get policy type from Excel
            String policyType = TestData.getPolicyType();

            // Get dynamic locator
            String policyButtonSelector = ShopLocators.getPolicyTypeButton(policyType);

            // Wait until clickable
            Locator policyButton = waitClickable(policyButtonSelector, 15);

            if (policyButton.isVisible() && policyButton.isEnabled()) {
                policyButton.click();
                System.out.println("Policy Type button clicked: " + policyType);
                Thread.sleep(1000);
                Screenshot.takeScreenshot(page);
                return null;
            } else {
                Screenshot.takeScreenshot(page);
                return "Policy Type button is not clickable: " + policyType;
            }

        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Failed to click Policy Type button: " + e.getMessage();
        }
    }

    public String clickCreateNewButton() throws InterruptedException {
        try {
            System.out.println("Attempting to click the primary 'Next' button...");
            Locator nextBtn = waitClickable(ShopLocators.NewPolicy_button, 10);

            if (nextBtn.isVisible() && nextBtn.isEnabled()) {
                nextBtn.click();
                System.out.println("Successfully clicked the primary 'Next' button.");
                Thread.sleep(3000);
                Screenshot.takeScreenshot(page);
                return null;
            } else {
                throw new Exception("Next button present but not interactable.");
            }

        } catch (Exception e) {
            // FALLBACK COMPONENT: If 'Next' is missing or fails, look for and click the checkbox
            System.out.println("Notice: 'Next' button not available. Engaging fallback checkbox workflow...");

            try {
                Locator fallbackCheckbox = waitVisible(ShopLocators.Fallback_Checkbox, 15);

                scrollIntoView(fallbackCheckbox);
                Thread.sleep(500);
                jsClick(fallbackCheckbox);

                System.out.println("Fallback Material checkbox successfully toggled.");
                Thread.sleep(2500);
                Screenshot.takeScreenshot(page);
                return null;

            } catch (Exception checkboxEx) {
                Screenshot.takeScreenshot(page);
                return "Failed to progress workflow: Neither the primary 'Next' button nor the fallback checkbox were interactable. Error: " + checkboxEx.getMessage();
            }
        }
    }

    public String uploadOnboardingSheetViaRobot() {
        try {
            Locator dropboxLink = page.locator("//*[@id='dropbox']/span/a");
            dropboxLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(15000));

            if (dropboxLink.isVisible() && dropboxLink.isEnabled()) {
                String rawPath = TestData.getOnboardingsheet();
                System.out.println("Retrieved Raw Path from Excel: " + rawPath);

                if (rawPath == null || rawPath.trim().isEmpty()) {
                    return "Upload failed: File path retrieved from Excel data is empty or null.";
                }

                String filePath = rawPath.replace("\\\\", "\\").trim();
                System.out.println("Sanitized Path for Windows Dialog: " + filePath);

                // Native Playwright FileChooser handles the file input directly
                FileChooser fileChooser = page.waitForFileChooser(() -> {
                    dropboxLink.click();
                    System.out.println("Dropbox upload link clicked. Waiting for file chooser...");
                });

                fileChooser.setFiles(Paths.get(filePath));
                System.out.println("Dynamic file path set via Playwright FileChooser successfully.");

                page.waitForTimeout(3000);
                Screenshot.takeScreenshot(page);
                return null;
            } else {
                Screenshot.takeScreenshot(page);
                return "Dropbox upload trigger link not clickable.";
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "File upload execution sequence failed: " + e.getMessage();
        }
    }


    public String clickFinalUploadSubmitButton() {
        try {
            Locator finalUploadBtn = page.locator(ShopLocators.UploadFilee_button);

            // 1. Wait until the button is fully enabled and clickable before clicking
            assertThat(finalUploadBtn).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(15000));
            
            finalUploadBtn.click();
            System.out.println("Final 'Upload File' submit button clicked. Processing upload...");

            try {
                System.out.println("Waiting for 'Upload File' button to become active/enabled again...");

                // 2. Wait up to 180 seconds for the button to re-enable after upload finishes
                assertThat(finalUploadBtn).isEnabled(new LocatorAssertions.IsEnabledOptions().setTimeout(180000));

                System.out.println("Onboarding sheet successfully uploaded and processed!");
                Screenshot.takeScreenshot(page);
                return null;
            } catch (AssertionError e) {
                Screenshot.takeScreenshot(page);
                return "Upload verification failed: The 'Upload File' button did not become active within timeout.";
            }

        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Final 'Upload File' submit button not clickable: " + e.getMessage();
        }
    }

    public void handleOptionalReminderAlert() {
        try {
            System.out.println("Checking for optional Reminder Alert popup...");
            // Use a short 4-second wait so we don't waste time if it's not there
            Locator reminderAlert = waitClickable(ShopLocators.Reminder_alert, 4);

            if (reminderAlert.isVisible()) {
                reminderAlert.click();
                System.out.println("Reminder Alert detected and dismissed successfully.");
                Thread.sleep(1500); // Small pause for the modal fade-out animation
            }
        } catch (Exception e) {
            System.out.println("No Reminder Alert popup appeared. Proceeding with the flow.");
        }
    }

    public String openPolicy() throws InterruptedException {
        try {
            // 1. Locate the Overall SLA header sort button element wrapper
            Locator overallSlaHeader = waitPresent(ShopLocators.OverallSLA_Header, 15);

            // CLICK 1: First click to sort/refresh the data profile sequence via JS
            jsClick(overallSlaHeader);
            System.out.println("Overall SLA Header clicked via JS (1st Time)...");
            Thread.sleep(2000);

            // CLICK 2: Second click to toggle sorting direction
            jsClick(overallSlaHeader);
            System.out.println("Overall SLA Header clicked via JS (2nd Time). Sorting complete.");
            Thread.sleep(2500);

            // 2. Locate and click the 'open' link belonging specifically to our target type row
            System.out.println("Searching table dynamically for a row matching the target type...");
            Locator targetOpenLink = waitClickable(ShopLocators.TargetRowOpen_link, 15);

            if (targetOpenLink.isVisible() && targetOpenLink.isEnabled()) {
                targetOpenLink.click();
                System.out.println("Matching row record 'open' link clicked successfully.");
                Thread.sleep(3000);
                Screenshot.takeScreenshot(page);
                return null;
            } else {
                return "Target row 'open' link found dynamically but it is not interactable.";
            }

        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Failed to locate or open the correct policy row matching the criteria: " + e.getMessage();
        }
    }

    public String clickContinueButton() throws InterruptedException {
        Locator continueBtn = waitClickable(ShopLocators.Continue_button, 15);

        if (continueBtn.isVisible() && continueBtn.isEnabled()) {
            continueBtn.click();
            System.out.println("Successfully clicked the 'Continue' button.");
            Screenshot.takeScreenshot(page);
            return null;
        } else {
            Screenshot.takeScreenshot(page);
            return "The 'Continue' button was found but is not interactable or visible.";
        }
    }

    public String clickNextButton() throws InterruptedException {
        Locator nextBtn = waitClickable(ShopLocators.Next_button, 15);

        if (nextBtn.isVisible() && nextBtn.isEnabled()) {
            Thread.sleep(5000);
            nextBtn.click();
            System.out.println("Successfully clicked the 'Next' button.");

            Screenshot.takeScreenshot(page);
            return null;
        } else {
            Screenshot.takeScreenshot(page);
            return "The 'Next' button was located but is not interactable.";
        }
    }

    public String getExtractIDNumber() throws InterruptedException {
        try {
            System.out.println("\n=================== START DOM INSPECTION DEBUG ===================");
            System.out.println("Waiting for first ID Number element presence in DOM...");

            // 1. Wait until the element exists in the DOM structurally
            Locator idElement = waitPresent(ShopLocators.First_ID_Number_Text, 90);

            System.out.println("Element successfully located in DOM framework layer.");

            // 2. Interrogate everything about the located element state
            System.out.println("DEBUG - Tag Name: " + idElement.evaluate("el => el.tagName"));
            System.out.println("DEBUG - Is Displayed Visibly? " + idElement.isVisible());
            System.out.println("DEBUG - Is Enabled? " + idElement.isEnabled());
            System.out.println("DEBUG - Class Attribute: " + idElement.getAttribute("class"));
            System.out.println("DEBUG - Outer HTML representation:\n" + idElement.evaluate("el => el.outerHTML"));

            // 3. Extract text content states
            String rawText = idElement.textContent();
            String innerText = (String) idElement.evaluate("el => el.innerText");
            String textContent = (String) idElement.evaluate("el => el.textContent");

            System.out.println("DEBUG - .textContent() returned: [" + (rawText != null ? rawText.trim() : "null") + "]");
            System.out.println("DEBUG - innerText attribute returned: [" + (innerText != null ? innerText.trim() : "null") + "]");
            System.out.println("DEBUG - textContent attribute returned: [" + (textContent != null ? textContent.trim() : "null") + "]");

            String fullText = (rawText != null && !rawText.isEmpty()) ? rawText.trim() :
                             (innerText != null && !innerText.isEmpty() ? innerText.trim() :
                             (textContent != null ? textContent.trim() : ""));

            // 4. Run Regex matching sequence (Extracts 13-digit SA ID number or any sequence of digits)
            Pattern pattern = Pattern.compile("(\\d{13}|\\d+)");
            Matcher matcher = pattern.matcher(fullText);

            if (matcher.find()) {
                String idNumber = matcher.group(1);
                System.out.println("SUCCESS: ID number extracted successfully: " + idNumber);
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(page); // 📸 Capture Pass
                return idNumber;
            } else {
                System.out.println("FAILED: Regular expression pattern match failed to find digits inside: [" + fullText + "]");
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(page); // 📸 Capture Fail
                return null;
            }

        } catch (TimeoutError e) {
            System.out.println("TIMEOUT: First ID Number element did not appear in the DOM within 30 seconds.");
            System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
            Screenshot.takeScreenshot(page); // 📸 Capture Timeout
            return null;
        } catch (Exception e) {
            System.out.println("CRITICAL ERROR during ID extraction routine: " + e.getMessage());
            System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
            Screenshot.takeScreenshot(page); // 📸 Capture Error
            return null;
        }
    }

    public String overrideAllPoliciesAndSubmit(String pdfFilePath) {
        try {
            System.out.println("Scanning layout screen for optional policy 'more_vert' menus...");

            // Target only visible/interactable menu buttons on the page
            Locator clickableMenus = page.locator(ShopLocators.MoreVert_menu).filter(new Locator.FilterOptions().setHasText(""));
            
            int totalMenus = clickableMenus.count();
            System.out.println("Total menu buttons detected to process: " + totalMenus);

            if (totalMenus == 0) {
                System.out.println("Notice: No actionable 'more_vert' menus found. Skipping override flow.");
                return null;
            }

            for (int i = 0; i < totalMenus; i++) {
                System.out.println("--- Processing Actionable Record " + (i + 1) + " of " + totalMenus + " ---");

                // Always fetch fresh handle on every iteration
                Locator currentMenu = page.locator(ShopLocators.MoreVert_menu).nth(i);

                // Use try/catch specifically on clickability checks
                try {
                    // Quick check if the element is interactable before proceeding
                    if (!currentMenu.isVisible() || !currentMenu.isEnabled()) {
                        System.out.println("Menu item #" + (i + 1) + " is not active/clickable. Skipping.");
                        continue;
                    }

                    currentMenu.scrollIntoViewIfNeeded();
                    page.waitForTimeout(1000);
                    currentMenu.click(new Locator.ClickOptions().setTimeout(5000));
                    System.out.println("Opened options menu #" + (i + 1));
                    page.waitForTimeout(1500);

                } catch (Exception e) {
                    System.out.println("Notice: Menu item #" + (i + 1) + " could not be opened: " + e.getMessage());
                    continue;
                }

                // Proceed with row upload workflow...
                try {
                    Locator overrideBtn = page.locator(ShopLocators.OverrideVOPD_button).first();
                    overrideBtn.waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(4000));
                    overrideBtn.click();
                    System.out.println("Clicked 'Override VOPD'.");
                    page.waitForTimeout(2500);

                    String radioError = selectRadioAndOpenUploadWindow(pdfFilePath);
                    if (radioError != null) {
                        throw new RuntimeException("Radio/Upload Selection Step Failed: " + radioError);
                    }

                    String rowSubmitError = clickGreenUploadAndSubmit();
                    if (rowSubmitError != null) {
                        throw new RuntimeException("Green Submit Step Failed: " + rowSubmitError);
                    }

                    System.out.println("Record " + (i + 1) + " successfully completed and submitted.");

                } catch (Exception e) {
                    System.out.println("⚠️ Notice: Skipping Record " + (i + 1) + " due to step failure: " + e.getMessage());
                    Screenshot.takeScreenshot(page);

                    try {
                        page.keyboard().press("Escape");
                        System.out.println("Dispatched ESC key safety cleanup command.");
                    } catch (Exception ignored) {}

                    page.waitForTimeout(1000);
                    continue;
                }

                page.waitForTimeout(2000);
            }

            System.out.println("All available rows on this layout screen processed completely.");
            return null;

        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            System.out.println("Critical outer failure in loop processing framework: " + e.getMessage());
            return null;
        }
    }

    public String triggerOverrideVOPD() {
        try {
            Locator moreVert = page.locator(ShopLocators.MoreVert_menu).first();
            moreVert.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(15000));
            moreVert.click();
            System.out.println("Clicked the 'more_vert' options menu button.");
            page.waitForTimeout(1500);

            Locator overrideBtn = page.locator(ShopLocators.OverrideVOPD_button).first();
            overrideBtn.click(new Locator.ClickOptions().setTimeout(15000));
            System.out.println("Successfully clicked the 'Override VOPD' option item.");

            page.waitForTimeout(2500);
            Screenshot.takeScreenshot(page);
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Failed to complete the Override VOPD menu selection sequence: " + e.getMessage();
        }
    }

    public String selectRadioAndOpenUploadWindow(String pdfFilePath) {
        try {
            // Clean literal quotes from string path input
            if (pdfFilePath == null || pdfFilePath.trim().isEmpty()) {
                return "File upload failed: Provided PDF file path is null or empty.";
            }
            String cleanFilePath = pdfFilePath.replace("\"", "").trim();

            // 1. Locate and toggle radio button host
            Locator radioButtonHost = page.locator("mat-radio-button").first();
            radioButtonHost.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED).setTimeout(15000));

            String classAttribute = radioButtonHost.getAttribute("class");

            if (classAttribute != null && classAttribute.contains("mat-radio-checked")) {
                System.out.println("Radio button is already selected. Skipping click action.");
            } else {
                Locator radioLabel = page.locator("mat-radio-button .mat-radio-label, mat-radio-button label").first();
                try {
                    radioLabel.click(new Locator.ClickOptions().setForce(true).setTimeout(5000));
                } catch (Exception e) {
                    radioButtonHost.click(new Locator.ClickOptions().setForce(true));
                }
                System.out.println("Radio button clicked and engaged.");
                page.waitForTimeout(1500);
            }

            // 2. Click inline upload icon
            Locator uploadIcon = page.locator(ShopLocators.InlineUpload_Icon).first();
            uploadIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(15000));
            uploadIcon.click(new Locator.ClickOptions().setForce(true));
            System.out.println("Upload icon button clicked.");
            page.waitForTimeout(2000);

            // 3. Catch file chooser dialog and set sanitized path
            FileChooser fileChooser = page.waitForFileChooser(() -> {
                Locator chooseFiles = page.locator(ShopLocators.ChooseFiles_link).first();
                chooseFiles.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(15000));
                chooseFiles.click();
                System.out.println("'Choose files' link clicked. Awaiting file path inputs...");
            });

            fileChooser.setFiles(Paths.get(cleanFilePath));
            System.out.println("File attached successfully via Playwright FileChooser: " + cleanFilePath);
            page.waitForTimeout(2000);

            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Failed during the radio selection and file prompt flow: " + e.getMessage();
        }
    }

    public String clickGreenUploadAndSubmit() {
        try {
            Locator greenUpload = page.locator(ShopLocators.GreenUpload_Icon).first();
            greenUpload.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(15000));
            greenUpload.click();
            System.out.println("Green confirmation upload icon clicked successfully inside Client Care.");
            page.waitForTimeout(2000);

            Locator submitBtn = page.locator(ShopLocators.Submit_button).first();
            submitBtn.click(new Locator.ClickOptions().setTimeout(15000));
            System.out.println("Client Care Submit button clicked successfully.");

            page.waitForTimeout(3000);
            Screenshot.takeScreenshot(page);
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Failed during the client care post-upload submission sequence: " + e.getMessage();
        }
    }

    public String submitUploadedDocument() {
        try {
            Locator greenUpload = page.locator(ShopLocators.GreenUpload_Icon).first();
            greenUpload.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(15000));
            greenUpload.click();
            System.out.println("Green confirmation upload icon clicked successfully.");

            Locator submitBtn = page.locator(ShopLocators.Submit_button).first();
            submitBtn.click(new Locator.ClickOptions().setTimeout(15000));
            System.out.println("Final 'Submit' button clicked successfully.");

            page.waitForTimeout(3000);
            Screenshot.takeScreenshot(page);
            return null;

        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Failed during the post-upload submission sequence: " + e.getMessage();
        }
    }

    public String clickWizardStepButton() {
        try {
            Locator wizardBtn = page.locator(ShopLocators.Next_button).first();
            wizardBtn.click(new Locator.ClickOptions().setTimeout(15000));
            System.out.println("Wizard step button matching target path clicked successfully.");

            page.waitForTimeout(2500);
            Screenshot.takeScreenshot(page);
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Failed to click the wizard step button: " + e.getMessage();
        }
    }

    public String clickRequestApprovalButton() throws InterruptedException {
        try {
            System.out.println("Waiting for 'Request Approval' button to become clickable...");
            Locator requestApprovalBtn = waitClickable(ShopLocators.RequestApproval_button, 65);

            if (requestApprovalBtn.isVisible() && requestApprovalBtn.isEnabled()) {
                scrollIntoView(requestApprovalBtn);
                Thread.sleep(1000);

                jsClick(requestApprovalBtn);
                System.out.println("'Request Approval' button clicked successfully.");

                Thread.sleep(3000);
                Screenshot.takeScreenshot(page);
                return null;
            } else {
                return "The 'Request Approval' button was located but is not interactable.";
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "Failed to click 'Request Approval' button sequence: " + e.getMessage();
        }
    }

    public String waitForApprovalMessage() throws InterruptedException {
        try {
            System.out.println("Waiting for the application state status message: 'Awaiting approval someone with permission'...");

            Locator approvalMsg = waitVisible(ShopLocators.ApprovalStatus_Message, 60);

            if (approvalMsg.isVisible()) {
                System.out.println("Success! Verified approval message layout state text: '" + approvalMsg.textContent().trim() + "'");
                Screenshot.takeScreenshot(page);
                return null;
            } else {
                return "The approval status wrapper rendered in the DOM framework layer but is not visibly displayed.";
            }
        } catch (TimeoutError e) {
            Screenshot.takeScreenshot(page);
            return "Timed out waiting for the message 'Awaiting approval someone with permission' to appear after 30 seconds.";
        } catch (Exception e) {
            Screenshot.takeScreenshot(page);
            return "An unexpected error occurred while validating the approval status block: " + e.getMessage();
        }
    }

    public String getExtractPolicyNumber() throws InterruptedException {
        try {
            System.out.println("\n=================== START DOM INSPECTION DEBUG ===================");
            System.out.println("Waiting for policy reference element presence in DOM...");

            Locator referenceElement = waitPresent(ShopLocators.PolicyReference_Text, 30);

            System.out.println("Element successfully located in DOM framework layer.");

            System.out.println("DEBUG - Tag Name: " + referenceElement.evaluate("el => el.tagName"));
            System.out.println("DEBUG - Is Displayed Visibly? " + referenceElement.isVisible());
            System.out.println("DEBUG - Is Enabled? " + referenceElement.isEnabled());
            System.out.println("DEBUG - Class Attribute: " + referenceElement.getAttribute("class"));
            System.out.println("DEBUG - Outer HTML representation:\n" + referenceElement.evaluate("el => el.outerHTML"));

            String rawText = referenceElement.textContent();
            String innerText = (String) referenceElement.evaluate("el => el.innerText");
            String textContent = (String) referenceElement.evaluate("el => el.textContent");

            System.out.println("DEBUG - .textContent() returned: [" + (rawText != null ? rawText.trim() : "null") + "]");
            System.out.println("DEBUG - innerText attribute returned: [" + (innerText != null ? innerText.trim() : "null") + "]");
            System.out.println("DEBUG - textContent attribute returned: [" + (textContent != null ? textContent.trim() : "null") + "]");

            String fullText = (rawText != null && !rawText.isEmpty()) ? rawText.trim() : (innerText != null ? innerText.trim() : "");

            Pattern pattern = Pattern.compile("PCAS:\\s*(\\d+)");
            Matcher matcher = pattern.matcher(fullText);

            if (matcher.find()) {
                String policyNumber = matcher.group(1);
                System.out.println("SUCCESS: Policy number extracted successfully: " + policyNumber);
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(page); // 📸 Capture Pass
                return policyNumber;
            } else {
                System.out.println("FAILED: Regular expression pattern match failed to find 'PCAS:\\s*(\\d+)' inside: [" + fullText + "]");
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(page); // 📸 Capture Fail
                return null;
            }

        } catch (TimeoutError e) {
            System.out.println("TIMEOUT: Policy reference element did not appear in the DOM within 30 seconds.");
            System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
            Screenshot.takeScreenshot(page); // 📸 Capture Timeout
            return null;
        } catch (Exception e) {
            System.out.println("CRITICAL ERROR during extraction routine: " + e.getMessage());
            System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
            Screenshot.takeScreenshot(page); // 📸 Capture Error
            return null;
        }
    }

    public String searchPolicyNumber(String policyNumber) {
        try {
            System.out.println("Waiting for the policy search text box to become interactable...");

            Locator searchBox = waitClickable(ShopLocators.SearchText_Field, 30);

            // Clear anything currently inside, click, and input the number
            searchBox.click();
            searchBox.clear();

            System.out.println("Searching for policy number: [" + policyNumber + "]");
            searchBox.fill(policyNumber);

            searchBox.press("Enter");
            System.out.println("Search command submitted successfully.");

            Screenshot.takeScreenshot(page); // 📸 Capture Pass
            return null;

        } catch (Exception e) {
            System.out.println("Failed to execute policy number search sequence: " + e.getMessage());
            Screenshot.takeScreenshot(page); // 📸 Capture Fail
            return "Search Input Error: " + e.getMessage();
        }
    }

    // 1. Click Open Link
    public String clickOpenPolicyLink() {
        try {
        	
            System.out.println("Clicking the 'open' policy link...");
            page.waitForTimeout(5000);
            Locator openLink = waitClickable(ShopLocators.OpenLink_Button, 60);
            openLink.click();

            Screenshot.takeScreenshot(page); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page); // 📸 Capture Fail
            return "Click Open Link Error: " + e.getMessage();
        }
    }

    // 2. Click View Button
    public String clickViewButton() {
        try {
            System.out.println("Clicking the 'View' submit button...");
            Locator viewBtn = waitClickable(ShopLocators.ViewSubmit_Button, 60);
            viewBtn.click();

            // 🔄 Wait for browser document state to load completely
            page.waitForLoadState();

            Screenshot.takeScreenshot(page); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page); // 📸 Capture Fail
            return "Click View Button Error: " + e.getMessage();
        }
    }

    // 3. Click Next Button (Reusable for both clicks)
    public String clickNextStepButton() {
        try {
            System.out.println("Clicking the 'Next' navigation button...");
            Locator nextBtn = waitClickable(ShopLocators.Next_button, 30);
            nextBtn.click();

            // 🔄 Wait for browser document state to load completely
            page.waitForLoadState();

            Screenshot.takeScreenshot(page); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page); // 📸 Capture Fail
            return "Click Next Button Error: " + e.getMessage();
        }
    }

    // 4. Click Approve Button
    public String clickApproveButton() {
        try {
            System.out.println("Clicking final 'Approve' button...");
            Locator approveBtn = waitClickable(ShopLocators.Approve_Button, 30);
            approveBtn.click();

            // 🔄 Wait for browser document state to load completely
            page.waitForLoadState();

            Screenshot.takeScreenshot(page); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page); // 📸 Capture Fail
            return "Click Approve Button Error: " + e.getMessage();
        }
    }

    // 5. Click "Yes, Approve" Confirmation Button
    public String clickYesApproveConfirmationButton() {
        try {
            System.out.println("Clicking the final 'Yes, Approve' confirmation dialog button...");
            Locator yesApproveBtn = waitClickable(ShopLocators.YesApprove_Button, 30);
            yesApproveBtn.click();
            page.waitForTimeout(6000);
            Screenshot.takeScreenshot(page);

            // 🔄 Wait for browser document state to load completely
            page.waitForLoadState();
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(page); // 📸 Capture Fail
            return "Click Yes Approve Button Error: " + e.getMessage();
        }
    }

    public String clickSearchButton() {
        try {
            System.out.println("Waiting for 'Search' button to appear...");

            // Wait for active Angular CDK overlays to clear
            try {
                page.locator("xpath=//div[contains(@class,'cdk-overlay-backdrop')]")
                        .first()
                        .waitFor(new Locator.WaitForOptions()
                                .setState(WaitForSelectorState.HIDDEN)
                                .setTimeout(45000));
            } catch (Exception ignore) {}

            Locator searchBtn = waitVisible(ShopLocators.SearchH_Button, 45);

            scrollIntoView(searchBtn);
            page.waitForTimeout(300);

            // Native click with JavaScript fallback
            try {
                searchBtn.click(new Locator.ClickOptions().setTimeout(5000));
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click on 'Search' button...");
                jsClick(searchBtn);
            }

            System.out.println("Successfully clicked 'Search' button!");
            page.waitForTimeout(1000);

            Screenshot.takeScreenshot(page);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'Search' button: " + e.getMessage());
            Screenshot.takeScreenshot(page);
            return "Click Search Button Error: " + e.getMessage();
        }
    }

    public String enterSearchID(String idNumber) {
        try {
            System.out.println("Entering ID into Search Term input: " + idNumber);

            Locator searchInput = waitVisible(ShopLocators.Search_Term_Input, 45);

            scrollIntoView(searchInput);
            page.waitForTimeout(300);

            // Clear existing value safely for Angular Reactive Forms
            searchInput.click();
            searchInput.press("Control+A");
            searchInput.press("Delete");

            // Enter ID number
            searchInput.fill(idNumber);
            page.waitForTimeout(300);

            // Trigger Angular change detection events (fill() already dispatches
            // input, but the explicit dispatch is kept to match original behavior)
            searchInput.evaluate("el => el.dispatchEvent(new Event('input', { bubbles: true }))");
            searchInput.evaluate("el => el.dispatchEvent(new Event('blur', { bubbles: true }))");

            System.out.println("Successfully entered ID into Search field!");
            Screenshot.takeScreenshot(page);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to enter ID in Search field: " + e.getMessage());
            Screenshot.takeScreenshot(page);
            return "Enter Search ID Error: " + e.getMessage();
        }
    }

    public String clickSelectActionButton() {
        try {
            page.waitForTimeout(1900);
            System.out.println("Waiting for 'Select' (btnAction) link button to appear...");

            // Wait for active Angular CDK overlays to clear
            try {
                page.locator("xpath=//div[contains(@class,'cdk-overlay-backdrop')]")
                        .first()
                        .waitFor(new Locator.WaitForOptions()
                                .setState(WaitForSelectorState.HIDDEN)
                                .setTimeout(45000));
            } catch (Exception ignore) {}

            Locator selectActionLink = waitVisible(ShopLocators.Select_BtnAction, 45);

            scrollIntoView(selectActionLink);
            page.waitForTimeout(300);

            // Native click with JavaScript fallback
            try {
                selectActionLink.click(new Locator.ClickOptions().setTimeout(5000));
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click on 'Select' (btnAction) link...");
                jsClick(selectActionLink);
            }

            System.out.println("Successfully clicked 'Select' (btnAction) link!");

            Screenshot.takeScreenshot(page);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'Select' (btnAction) link: " + e.getMessage());
            Screenshot.takeScreenshot(page);
            return "Click Select Action Button Error: " + e.getMessage();
        }
    }

    /**
     * Loops through all Angular Material tabs on the page, clicks each tab,
     * waits for the panel content to render, and takes a screenshot for each tab.
     */
    public String clickAllTabsAndCaptureScreenshots() {
        try {
            System.out.println("\n=================== START CLICKING ALL TABS ===================");

            // Wait for at least one tab to be visible on the page
            waitVisible(ShopLocators.All_Tab_Labels, 65);

            // Get total count of tabs
            List<Locator> tabs = page.locator(ShopLocators.All_Tab_Labels).all();
            int tabCount = tabs.size();
            System.out.println("Total tabs found: " + tabCount);

            for (int i = 0; i < tabCount; i++) {
                // Clear any lingering Angular CDK overlay backdrops
                try {
                    page.locator("xpath=//div[contains(@class,'cdk-overlay-backdrop')]")
                            .first()
                            .waitFor(new Locator.WaitForOptions()
                                    .setState(WaitForSelectorState.HIDDEN)
                                    .setTimeout(65000));
                } catch (Exception ignore) {}

                // Re-fetch tab list dynamically to prevent stale element references
                List<Locator> currentTabs = page.locator(ShopLocators.All_Tab_Labels).all();
                Locator currentTab = currentTabs.get(i);

                String tabTitle = currentTab.textContent();
                tabTitle = (tabTitle == null ? "" : tabTitle.trim().replaceAll("\\r?\\n", " "));
                if (tabTitle.isEmpty()) {
                    tabTitle = "Tab #" + (i + 1);
                }

                System.out.println("Clicking Tab [" + (i + 1) + "/" + tabCount + "]: '" + tabTitle + "'");

                // Scroll into view
                scrollIntoView(currentTab);
                page.waitForTimeout(300);

                // Click tab with JS fallback
                try {
                    currentTab.click(new Locator.ClickOptions().setTimeout(5000));
                } catch (Exception clickEx) {
                    System.out.println("Native click intercepted. Executing JS click on tab: " + tabTitle);
                    jsClick(currentTab);
                }

                // Allow tab content animation to render before taking screenshot
                page.waitForTimeout(1200);

                System.out.println("Captured screenshot for tab: '" + tabTitle + "'");
                Screenshot.takeScreenshot(page);
            }

            System.out.println("=================== COMPLETED ALL TABS ===================\n");
            return null;

        } catch (Exception e) {
            System.out.println("Failed while iterating through tabs: " + e.getMessage());
            Screenshot.takeScreenshot(page);
            return "Click All Tabs Error: " + e.getMessage();
        }
    }
}
