package RMA;

import org.openqa.selenium.By;

public class ShopLocators {
    // Login Elements
    public static final By EMAIL_FIELD = By.id("email");
    public static final By PASSWORD_FIELD = By.id("password");
    public static final By LOGIN_BTN = By.xpath("/html/body/app-root/app-sign-in/body/div[1]/form/button");
    public static final By BODY_TAG = By.tagName("body");
    
    // Shop Navigation & Headers
    public static final By Clientcare_LINK = By.xpath("/html/body/app-root/app-host/app-left-menu/div/div/mat-accordion/mat-expansion-panel[2]");
   // public static final By PolicyManager_button = By.xpath("//*[@id=\"cdk-accordion-child-1\"]/div/div[4]/a/span");
    public static final By PolicyManager_button = By.xpath("//span[@class='title-description' and contains(text(), 'Policy Manager')]");
 // Strictly targets buttons containing 'Upload' or 'Group Members' while excluding 'Case'
    public static final By Upload_button = By.xpath(
        "//button[contains(translate(normalize-space(.), 'UPLOAD', 'upload'), 'upload') and not(contains(., 'Case'))] | " +
        "//span[contains(translate(normalize-space(.), 'UPLOAD', 'upload'), 'upload')]//ancestor-or-self::button"
    );

    public static final By Group_button = By.xpath(
        "//button[contains(translate(normalize-space(.), 'GROUP MEMBERS', 'group members'), 'group members') and not(contains(., 'Case'))] | " +
        "//span[contains(translate(normalize-space(.), 'GROUP MEMBERS', 'group members'), 'group members')]//ancestor-or-self::button"
    );
    public static final By Funeral_button = By.xpath("//button[normalize-space(.)='Funeral']");
    //public static final By Funeral_button = By.xpath("//button[normalize-space(.)='Access Bank']");
    public static By getPolicyTypeButton(String buttonText) {

        return By.xpath("//button[@mat-menu-item and contains(normalize-space(.),'" + buttonText + "')]");
    }
    
    public static final By NewPolicy_button = By.xpath("(//mat-radio-button)[1]");
    public static final By Fallback_Checkbox = By.xpath("//span[contains(@class, 'mat-checkbox-inner-container')] | //input[@type='checkbox' and contains(@id, 'mat-checkbox')]");
    public static final By ChooseFile_button = By.xpath("//input[@type='file']");
    
    public static final By UploadFilee_button = By.xpath("//button[text()='Upload File']");
    
    public static final By Reminder_alert = By.xpath("//*[@id=\"mat-dialog-2\"]/ng-component/div/span[2]");
 // Better header locator targeting the text node inside the header cell
    public static final By OverallSLA_Header = By.xpath("//mat-header-cell[contains(., 'Overall SLA')]");
 // Locates the 'open' link inside the SPECIFIC row where the Type cell contains text like 'Onboarding' or 'Manage Group Risk'
 // Targets an 'open' link strictly inside a row where the SLA tracker is less than 2 minutes old
    public static final By TargetRowOpen_link = By.xpath(
        "//mat-row[mat-cell[contains(., '00 days,00 hrs,00 mins') or contains(., '00 days,00 hrs,01 mins')]]//a[text()='open']"
    );  
    public static final By FirstRow_Name = By.xpath("(//mat-table/mat-row)[1]/mat-cell[contains(@class, 'mat-column-Name')] | (//mat-row)[1]/mat-cell[2]");
    public static final By FirstRow_SLA = By.xpath("(//mat-table/mat-row)[1]/mat-cell[contains(@class, 'mat-column-Overall-SLA')] | (//mat-row)[1]/mat-cell[7]");
 // Locates the 'Select' action link by its unique ID
    public static final By Select_BtnAction = By.xpath("//a[@id='btnAction' or normalize-space(text())='Select']");
 
    public static final By FirstRowOpen_link = By.xpath("(//mat-table/mat-row)[1]/mat-cell[8]/a | (//mat-row)[1]//a[text()='open']");    
    public static final By Continue_button = By.xpath("//button[text()=' Continue ' or contains(text(), 'Continue')]");
 // Locates specifically the FIRST ID Number cell in the Angular Material table
    public static final By First_ID_Number_Text = By.xpath("(//mat-cell[contains(@class, 'mat-column-idNumber')])[1]");
    public static final By Next_button = By.xpath("//button[text()='Next' or contains(text(), 'Next')]");
 // Locates all Angular Material tab headers dynamically
    
 // Strictly targets actual individual tab labels, ignoring tab header wrappers
    public static final By All_Tab_Labels = By.xpath("//div[@role='tab']//div[contains(@class, 'mat-tab-label-content')]");
   // public static final By All_Tab_Labels1 = By.xpath("//mat-tab-header//div[contains(@class,'mat-tab-label')]");
    // Dynamically targets the 'Select' button strictly inside the table row containing the searched ID
 public static final By MoreVert_menu = By.xpath("//span[text()='more_vert'] | //mat-icon[text()='more_vert'] | //*[contains(@class, 'more_vert')]");
 
//Strictly targets the 'Select' link inside the Search Results table

    // Targets the dropdown menu item button containing the 'Override VOPD' text
    public static final By OverrideVOPD_button = By.xpath("//button[contains(., 'Override VOPD')]");
 // Targets the radio button input container (safer to check selection state on the parent/input)
    public static final By Target_RadioButton = By.xpath("//mat-radio-button[contains(@class, 'mat-radio-checked')] | //span[@class='mat-radio-inner-circle']");

    // Targets the material icon labeled 'upload'
    public static final By InlineUpload_Icon = By.xpath("//span[contains(@class, 'material-icons') and text()='upload']");

    // Targets the 'Choose files' trigger element link
    public static final By ChooseFiles_link = By.xpath("//a[@name='openFile' or contains(text(), 'Choose files')]");

 public static final By GreenUpload_Icon = By.xpath("//span[text()='upload' or contains(text(),'upload')][not(ancestor::mat-header-cell)]");

    // Targets the Submit button text wrapper
    public static final By Submit_button = By.xpath("//button[text()='Submit' or contains(text(), 'Submit')]");
 // Targets the Request Approval button cleanly by its visible text label
    public static final By RequestApproval_button = By.xpath("//button[contains(@class, 'submit-button') and text()='Request Approval']");
    

    public static final By ApprovalStatus_Message = By.xpath("//span[contains(@class, 'valid') and contains(text(), 'Awaiting approval someone with permission')]");
 // Targets the card title div by matching the static starting string pattern
 // Uses the dot operator (.) instead of text() to read across the entire inner element content safely
   
    public static final By PolicyReference_Text = By.xpath("//mat-card-content/div/div[1]");
    public static final By SearchText_Field = By.id("searchText");
    
    public static final By OpenLink_Button = By.className("link-edit");
    public static final By ViewSubmit_Button = By.xpath("//button[contains(@class, 'submit-button') and text()='View']");
    public static final By Approve_Button = By.xpath("//button[contains(@class, 'submit-button') and text()='Approve']");
    public static final By YesApprove_Button = By.xpath("//button[contains(@class, 'submit-button') and (text()='Yes, Approve' or contains(., 'Yes, Approve'))]");
 // Locates the 'Search' button
    public static final By SearchH_Button = By.xpath(
        "//button[.//span[normalize-space(text())='Search']] | " +
        "//span[contains(@class, 'mat-button-wrapper') and normalize-space(text())='Search']//ancestor-or-self::button"
    );
   
 // Locates the autocomplete option matching the typed ID number in the mat-option overlay
    public static By getAutocompleteOption(String idNumber) {
        return By.xpath("//mat-option//span[contains(@class,'mat-option-text') and normalize-space(text())='" + idNumber + "'] | //mat-option[.//span[contains(text(),'" + idNumber + "')]]");
    }
 // Locates the "Search Results for <idNumber>" banner header
   
 // Strictly targets top-level Angular Material tab headers
    public static final By All_Tab_Labels1 = By.xpath("//mat-tab-header//div[contains(@class,'mat-tab-label')]");
 // Dynamically targets the 'Select' button strictly inside the table row containing the searched ID
    public static By getSelectBtnActionForId(String idNumber) {
        return By.xpath(
            "//tr[contains(., '" + idNumber + "')]//a[@id='btnAction' or normalize-space(text())='Select'] | " +
            "//mat-row[contains(., '" + idNumber + "')]//a[@id='btnAction' or normalize-space(text())='Select'] | " +
            "//*[contains(@class,'mat-row') or contains(@class,'table-row') or self::tr][contains(., '" + idNumber + "')]//a[contains(@class,'link-edit') or normalize-space(text())='Select']"
        );
    }
    
 // Strictly targets the 'Select' button INSIDE the Search Results table (ignoring Workflows)
    public static final By First_Select_BtnAction = By.xpath(
        "//mat-table[not(contains(@class,'workflow')) and not(ancestor::*[contains(@class,'workflow')])]//a[@id='btnAction' and normalize-space()='Select'] | " +
        "//table[not(contains(@class,'workflow')) and not(ancestor::*[contains(@class,'workflow')])]//a[@id='btnAction' and normalize-space()='Select'] | " +
        "(//div[contains(@class,'search-result')]//a[@id='btnAction' and normalize-space()='Select'])[1]"
    );
 // Locates the "Search Results for <idNumber>" header message
    public static By getSearchResultsHeader(String idNumber) {
        return By.xpath("//div[contains(text(), 'Search Results for') and contains(., '" + idNumber + "')]");
    }

    // Locates strictly the FIRST 'Select' button/link found on the page
 //   public static final By First_Select_BtnAction = By.xpath( "(//a[@id='btnAction' or normalize-space(text())='Select' or (contains(@class, 'link-edit') and normalize-space(text())='Select')])[1]"  );
    // Locates the Search Term input field
    public static final By Search_Term_Input = By.xpath("//input[@id='searchTerm' or @name='searchTerm']");
    
    
    // Claim Care module 
    public static final By ClaimCare_button = By.xpath("//span[contains(@class, 'menu-title') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'claimcare')]");
    
    public static final By FuneralWorkPool_span = By.xpath("//span[contains(@class, 'title-description') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'funeral work pool')]");
    public static final By RegisterFuneralClaim_menuitem = By.xpath("//button[@role='menuitem' and contains(., 'Register Funeral Claim')]");
    
    public static final By Search_Input_Field = By.id("query");
    
    public static final By Claims_Dropdown_Trigger = By.xpath("//button[./span[contains(text(), 'Claims')]]");
    
    public static final By Select_Action_Link = By.id("btnAction");
    
 // Dropdown Locators
    public static final By TypeOfDeath_Dropdown = By.id("filter");
    public static final By Mat_Options = By.cssSelector("mat-option");

    // Datepicker Locators
    public static final By DateNotified_Calendar_Btn = By.xpath("//mat-form-field[.//label[contains(.,'Date Notified') or contains(text(),'Date Notified')]]//mat-datepicker-toggle//button | //div[contains(text(),'Date Notified')]/following-sibling::div//mat-datepicker-toggle//button | //mat-form-field[contains(.,'Date Notified')]//mat-datepicker-toggle//button");
    public static final By DateDeath_Calendar_Btn = By.xpath("//mat-form-field[.//label[contains(.,'Date of Death') or contains(text(),'Date of Death')]]//mat-datepicker-toggle//button | //div[contains(text(),'Date of Death')]/following-sibling::div//mat-datepicker-toggle//button | //mat-form-field[contains(.,'Date of Death')]//mat-datepicker-toggle//button");
    public static final By Calendar_Today_Cell = By.cssSelector(".mat-calendar-body-today, .mat-calendar-body-active");
    public static final By RegisterNotifier_button = By.xpath("//button[contains(text(), 'Register Notifier')]");
    
 // Text Inputs
    public static final By Notifier_FirstName_Input = By.id("claimantFirstName");
    public static final By Notifier_LastName_Input = By.id("claimantLastName");
    public static final By Notifier_IdentityNumber_Input = By.id("claimantIdentityNumber");
    public static final By Notifier_Mobile_Input = By.id("claimantMobileNumber");
    public static final By Notifier_Email_Input = By.id("claimantEmail");

    // Dropdowns (Targeting mat-select triggers specifically)
    public static final By RelationToDeceased_Dropdown = By.xpath("//mat-select[@formcontrolname='relation' or @formcontrolname='claimantRelation' or contains(@formcontrolname,'relation') or contains(@formcontrolname,'Relation')] | (//mat-select)[1]");
    public static final By PreferredMethodContact_Dropdown = By.xpath("//mat-select[@formcontrolname='preferredCommunicationMethod' or @formcontrolname='communicationMethod' or contains(@formcontrolname,'preferred') or contains(@formcontrolname,'communication')] | (//mat-select)[2]");
 // Strictly target individual mat-tab clickable labels
    public static final By Main_Tabs_Locator = By.xpath(
        "//div[@role='tab']//div[contains(@class,'mat-tab-label-content')] | " +
        "//div[contains(@class,'mat-tab-label')]//span"
    );
    // Angular Overlay Options (presence-based)
    public static final By Mat_Option_First = By.cssSelector("mat-option");
    public static final By Mat_Option_Email = By.xpath("//mat-option[contains(., 'Email')]");
    public static final By Register_Final_Button = By.xpath("//button[contains(@class,'button') and text()='Register'] | //button[normalize-space()='Register']");
 // Confirmation Tab Locators
    public static final By Confirmation_ClaimNumber_Cell = By.xpath("//th[normalize-space()='Claim Number']/ancestor::table//tr/td[4] | //td[preceding-sibling::td[contains(.,'Life Insured')]]");
    public static final By Confirmation_Done_Button = By.xpath("//button[normalize-space()='Done']");
    
    
 // Search Field & Submit Button Locators
    public static final By Search_Input_Fieldd = By.xpath("//input[@name='searchWord'] | //input[@id='mat-input-3'] | //input[@matinput and @name='searchWord']");
    public static final By Search_Button = By.xpath("//button[contains(.,'Search') or @type='submit'] | //mat-icon[contains(text(),'search')]/ancestor::button | //button[contains(@class,'search')]");
    
 // Actions Menu Locators
    public static final By More_Actions_Menu_Icon = By.xpath("//span[contains(@class,'material-icons') and text()='more_vert'] | //span[normalize-space()='more_vert']");
    public static final By Menu_Option_View = By.xpath("//button[@role='menuitem' and (contains(.,'View') or text()='View')] | //button[contains(@class,'mat-menu-item') and contains(.,'View')]");
 // Locates the 'Decision' option inside the Angular Material menu popover
    public static final By Decision_Menu_Option = By.xpath("//button[@role='menuitem' and contains(normalize-space(text()), 'Decision')] | //button[contains(@class,'mat-menu-item') and contains(., 'Decision')]");
 // Navigation / View Screen Locators
    public static final By Continue_Button = By.xpath("//button[contains(@class,'submit-button') and normalize-space()='Continue'] | //button[normalize-space()='Continue']");
    
    
    
 /// Generic Locators
    public static final By All_Mat_Selects = By.xpath("//mat-select | //div[contains(@class,'mat-select-trigger')]");
    public static final By All_Checkboxes = By.xpath("//mat-checkbox | //input[@type='checkbox']/ancestor::mat-checkbox | //span[contains(@class,'mat-checkbox-inner-container')]");
    public static final By Mat_Option_Index_1 = By.xpath("(//mat-option)[1] | (//span[contains(@class,'mat-option-text')])[1]");

    // Stepper Navigation Buttons
    public static final By Next_Button = By.xpath("//button[normalize-space()='Next'] | //button[contains(@class,'button') and text()='Next']");
    public static final By Finish_Button = By.xpath("//button[normalize-space()='Finish'] | //button[contains(@class,'submit-button') and text()='Finish']");
    
 // Locates either "Add Banking Detail" or "Update Banking Detail" dynamically
    public static final By Add_Or_Update_Banking_Menu_Option = By.xpath(
    	    "//button[@role='menuitem' and contains(normalize-space(.), 'Add Banking')] | " +
    	    "//button[contains(@class,'mat-menu-item') and contains(., 'Add Banking')]"
    	);
 // Locates the 'Yes' floating confirmation button safely
    public static final By Yes_Button = By.xpath("//button[contains(@class, 'button') and normalize-space(text())='Yes'] | //button[normalize-space()='Yes']");
    
 // Locates the Account Number input field
    public static final By Account_Number_Input = By.xpath("//input[@id='accountNumber' or @name='accountNumber']");
 // Locates the 'Select' link button
    public static final By Select_Link = By.xpath("//a[contains(@class, 'link-edit') and normalize-space(text())='Select'] | //a[@role='button' and normalize-space()='Select']");
 // Locators based on the provided HTML
    public static final By Initials_Input = By.xpath("//input[@id='initials' or @formcontrolname='initials']");
    public static final By ID_Number_Input = By.xpath("//input[@id='idNumber' or @formcontrolname='idNumber']");
    public static final By Bank_Dropdown = By.xpath(
    	    "//mat-select[@formcontrolname='bank'] | " +
    	    "//mat-form-field[.//mat-label[contains(text(),'Bank')]]//mat-select | " +
    	    "(//mat-select)[1]"
    	);

    	public static final By Branch_Dropdown = By.xpath(
    	    "//mat-select[@formcontrolname='branch'] | " +
    	    "//mat-form-field[.//mat-label[contains(text(),'Branch')]]//mat-select | " +
    	    "(//mat-select)[2]"
    	);

    	public static final By Account_Type_Dropdown = By.xpath(
    	    "//mat-select[@formcontrolname='accountType'] | " +
    	    "//mat-form-field[.//mat-label[contains(text(),'Account Type')]]//mat-select | " +
    	    "(//mat-select)[3]"
    	);

    	public static final By Mat_Option_Items = By.xpath(
    	    "//mat-option[not(contains(@class,'mat-option-disabled')) and not(contains(@class,'ng-hidden'))]"
    	);
    	
    	public static final By Account_Holder_Input = By.xpath(
    		    "//input[@formcontrolname='accountHolder'] | " +
    		    "//input[@name='accountHolder' or contains(@id,'accountHolder')] | " +
    		    "//mat-form-field[.//mat-label[contains(text(),'Account Holder')]]//input | " +
    		    "//input[preceding-sibling::mat-label[contains(text(),'Account Holder')]]"
    		);

    	

    	
    public static final By Verify_Account_Btn = By.xpath("//button[contains(@class, 'submit-button') and normalize-space(text())='Verify Account']");
    
 // Locates the Close button/link at the bottom of the Banking Details modal popup
    public static final By Close_Modal_Button = By.xpath(
        "//button[normalize-space()='Close'] | " +
        "//a[normalize-space()='Close'] | " +
        "//*[@role='dialog']//*[normalize-space()='Close'] | " +
        "//mat-dialog-actions//*[normalize-space()='Close']"
    );
 // Locates the enabled 'Submit' menu item inside an opened Angular mat-menu
    public static final By Submit_Menu_Option = By.xpath(
        "//div[contains(@class, 'mat-menu-panel')]//button[not(@disabled) and normalize-space(text())='Submit']"
    );
    
 // Locates the Angular mat-select dropdown
    public static final By Approval_Status_Dropdown = By.xpath("//mat-select[.//span[contains(@class, 'mat-select-placeholder') or contains(@class, 'mat-select-value-text')]] | //mat-select[@formcontrolname='status' or @id='status']");

    // Locates the 'Approve' option inside the opened mat-option list overlay
    public static final By Approve_Option = By.xpath("//mat-option//span[normalize-space(text())='Approve'] | //mat-option[normalize-space(.)='Approve']");

    // Locates the note text area
    public static final By Note_Textarea = By.xpath("//textarea[@id='code' or @name='noteText' or @formcontrolname='noteText']");
 // Locates the Save button on the form
    public static final By Save_Button = By.xpath("//button[contains(@class,'submit-button') and normalize-space(text())='Save']");
    
    public static final By CART_TOTAL_TEXT = By.xpath("//*[@id='root']/div/div/div[25]/div/div/div[1]/span[2]");
    public static final By CART_UI_TOTAL = By.xpath("//*[@id='root']/div/div/div[25]/div/div/div[2]/span[2]");

    // Dynamic Locator Builders (Kept pure, no logic)
    public static By getProductAddBtn(int index) { 
        return By.xpath("//*[@id='root']/div/div[3]/div[" + index + "]/div[2]/button"); 
    }
    
    public static By getProductPrice(int index) { 
        return By.xpath("//*[@id='root']/div/div/div[" + index + "]/p"); 
    }
}