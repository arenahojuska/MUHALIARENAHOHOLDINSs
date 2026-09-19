package RMA;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

public class Test_Cases {

    Playwright playwright;
    Browser browser;
    Page page;
    Login loginPage;


    @BeforeTest
    public void setup() {
        playwright = Playwright.create();
        
        // 1. Launch Chromium with '--start-maximized' argument
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(java.util.Arrays.asList("--start-maximized")));
        
        // 2. Disable default viewport so the page expands to fill the maximized window
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
        
        page = context.newPage();
        
        // Default timeout setting
        page.setDefaultTimeout(9999999);
    }

    @Test(priority = 1)
    public void loginTest() throws Exception {
        System.out.println("Executing Login Test Case Phase...");
        
        Login login = new Login(page);
        
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

        // 6. Step 5: Landing/Session Verification State
        String sessionError = login.verifyLoginSuccessState();
        if (sessionError != null) Assert.fail(sessionError);
    }
    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void clientCareTest() throws Exception {
        System.out.println("Executing Client Care Test Case Phase...");
        ClientCare clientCarePage = new ClientCare(page);

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
        loginPage = new Login(page);
        
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
        String selectActionError = clientCarePage.clickSelectActionButton();
        if (selectActionError != null) {
            Assert.fail(selectActionError);
        }
        System.out.println("Successfully clicked 'Select' action link!");
        
     // Step: Loop through all tabs, click each one, and capture a screenshot
        String tabsError = clientCarePage.clickAllTabsAndCaptureScreenshots();
        if (tabsError != null) {
            Assert.fail(tabsError);
        }
        System.out.println("All tabs clicked and screenshots captured successfully!");
    }
    
    
    
    @Test(priority = 3, dependsOnMethods = "loginTest")
    public void claimCareReportTest() throws Exception {
    	
    	System.out.println("Executing Claim Care Test Case Phase...");
        
        // Pass the Playwright Page instance to the converted ClaimCare object
    	ClaimCareReporting claimCarePage = new ClaimCareReporting(page);

        // Call the converted method and assert on returned error string
        String claimCareClickError = claimCarePage.clickClaimCareButton();
        if (claimCareClickError != null) {
            Assert.fail(claimCareClickError);
        }
        System.out.println("Executing Reports Manager Navigation Phase...");

        String reportsError = claimCarePage.clickReportsManagerButton();
        if (reportsError != null) {
            Assert.fail(reportsError);
        }
        System.out.println("Executing Step 3: Fetching Dropdown Options...");

        ClaimCareReporting reporting = new ClaimCareReporting(page);
        List<String> options = reporting.getDropdownOptions();

        // Assert options list is not empty
        Assert.assertFalse(options.isEmpty(), "Dropdown option list was empty or failed to load.");

        // Print retrieved items
        options.forEach(option -> System.out.println("Option: " + option));
     

     // Executes dropdown selection, inputs today - 5 days, and clicks View
     String result = reporting.generateActuarialReport(5);
     Assert.assertNull(result, "Actuarial report generation failed: " + result);
     
     System.out.println("Executing Step 4: Downloading and Verifying PDF...");

    
     String downloadedFilePath = reporting.downloadAndVerifyPDF();

     System.out.println("PDF downloaded and verified successfully at: " + downloadedFilePath);
    	
    	
    	
    }
    
    
    
    
    
    
    
    
    
    

    @AfterTest
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
            System.out.println("Browser context cleared out cleanly.");
        }
    }
}