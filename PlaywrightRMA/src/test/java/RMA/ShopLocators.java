package RMA;

public class ShopLocators {
    // Login Elements
    public static final String EMAIL_FIELD = "#email";
    public static final String PASSWORD_FIELD = "#password";
    public static final String LOGIN_BTN = "xpath=/html/body/app-root/app-sign-in/body/div[1]/form/button";
    public static final String BODY_TAG = "body";
    public static final String Clientcare_LINK = "xpath=/html/body/app-root/app-host/app-left-menu/div/div/mat-accordion/mat-expansion-panel[2]";

    // Shop Navigation & Headers
   
   // public static final String PolicyManager_button = "xpath=//*[@id=\"cdk-accordion-child-1\"]/div/div[4]/a/span";
    public static final String PolicyManager_button = "xpath=//span[@class='title-description' and contains(text(), 'Policy Manager')]";
 // Strictly targets buttons containing 'Upload' or 'Group Members' while excluding 'Case'
    public static final String Upload_button = "xpath=" +
        "//button[contains(translate(normalize-space(.), 'UPLOAD', 'upload'), 'upload') and not(contains(., 'Case'))] | " +
        "//span[contains(translate(normalize-space(.), 'UPLOAD', 'upload'), 'upload')]//ancestor-or-self::button";

    public static final String Group_button = "xpath=" +
        "//button[contains(translate(normalize-space(.), 'GROUP MEMBERS', 'group members'), 'group members') and not(contains(., 'Case'))] | " +
        "//span[contains(translate(normalize-space(.), 'GROUP MEMBERS', 'group members'), 'group members')]//ancestor-or-self::button";
    public static final String Funeral_button = "xpath=//button[normalize-space(.)='Funeral']";
    //public static final String Funeral_button = "xpath=//button[normalize-space(.)='Access Bank']";
    
    public static String getPolicyTypeButton(String buttonText) {
        return "xpath=//button[@mat-menu-item and contains(normalize-space(.),'" + buttonText + "')]";
    }
    
    public static final String NewPolicy_button = "xpath=(//mat-radio-button)[1]";
    public static final String Fallback_Checkbox = "xpath=//span[contains(@class, 'mat-checkbox-inner-container')] | //input[@type='checkbox' and contains(@id, 'mat-checkbox')]";
    public static final String ChooseFile_button = "xpath=//input[@type='file']";
    
    public static final String UploadFilee_button = "xpath=//button[text()='Upload File']";
    
    public static final String Reminder_alert = "xpath=//*[@id=\"mat-dialog-2\"]/ng-component/div/span[2]";
 // Better header locator targeting the text node inside the header cell
    public static final String OverallSLA_Header = "xpath=//mat-header-cell[contains(., 'Overall SLA')]";
 // Locates the 'open' link inside the SPECIFIC row where the Type cell contains text like 'Onboarding' or 'Manage Group Risk'
 // Targets an 'open' link strictly inside a row where the SLA tracker is less than 2 minutes old
    public static final String TargetRowOpen_link = "xpath=" +
        "//mat-row[mat-cell[contains(., '00 days,00 hrs,00 mins') or contains(., '00 days,00 hrs,01 mins')]]//a[text()='open']";  
    public static final String FirstRow_Name = "xpath=(//mat-table/mat-row)[1]/mat-cell[contains(@class, 'mat-column-Name')] | (//mat-row)[1]/mat-cell[2]";
    public static final String FirstRow_SLA = "xpath=(//mat-table/mat-row)[1]/mat-cell[contains(@class, 'mat-column-Overall-SLA')] | (//mat-row)[1]/mat-cell[7]";
 // Locates the 'Select' action link by its unique ID
    public static final String Select_BtnAction = "xpath=//a[@id='btnAction' or normalize-space(text())='Select']";
 
    public static final String FirstRowOpen_link = "xpath=(//mat-table/mat-row)[1]/mat-cell[8]/a | (//mat-row)[1]//a[text()='open']";    
    public static final String Continue_button = "xpath=//button[text()=' Continue ' or contains(text(), 'Continue')]";
 // Locates specifically the FIRST ID Number cell in the Angular Material table
    public static final String First_ID_Number_Text = "xpath=(//mat-cell[contains(@class, 'mat-column-idNumber')])[1]";
    public static final String Next_button = "xpath=//button[text()='Next' or contains(text(), 'Next')]";
 // Locates all Angular Material tab headers dynamically
    
 // Strictly targets actual individual tab labels, ignoring tab header wrappers
    public static final String All_Tab_Labels = "xpath=//div[@role='tab']//div[contains(@class, 'mat-tab-label-content')]";

    public static final String MoreVert_menu = "xpath=//span[text()='more_vert'] | //mat-icon[text()='more_vert'] | //*[contains(@class, 'more_vert')]";

    // Targets the dropdown menu item button containing the 'Override VOPD' text
    public static final String OverrideVOPD_button = "xpath=//button[contains(., 'Override VOPD')]";
 // Targets the radio button input container (safer to check selection state on the parent/input)
    public static final String Target_RadioButton = "xpath=//mat-radio-button[contains(@class, 'mat-radio-checked')] | //span[@class='mat-radio-inner-circle']";

    // Targets the material icon labeled 'upload'
    public static final String InlineUpload_Icon = "xpath=//span[contains(@class, 'material-icons') and text()='upload']";

    // Targets the 'Choose files' trigger element link
    public static final String ChooseFiles_link = "xpath=//a[@name='openFile' or contains(text(), 'Choose files')]";

    public static final String GreenUpload_Icon = "xpath=//span[text()='upload' or contains(text(),'upload')][not(ancestor::mat-header-cell)]";

    // Targets the Submit button text wrapper
    public static final String Submit_button = "xpath=//button[text()='Submit' or contains(text(), 'Submit')]";
 // Targets the Request Approval button cleanly by its visible text label
    public static final String RequestApproval_button = "xpath=//button[contains(@class, 'submit-button') and text()='Request Approval']";
    
    public static final String ApprovalStatus_Message = "xpath=//span[contains(@class, 'valid') and contains(text(), 'Awaiting approval someone with permission')]";
 // Targets the card title div by matching the static starting string pattern
 // Uses the dot operator (.) instead of text() to read across the entire inner element content safely
   
    public static final String PolicyReference_Text = "xpath=//mat-card-content/div/div[1]";
    public static final String SearchText_Field = "#searchText";
    
    public static final String OpenLink_Button = ".link-edit";
    public static final String ViewSubmit_Button = "xpath=//button[contains(@class, 'submit-button') and text()='View']";
    public static final String Approve_Button = "xpath=//button[contains(@class, 'submit-button') and text()='Approve']";
    public static final String YesApprove_Button = "xpath=//button[contains(@class, 'submit-button') and (text()='Yes, Approve' or contains(., 'Yes, Approve'))]";
 // Locates the 'Search' button
    public static final String SearchH_Button = "xpath=" +
        "//button[.//span[normalize-space(text())='Search']] | " +
        "//span[contains(@class, 'mat-button-wrapper') and normalize-space(text())='Search']//ancestor-or-self::button";

    // Locates the Search Term input field
    public static final String Search_Term_Input = "xpath=//input[@id='searchTerm' or @name='searchTerm']";
    
    // Claim Care module 
    public static final String ClaimCare_button = "xpath=//span[contains(@class, 'menu-title') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'claimcare')]";
    
    public static final String FuneralWorkPool_span = "xpath=//span[contains(@class, 'title-description') and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'funeral work pool')]";
    public static final String RegisterFuneralClaim_menuitem = "xpath=//button[@role='menuitem' and contains(., 'Register Funeral Claim')]";
    
    public static final String Search_Input_Field = "#query";
    
    public static final String Claims_Dropdown_Trigger = "xpath=//button[./span[contains(text(), 'Claims')]]";
    
    public static final String Select_Action_Link = "#btnAction";
    
 // Dropdown Locators
    public static final String TypeOfDeath_Dropdown = "#filter";
    public static final String Mat_Options = "mat-option";

    // Datepicker Locators
    public static final String DateNotified_Calendar_Btn = "xpath=//mat-form-field[.//label[contains(.,'Date Notified') or contains(text(),'Date Notified')]]//mat-datepicker-toggle//button | //div[contains(text(),'Date Notified')]/following-sibling::div//mat-datepicker-toggle//button | //mat-form-field[contains(.,'Date Notified')]//mat-datepicker-toggle//button";
    public static final String DateDeath_Calendar_Btn = "xpath=//mat-form-field[.//label[contains(.,'Date of Death') or contains(text(),'Date of Death')]]//mat-datepicker-toggle//button | //div[contains(text(),'Date of Death')]/following-sibling::div//mat-datepicker-toggle//button | //mat-form-field[contains(.,'Date of Death')]//mat-datepicker-toggle//button";
    public static final String Calendar_Today_Cell = ".mat-calendar-body-today, .mat-calendar-body-active";
    public static final String RegisterNotifier_button = "xpath=//button[contains(text(), 'Register Notifier')]";
    
 // Text Inputs
    public static final String Notifier_FirstName_Input = "#claimantFirstName";
    public static final String Notifier_LastName_Input = "#claimantLastName";
    public static final String Notifier_IdentityNumber_Input = "#claimantIdentityNumber";
    public static final String Notifier_Mobile_Input = "#claimantMobileNumber";
    public static final String Notifier_Email_Input = "#claimantEmail";

    // Dropdowns (Targeting mat-select triggers specifically)
    public static final String RelationToDeceased_Dropdown = "xpath=//mat-select[@formcontrolname='relation' or @formcontrolname='claimantRelation' or contains(@formcontrolname,'relation') or contains(@formcontrolname,'Relation')] | (//mat-select)[1]";
    public static final String PreferredMethodContact_Dropdown = "xpath=//mat-select[@formcontrolname='preferredCommunicationMethod' or @formcontrolname='communicationMethod' or contains(@formcontrolname,'preferred') or contains(@formcontrolname,'communication')] | (//mat-select)[2]";

    // Angular Overlay Options (presence-based)
    public static final String Mat_Option_First = "mat-option";
    public static final String Mat_Option_Email = "xpath=//mat-option[contains(., 'Email')]";
    public static final String Register_Final_Button = "xpath=//button[contains(@class,'button') and text()='Register'] | //button[normalize-space()='Register']";
 // Confirmation Tab Locators
    public static final String Confirmation_ClaimNumber_Cell = "xpath=//th[normalize-space()='Claim Number']/ancestor::table//tr/td[4] | //td[preceding-sibling::td[contains(.,'Life Insured')]]";
    public static final String Confirmation_Done_Button = "xpath=//button[normalize-space()='Done']";
    
 // Search Field & Submit Button Locators
    public static final String Search_Input_Fieldd = "xpath=//input[@name='searchWord'] | //input[@id='mat-input-3'] | //input[@matinput and @name='searchWord']";
    public static final String Search_Button = "xpath=//button[contains(.,'Search') or @type='submit'] | //mat-icon[contains(text(),'search')]/ancestor::button | //button[contains(@class,'search')]";
    
 // Actions Menu Locators
    public static final String More_Actions_Menu_Icon = "xpath=//span[contains(@class,'material-icons') and text()='more_vert'] | //span[normalize-space()='more_vert']";
    public static final String Menu_Option_View = "xpath=//button[@role='menuitem' and (contains(.,'View') or text()='View')] | //button[contains(@class,'mat-menu-item') and contains(.,'View')]";
 // Locates the 'Decision' option inside the Angular Material menu popover
    public static final String Decision_Menu_Option = "xpath=//button[@role='menuitem' and contains(normalize-space(text()), 'Decision')] | //button[contains(@class,'mat-menu-item') and contains(., 'Decision')]";
 // Navigation / View Screen Locators
    public static final String Continue_Button = "xpath=//button[contains(@class,'submit-button') and normalize-space()='Continue'] | //button[normalize-space()='Continue']";
    
 /// Generic Locators
    public static final String All_Mat_Selects = "xpath=//mat-select | //div[contains(@class,'mat-select-trigger')]";
    public static final String All_Checkboxes = "xpath=//mat-checkbox | //input[@type='checkbox']/ancestor::mat-checkbox | //span[contains(@class,'mat-checkbox-inner-container')]";
    public static final String Mat_Option_Index_1 = "xpath=(//mat-option)[1] | (//span[contains(@class,'mat-option-text')])[1]";

    // Stepper Navigation Buttons
    public static final String Next_Button = "xpath=//button[normalize-space()='Next'] | //button[contains(@class,'button') and text()='Next']";
    public static final String Finish_Button = "xpath=//button[normalize-space()='Finish'] | //button[contains(@class,'submit-button') and text()='Finish']";
    
 // Locates either "Add Banking Detail" or "Update Banking Detail" dynamically
    public static final String Add_Or_Update_Banking_Menu_Option = "xpath=" +
        "//button[@role='menuitem' and (contains(normalize-space(.), 'Update Banking') or contains(normalize-space(.), 'Add Banking'))] | " +
        "//button[contains(@class,'mat-menu-item') and (contains(., 'Update Banking') or contains(., 'Add Banking'))]";
 // Locates the 'Yes' floating confirmation button safely
    public static final String Yes_Button = "xpath=//button[contains(@class, 'button') and normalize-space(text())='Yes'] | //button[normalize-space()='Yes']";
    
 // Locates the Account Number input field
    public static final String Account_Number_Input = "xpath=//input[@id='accountNumber' or @name='accountNumber']";
 // Locates the 'Select' link button
    public static final String Select_Link = "xpath=//a[contains(@class, 'link-edit') and normalize-space(text())='Select'] | //a[@role='button' and normalize-space()='Select']";
    
    public static final String CART_TOTAL_TEXT = "xpath=//*[@id='root']/div/div/div[25]/div/div/div[1]/span[2]";
    public static final String CART_UI_TOTAL = "xpath=//*[@id='root']/div/div/div[25]/div/div/div[2]/span[2]";

    // Dynamic Locator Builders (Kept pure, no logic)
    public static String getProductAddBtn(int index) { 
        return "xpath=//*[@id='root']/div/div3]/div[" + index + "]/div[2]/button"; 
    }
    
    public static String getProductPrice(int index) { 
        return "xpath=//*[@id='root']/div/div/div[" + index + "]/p"; 
    }
    
    
    public static final String Reports_Manager_button =
            "//span[contains(@class,'title-description') and normalize-space()='Reports Manager']";   
    
 // Target mat-select dropdown containers (or specifically by your placeholder class)
    public static final String Dynamic_Dropdown_Trigger = "//mat-select[.//span[contains(@class, 'mat-select-placeholder')]]";

    // Options rendered in the Angular Material overlay panel
 // Target mat-option nodes directly without selecting internal child spans separately
    public static final String Mat_Options_List = "//div[contains(@class, 'cdk-overlay-container')]//mat-option";
    
 // Option selection inside Angular overlay
    

    // Date input field associated with the datepicker toggle button
    public static final String Date_Input = "//input[contains(@class, 'mat-datepicker-input')]";
 // Calendar icon button trigger
 // Open calendar button using aria-label
    public static final String Calendar_Toggle = "//button[@aria-label='Open calendar']";

    // Actuarial Report option
    public static final String Actuarial_Report_Option = "//mat-option[.//span[normalize-space(text())='Actuarial Report']]";

    // View button
    public static final String View_Button = "//button[contains(@class, 'submit-button') and normalize-space(text())='View']";
 // Calendar view control locators
 // Previous month arrow button in Angular Material Datepicker
    public static final String Calendar_Previous_Month_Btn = "//button[contains(@class, 'mat-calendar-previous-button')] | //button[@aria-label='Previous month']";
    public static final String Calendar_Period_Button = "//button[contains(@class, 'mat-calendar-period-button')]";
 // Locates the specific Angular Material dropdown trigger
    public static final String Target_Dropdown = "//mat-select[.//span[contains(@class, 'mat-select-placeholder')]]";

    // Locates all visible option items in the opened Angular overlay container
    public static final String Dropdown_Options = "//div[contains(@class, 'cdk-overlay-container')]//mat-option";
 // Handles standard DOM or deep shadow DOM download buttons
    public static final String Download_PDF_Button = "cr-icon-button#save, button#save, [aria-label='Download']";
}