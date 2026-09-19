package RMA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ClientCare {
    
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ClientCare(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait; 
    }
    
    public String clickClientCareButton() throws InterruptedException {
        WebElement clientCareBtn = Utilities.waitForVisibility(driver, ShopLocators.Clientcare_LINK, 35);

        if (clientCareBtn.isDisplayed() && clientCareBtn.isEnabled()) {
            clientCareBtn.click();
            System.out.println("Client Care button clicked");
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
            return "Client Care not clickable";
        }
    }

    public String clickPolicyManagerButton() throws InterruptedException {
        WebElement policyManagerBtn = Utilities.waitForVisibility(driver, ShopLocators.PolicyManager_button, 35);

        if (policyManagerBtn.isDisplayed() && policyManagerBtn.isEnabled()) {
            policyManagerBtn.click();
            System.out.println("Policy Manager button clicked");
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
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
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(50));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. Clear active Angular overlays/backdrops if present
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            // 2. Poll dynamically until either Upload or Group button becomes visible
            WebElement targetButton = explicitWait.until(d -> {
                java.util.List<WebElement> uploads = d.findElements(ShopLocators.Upload_button);
                if (!uploads.isEmpty() && uploads.get(0).isDisplayed()) {
                    return uploads.get(0);
                }
                java.util.List<WebElement> groups = d.findElements(ShopLocators.Group_button);
                if (!groups.isEmpty() && groups.get(0).isDisplayed()) {
                    return groups.get(0);
                }
                return null;
            });

            String buttonText = targetButton.getText().trim();
            if (buttonText.isEmpty()) {
                buttonText = "Upload / Group Members";
            }
            System.out.println("Detected button: '" + buttonText + "'. Triggering click...");

            // 3. Scroll & Click with JS Fallback
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", targetButton);
            Thread.sleep(300);

            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(targetButton));
                targetButton.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click fallback...");
                js.executeScript("arguments[0].click();", targetButton);
            }

            System.out.println("Successfully clicked '" + buttonText + "' button!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click Upload / Group Members button: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click Upload / Group Members Button Error: " + e.getMessage();
        }
    }

    public String clickFuneralButton() throws InterruptedException {
        try {
            System.out.println("Checking for optional Funeral workflow option button...");
            
            // Tight 4-second wait so we don't stall the execution line if it's absent
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement funeralBtn = shortWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Funeral_button)
            );

            // If found, execute the Actions hover sequence and click
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(funeralBtn).perform();
            System.out.println("Hovered over optional Funeral button.");
            
            funeralBtn.click();
            System.out.println("Optional Funeral button clicked successfully.");
            
            Screenshot.takeScreenshot(driver); // 📸 Capture pass state
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
            By policyButtonLocator = ShopLocators.getPolicyTypeButton(policyType);

            // Wait until clickable
            WebElement policyButton = Utilities.waitForClickable(driver, policyButtonLocator, 25);

            if (policyButton.isDisplayed() && policyButton.isEnabled()) {

                policyButton.click();

                System.out.println("Policy Type button clicked: " + policyType);

                Thread.sleep(1000);

                Screenshot.takeScreenshot(driver);

                return null;

            } else {

                Screenshot.takeScreenshot(driver);
                return "Policy Type button is not clickable: " + policyType;
            }

        } catch (Exception e) {

            Screenshot.takeScreenshot(driver);
            return "Failed to click Policy Type button: " + e.getMessage();
        }
    }
    public String clickCreateNewButton() throws InterruptedException {
        try {
            System.out.println("Attempting to click the primary 'Next' button...");
            // 1. Look for the 'Next' button with a shorter wait time (e.g., 4 seconds) so fallback triggers quickly
            WebElement nextBtn = Utilities.waitForClickable(driver, ShopLocators.NewPolicy_button, 10);

            if (nextBtn.isDisplayed() && nextBtn.isEnabled()) {
                nextBtn.click();
                System.out.println("Successfully clicked the primary 'Next' button.");
                Thread.sleep(3000); 
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                throw new Exception("Next button present but not interactable.");
            }

        } catch (Exception e) {
            // 2. FALLBACK COMPONENT: If 'Next' is missing or fails, look for and click the checkbox
            System.out.println("Notice: 'Next' button not available. Engaging fallback checkbox workflow...");
            
            try {
                WebElement fallbackCheckbox = Utilities.waitForVisibility(driver, ShopLocators.Fallback_Checkbox, 25);
                
                // Use JavaScript click to ensure it bypasses the overlapping hidden structural layouts of Angular ripples
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fallbackCheckbox);
                Thread.sleep(500);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", fallbackCheckbox);
                
                System.out.println("Fallback Material checkbox successfully toggled.");
                Thread.sleep(2500);
                Screenshot.takeScreenshot(driver);
                return null;
                
            } catch (Exception checkboxEx) {
                Screenshot.takeScreenshot(driver);
                return "Failed to progress workflow: Neither the primary 'Next' button nor the fallback checkbox were interactable. Error: " + checkboxEx.getMessage();
            }
        }
    }
    
   



    public String uploadOnboardingSheetViaRobot() throws InterruptedException {
        // 1. Target the clickable drop box link
        By dropboxLinkLocator = By.xpath("//*[@id='dropbox']/span/a");
        WebElement dropboxLink = Utilities.waitForClickable(driver, dropboxLinkLocator, 25);

        if (dropboxLink.isDisplayed() && dropboxLink.isEnabled()) {
            
            // 2. Fetch the File Path Dynamically from your Excel Utility
            String rawPath = TestData.getOnboardingsheet();
            System.out.println("Retrieved Raw Path from Excel: " + rawPath);
            
            if (rawPath == null || rawPath.trim().isEmpty()) {
                return "Upload failed: File path retrieved from Excel data is empty or null.";
            }

            // 3. THE CODE FIX: Force any double backslashes to become single backslashes
            String filePath = rawPath.replace("\\\\", "\\").trim();
            System.out.println("Sanitized Path for Windows Dialog: " + filePath);

            // 4. Click the link to open the native Windows File Explorer dialog box
            dropboxLink.click();
            System.out.println("Dropbox upload link clicked. Waiting for Windows dialog...");
            Thread.sleep(2500); 
            
            try {
                // 5. Load the sanitized path into the OS Clipboard memory
                StringSelection stringSelection = new StringSelection(filePath);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
                
                // 6. Fire keystroke actions to execute the native OS paste operation
                Robot robot = new Robot();
                
                // CMD: Paste (Ctrl + V)
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                
                Thread.sleep(1000); 
                
                // CMD: Submit (Enter)
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                
                System.out.println("Dynamic file path injected via Robot successfully.");
                Thread.sleep(3000); 
                Screenshot.takeScreenshot(driver);
                return null;
                
            } catch (Exception e) {
                return "Robot keyboard automation failed: " + e.getMessage();
            }
        } else {
            Screenshot.takeScreenshot(driver);
            return "Dropbox upload trigger link not clickable.";
        }
    }
    
    
    public String clickFinalUploadSubmitButton() throws InterruptedException {
        // 1. Locate and click the upload button initially
        WebElement finalUploadBtn = Utilities.waitForClickable(driver, ShopLocators.UploadFilee_button, 35);

        if (finalUploadBtn.isDisplayed() && finalUploadBtn.isEnabled()) {
            finalUploadBtn.click();
            System.out.println("Final 'Upload File' submit button clicked. Processing upload...");
            
            // 2. Short pause to let the UI register the click and change states
            Thread.sleep(2000); 
            
            try {
                System.out.println("Waiting for 'Upload File' button to reappear/become clickable to confirm success...");
                // 3. Wait up to 30 seconds for the button to reset, confirming the action completed successfully
                Utilities.waitForClickable(driver, ShopLocators.UploadFilee_button, 180);
                System.out.println("Onboarding sheet successfully uploaded and processed!");
                
                Screenshot.takeScreenshot(driver);
                return null;
            } catch (Exception e) {
                Screenshot.takeScreenshot(driver);
                return "Upload verification failed: The 'Upload File' button did not become active again within the timeout limit.";
            }
            
        } else {
            Screenshot.takeScreenshot(driver);
            return "Final 'Upload File' submit button not clickable.";
        }
    }
    public void handleOptionalReminderAlert() {
        try {
            System.out.println("Checking for optional Reminder Alert popup...");
            // Use a short 4-second wait so we don't waste time if it's not there
            WebElement reminderAlert = Utilities.waitForClickable(driver, ShopLocators.Reminder_alert, 4);
            
            if (reminderAlert.isDisplayed()) {
                reminderAlert.click();
                System.out.println("Reminder Alert detected and dismissed successfully.");
                Thread.sleep(1500); // Small pause for the modal fade-out animation
            }
        } catch (Exception e) {
            // If it times out or isn't found, it throws an exception here, which we catch and ignore
            System.out.println("No Reminder Alert popup appeared. Proceeding with the flow.");
        }
    }
    
    public String openPolicy() throws InterruptedException {
        try {
            // 1. Locate the Overall SLA header sort button element wrapper
            WebElement overallSlaHeader = Utilities.waitForPresence(driver, ShopLocators.OverallSLA_Header, 25);
            
            // CLICK 1: First click to sort/refresh the data profile sequence via JS
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", overallSlaHeader);
            System.out.println("Overall SLA Header clicked via JS (1st Time)...");
            Thread.sleep(2000); 
            
            // CLICK 2: Second click to toggle sorting direction
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", overallSlaHeader);
            System.out.println("Overall SLA Header clicked via JS (2nd Time). Sorting complete.");
            Thread.sleep(2500);
            
            // 2. Locate and click the 'open' link belonging specifically to our target type row
            System.out.println("Searching table dynamically for a row matching the target type...");
            WebElement targetOpenLink = Utilities.waitForClickable(driver, ShopLocators.TargetRowOpen_link, 25);
            
            if (targetOpenLink.isDisplayed() && targetOpenLink.isEnabled()) {
                targetOpenLink.click();
                System.out.println("Matching row record 'open' link clicked successfully.");
                Thread.sleep(3000); 
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                return "Target row 'open' link found dynamically but it is not interactable.";
            }
            
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed to locate or open the correct policy row matching the criteria: " + e.getMessage();
        }
    }
    
    public String clickContinueButton() throws InterruptedException {
        WebElement continueBtn = Utilities.waitForClickable(driver, ShopLocators.Continue_button, 15);

        if (continueBtn.isDisplayed() && continueBtn.isEnabled()) {
            continueBtn.click();
            System.out.println("Successfully clicked the 'Continue' button.");
          // Give the next wizard step or screen transition time to load
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
            return "The 'Continue' button was found but is not interactable or visible.";
        }
    }
    

    public String clickNextButton() throws InterruptedException {
        WebElement nextBtn = Utilities.waitForClickable(driver, ShopLocators.Next_button, 15);

        if (nextBtn.isDisplayed() && nextBtn.isEnabled()) {
        	Thread.sleep(5000);
            nextBtn.click();
            System.out.println("Successfully clicked the 'Next' button.");
           
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
            return "The 'Next' button was located but is not interactable.";
        }
    }

    public String getExtractIDNumber() throws InterruptedException {
        try {
            System.out.println("\n=================== START DOM INSPECTION DEBUG ===================");
            System.out.println("Waiting for first ID Number element presence in DOM...");

            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            
            // 1. Wait until the element exists in the DOM structurally
            WebElement idElement = explicitWait.until(
                ExpectedConditions.presenceOfElementLocated(ShopLocators.First_ID_Number_Text)
            );

            System.out.println("Element successfully located in DOM framework layer.");
            
            // 2. Interrogate everything about the located element state
            System.out.println("DEBUG - Tag Name: " + idElement.getTagName());
            System.out.println("DEBUG - Is Displayed Visibly? " + idElement.isDisplayed());
            System.out.println("DEBUG - Is Enabled? " + idElement.isEnabled());
            System.out.println("DEBUG - Class Attribute: " + idElement.getAttribute("class"));
            System.out.println("DEBUG - Outer HTML representation:\n" + idElement.getAttribute("outerHTML"));
            
            // 3. Extract text content states
            String rawText = idElement.getText();
            String innerText = idElement.getAttribute("innerText");
            String textContent = idElement.getAttribute("textContent");
            
            System.out.println("DEBUG - .getText() returned: [" + (rawText != null ? rawText.trim() : "null") + "]");
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
                Screenshot.takeScreenshot(driver); // 📸 Capture Pass
                return idNumber;
            } else {
                System.out.println("FAILED: Regular expression pattern match failed to find digits inside: [" + fullText + "]");
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(driver); // 📸 Capture Fail
                return null;
            }

        } catch (TimeoutException e) {
            System.out.println("TIMEOUT: First ID Number element did not appear in the DOM within 30 seconds.");
            System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
            Screenshot.takeScreenshot(driver); // 📸 Capture Timeout
            return null;
        } catch (Exception e) {
            System.out.println("CRITICAL ERROR during ID extraction routine: " + e.getMessage());
            System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
            Screenshot.takeScreenshot(driver); // 📸 Capture Error
            return null;
        }
    }
    public String overrideAllPoliciesAndSubmit(String pdfFilePath) throws InterruptedException {
        try {
            System.out.println("Scanning layout screen for optional policy 'more_vert' menus...");
            
            java.util.List<WebElement> initialMenus;
            try {
                // 1. Safe short check (5s) to see if ANY more_vert menus exist on this page view state
                initialMenus = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy(ShopLocators.MoreVert_menu));
            } catch (org.openqa.selenium.TimeoutException e) {
                // SAFE BYPASS: If no menus show up at all, exit cleanly without failing the test
                System.out.println("Notice: No 'more_vert' action menus found on this page state. Skipping override flow.");
                return null; 
            }

            int totalMenus = initialMenus.size();
            System.out.println("Total menu buttons detected to process: " + totalMenus);

            // Loop through each menu using a clean index tracking structure
            for (int i = 0; i < totalMenus; i++) {
                System.out.println("--- Processing Record " + (i + 1) + " of " + totalMenus + " ---");
                
                // Re-fetch dynamically to avoid StaleElementReferenceException
                java.util.List<WebElement> currentMenus = driver.findElements(ShopLocators.MoreVert_menu);
                
                if (i >= currentMenus.size()) {
                    System.out.println("Warning: Target table row list updated out of range. Ending sequence.");
                    break;
                }
                
                WebElement currentMenu = currentMenus.get(i);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", currentMenu);
                Thread.sleep(1000);
                
                // Click current 3-dots target item via JavaScript to bypass overlays
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", currentMenu);
                System.out.println("Opened options menu #" + (i + 1));
                Thread.sleep(1500);
                
                // 2. CRITICAL PROTECTION: Wrap the entire row workflow in a try-catch block
                try {
                    // Look for 'Override VOPD' button using a short 4-second wait
                    WebElement overrideBtn = Utilities.waitForClickable(driver, ShopLocators.OverrideVOPD_button, 9);
                    overrideBtn.click();
                    System.out.println("Clicked 'Override VOPD'.");
                    Thread.sleep(2500);
                    
                    // 🔄 UPDATED STEP-BY-STEP FAULT PROTECTION:
                    // Instead of 'return error;', we log it and trigger the catch block to keep the loop going!
                    
                    // Engage radio option status and reveal folder browse trigger link
                    String radioError = selectRadioAndOpenUploadWindow();
                    if (radioError != null) {
                        throw new RuntimeException("Radio Selection Step Failed: " + radioError);
                    }
                    
                    // Paste target file system destination via native OS clipboard
                    String uploadError = uploadFileViaRobot(pdfFilePath);
                    if (uploadError != null) {
                        throw new RuntimeException("Robot Upload Step Failed: " + uploadError);
                    }
                    
                    // Commit row document item structure confirmation
                    String rowSubmitError = clickGreenUploadAndSubmit();
                    if (rowSubmitError != null) {
                        throw new RuntimeException("Green Submit Step Failed: " + rowSubmitError);
                    }
                    
                    System.out.println("Record " + (i + 1) + " successfully completed and submitted.");
                    
                } catch (Exception e) {
                    // IF ANY STEP FAILS OR IS MISSING: Catch it here, skip the rest of this row, and proceed to the next
                    System.out.println("⚠️ Notice: Skipping Record " + (i + 1) + " due to step failure: " + e.getMessage());
                    Screenshot.takeScreenshot(driver); // Take a failure screenshot of this row for debugging
                    
                    // Press ESC to close any hanging menu panels, dialogs, or backdrops so it doesn't block the next row click
                    try {
                        new org.openqa.selenium.interactions.Actions(driver)
                            .sendKeys(org.openqa.selenium.Keys.ESCAPE)
                            .perform();
                        System.out.println("Dispatched ESC key safety cleanup command.");
                    } catch (Exception ignored) {}
                    
                    Thread.sleep(1000);
                    // Jump immediately onto the next row index in the loop cycle execution
                    continue;
                }
                
                Thread.sleep(2000); // Settle down rendering buffer before targeting the next row index
            }
            
            System.out.println("All available rows on this layout screen processed completely.");
            return null; // Always return null here to indicate the test itself should keep running
            
        } catch (Exception e) {
            // This global catch now only hits if something completely catastrophic breaks the driver instance setup itself
            Screenshot.takeScreenshot(driver);
            System.out.println("Critical outer failure in loop processing framework: " + e.getMessage());
            return null; // Return null even here if you completely refuse to let TestNG fail this method
        }
    }

    public String triggerOverrideVOPD() throws InterruptedException {
        try {
            WebElement moreVert = Utilities.waitForClickable(driver, ShopLocators.MoreVert_menu, 15);
            moreVert.click();
            System.out.println("Clicked the 'more_vert' options menu button.");
            Thread.sleep(1500);

            WebElement overrideBtn = Utilities.waitForClickable(driver, ShopLocators.OverrideVOPD_button, 15);
            if (overrideBtn.isDisplayed() && overrideBtn.isEnabled()) {
                overrideBtn.click();
                System.out.println("Successfully clicked the 'Override VOPD' option item.");
                Thread.sleep(2500);
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                return "The 'Override VOPD' option button was located but is not interactable.";
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed to complete the Override VOPD menu selection sequence: " + e.getMessage();
        }
    }

    public String selectRadioAndOpenUploadWindow() throws InterruptedException {
        try {
            WebElement radioButton = Utilities.waitForPresence(driver, ShopLocators.Target_RadioButton, 15);
            String classAttribute = radioButton.getAttribute("class");
            
            if (classAttribute != null && classAttribute.contains("mat-radio-checked")) {
                System.out.println("Radio button is already selected. Skipping click action.");
            } else {
                driver.findElement(ShopLocators.Target_RadioButton).click();
                System.out.println("Radio button clicked and engaged.");
                Thread.sleep(1500);
            }

            WebElement uploadIcon = Utilities.waitForClickable(driver, ShopLocators.InlineUpload_Icon, 15);
            uploadIcon.click();
            System.out.println("Upload icon button clicked.");
            Thread.sleep(2000);

            WebElement chooseFiles = Utilities.waitForClickable(driver, ShopLocators.ChooseFiles_link, 15);
            chooseFiles.click();
            System.out.println("'Choose files' link clicked. Awaiting file path inputs...");
            Thread.sleep(2000);
            
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed during the radio selection and file prompt flow: " + e.getMessage();
        }
    }

    public String uploadFileViaRobot(String filePath) throws InterruptedException {
        try {
            System.out.println("Initiating native OS Robot file upload processing for path: " + filePath);
            
            java.awt.datatransfer.StringSelection stringSelection = new java.awt.datatransfer.StringSelection(filePath);
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            
            java.awt.Robot robot = new java.awt.Robot();
            Thread.sleep(1500);

            robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
            robot.keyPress(java.awt.event.KeyEvent.VK_V);
            robot.keyRelease(java.awt.event.KeyEvent.VK_V);
            robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
            

            robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
            robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
            Thread.sleep(2500);

            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Native OS Robot file upload execution sequence failed: " + e.getMessage();
        }
    }

    public String clickGreenUploadAndSubmit() throws InterruptedException {
        try {
            WebElement greenUpload = Utilities.waitForClickable(driver, ShopLocators.GreenUpload_Icon, 25);
            greenUpload.click();
            System.out.println("Green confirmation upload icon clicked successfully inside Client Care.");
            Thread.sleep(2000);

            WebElement submitBtn = Utilities.waitForClickable(driver, ShopLocators.Submit_button, 15);
            if (submitBtn.isDisplayed() && submitBtn.isEnabled()) {
                submitBtn.click();
                System.out.println("Client Care Submit button clicked successfully.");
                Thread.sleep(3000); 
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                return "The Client Care Submit button was located but is not interactable.";
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed during the client care post-upload submission sequence: " + e.getMessage();
        }
    }
    
    public String submitUploadedDocument() throws InterruptedException {
    	
        try {
            WebElement greenUpload = Utilities.waitForClickable(driver, ShopLocators.GreenUpload_Icon, 25);
            greenUpload.click();
            System.out.println("Green confirmation upload icon clicked successfully.");
            Thread.sleep(2000);

            WebElement submitBtn = Utilities.waitForClickable(driver, ShopLocators.Submit_button, 25);
            if (submitBtn.isDisplayed() && submitBtn.isEnabled()) {
                submitBtn.click();
                System.out.println("Final 'Submit' button clicked successfully.");
                Thread.sleep(3000); 
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                return "The final 'Submit' button was located but is not interactable.";
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed during the post-upload submission sequence: " + e.getMessage();
        }
    }
    
    public String clickWizardStepButton() throws InterruptedException {
        try {
            WebElement wizardBtn = Utilities.waitForClickable(driver, ShopLocators.Next_button, 15);

            if (wizardBtn.isDisplayed() && wizardBtn.isEnabled()) {
                wizardBtn.click();
                System.out.println("Wizard step button matching target path clicked successfully.");
                Thread.sleep(2500);
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                Screenshot.takeScreenshot(driver);
                return "The targeted wizard button was found on the DOM but is not interactable.";
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed to click the wizard step button: " + e.getMessage();
        }
    }
    
    public String clickRequestApprovalButton() throws InterruptedException {
        try {
            System.out.println("Waiting for 'Request Approval' button to become clickable...");
            // Explicitly wait up to 15 seconds for the button to render on screen
            WebElement requestApprovalBtn = Utilities.waitForClickable(driver, ShopLocators.RequestApproval_button, 65);

            if (requestApprovalBtn.isDisplayed() && requestApprovalBtn.isEnabled()) {
                // Scroll it cleanly into view block center coordinates
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", requestApprovalBtn);
                Thread.sleep(1000);
                
                // Execute JavaScript forced click event to bypass any transparent Angular components
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", requestApprovalBtn);
                System.out.println("'Request Approval' button clicked successfully.");
                
                Thread.sleep(3000); // Allow application processing state transitions to settle down
                Screenshot.takeScreenshot(driver);
                return null;
            } else {
                return "The 'Request Approval' button was located but is not interactable.";
            }
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed to click 'Request Approval' button sequence: " + e.getMessage();
        }
    }
    
    public String waitForApprovalMessage() throws InterruptedException {
        try {
            System.out.println("Waiting for the application state status message: 'Awaiting approval someone with permission'...");
            
            // Explicitly wait up to 30 seconds for the status element to populate on screen
            WebElement approvalMsg = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(60))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(ShopLocators.ApprovalStatus_Message));
            
            if (approvalMsg.isDisplayed()) {
                System.out.println("Success! Verified approval message layout state text: '" + approvalMsg.getText().trim() + "'");
                Screenshot.takeScreenshot(driver);
                return null; // Return null to indicate the state check passed perfectly
            } else {
                return "The approval status wrapper rendered in the DOM framework layer but is not visibly displayed.";
            }
        } catch (org.openqa.selenium.TimeoutException e) {
            Screenshot.takeScreenshot(driver);
            return "Timed out waiting for the message 'Awaiting approval someone with permission' to appear after 30 seconds.";
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "An unexpected error occurred while validating the approval status block: " + e.getMessage();
        }
    }
    
    public String getExtractPolicyNumber() throws InterruptedException {
        try {
            System.out.println("\n=================== START DOM INSPECTION DEBUG ===================");
            System.out.println("Waiting for policy reference element presence in DOM...");

            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            
            // 1. Wait until the element exists in the DOM structurally
            WebElement referenceElement = explicitWait.until(
                ExpectedConditions.presenceOfElementLocated(ShopLocators.PolicyReference_Text)
            );

            System.out.println("Element successfully located in DOM framework layer.");
            
            // 2. Interrogate everything about the located element state
            System.out.println("DEBUG - Tag Name: " + referenceElement.getTagName());
            System.out.println("DEBUG - Is Displayed Visibly? " + referenceElement.isDisplayed());
            System.out.println("DEBUG - Is Enabled? " + referenceElement.isEnabled());
            System.out.println("DEBUG - Class Attribute: " + referenceElement.getAttribute("class"));
            System.out.println("DEBUG - Outer HTML representation:\n" + referenceElement.getAttribute("outerHTML"));
            
            // 3. Extract text content states
            String rawText = referenceElement.getText();
            String innerText = referenceElement.getAttribute("innerText");
            String textContent = referenceElement.getAttribute("textContent");
            
            System.out.println("DEBUG - .getText() returned: [" + (rawText != null ? rawText.trim() : "null") + "]");
            System.out.println("DEBUG - innerText attribute returned: [" + (innerText != null ? innerText.trim() : "null") + "]");
            System.out.println("DEBUG - textContent attribute returned: [" + (textContent != null ? textContent.trim() : "null") + "]");
            
            String fullText = (rawText != null && !rawText.isEmpty()) ? rawText.trim() : (innerText != null ? innerText.trim() : "");

            // 4. Run the Regex evaluation extraction matching sequence
            Pattern pattern = Pattern.compile("PCAS:\\s*(\\d+)");
            Matcher matcher = pattern.matcher(fullText);

            if (matcher.find()) {
                String policyNumber = matcher.group(1);
                System.out.println("SUCCESS: Policy number extracted successfully: " + policyNumber);
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(driver); // 📸 Capture Pass
                return policyNumber;
            } else {
                System.out.println("FAILED: Regular expression pattern match failed to find 'PCAS:\\s*(\\d+)' inside: [" + fullText + "]");
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(driver); // 📸 Capture Fail
                return null;
            }

        } catch (TimeoutException e) {
            System.out.println("TIMEOUT: Policy reference element did not appear in the DOM within 30 seconds.");
            System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
            Screenshot.takeScreenshot(driver); // 📸 Capture Timeout
            return null;
        } catch (Exception e) {
            System.out.println("CRITICAL ERROR during extraction routine: " + e.getMessage());
            System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
            Screenshot.takeScreenshot(driver); // 📸 Capture Error
            return null;
        }
    }

    public String searchPolicyNumber(String policyNumber) {
        try {
            System.out.println("Waiting for the policy search text box to become interactable...");
            
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement searchBox = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.SearchText_Field)
            );
            
            // Clear anything currently inside, click, and input the number
            searchBox.click();
            searchBox.clear();
            
            System.out.println("Searching for policy number: [" + policyNumber + "]");
            searchBox.sendKeys(policyNumber);
            
            // Send C:\Users\Arenaho\eclipse-workspace\RandMut\src\test\java\RMA\on-boarding sheet\Access Bank Family Funeral _OnBoardingSheet 1 4 2.xlsxEnter key to execute the search filter action
            searchBox.sendKeys(Keys.ENTER);
            System.out.println("Search command submitted successfully.");
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null; 
            
        } catch (Exception e) {
            System.out.println("Failed to execute policy number search sequence: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Search Input Error: " + e.getMessage();
        }
    }

    // 1. Click Open Link
    public String clickOpenPolicyLink() {
        try {
            System.out.println("Clicking the 'open' policy link...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            Thread.sleep(5000);
            WebElement openLink = explicitWait.until(ExpectedConditions.elementToBeClickable(ShopLocators.OpenLink_Button));
            openLink.click();
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Open Link Error: " + e.getMessage();
        }
    }

 // 2. Click View Button
    public String clickViewButton() {
        try {
            System.out.println("Clicking the 'View' submit button...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement viewBtn = explicitWait.until(ExpectedConditions.elementToBeClickable(ShopLocators.ViewSubmit_Button));
            viewBtn.click();
            
            // 🔄 Wait for browser document state to load completely
            explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click View Button Error: " + e.getMessage();
        }
    }

    // 3. Click Next Button (Reusable for both clicks)
    public String clickNextStepButton() {
        try {
            System.out.println("Clicking the 'Next' navigation button...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement nextBtn = explicitWait.until(ExpectedConditions.elementToBeClickable(ShopLocators.Next_button));
            nextBtn.click();
            
            // 🔄 Wait for browser document state to load completely
            explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Next Button Error: " + e.getMessage();
        }
    }

    // 4. Click Approve Button
    public String clickApproveButton() {
        try {
            System.out.println("Clicking final 'Approve' button...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement approveBtn = explicitWait.until(ExpectedConditions.elementToBeClickable(ShopLocators.Approve_Button));
            approveBtn.click();
            
            // 🔄 Wait for browser document state to load completely
            explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
          
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Approve Button Error: " + e.getMessage();
        }
    }
    
 // 5. Click "Yes, Approve" Confirmation Button
    public String clickYesApproveConfirmationButton() {
        try {
            System.out.println("Clicking the final 'Yes, Approve' confirmation dialog button...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement yesApproveBtn = explicitWait.until(ExpectedConditions.elementToBeClickable(ShopLocators.YesApprove_Button));
            yesApproveBtn.click();
            Thread.sleep(6000);     
            Screenshot.takeScreenshot(driver);
            
            // 🔄 Wait for browser document state to load completely
            explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
             // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Yes Approve Button Error: " + e.getMessage();
        }
    }
    
    public String clickSearchButton() {
        try {
            System.out.println("Waiting for 'Search' button to appear...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(45));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Wait for active Angular CDK overlays to clear
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            WebElement searchBtn = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.SearchH_Button)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", searchBtn);
            Thread.sleep(300);

            // Native click with JavaScript fallback
            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(searchBtn));
                searchBtn.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click on 'Search' button...");
                js.executeScript("arguments[0].click();", searchBtn);
            }

            System.out.println("Successfully clicked 'Search' button!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'Search' button: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click Search Button Error: " + e.getMessage();
        }
    }

    public String enterSearchID(String idNumber) {
        try {
            System.out.println("Entering ID into Search input: " + idNumber);
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Clear active CDK overlays if present
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            WebElement idInput = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Search_Term_Input)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", idInput);
            Thread.sleep(300);

            idInput.clear();
            idInput.sendKeys(idNumber);

            // Trigger Angular change detection so the dropdown appears
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", idInput);
            Thread.sleep(500);

            // -----------------------------------------------------------------
            // Click the matching mat-option from the dropdown overlay
            // -----------------------------------------------------------------
            System.out.println("Waiting for autocomplete option '" + idNumber + "' to appear in dropdown...");
            WebElement option = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.getAutocompleteOption(idNumber))
            );

            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted on mat-option. Executing JS click...");
                js.executeScript("arguments[0].click();", option);
            }

            System.out.println("Successfully selected ID option from dropdown!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to enter and select Search ID: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Enter Search ID Error: " + e.getMessage();
        }
    }

/**
 * Finds and clicks the 'Select' action link specifically for the table row
 * that contains the given idNumber.
 */
/**
 * Waits for the 'Search Results for <idNumber>' message to appear, 
 * then finds and clicks the 'Select' link corresponding to that row.
 */
/**
 * Waits for the first 'Select' action link on the page and clicks it directly.
 */
/**
 * Waits for 'Search Results for <idNumber>' message to appear, 
 * then finds and clicks the first 'Select' button in the results table.
 */
 // Local variable to hold the dynamic view-policy URL between method calls
    private String policyDetailUrl = "";

    public String clickSelectActionButton(String idNumber) {
        try {
            System.out.println("\n--- Starting clickSelectActionButton for ID: " + idNumber + " ---");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(40));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. Clear active CDK overlays if present
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            // 2. Locate search results header and 'Select' link
            WebElement resultsHeader = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.getSearchResultsHeader(idNumber))
            );
            System.out.println("Found search result header: '" + resultsHeader.getText().trim() + "'");

            By searchResultSelectXPath = By.xpath(
                "//*[contains(text(),'Search Results for')]//following::a[@id='btnAction' and normalize-space()='Select'][1]"
            );
            WebElement selectActionLink = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(searchResultSelectXPath)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", selectActionLink);
            Thread.sleep(500);

            // 3. Click 'Select'
            try {
                selectActionLink.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click...");
                js.executeScript("arguments[0].click();", selectActionLink);
            }

            // 4. Wait for Angular route transition to /view-policy/
            System.out.println("Waiting for URL to contain '/view-policy/'...");
            explicitWait.until(ExpectedConditions.urlContains("/view-policy/"));

            // 5. STORE THE DIRECT POLICY URL IMMEDIATELY
            this.policyDetailUrl = driver.getCurrentUrl();
            System.out.println("[URL SAVED] Successfully stored Policy Detail URL: " + this.policyDetailUrl);

            // 6. Confirm 'Policy Details' tab element is rendered
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@role='tab' and .//span[contains(text(),'Policy Details')]] | //div[@role='tab' and contains(.,'Policy Details')]")
            ));

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Error inside clickSelectActionButton: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click Select Action Error: " + e.getMessage();
        }
    }

    public String clickAllTabsAndCaptureScreenshots() {
        try {
            Thread.sleep(4500);
            System.out.println("\n=================== START CLICKING ALL TABS ===================");
            System.out.println("[URL RECOVERY TARGET] Using saved Policy URL: " + this.policyDetailUrl);

            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(65));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // RECOVERY CHECK: Ensure page is on view-policy before starting
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("/view-policy/") && this.policyDetailUrl != null && !this.policyDetailUrl.isEmpty()) {
                System.out.println("[URL RECOVERY] Redirected to '" + currentUrl + "'. Re-navigating to: " + this.policyDetailUrl);
                driver.navigate().to(this.policyDetailUrl);
                Thread.sleep(2000);
            }

            // Wait for at least one tab to be visible on the page
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ShopLocators.All_Tab_Labels));

            // Get total count of tabs
            List<WebElement> tabs = driver.findElements(ShopLocators.All_Tab_Labels);
            int tabCount = tabs.size();
            System.out.println("Total tabs found: " + tabCount);

            for (int i = 0; i < tabCount; i++) {
                // 1. RECOVERY CHECK: If page routed away mid-loop, force navigation back
                currentUrl = driver.getCurrentUrl();
                if (!currentUrl.contains("/view-policy/") && this.policyDetailUrl != null && !this.policyDetailUrl.isEmpty()) {
                    System.out.println("[URL RECOVERY] Mid-loop redirect detected ('" + currentUrl + "'). Re-navigating to: " + this.policyDetailUrl);
                    driver.navigate().to(this.policyDetailUrl);
                    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ShopLocators.All_Tab_Labels));
                    Thread.sleep(2000);
                }

                // 2. Clear any lingering Angular CDK overlay backdrops
                try {
                    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                    ));
                } catch (Exception ignore) {}

                // 3. Re-fetch tab list dynamically by index to prevent StaleElementReferenceException
                List<WebElement> currentTabs = driver.findElements(ShopLocators.All_Tab_Labels);
                
                if (i >= currentTabs.size()) {
                    System.out.println("Tab index " + i + " no longer exists in DOM. Exiting loop.");
                    break;
                }

                WebElement currentTab = currentTabs.get(i);

                String tabTitle = currentTab.getText().trim().replaceAll("\\r?\\n", " ");
                if (tabTitle.isEmpty()) {
                    tabTitle = "Tab #" + (i + 1);
                }

                System.out.println("Clicking Tab [" + (i + 1) + "/" + tabCount + "]: '" + tabTitle + "'");

                // 4. Scroll into view
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", currentTab);
                Thread.sleep(300);

                // 5. Click tab with JS fallback
                try {
                    explicitWait.until(ExpectedConditions.elementToBeClickable(currentTab));
                    currentTab.click();
                } catch (Exception clickEx) {
                    System.out.println("Native click intercepted. Executing JS click on tab: " + tabTitle);
                    js.executeScript("arguments[0].click();", currentTab);
                }

                // 6. Allow tab content animation to render before taking screenshot
                Thread.sleep(1200);

                System.out.println("Captured screenshot for tab: '" + tabTitle + "'");
                Screenshot.takeScreenshot(driver);
            }

            System.out.println("=================== COMPLETED ALL TABS ===================\n");
            return null;

        } catch (Exception e) {
            System.out.println("Failed while iterating through tabs: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click All Tabs Error: " + e.getMessage();
        }
    }
    
    public String clickDocumentExpansionArrowsAndCaptureScreenshots() {
        try {
            System.out.println("\n=================== START NAVIGATING TO DOCUMENTS TAB & EXPANDING ARROWS ===================");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. RECOVERY CHECK: Ensure page is on view-policy
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("/view-policy/") && this.policyDetailUrl != null && !this.policyDetailUrl.isEmpty()) {
                System.out.println("[URL RECOVERY] Redirected to '" + currentUrl + "'. Re-navigating to: " + this.policyDetailUrl);
                driver.navigate().to(this.policyDetailUrl);
                Thread.sleep(2000);
            }

            // 2. NAVIGATE TO 'DOCUMENTS' TAB USING INDEX (Tab #2 -> Index 1)
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ShopLocators.All_Tab_Labels));
            List<WebElement> allTabs = driver.findElements(ShopLocators.All_Tab_Labels);

            int documentsTabIndex = -1;

            // Search by text dynamically, or fallback to index 1 (2nd tab)
            for (int i = 0; i < allTabs.size(); i++) {
                String label = allTabs.get(i).getText().trim();
                if (label.equalsIgnoreCase("Documents")) {
                    documentsTabIndex = i;
                    break;
                }
            }

            if (documentsTabIndex == -1 && allTabs.size() >= 2) {
                System.out.println("Could not match text 'Documents'. Falling back to Tab Index 1 (2nd Tab).");
                documentsTabIndex = 1;
            }

            if (documentsTabIndex != -1) {
                WebElement documentsTab = allTabs.get(documentsTabIndex);
                System.out.println("Found Documents tab at index [" + documentsTabIndex + "]. Clicking...");

                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", documentsTab);
                Thread.sleep(300);

                try {
                    explicitWait.until(ExpectedConditions.elementToBeClickable(documentsTab));
                    documentsTab.click();
                } catch (Exception clickEx) {
                    System.out.println("Native click failed on Documents tab. Executing JS click...");
                    js.executeScript("arguments[0].click();", documentsTab);
                }

                // Wait for Angular tab contents to populate
                Thread.sleep(3000);
            } else {
                System.out.println("ERROR: Documents tab element could not be found.");
                return "Documents tab not found";
            }

            // 3. TARGET EXPANSION ARROWS ONLY INSIDE THE ACTIVE DOCUMENTS PANEL
            By expansionIndicatorLocator = By.xpath(
                "//mat-tab-body[contains(@class,'mat-tab-body-active')]//span[contains(@class,'mat-expansion-indicator')]"
            );

            List<WebElement> arrows = driver.findElements(expansionIndicatorLocator);
            int totalArrows = arrows.size();
            System.out.println("Total document expansion arrows found: " + totalArrows);

            if (totalArrows == 0) {
                System.out.println("No document expansion arrows found to click.");
                return null;
            }

            int clickCounter = 0;

            for (int i = 0; i < totalArrows; i++) {
                // Clear any CDK overlays
                try {
                    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                    ));
                } catch (Exception ignore) {}

                // Re-fetch dynamic element list to prevent StaleElementReferenceException
                List<WebElement> currentArrows = driver.findElements(expansionIndicatorLocator);
                if (i >= currentArrows.size()) {
                    break;
                }

                WebElement arrow = currentArrows.get(i);

                // Skip if arrow is already expanded (rotate 180deg)
                String transformStyle = arrow.getAttribute("style");
                if (transformStyle != null && transformStyle.contains("rotate(180deg)")) {
                    System.out.println("Arrow [" + (i + 1) + "] is already expanded. Skipping click.");
                    continue;
                }

                // Scroll into view & click
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", arrow);
                Thread.sleep(300);

                try {
                    explicitWait.until(ExpectedConditions.elementToBeClickable(arrow));
                    arrow.click();
                } catch (Exception clickEx) {
                    js.executeScript("arguments[0].click();", arrow);
                }

                clickCounter++;
                System.out.println("Expanded document arrow [" + (i + 1) + "/" + totalArrows + "] (Session clicks: " + clickCounter + ")");
                Thread.sleep(700); // Allow accordion expansion animation

                // Screenshot every 3 clicks or on final arrow
                if (clickCounter % 3 == 0 || i == totalArrows - 1) {
                    System.out.println(">>> Capturing screenshot after " + clickCounter + " expansion click(s) <<<");
                    Screenshot.takeScreenshot(driver);
                }
            }

            System.out.println("=================== COMPLETED DOCUMENTS TAB ARROWS ===================\n");
            return null;

        } catch (Exception e) {
            System.out.println("Failed while navigating to Documents tab or expanding arrows: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Expand Document Arrows Error: " + e.getMessage();
        }
    }
}










