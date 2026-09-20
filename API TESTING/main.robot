*** Settings ***
Library    DataDriver    file=Web_Automation/Book11.xlsx    file_search_strategy=None
Resource    perfomance.robot
Resource    get.robot
Resource    post.robot
Resource    Put.robot
Resource    delete.robot
Library    OperatingSystem

*** Keywords ***
Run PDF Combiner Script
     ${script}=    Set Variable    ${CURDIR}/testEvidencepdf.py
    ${output}=    Run    python ${script}
    Log    ${output}

*** Test Cases ***

Get
    Verify_Response_Is_JSON
    Verify_Invalid_Pokemon_Status_Code
    Test Response Header

Perfomance Testing
    Performance Test

Post Request
    Postt

Update Bookings
    Update Bookings From JSON

Delete from the API
    Delete