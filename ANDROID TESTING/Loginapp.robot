*** Settings ***
Library    AppiumLibrary
Library    OperatingSystem
Library    String
Library    BuiltIn
Library    DateTime
Library    Process
Library    AdderLibrary.py    # contains Load Credentials & Get Credential By Index
Resource    open.robot

*** Variables ***
${EXCEL PATH}    C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/ANDROID TESTING/Book11.xlsx
${SHEET NAME}    Sheet1

*** Test Cases ***
Loginn
    ${count}=    Load Credentials    ${EXCEL PATH}
    ${sum}=      Add Two Numbers    ${count}    0

    FOR    ${index}    IN RANGE    ${sum}
        Open_App
        ${username}    ${password}=    Get Credential By Index    ${index}


        Wait Until Element Is Visible    xpath=//android.widget.ImageView[@content-desc="Cart"]    10s
        Click Element    xpath=//android.widget.ImageView[@content-desc="Cart"]

        Wait Until Element Is Visible    xpath=//android.widget.EditText[@resource-id="fi.android.takealot:id/input_field_text_input" and @text="Email Address"]    10s
        Input Text    xpath=//android.widget.EditText[@resource-id="fi.android.takealot:id/input_field_text_input" and @text="Email Address"]    ${username}

        Wait Until Element Is Visible    xpath=//android.widget.EditText[@resource-id="fi.android.takealot:id/input_field_text_input" and @text="Password"]    10s
        Input Text    xpath=//android.widget.EditText[@resource-id="fi.android.takealot:id/input_field_text_input" and @text="Password"]    ${password}

        Click Element    //android.widget.Button[@resource-id="fi.android.takealot:id/auth_login_dynamic_form_call_to_action"]






    END

    Log    Finished testing all users
