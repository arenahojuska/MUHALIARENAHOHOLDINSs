*** Settings ***
Library    SeleniumLibrary
Library    AdderLibrary.py
Library           OperatingSystem
Library           String
Library           BuiltIn
Library    DateTime
Library    Process


*** Variables ***
${EXCEL PATH}    C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/Web_Automation/Book11.xlsx
${SHEET NAME}    Sheet1
${BROWSER}       edge
${URL}           https://www.saucedemo.com/v1/
${LOOP COUNT}    sum
${EVIDENCE ROOT}    Web_Automation/Test_Evidence



*** Keywords ***
Valid LogintEST
    ${count}=    Load Credentials    ${EXCEL PATH}
    ${sum}=      Add Two Numbers      ${count}    2

    ${today}=     Get Current Date    result_format=%Y-%m-%d
    ${folder}=    Set Variable    ${EVIDENCE ROOT}/${today}
    Create Directory    ${folder}
    FOR     ${index}    IN RANGE    ${sum}
        Open Browser    ${URL}    ${BROWSER}
        Maximize Browser Window
        Title Should Be    Swag Labs


        ${msg}=    Load Credentials    ${EXCEL PATH}


        ${username}    ${password}=    Get Credential By Index    ${index}


        Wait Until Element Is Visible    id=user-name    10s
        Input Text    id=user-name    ${username}
        Wait Until Element Is Visible    id=password     10s
        Input Text    id=password     ${password}
        ${random}=    Generate Random String    8
        ${file}=      Set Variable    ${folder}/${random}
       ${file}=    Set Variable    ${CURDIR}/${folder}/${TEST NAME}_${index + 1}.png

        Capture Page Screenshot    ${file}


        Click Button    id=login-button

        Capture Page Screenshot    ${file}
         Title Should Be    Swag Labs

        Sleep    5s


    END

    Log    Outside loop
