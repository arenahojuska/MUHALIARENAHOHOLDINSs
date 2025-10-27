*** Settings ***
Library    SeleniumLibrary
Library    AdderLibrary.py
Library    OperatingSystem
Library    String
Library    BuiltIn
Library    DateTime
Library    Process


Suite Setup    Setup Evidence Folder

*** Variables ***
${EXCEL PATH}        C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/Web_Automation/Book11.xlsx
${SHEET NAME}        Sheet1
${BROWSER}           edge
${URL}               https://www.saucedemo.com/v1/
${EVIDENCE ROOT}     Web_Automation/Test_Evidence

*** Keywords ***

Setup Evidence Folder
    ${today}=     Get Current Date    result_format=%Y-%m-%d
    ${folder}=    Set Variable    ${EVIDENCE ROOT}/${today}
    Set Suite Variable    ${FOLDER}    ${folder}
    Create Directory    ${FOLDER}

Given the user opens the login page
    Open Browser    ${URL}    ${BROWSER}
    Maximize Browser Window
    Title Should Be    Swag Labs

When the user logs in with valid credentials from Excel
    ${count}=    Load Credentials    ${EXCEL PATH}
    ${sum}=      Add Two Numbers      ${count}    2
    Log          Sum: ${sum}
    ${msg}=    Load Credentials    ${EXCEL PATH}
    Log         ${msg}
    ${username}    ${password}=    Get Credential By Index    0
    Log    Username: ${username}
    Log    Password: ${password}
    Input Text    id=user-name    ${username}
    Input Text    id=password     ${password}
    Click Button  id=login-button

Then the user should be logged in and screenshot is captured
    ${random}=    Generate Random String    8
    ${file}=      Set Variable    ${CURDIR}/${FOLDER}/${TEST NAME}_${random}.png
    Capture Page Screenshot    ${file}
    Title Should Be    Swag Labs
    Sleep    3s
