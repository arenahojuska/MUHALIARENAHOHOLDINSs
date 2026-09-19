package RMA;
import RMA.SAnewIdGen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ClaimCare {
    
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ClaimCare(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait; 
    }
    public String clickClaimCareButton() throws InterruptedException {
        try {
            System.out.println("Waiting for ClaimCare option button control layout to appear...");
            
            // Explicitly sync on visibility and interactability state using your ShopLocators configuration
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(45));
            WebElement claimCareBtn = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.ClaimCare_button)
            );

            if (claimCareBtn.isDisplayed() && claimCareBtn.isEnabled()) {
                claimCareBtn.click();
                System.out.println("ClaimCare interface action button clicked successfully.");
                
                // 🔄 Sync on document initialization loop context before finalizing snapshot capturing
                explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                    .executeScript("return document.readyState").equals("complete"));
                
                Screenshot.takeScreenshot(driver); // 📸 Capture Pass
                return null;
            } else {
                Screenshot.takeScreenshot(driver); // 📸 Capture Fail
                return "The ClaimCare interface target element was located but is not clickable.";
            }
        } catch (Exception e) {
            System.out.println("Exception handled in ClaimCare selection process flow: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Failed to complete ClaimCare action invocation sequence: " + e.getMessage();
        }
    }
    
 
    public String clickFuneralWorkPool() {
        try {
            System.out.println("Waiting for 'Funeral Work Pool' description workspace option to appear...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(45));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. Wait for any active Angular CDK overlays/backdrops to fade out
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {
                // Overlay wasn't present or already faded out
            }

            // 2. Wait until element is present and visible
            WebElement workPoolSpan = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.FuneralWorkPool_span)
            );
            
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", workPoolSpan);
            Thread.sleep(300);

            System.out.println("Funeral Work Pool item targeted. Triggering selection now...");

            // 3. Try standard click first; fall back to JS click if intercepted
            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(workPoolSpan));
                workPoolSpan.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted by overlay/backdrop. Executing JS click fallback...");
                js.executeScript("arguments[0].click();", workPoolSpan);
            }
            
            // 4. Wait for page/framework to settle completely
            explicitWait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
            Thread.sleep(1000);
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;
            
        } catch (Exception e) {
            System.out.println("Exception hit during Funeral Work Pool click sequence: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Funeral Work Pool Error: " + e.getMessage();
        }
    }
    
    /**
     * Synchronizes on and opens the Claims action dropdown menu view trigger panel.
     */
    public String clickClaimsDropdownButton() {
        try {
            System.out.println("Waiting for the 'Claims' dropdown root component button to load...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(25));
            
            WebElement claimsBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Claims_Dropdown_Trigger)
            );
            
            System.out.println("Claims trigger targeted. Expanding menu layer now...");
            claimsBtn.click();
            
            // Allow a short buffer window for the Angular Material overlay panel structure to inject into DOM
            Thread.sleep(1500);
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            System.out.println("Exception hit during Claims element click cycle: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Claims Dropdown Error: " + e.getMessage();
        }
    }
    /**
     * Selects 'Register Funeral Claim' from the active menu options list.
     */
    public String clickRegisterFuneralClaim() {
        try {
            System.out.println("Waiting for 'Register Funeral Claim' menu option to appear...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(25));
            
            WebElement menuItem = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.RegisterFuneralClaim_menuitem)
            );
            
            System.out.println("Menu item located. Clicking selection now...");
            menuItem.click();
            
            // 🔄 Synchronize completely with document rendering
            explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            System.out.println("Failed to click 'Register Funeral Claim' choice: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Register Funeral Claim Error: " + e.getMessage();
        }
    }
    /**
     * Pulls the ID value from the Excel data file, enters it into the query input field, 
     * and submits the search by firing a native ENTER key stroke events chain.
     */
    public String searchIdFromExcel() {
        try {
            // Fetch data cleanly from your data utility layout setup
            String searchIdValue = TestData.getId(); 
            System.out.println("Retrieved target identification search key from Excel matrix: [" + searchIdValue + "]");
            
            if (searchIdValue == null || searchIdValue.trim().isEmpty()) {
                return "Search Parameter Error: The value retrieved from the Excel dataset row is null or empty.";
            }

            System.out.println("Waiting for search query input box (#query) to appear...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(65));
            WebElement inputField = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Search_Input_Field)
            );
            
            // Interact cleanly with the element structure
            inputField.click();
            inputField.clear();
            inputField.sendKeys(searchIdValue);
            System.out.println("Search value injected. Dispatching system ENTER command payload...");
            
            // 🔄 Fire the key event sequence to trigger the application query handler
            inputField.sendKeys(org.openqa.selenium.Keys.ENTER);
            
            // Wait for network requests and DOM tables to redraw completely
            Thread.sleep(2500);
            explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
           
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;
            
        } catch (Exception e) {
            System.out.println("Exception intercepted during data search execution phase: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Search Identification Pipeline Error: " + e.getMessage();
        }
    }
    
    /**
     * Synchronizes on and clicks the 'Select' link button to proceed with the chosen record.
     */
    public String clickSelectActionLink() {
        try {
            System.out.println("Waiting for the 'Select' action link to become click-ready...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(180));
            
            WebElement selectLink = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Select_Action_Link)
            );
            
            System.out.println("Select link found. Clicking to confirm option entry...");
            selectLink.click();
            
            // Wait for the workspace view container to fully render
            explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
          
            return null;
        } catch (Exception e) {
            System.out.println("Exception hit during Select link click cycle: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Select Action Link Error: " + e.getMessage();
        }
    }
    
    /**
     * Opens the 'Type of Death' dropdown and selects the very first option available.
     */
    public String selectAnyTypeOfDeathOption() {
        try {
            System.out.println("Waiting for 'Type of Death' dropdown trigger (#filter) to appear...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(65));
            
            WebElement dropdown = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.TypeOfDeath_Dropdown)
            );
            
            System.out.println("Opening dropdown panel container...");
            dropdown.click();
            Thread.sleep(1000); // Wait for the Angular options overlay to animate open
            
            System.out.println("Fetching visible dropdown options...");
            java.util.List<WebElement> options = explicitWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(ShopLocators.Mat_Options)
            );
            
            if (options.isEmpty()) {
                return "Dropdown Selection Error: No mat-option elements were found inside the overlay panel.";
            }
            
            // Pick the first available option choice item dynamically
            WebElement targetOption = options.get(0);
            System.out.println("Selecting option index 0 text: [" + targetOption.getText().trim() + "]");
            targetOption.click();
            
            Thread.sleep(1000); // Sync animation pause
            return null;
        } catch (Exception e) {
            System.out.println("Exception inside selectAnyTypeOfDeathOption: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Dropdown Selection Failure: " + e.getMessage();
        }
    }

    /**
     * Opens the calendar component and selects today's date dynamically using the native class indicator.
     */
    /**
     * Loops through both the 'Date Notified' and 'Date of Death' calendar component dropdowns
     * and assigns today's active date into both form fields.
     */
    public String selectTodayForBothDatefields() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // 1. Process Date Notified Field
        try {
            System.out.println("Processing 'Date Notified' calendar toggle button...");
            WebElement notifiedBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.DateNotified_Calendar_Btn)
            );
            notifiedBtn.click();
            Thread.sleep(1200); // Allow overlay animation
            
            WebElement todayCell = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Calendar_Today_Cell)
            );
            todayCell.click();
            System.out.println("'Date Notified' updated to Today.");
            Thread.sleep(1000);
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed to set 'Date Notified' field: " + e.getMessage();
        }

        // 2. Process Date of Death Field
        try {
            System.out.println("Processing 'Date of Death' calendar toggle button...");
            WebElement deathBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.DateDeath_Calendar_Btn)
            );
            deathBtn.click();
            Thread.sleep(1200); // Allow overlay animation
            
            WebElement todayCell = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Calendar_Today_Cell)
            );
            todayCell.click();
            System.out.println("'Date of Death' updated to Today.");
            Thread.sleep(1000);
            
            Screenshot.takeScreenshot(driver); // 📸 Capture overall pass state
            return null;
        } catch (Exception e) {
            Screenshot.takeScreenshot(driver);
            return "Failed to set 'Date of Death' field: " + e.getMessage();
        }
    }
    public String clickNextButton() throws InterruptedException {
        WebElement nextBtn = Utilities.waitForClickable(driver, ShopLocators.Next_button, 15);

        if (nextBtn.isDisplayed() && nextBtn.isEnabled()) {
        	
            nextBtn.click();
            System.out.println("Successfully clicked the 'Next' button.");
           
            Screenshot.takeScreenshot(driver);
            return null;
        } else {
            Screenshot.takeScreenshot(driver);
            return "The 'Next' button was located but is not interactable.";
        }
    }
    
    /**
     * Synchronizes on and clicks the 'Register Notifier' action button.
     */
    public String clickRegisterNotifierButton() {
        try {
            System.out.println("Waiting for 'Register Notifier' button to become click-ready...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            
            WebElement registerBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.RegisterNotifier_button)
            );
            
            System.out.println("Register Notifier button found. Clicking now...");
            registerBtn.click();
            
            // Wait for DOM state rendering to finish
            explicitWait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
            
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;
        } catch (Exception e) {
            System.out.println("Exception hit during Register Notifier click sequence: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Register Notifier Error: " + e.getMessage();
        }
    }

    /**
     * Fills out the entire Notifier Information form, opens Angular Material dropdowns reliably,
     * and selects both Relation and Preferred Contact Method.
     */
    /**
     * Fills out the entire Notifier Information form.
     * Uses resilient Angular Material overlay triggers (native click + Keys.ENTER fallback)
     * and explicit waits on `.cdk-overlay-container`.
     */
    
    public String populateNotifierDetails() {
        try {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            System.out.println("Beginning Notifier Details form filling...");

            // 1. Enter First Name (John)
            WebElement firstName = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Notifier_FirstName_Input)
            );
            firstName.clear();
            firstName.sendKeys("John");

            // 2. Enter Last Name (Smith)
            WebElement lastName = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Notifier_LastName_Input)
            );
            lastName.clear();
            lastName.sendKeys("Smith");

            // 3. Generate and Enter ID Number using SA ID Generator
            String getIDRegister = SAnewIdGen.generateIdForAgeRange();
            System.out.println("Generated Claimant ID: [" + getIDRegister + "]");

            WebElement idInput = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Notifier_IdentityNumber_Input)
            );
            idInput.clear();
            idInput.sendKeys(getIDRegister);

            // 4. Mobile Number ("072" + 7 random digits = 10 digits total)
            long randomDigits = (long) (Math.random() * 9000000L) + 1000000L;
            String generatedMobile = "072" + randomDigits;
            System.out.println("Generated mobile contact number: " + generatedMobile);

            WebElement mobileInput = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Notifier_Mobile_Input)
            );
            mobileInput.clear();
            mobileInput.sendKeys(generatedMobile);

            // 5. Fetch and Enter Email from Excel Data Utility
            String adminEmail = TestData.getAdminEmail();
            System.out.println("Retrieved email from Excel: [" + adminEmail + "]");

            WebElement emailInput = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Notifier_Email_Input)
            );
            emailInput.clear();
            emailInput.sendKeys(adminEmail);

            // 6. Handle 'Relation To The Deceased' Dropdown
            System.out.println("Selecting 'Relation To The Deceased' option...");
            WebElement relationDropdown = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.RelationToDeceased_Dropdown)
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", relationDropdown);
            Thread.sleep(400);

            // Trigger dropdown open (click with ENTER fallback for Angular event listener)
            try {
                relationDropdown.click();
            } catch (Exception e) {
                relationDropdown.sendKeys(org.openqa.selenium.Keys.ENTER);
            }

            // Wait specifically for Angular Overlay container panel to attach to DOM
            explicitWait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cdk-overlay-container, .mat-select-panel"))
            );

            java.util.List<WebElement> options = explicitWait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(ShopLocators.Mat_Option_First)
            );

            if (!options.isEmpty()) {
                WebElement targetOption = options.get(0);
                js.executeScript("arguments[0].click();", targetOption);
                System.out.println("Selected relation option: [" + targetOption.getText().trim() + "]");
            }
            Thread.sleep(800);

            // 7. Handle 'Preferred Method Of Contact' Dropdown (Select Email)
            System.out.println("Selecting 'Preferred Method Of Contact' -> Email...");
            WebElement prefContactDropdown = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.PreferredMethodContact_Dropdown)
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", prefContactDropdown);
            Thread.sleep(400);

            try {
                prefContactDropdown.click();
            } catch (Exception e) {
                prefContactDropdown.sendKeys(org.openqa.selenium.Keys.ENTER);
            }

            // Wait for overlay panel
            explicitWait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cdk-overlay-container, .mat-select-panel"))
            );

            WebElement emailOption = explicitWait.until(
                ExpectedConditions.presenceOfElementLocated(ShopLocators.Mat_Option_Email)
            );
            js.executeScript("arguments[0].click();", emailOption);
            System.out.println("Selected 'Email' as preferred contact method.");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver); // 📸 Capture pass
            return null;

        } catch (Exception e) {
            System.out.println("Exception inside populateNotifierDetails: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture fail
            return "Notifier Details Population Failure: " + e.getMessage();
        }
    }
    /**
     * Clicks the final 'Register' button on the Notifier Information screen.
     */
    public String clickRegisterButton() {
        try {
            System.out.println("Waiting for the final 'Register' button to become clickable...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(340));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement registerBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Register_Final_Button)
            );

            // Scroll down into view and trigger click
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", registerBtn);
            

            registerBtn.click();
            System.out.println("Successfully clicked the 'Register' button.");

            // Wait briefly for page context/spinner to update
            Thread.sleep(500);
            Screenshot.takeScreenshot(driver); // 📸 Capture Pass
            return null;

        } catch (Exception e) {
            System.out.println("Exception hit while clicking Register button: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture Fail
            return "Click Register Button Error: " + e.getMessage();
        }
    }
    /**
     * Captures the generated Claim Number from the Confirmation screen table,
     * stores it in TestData, and clicks 'Done'.
     */
    /**
     * Extracts and stores the Claim Number from the Confirmation table using thorough DOM inspection logic.
     */
    /**
     * Extracts the Claim Number from the Confirmation table using thorough DOM inspection logic
     * and returns it directly without saving to TestData.
     */
    
        public String extractClaimNumber() throws InterruptedException {
            try {
                System.out.println("\n=================== START DOM INSPECTION DEBUG ===================");
                System.out.println("Waiting for Claim Number cell element presence in DOM...");

                WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(250));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                
                // 1. Wait until the cell element exists in the DOM structurally
                WebElement referenceElement = explicitWait.until(
                    ExpectedConditions.presenceOfElementLocated(ShopLocators.Confirmation_ClaimNumber_Cell)
                );

                System.out.println("Element successfully located in DOM framework layer.");
                
                // 2. Interrogate element state
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
                
                String fullText = (rawText != null && !rawText.trim().isEmpty()) ? rawText.trim() : 
                                   (innerText != null && !innerText.trim().isEmpty()) ? innerText.trim() : 
                                   (textContent != null ? textContent.trim() : "");

                // 4. Run Regex evaluation (Extract 6-10 digit numbers)
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(\\d{6,10})");
                java.util.regex.Matcher matcher = pattern.matcher(fullText);

                if (matcher.find()) {
                    String claimNumber = matcher.group(1);
                    System.out.println("SUCCESS: Claim number extracted successfully: " + claimNumber);
                    
                    // Click Done button to conclude step
                    try {
                        WebElement doneBtn = explicitWait.until(
                            ExpectedConditions.elementToBeClickable(ShopLocators.Confirmation_Done_Button)
                        );
                        
                        try {
                            doneBtn.click();
                        } catch (Exception clickEx) {
                            // Fallback JS click if standard click is intercepted by Angular overlay
                            js.executeScript("arguments[0].click();", doneBtn);
                        }
                        System.out.println("Clicked 'Done' button on Confirmation screen.");
                    } catch (Exception ex) {
                        System.out.println("Note: 'Done' button click bypassed or failed: " + ex.getMessage());
                    }

                    System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                    Screenshot.takeScreenshot(driver); // 📸 Capture Pass
                    return claimNumber;
                } else {
                    System.out.println("FAILED: Regular expression pattern failed to find digits inside: [" + fullText + "]");
                    System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                    Screenshot.takeScreenshot(driver); // 📸 Capture Fail
                    return null;
                }

            } catch (TimeoutException e) {
                System.out.println("TIMEOUT: Claim Number element did not appear in the DOM within 60 seconds.");
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(driver); // 📸 Capture Timeout
                return null;
            } catch (Exception e) {
                System.out.println("CRITICAL ERROR during extraction routine: " + e.getMessage());
                System.out.println("==================== END DOM INSPECTION DEBUG ====================\n");
                Screenshot.takeScreenshot(driver); // 📸 Capture Error
                return null;
            }
        }    /**
     * Types the extracted claim number into the search input box (name="searchWord")
     * and submits the search request.
     * 
     * @param claimNumber The claim number string extracted from previous steps
     * @return null if execution passes, or error message String if an exception occurs
     */
    public String searchClaimNumber(String claimNumber) {
        try {
            System.out.println("Initiating search for Claim Number: [" + claimNumber + "]...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(60));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. Locate search input element
            WebElement searchInput = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Search_Input_Fieldd)
            );

            // 2. Scroll into center view and ensure focus
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", searchInput);
            Thread.sleep(400);

            // 3. Clear existing text cleanly
            searchInput.clear();
            searchInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            searchInput.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);

            // 4. Type the Claim Number
            searchInput.sendKeys(claimNumber);
            System.out.println("Entered [" + claimNumber + "] into search field.");
            Thread.sleep(300);

            // 5. Submit Search (Click Search button or press ENTER)
            try {
            	
                java.util.List<WebElement> searchButtons = driver.findElements(ShopLocators.Search_Button);
                searchInput.sendKeys(org.openqa.selenium.Keys.ENTER);
                System.out.println("Pressed ENTER to submit search.");
                
            } catch (Exception ex) {
                searchInput.sendKeys(org.openqa.selenium.Keys.ENTER);
                System.out.println("Fallback: Sent ENTER key to search input field.");
            }

            Thread.sleep(2000); // Allow results grid to load
            Screenshot.takeScreenshot(driver); // 📸 Capture pass
            return null;

        } catch (Exception e) {
            System.out.println("Exception hit in searchClaimNumber: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture fail
            return "Search Claim Number Execution Error: " + e.getMessage();
        }
    }
    
    /**
     * Clicks the three-dots action icon ('more_vert') and selects 'View' from the Angular Material menu.
     * 
     * @return null if successful, error message string if an exception occurs
     */
    public String openMoreActionsAndView() {
        try {
            System.out.println("Opening 'more_vert' actions menu...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(95));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. Locate and click the 'more_vert' icon
            WebElement menuIcon = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.More_Actions_Menu_Icon)
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", menuIcon);
            Thread.sleep(300);

            try {
                menuIcon.click();
            } catch (Exception e) {
                // Fallback JS click if overlay intercepts standard click
                js.executeScript("arguments[0].click();", menuIcon);
            }
            System.out.println("Clicked 'more_vert' icon. Waiting for menu items...");

            // 2. Wait for the Angular Material menu overlay to appear and click 'View'
            WebElement viewButton = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Menu_Option_View)
            );

            try {
                viewButton.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", viewButton);
            }
            System.out.println("Successfully clicked 'View' menu option.");

            Thread.sleep(1500); // Allow view interface to render
            Screenshot.takeScreenshot(driver); // 📸 Capture pass
            return null;

        } catch (Exception e) {
            System.out.println("Exception inside openMoreActionsAndView: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture fail
            return "Actions Menu 'View' Click Failure: " + e.getMessage();
        }
    }
    
    public String clickMoreActionsMenu() {
        try {
            System.out.println("Waiting for 'more_vert' options menu icon...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(25));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Wait for any active Angular CDK overlays to clear
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            // Locate element
            WebElement moreIcon = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.More_Actions_Menu_Icon)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", moreIcon);
            Thread.sleep(300);

            // Click with fallback
            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(moreIcon));
                moreIcon.click();
            } catch (Exception clickEx) {
                System.out.println("Native click failed on 'more_vert' icon. Executing JS click fallback...");
                js.executeScript("arguments[0].click();", moreIcon);
            }

            Thread.sleep(500); // Allow popover menu to open
            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'more_vert' menu: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click More Actions Error: " + e.getMessage();
        }
    }
    /**
     * Clicks the 'Continue' button on the View/Details screen.
     * 
     * @return null if successful, error message string if an exception occurs
     */
    public String clickContinueButton() {
        try {
            System.out.println("Waiting for 'Continue' button to become clickable...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(35));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1. Locate the button
            WebElement continueBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Continue_Button)
            );

            // 2. Scroll into center view
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", continueBtn);
            Thread.sleep(400);

            // 3. Trigger Click (Standard click with JS fallback)
            try {
                continueBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", continueBtn);
            }

            System.out.println("Successfully clicked the 'Continue' button.");

             // Allow next step or workflow screen to load
            Screenshot.takeScreenshot(driver); // 📸 Capture pass
            return null;

        } catch (Exception e) {
            System.out.println("Exception inside clickContinueButton: " + e.getMessage());
            Screenshot.takeScreenshot(driver); // 📸 Capture fail
            return "Click Continue Button Failure: " + e.getMessage();
        }
    }
    
  
    /**
     * Finds all visible dropdowns on the page, selects Option 1 for each,
     * checks Medical Practitioner Opinion if present, scrolls to the bottom,
     * clicks 'Next' (capturing screenshots at each step), and submits 'Finish'.
     */
    /**
     * Selects Option 1 for all visible dropdowns, clicks all visible checkboxes,
     * scrolls to bottom, clicks 'Next' (taking screenshots), and clicks 'Finish'.
     */
    /**
     * Fills all checkboxes and dropdowns on STEP 1 ONLY.
     * For all subsequent steps, it only scrolls to the bottom, clicks Next
     * (capturing screenshots), and submits Finish when visible.
     */
    public String fillFormAndIterateToFinish() {
        try {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(25));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            int maxNextClicks = 10;

            for (int step = 1; step <= maxNextClicks; step++) {
                System.out.println("\n--- Processing Step #" + step + " ---");

                // =============================================================
                // RUN FIELD INTERACTIONS ON STEP 1 ONLY
                // =============================================================
                if (step == 1) {
                    System.out.println("Step 1 detected: Interacting with all checkboxes and dropdowns...");

                    // 1. Locate and click ALL visible checkboxes on Step 1
                    java.util.List<WebElement> checkboxes = driver.findElements(ShopLocators.All_Checkboxes);
                    System.out.println("Found " + checkboxes.size() + " checkbox element(s) on Step 1.");

                    for (int c = 0; c < checkboxes.size(); c++) {
                        checkboxes = driver.findElements(ShopLocators.All_Checkboxes);
                        WebElement cb = checkboxes.get(c);

                        if (cb.isDisplayed() && cb.isEnabled()) {
                            try {
                                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", cb);
                                Thread.sleep(200);

                                String isChecked = cb.getAttribute("aria-checked");
                                if (!"true".equals(isChecked)) {
                                    js.executeScript("arguments[0].click();", cb);
                                    System.out.println("Checked checkbox #" + (c + 1));
                                    Thread.sleep(300);
                                }
                            } catch (Exception ex) {
                                System.out.println("Could not toggle checkbox #" + (c + 1) + ": " + ex.getMessage());
                            }
                        }
                    }

                    // 2. Locate ALL visible dropdowns on Step 1 & select Option 1
                    java.util.List<WebElement> dropdowns = driver.findElements(ShopLocators.All_Mat_Selects);
                    System.out.println("Found " + dropdowns.size() + " dropdown element(s) on Step 1.");

                    for (int i = 0; i < dropdowns.size(); i++) {
                        dropdowns = driver.findElements(ShopLocators.All_Mat_Selects);
                        WebElement selectElem = dropdowns.get(i);

                        if (selectElem.isDisplayed() && selectElem.isEnabled()) {
                            try {
                                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", selectElem);
                                Thread.sleep(300);

                                try {
                                    selectElem.click();
                                } catch (Exception ex) {
                                    js.executeScript("arguments[0].click();", selectElem);
                                }

                                WebElement option1 = explicitWait.until(
                                    ExpectedConditions.elementToBeClickable(ShopLocators.Mat_Option_Index_1)
                                );
                                
                                try {
                                    option1.click();
                                } catch (Exception ex) {
                                    js.executeScript("arguments[0].click();", option1);
                                }

                                System.out.println("Successfully selected Option 1 for dropdown #" + (i + 1));
                                Thread.sleep(400);

                            } catch (Exception ex) {
                                System.out.println("Could not select option for dropdown #" + (i + 1) + ": " + ex.getMessage());
                            }
                        }
                    }
                } else {
                    System.out.println("Step " + step + " detected: Skipping field interactions (Step 1 only).");
                }

                // =============================================================
                // NAVIGATION (FOR ALL STEPS)
                // =============================================================

                // 3. Scroll to bottom of page
                System.out.println("Scrolling to bottom of page...");
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(600);

                // 4. Check if 'Finish' button is present & visible
                java.util.List<WebElement> finishButtons = driver.findElements(ShopLocators.Finish_Button);
                if (!finishButtons.isEmpty() && finishButtons.get(0).isDisplayed()) {
                    System.out.println("'Finish' button detected! Exiting Next-button loop.");
                    break;
                }

                // 5. Look for active 'Next' button & Click
                java.util.List<WebElement> nextButtons = driver.findElements(ShopLocators.Next_Button);
                if (!nextButtons.isEmpty() && nextButtons.get(0).isDisplayed() && nextButtons.get(0).isEnabled()) {
                    WebElement nextBtn = nextButtons.get(0);
                    System.out.println("Clicking 'Next' button...");

                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", nextBtn);
                    Thread.sleep(300);

                    try {
                        nextBtn.click();
                    } catch (Exception e) {
                        js.executeScript("arguments[0].click();", nextBtn);
                    }

                    Thread.sleep(1500); // Allow step transition

                    // 📸 Take screenshot after clicking Next
                    Screenshot.takeScreenshot(driver);
                    System.out.println("📸 Captured screenshot after Next step #" + step);

                } else {
                    System.out.println("No active 'Next' button found on step #" + step + " after scrolling.");
                    break;
                }
            }

            // =============================================================
            // FINAL STEP: SUBMIT FINISH
            // =============================================================
            System.out.println("Scrolling to bottom & waiting up to 15 seconds for 'Finish' button...");
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            WebElement finishButton = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Finish_Button)
            );

            explicitWait.until(ExpectedConditions.elementToBeClickable(finishButton));

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", finishButton);
            Thread.sleep(500);

            try {
                finishButton.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", finishButton);
            }

            System.out.println("Successfully clicked 'Finish' button!");
            Thread.sleep(2000);

            Screenshot.takeScreenshot(driver); // 📸 Final Pass Screenshot
            return null;

        } catch (TimeoutException e) {
            System.out.println("TIMEOUT: 'Finish' button did not appear within 15 seconds.");
            Screenshot.takeScreenshot(driver);
            return "Timeout waiting for Finish button to appear: " + e.getMessage();
        } catch (Exception e) {
            System.out.println("Exception inside fillFormAndIterateToFinish: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Form fill / Finish iteration error: " + e.getMessage();
        }
    }
    public String clickDecisionOption() {
        try {
            System.out.println("Waiting for 'Decision' menu option to appear...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(25));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Locate the Decision button inside the opened menu
            WebElement decisionBtn = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Decision_Menu_Option)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", decisionBtn);
            Thread.sleep(300);

            // Attempt native click with JavaScript fallback
            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(decisionBtn));
                decisionBtn.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click on 'Decision' option...");
                js.executeScript("arguments[0].click();", decisionBtn);
            }

            System.out.println("Successfully selected 'Decision' option!");
            Thread.sleep(1000); // Allow step transition/modal trigger

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'Decision' option: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click Decision Option Error: " + e.getMessage();
        }
    }
    
    /**
     * Dynamically finds and clicks whichever banking detail option is present:
     * "Add Banking Detail" or "Update Banking Detail".
     */
    /**
     * Executes the complete banking details and account verification workflow:
     * 1. Clicks Add/Update Banking Detail
     * 2. Clicks Yes
     * 3. Enters Account Number
     * 4. Clicks Select link
     * 5. Verifies account with random initials and provided ID number
     */
    /**
     * Executes the complete banking details and account verification workflow:
     * 1. Clicks Add/Update Banking Detail
     * 2. Clicks Yes
     * 3. Clicks Continue
     * 4. Enters Account Number
     * 5. Clicks Select link
     * 6. Verifies account with random initials and provided ID number
     */
    public String processBankingDetailsAndUpdateAccount(String unusedAccountNum, String idNumber) {
        try {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(40));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Clear Angular CDK overlays helper
            Runnable clearCDKOverlays = () -> {
                try {
                    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                    ));
                } catch (Exception ignore) {}
            };

            // Safe click helper
            java.util.function.Consumer<WebElement> safeClick = (element) -> {
                try {
                    explicitWait.until(ExpectedConditions.elementToBeClickable(element));
                    element.click();
                } catch (Exception clickEx) {
                    js.executeScript("arguments[0].click();", element);
                }
            };

            // -----------------------------------------------------------------
            // 1. Check 'Add Banking Detail' vs 'Update Banking Detail' Option
            // -----------------------------------------------------------------
            System.out.println("Checking for 'Add/Update Banking Detail' menu option...");
            clearCDKOverlays.run();

            WebElement bankingOption = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Add_Or_Update_Banking_Menu_Option)
            );

            String buttonText = bankingOption.getText().trim();
            System.out.println("Detected option text: '" + buttonText + "'");

            if (buttonText.equalsIgnoreCase("Update Banking Detail") || buttonText.contains("Update Banking")) {
                System.out.println("-> Option is '" + buttonText + "'. Skipping banking details process.");
                return "SKIPPED";
            }

            System.out.println("Proceeding with clicking option: '" + buttonText + "'...");
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", bankingOption);
            Thread.sleep(300);
            safeClick.accept(bankingOption);
            Thread.sleep(1000);

            // -----------------------------------------------------------------
            // 2. Click 'Yes' Button
            // -----------------------------------------------------------------
            System.out.println("Waiting for 'Yes' button...");
            clearCDKOverlays.run();

            WebElement yesBtn = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Yes_Button)
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", yesBtn);
            Thread.sleep(300);
            safeClick.accept(yesBtn);
            Thread.sleep(1000); 

            // -----------------------------------------------------------------
            // 3. Click 'Continue' Button
            // -----------------------------------------------------------------
            System.out.println("Waiting for 'Continue' button...");
            clearCDKOverlays.run();

            WebElement continueBtn = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Continue_Button)
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", continueBtn);
            Thread.sleep(300);
            safeClick.accept(continueBtn);
            Thread.sleep(1000);

            // -----------------------------------------------------------------
            // 4. Select Dropdowns (Bank, Branch, Account Type) if available
            // -----------------------------------------------------------------
            selectRandomMatDropdownOption(ShopLocators.Bank_Dropdown, "Bank");
            selectRandomMatDropdownOption(ShopLocators.Branch_Dropdown, "Branch");
            selectRandomMatDropdownOption(ShopLocators.Account_Type_Dropdown, "Account Type");

            // -----------------------------------------------------------------
            // 5. Enter Random 10-Digit Account Number
            // -----------------------------------------------------------------
            String randomAccountNumber = generateRandomDigits(10);
            System.out.println("Entering Random Account Number: " + randomAccountNumber);
            WebElement accountInput = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Account_Number_Input)
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", accountInput);
            Thread.sleep(300);

            accountInput.clear();
            accountInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            accountInput.sendKeys(org.openqa.selenium.Keys.DELETE);
            accountInput.sendKeys(randomAccountNumber);
            Thread.sleep(300);

            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", accountInput);
            js.executeScript("arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));", accountInput);

            // -----------------------------------------------------------------
            // 6. Enter Account Holder (Random String < 20 chars)
            // -----------------------------------------------------------------
            String randomAccountHolder = generateRandomAlphabets(15);
            System.out.println("Entering Account Holder: " + randomAccountHolder);
            try {
                WebElement accountHolderInput = driver.findElement(ShopLocators.Account_Holder_Input);
                accountHolderInput.clear();
                accountHolderInput.sendKeys(randomAccountHolder);
                js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", accountHolderInput);
                js.executeScript("arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));", accountHolderInput);
            } catch (Exception ignore) {
                System.out.println("Account Holder field not visible or skipped.");
            }

            // -----------------------------------------------------------------
            // 7. Enter Initials (2 Random Letters) & ID Number
            // -----------------------------------------------------------------
            String randomInitials = generateRandomAlphabets(2);
            System.out.println("Entering Initials: " + randomInitials);

            WebElement initialsInput = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Initials_Input)
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", initialsInput);
            Thread.sleep(200);

            initialsInput.clear();
            initialsInput.sendKeys(randomInitials);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", initialsInput);
            js.executeScript("arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));", initialsInput);

            System.out.println("Entering ID Number: " + idNumber);
            WebElement idInput = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.ID_Number_Input)
            );
            idInput.clear();
            idInput.sendKeys(idNumber);
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", idInput);
            js.executeScript("arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));", idInput);
            Thread.sleep(500);

            // -----------------------------------------------------------------
            // 8. Click 'Verify Account' Button
            // -----------------------------------------------------------------
            System.out.println("Waiting for 'Verify Account' button...");
            WebElement verifyBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Verify_Account_Btn)
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", verifyBtn);
            Thread.sleep(300);
            safeClick.accept(verifyBtn);

            System.out.println("Successfully completed Banking Details & Account Verification workflow!");
            Thread.sleep(1000);


            System.out.println("Successfully completed Banking Details & Account Verification workflow!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed during Banking Details & Account Verification workflow: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Banking & Account Verification Error: " + e.getMessage();
        }
    }

    /**
     * Helper to select any available option from Angular Mat-Select dropdown
     */
    private void selectRandomMatDropdownOption(By dropdownLocator, String dropdownName) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            System.out.println("Waiting for dropdown: " + dropdownName + "...");

            // 1. Clear Angular CDK overlays
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            // 2. EXPLICIT WAIT: Wait for the mat-select element to become visible on the DOM
            WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(dropdownLocator)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
            Thread.sleep(400);

            // 3. CLICK TO OPEN DROPDOWN
            try {
                wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
            } catch (Exception ex) {
                System.out.println("Native click failed on " + dropdownName + ". Using JavaScript click...");
                js.executeScript("arguments[0].click();", dropdown);
            }

            // Post-click wait: Allow CDK overlay options to animate open
            System.out.println("Clicked '" + dropdownName + "' dropdown. Waiting for option panel...");
            Thread.sleep(1000);

            // 4. FETCH AND SELECT OPTION
            List<WebElement> options = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(ShopLocators.Mat_Option_Items)
            );

            if (!options.isEmpty()) {
                Random rand = new Random();
                // Skip index 0 if it represents a blank placeholder
                int startIndex = (options.size() > 1 && options.get(0).getText().trim().isEmpty()) ? 1 : 0;
                WebElement selectedOption = options.get(startIndex + rand.nextInt(options.size() - startIndex));

                String selectedText = selectedOption.getText().trim();
                System.out.println("-> Selected '" + dropdownName + "': " + selectedText);

                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", selectedOption);
                Thread.sleep(300);

                try {
                    wait.until(ExpectedConditions.elementToBeClickable(selectedOption)).click();
                } catch (Exception ex) {
                    js.executeScript("arguments[0].click();", selectedOption);
                }

                // Post-click wait: Give Angular time to trigger change detection & load dependent fields
                Thread.sleep(1000);
            } else {
                System.out.println("WARNING: No options found in panel for dropdown '" + dropdownName + "'");
            }

        } catch (Exception e) {
            System.out.println("Could not select dropdown '" + dropdownName + "': " + e.getMessage());
        }
    }

    /**
     * Generates random uppercase alphabets of given length
     */
    private String generateRandomAlphabets(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    /**
     * Generates random digits of given length
     */
    private String generateRandomDigits(int length) {
        String numbers = "1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return sb.toString();
    }
    /**
     * Waits for the 'Finish' button to become visible and clickable, then performs the click.
     */
    public String clickFinishButton() {
        try {
            System.out.println("Waiting for 'Finish' button to become clickable...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Clear active Angular CDK overlays if present
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            // Wait for Finish button to become clickable
            WebElement finishBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Finish_Button)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", finishBtn);
            Thread.sleep(300);

            // Native click with JavaScript fallback
            try {
                finishBtn.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click on 'Finish' button...");
                js.executeScript("arguments[0].click();", finishBtn);
            }

            System.out.println("Successfully clicked 'Finish' button!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'Finish' button: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click Finish Button Error: " + e.getMessage();
        }
    }
    
    /**
     * Finds and clicks the 'Close' button on the Banking Details modal popup.
     */
    /**
     * Dismisses the modal popup by clicking on the far top-left corner of the page/screen.
     */
    public String clickCloseModalButton() {
        try {
            System.out.println("Clicking far top-left of the page to close/dismiss the modal...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(65));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Wait brief moment for modal overlay to be ready
            Thread.sleep(500);

            // 1. Primary approach: Perform an absolute coordinate click at (x=10, y=10) via Actions
            try {
                org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
                actions.moveByOffset(10, 10).click().build().perform();
            } catch (Exception actionEx) {
                System.out.println("Actions click failed, falling back to JavaScript elementFromPoint click...");
                
                // 2. Fallback approach: Find element at top-left (x=10, y=10) and click via JS
                js.executeScript(
                    "var elem = document.elementFromPoint(10, 10); " +
                    "if (elem) { elem.click(); }"
                );
            }

            // Clear any active CDK overlay backdrop if left behind
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            System.out.println("Successfully clicked far top-left corner to dismiss modal!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click top-left to close modal: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click Far Top-Left Close Modal Error: " + e.getMessage();
        }
    }
    
    /**
     * Finds and clicks the 'more_vert' menu icon to open the actions dropdown.
     */
    public String clickMoreActionsMenuIcon() {
        try {
            System.out.println("Waiting for 'more_vert' menu icon to appear...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(85));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Clear any active transparent Angular CDK overlays if present
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            // Wait for the 'more_vert' icon to become visible
            WebElement menuIcon = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.More_Actions_Menu_Icon)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", menuIcon);
            Thread.sleep(300);

            // Click with JavaScript fallback
            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(menuIcon));
                menuIcon.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click on 'more_vert' icon...");
                js.executeScript("arguments[0].click();", menuIcon);
            }

            System.out.println("Successfully clicked 'more_vert' menu icon!");
            Thread.sleep(500); // Allow time for the mat-menu popup to expand

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'more_vert' menu icon: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click More Actions Menu Icon Error: " + e.getMessage();
        }
    }/**
     * Finds and clicks the 'Submit' menu option inside the active mat-menu dropdown.
     */
    public String clickSubmitMenuOption() {
        try {
            System.out.println("Waiting for 'Submit' option inside the menu overlay...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Wait for the enabled Submit button inside the mat-menu
            WebElement submitBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Submit_Menu_Option)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBtn);
            Thread.sleep(200);

            // Native click with JavaScript fallback
            try {
                submitBtn.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click on 'Submit' menu option...");
                js.executeScript("arguments[0].click();", submitBtn);
            }

            System.out.println("Successfully clicked 'Submit' menu option!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'Submit' menu option: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click Submit Menu Option Error: " + e.getMessage();
        }
    }
    
    public String approveWithNote() {
        try {
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // -----------------------------------------------------------------
            // 1. Click on the Dropdown
            // -----------------------------------------------------------------
            System.out.println("Clicking on the status dropdown...");
            WebElement dropdown = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Approval_Status_Dropdown)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
            Thread.sleep(300);

            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(dropdown));
                dropdown.click();
            } catch (Exception clickEx) {
                js.executeScript("arguments[0].click();", dropdown);
            }

            // -----------------------------------------------------------------
            // 2. Select 'Approve' Option from Overlay
            // -----------------------------------------------------------------
            System.out.println("Selecting 'Approve' from options...");
            WebElement approveOpt = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Approve_Option)
            );

            try {
                explicitWait.until(ExpectedConditions.elementToBeClickable(approveOpt));
                approveOpt.click();
            } catch (Exception clickEx) {
                js.executeScript("arguments[0].click();", approveOpt);
            }
            Thread.sleep(500);

            // -----------------------------------------------------------------
            // 3. Generate 20-Character Random String & Enter into Textarea
            // -----------------------------------------------------------------
            String randomNoteText = generateRandomString(20);
            System.out.println("Entering 20-character random note: " + randomNoteText);

            WebElement noteArea = explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(ShopLocators.Note_Textarea)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", noteArea);
            Thread.sleep(300);

            // Clear existing spacing/content safely for Angular
            noteArea.clear();
            noteArea.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            noteArea.sendKeys(org.openqa.selenium.Keys.DELETE);

            // Enter random note text
            noteArea.sendKeys(randomNoteText);
            Thread.sleep(300);

            // Trigger Angular Reactive Form change detection events
            js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", noteArea);
            js.executeScript("arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));", noteArea);

            System.out.println("Successfully selected 'Approve' and entered note!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed during approval and note entry: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Approve With Note Error: " + e.getMessage();
        }
    }

    /**
     * Helper method to generate a random alphanumeric string of a specified length
     */
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
    
    /**
     * Waits for the 'Save' button to become clickable and performs the click action.
     */
    public String clickSaveButton() {
        try {
            System.out.println("Waiting for 'Save' button to become clickable...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Clear any active CDK overlays if present
            try {
                explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'cdk-overlay-backdrop')]")
                ));
            } catch (Exception ignore) {}

            // Locate and wait for Save button
            WebElement saveBtn = explicitWait.until(
                ExpectedConditions.elementToBeClickable(ShopLocators.Save_Button)
            );

            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", saveBtn);
            Thread.sleep(300);

            // Native click with JS fallback
            try {
                saveBtn.click();
            } catch (Exception clickEx) {
                System.out.println("Native click intercepted. Executing JS click on 'Save' button...");
                js.executeScript("arguments[0].click();", saveBtn);
            }

            System.out.println("Successfully clicked 'Save' button!");
            Thread.sleep(1000);

            Screenshot.takeScreenshot(driver);
            return null;

        } catch (Exception e) {
            System.out.println("Failed to click 'Save' button: " + e.getMessage());
            Screenshot.takeScreenshot(driver);
            return "Click Save Button Error: " + e.getMessage();
        }
    }
}
