
*** Settings ***
Library    Browser
Library    AdderLibrary.py
Library    OperatingSystem
Library    String
Library    BuiltIn
Library    DateTime

*** Variables ***
${EXCEL PATH}       C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/Web_Automation/Book11.xlsx
${SHEET NAME}       Sheet2
${BROWSER}          chromium
${URL}              https://www.saucedemo.com/v1/
${LOOP COUNT}       sum
${EVIDENCE ROOT}    Web_Automation/Test_Evidence

*** Test Cases ***
Invalid Login
    ${count}=    Load Credentials    ${EXCEL PATH}    ${SHEET NAME}
    ${sum}=      Add Two Numbers     ${count}    2

    ${today}=     Get Current Date    result_format=%Y-%m-%d
    ${folder}=    Set Variable    ${EVIDENCE ROOT}/${today}
    Create Directory    ${folder}

    FOR    ${index}    IN RANGE    ${sum}
        New Browser    ${BROWSER}
        New Context
        New Page    ${URL}

        ${msg}=    Load Credentials    ${EXCEL PATH}    ${SHEET NAME}
        ${username}    ${password}=    Get Credential By Index    ${index}

        Wait For Elements State    id=user-name    visible    timeout=10s
        Fill Text    id=user-name    ${username}
        Wait For Elements State    id=password    visible    timeout=10s
        Fill Text    id=password    ${password}

        ${clean_name}=    Replace String    ${TEST NAME}    ${SPACE}    _
        ${file}=          Set Variable      ${CURDIR}/${folder}/${clean_name}_${index + 1}.png

        Take Screenshot    path=${file}

        Click    id=login-button

        Take Screenshot    path=${file}

        Sleep    15s
        ${actual_title}=    Get Title
        Should Not Be Equal    ${actual_title}    Swag Lab

        Close Browser
    END

    Log    Outside loop
