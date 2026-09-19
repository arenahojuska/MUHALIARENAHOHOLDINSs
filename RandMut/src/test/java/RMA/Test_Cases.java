package RMA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import ONboarding.OnboardingFiller;

import java.time.Duration;

public class Test_Cases {

    WebDriver driver;
    WebDriverWait wait;
    Login loginPage;
    ClientCare clientCarePage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void loginTest() throws Exception {
        System.out.println("Executing Login Test Case Phase...");
        // editing the ID 
       //OnboardingFiller.main(null);
        Login login = new Login(driver, wait);
        
        // 1. Initial Page Navigation
        login.navigateToLandingPage();

        // 2. Step 1: Title Validation
        String titleError = login.verifyPageTitle();
        if (titleError != null) Assert.fail(titleError);

        // 3. Step 2: Email Processing Validation
        String emailError = login.enterEmailField();
        if (emailError != null) Assert.fail(emailError);

        // 4. Step 3: Password Processing Validation
        String passwordError = login.enterPasswordField();
        if (passwordError != null) Assert.fail(passwordError);

        // 5. Step 4: Login Submission Verification
        String clickError = login.clickLoginButton();
        if (clickError != null) Assert.fail(clickError);
 // testing git 
        // 6. Step 5: Landing/Session Verification State
        String sessionError = login.verifyLoginSuccessState();
        if (sessionError != null) Assert.fail(sessionError);
    }
  
    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void clientCareTest() throws Exception {
        System.out.println("Executing Client Care Test Case Phase...");
        ClientCare clientCarePage = new ClientCare(driver, wait);

        // 1. Click Client Care Button
        String clientCareClickError = clientCarePage.clickClientCareButton();
        if (clientCareClickError != null) {
            Assert.fail(clientCareClickError);
        }
        
        // 2. Click Policy Manager Button
        String policyManagerClickError = clientCarePage.clickPolicyManagerButton();
        if (policyManagerClickError != null) {
            Assert.fail(policyManagerClickError);
        }

        // 3. Click Uploads Button
        String uploadsClickError = clientCarePage.clickUploadsButton();
        if (uploadsClickError != null) {
            Assert.fail(uploadsClickError);
        }

        // 4. Click Funeral Button (This handles the mouse hover and click)
        String funeralClickError = clientCarePage.clickFuneralButton();
        if (funeralClickError != null) {
            Assert.fail(funeralClickError);
        }

        
        String policyIndex = TestData.getPolicyType();
        
        if (policyIndex != null && policyIndex.contains(".")) {
            policyIndex = policyIndex.split("\\.")[0].trim();
        }

       
       

        // Execute the updated scoped click action sequence
     // 8. Click Policy Type Button
        String policyTypeClickError = clientCarePage.clickPolicyTypeButton();

        if (policyTypeClickError != null) {
            Assert.fail(policyTypeClickError);
        }

        // 7. Click Create New Button
        String createNewClickError = clientCarePage.clickCreateNewButton();
        if (createNewClickError != null) {
            Assert.fail(createNewClickError);
        }

        // 8. Run the native OS upload routine instead of a pure element click
        String uploadError = clientCarePage.uploadOnboardingSheetViaRobot();
        if (uploadError != null) {
            Assert.fail(uploadError);
        }
        
        // 9. Click the final submit button and wait for confirmation verification
        String submitError = clientCarePage.clickFinalUploadSubmitButton();
        if (submitError != null) {
            Assert.fail(submitError);
        }
        
        // 10. Re-click Policy Manager navigation layout to return home after upload
        String reClickPolicyManagerError = clientCarePage.clickPolicyManagerButton();
        if (reClickPolicyManagerError != null) {
        	
            Assert.fail(reClickPolicyManagerError);
        }
        clientCarePage.handleOptionalReminderAlert();
        
    
        String tableSequenceError = clientCarePage.openPolicy();
        if (tableSequenceError != null) {
            Assert.fail(tableSequenceError);
    }
        
        String continueClickError = clientCarePage.clickContinueButton();
        if (continueClickError != null) {
            Assert.fail(continueClickError);
        }
        String nextClickError = clientCarePage.clickNextButton();
        if (nextClickError != null) {
            Assert.fail(nextClickError);
        }
        
     // Step: Extract First ID Number from Table
        String currentIDNumber = clientCarePage.getExtractIDNumber();
        if (currentIDNumber == null) {
            Assert.fail("Failed to extract ID Number from table.");
        }
        System.out.println("Successfully extracted ID Number: " + currentIDNumber);

        // 16. Fetch file configuration path for the PDF
        String pdfFilePath = TestData.getUploadpdf();

        // 17. Run loop processing to find and override every 'more_vert' menu row present
        String loopError = clientCarePage.overrideAllPoliciesAndSubmit(pdfFilePath);
        if (loopError != null) {
            Assert.fail(loopError);
        }
     // 15. Click the master 'Next' button to shift view onto the policy listings
        String nextError = clientCarePage.clickNextButton();
        
        if (nextError != null) {
        	
            Assert.fail(nextError);
        }
     // Commit change approvals and dispatch the finalized policy data model
        String approvalError = clientCarePage.clickRequestApprovalButton();
        if (approvalError != null) {
            Assert.fail(approvalError);
        }

        // Halt and wait until the interface displays the final validation confirmation string
        String messageValidationError = clientCarePage.waitForApprovalMessage();
        if (messageValidationError != null) {
            Assert.fail(messageValidationError);
        }
     // Extract and store the unique reference digits generated by this run
        String policyNumber = clientCarePage.getExtractPolicyNumber();
        if (policyNumber != null) {
                System.out.println("SUCCESS: Captured Policy ID [" + policyNumber + "] for downstream system tasks.");
        } else {
            Assert.fail("Failed to capture the changing policy reference number from the header asset wrapper.");
        }
        
        System.out.println("Proceeding to launch approval verification window loop...");
        
        // 👉 ADD THIS LINE TO FIX THE NULL POINTER EXCEPTION:
        loginPage = new Login(driver, wait);
        
        String newTabLoginError = loginPage.openNewTabAndLogin();
        if (newTabLoginError != null) {
            Assert.fail(newTabLoginError);
        }
        String reclientCareClickError = clientCarePage.clickClientCareButton();
        if (reclientCareClickError != null) {
            Assert.fail(reclientCareClickError);
        }
        
        // 2. Click Policy Manager Button
        String repolicyManagerClickError = clientCarePage.clickPolicyManagerButton();
        
        if (repolicyManagerClickError != null) {
            Assert.fail(repolicyManagerClickError);
        }
        
       
        String searchError = clientCarePage.searchPolicyNumber(policyNumber);
        
        if (searchError != null) {
            Assert.fail(searchError);
        }
     

        // 1. Click Open Link
        String openError = clientCarePage.clickOpenPolicyLink();
        if (openError != null) Assert.fail(openError);

        // 2. Click View Button
        String viewError = clientCarePage.clickViewButton();
        if (viewError != null) Assert.fail(viewError);

        // 3. Click Next Button (First Time)
        String nextError1 = clientCarePage.clickNextStepButton();
        if (nextError1 != null) Assert.fail(nextError1);
        Thread.sleep(1000); // Small animation pause pause for the UI cards to slide

        // 3b. Click Next Button (Second Time)
        String nextError2 = clientCarePage.clickNextStepButton();
        if (nextError2 != null) Assert.fail(nextError2);

        // 4. Click Approve Button
        String approveError = clientCarePage.clickApproveButton();
        if (approveError != null) Assert.fail(approveError);
        
        System.out.println("Policy completely verified and approved across both operational testing browser sessions!");
     // 5. Click Final "Yes, Approve" Confirmation
        String yesApproveError = clientCarePage.clickYesApproveConfirmationButton();
        if (yesApproveError != null) Assert.fail(yesApproveError);
        
        System.out.println("Policy completely verified and finalized across the workflow pipeline!"); 
        
        String rereClickPolicyManagerError = clientCarePage.clickPolicyManagerButton();
        if (reClickPolicyManagerError != null) {
        	
            Assert.fail(rereClickPolicyManagerError);
        }
    
    
     // Step 1: Click 'Search' button
        String searchBtnError = clientCarePage.clickSearchButton();
        if (searchBtnError != null) {
            Assert.fail(searchBtnError);
        }

        // Step 2: Enter extracted ID into the search input field
        String enterIdError = clientCarePage.enterSearchID(currentIDNumber);
        if (enterIdError != null) {
            Assert.fail(enterIdError);
        }
    
     // Step: Click 'Select' action link button
     // Updated step in Test_Cases.java
     // Step: Wait for Search Results banner for ID and click first 'Select'
        String selectActionError = clientCarePage.clickSelectActionButton(currentIDNumber);
        if (selectActionError != null) {
            Assert.fail(selectActionError);
        }
        
     // Step: Loop through all tabs, click each one, and capture a screenshot
        String tabsError = clientCarePage.clickAllTabsAndCaptureScreenshots();
        if (tabsError != null) {
            Assert.fail(tabsError);
        }
        System.out.println("All tabs clicked and screenshots captured successfully!");
        
        clientCarePage.clickDocumentExpansionArrowsAndCaptureScreenshots();
    }
    
    
    
    
    
    
    
    @Test(priority = 3, dependsOnMethods = "loginTest")
    public void claimCareTest() throws Exception {
        System.out.println("Executing Claim Care Test Case Phase...");
        
        // Match object class types to keep compiler satisfied
        ClaimCare claimCarePage = new ClaimCare(driver, wait);

        // Call the instantiated variable smoothly
        String claimCareClickError = claimCarePage.clickClaimCareButton();
        if (claimCareClickError != null) {
            Assert.fail(claimCareClickError);
        }
        String workPoolError = claimCarePage.clickFuneralWorkPool();
        if (workPoolError != null) {
            Assert.fail(workPoolError);
        }
     // Step 5: Click the Claims top navigation dropdown trigger component
        String claimsDropdownError = claimCarePage.clickClaimsDropdownButton();
        if (claimsDropdownError != null) {
            Assert.fail(claimsDropdownError);
        }
        System.out.println("Claims dropdown menu interface expanded completely.");
     // Step 3: Select Register Funeral Claim option from menu
        String registerClaimError = claimCarePage.clickRegisterFuneralClaim();
        if (registerClaimError != null) {
            Assert.fail(registerClaimError);
        }
        System.out.println("Register Funeral Claim form interface opened successfully.");
        
        
        
        String searchError = claimCarePage.searchIdFromExcel();
        if (searchError != null) {
        }
        System.out.println("Search query processed. Checking data table outputs...");
    
 // Step 6: Click the Select link button for the active row record
    String selectLinkError = claimCarePage.clickSelectActionLink();
    if (selectLinkError != null) {
        Assert.fail(selectLinkError);
    }
    System.out.println("Record chosen successfully. Advancing to the next workflow interface.");
    
    
 // Step 7: Open dropdown and select the first available option selection mapping
    String dropdownError = claimCarePage.selectAnyTypeOfDeathOption();
    if (dropdownError != null) {
        Assert.fail(dropdownError);
    }

 // Step 8: Trigger calendar pickers and populate today's date for both fields
    String datepickerError = claimCarePage.selectTodayForBothDatefields();
    if (datepickerError != null) {
        Assert.fail(datepickerError);
    }
    System.out.println("Both operational date fields have been assigned successfully.");

    String nextError1 = claimCarePage.clickNextButton();
    if (nextError1 != null) Assert.fail(nextError1);
    

    // Step 9: Click the Register Notifier button
    String registerNotifierError = claimCarePage.clickRegisterNotifierButton();
    if (registerNotifierError != null) {
        Assert.fail(registerNotifierError);
    }
    System.out.println("Register Notifier step triggered successfully.");

    // Step 10: Populate Notifier Details form fields
    String notifierError = claimCarePage.populateNotifierDetails();
    if (notifierError != null) {
        Assert.fail(notifierError);
    }
    System.out.println("Notifier Details form populated and selected successfully.");


 // Step 11: Complete registration
    String registerError = claimCarePage.clickRegisterButton();
    if (registerError != null) {
        Assert.fail(registerError);
    }
    System.out.println("Claim notification registered successfully!");
 // Step 12: Extract Claim Number directly to a local variable
    String currentClaimNumber = claimCarePage.extractClaimNumber();
    
    // Validate extraction
    Assert.assertNotNull(currentClaimNumber, "Failed to extract Claim Number from Confirmation screen.");
    Assert.assertFalse(currentClaimNumber.isEmpty(), "Extracted Claim Number was empty.");

    System.out.println("Extracted Claim Number for this execution: " + currentClaimNumber);
    
    
    String workPoolErrorr = claimCarePage.clickFuneralWorkPool();
    if (workPoolError != null) {
        Assert.fail(workPoolErrorr);
    }
 

    // Step 13: Search the extracted Claim Number in the search box
    String searchError1 = claimCarePage.searchClaimNumber(currentClaimNumber);
    if (searchError1 != null) {
        Assert.fail(searchError1);
    }
    System.out.println("Claim search operation executed successfully for: " + currentClaimNumber);
    
 // Step 14: Click 'more_vert' and select 'View'
    String menuError = claimCarePage.openMoreActionsAndView();
    if (menuError != null) {
        Assert.fail(menuError);
    }
    System.out.println("Navigated to 'View' page successfully.");
    
 // Step 15: Click 'Continue' button
    String continueError = claimCarePage.clickContinueButton();
    if (continueError != null) {
        Assert.fail(continueError);
    }
    System.out.println("Advanced to the next step successfully!");
 // Step 17: Fill dropdowns, select checkbox, and advance to Finish
    String finishError = claimCarePage.fillFormAndIterateToFinish();
    if (finishError != null) {
        Assert.fail(finishError);
    }
 // Step: Click Funeral Work Pool
    String workPoolEror = claimCarePage.clickFuneralWorkPool();
    if (workPoolEror != null) {
        Assert.fail(workPoolEror);
    }
    System.out.println("Successfully selected and loaded Funeral Work Pool!");
    Thread.sleep(4000);
    // Step: Search Claim Number
    String searchError2 = claimCarePage.searchClaimNumber(currentClaimNumber);
    if (searchError2 != null) {
        Assert.fail(searchError2);
    }
    System.out.println("Claim search operation executed successfully for: " + currentClaimNumber);
    
 // Step: Click 'more_vert' Actions Menu
    String menuError1 = claimCarePage.clickMoreActionsMenu();
    if (menuError1 != null) {
        Assert.fail(menuError1);
    }
    System.out.println("Opened 'more_vert' options menu successfully!");
 // Step: Click 'Decision' option from the opened menu
    String decisionError = claimCarePage.clickDecisionOption();
    if (decisionError != null) {
        Assert.fail(decisionError);
    }
    System.out.println("Decision step initiated successfully!");
    
    
    String menuError11 = claimCarePage.clickMoreActionsMenu();
    if (menuError11 != null) {
        Assert.fail(menuError11);
    }


    String bankingAndVerifyError = claimCarePage.processBankingDetailsAndUpdateAccount("1165210736", "7809246546086");

    if ("SKIPPED".equalsIgnoreCase(bankingAndVerifyError)) {
        System.out.println("Banking details process was skipped. Bypassing Next, Finish, and Close Modal steps.");
    } else {
        if (bankingAndVerifyError != null) {
            Assert.fail(bankingAndVerifyError);
        }

        String nextError2 = claimCarePage.clickNextButton();
        if (nextError2 != null) {
            Assert.fail(nextError2);
        }

        String finishError1 = claimCarePage.clickFinishButton();
        if (finishError1 != null) {
            Assert.fail(finishError1);
        }

        // Step: Close Banking Details Modal Dialog
        
        
    }

    String workPoolEror1 = claimCarePage.clickFuneralWorkPool();
    if (workPoolEror1 != null) {
        Assert.fail(workPoolEror1);
    }
    System.out.println("Successfully selected and loaded Funeral Work Pool!");
    Thread.sleep(4000);
    // Step: Search Claim Number
    String searchErro = claimCarePage.searchClaimNumber(currentClaimNumber);
    if (searchError2 != null) {
        Assert.fail(searchError2);
    }
    System.out.println("Claim search operation executed successfully for: " + currentClaimNumber);
    
 // Step: Click 'more_vert' Actions Menu
    String menuErro = claimCarePage.clickMoreActionsMenu();
    if (menuError1 != null) {
        Assert.fail(menuError1);
    }
    System.out.println("Opened 'more_vert' options menu successfully!");
 // Step: Click 'Decision' option from the opened menu
    String decisionErr = claimCarePage.clickDecisionOption();
    if (decisionError != null) {
        Assert.fail(decisionError);
    }
    System.out.println("Decision step initiated successfully!");
    
    
    String menuErr = claimCarePage.clickMoreActionsMenu();
    if (menuError11 != null) {
        Assert.fail(menuError11);
    }
    Thread.sleep(3000);
    String menuIconError = claimCarePage.clickMoreActionsMenuIcon();
    if (menuIconError != null) {
        Assert.fail(menuIconError);
    }
    String submitError = claimCarePage.clickSubmitMenuOption();
    if (submitError != null) {
        Assert.fail(submitError);
    }
    String nextError21 = claimCarePage.clickNextButton();
    if (nextError1 != null) Assert.fail(nextError21);
    String nextError221 = claimCarePage.clickNextButton();
    if (nextError1 != null) Assert.fail(nextError21);
    String nextError241 = claimCarePage.clickNextButton();
    if (nextError1 != null) Assert.fail(nextError21);
    
    String approveError = claimCarePage.approveWithNote();
    if (approveError != null) {
        Assert.fail(approveError);
    }
    
    String saveError = claimCarePage.clickSaveButton();
    if (saveError != null) {
        Assert.fail(saveError);
    }
    }

    
    
    
    

@AfterTest
public void tearDown() {
    if (driver != null) {
        driver.quit();
        System.out.println("Browser context cleared out cleanly.");
    }
}
}