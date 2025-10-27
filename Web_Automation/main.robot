*** Settings ***
Library    DataDriver    file=Web_Automation/Book11.xlsx    file_search_strategy=None
Resource    login.robot
Resource    addTocart.robot
Resource    Data_driven_testing_invalid_Login.robot
Resource    login_test.robot
Resource    Data_driven_testing_invalid_Login.robot
Library    OperatingSystem

*** Keywords ***
Run PDF Combiner Script
     ${script}=    Set Variable    ${CURDIR}/testEvidencepdf.py
    ${output}=    Run    python ${script}
    Log    ${output}

*** Test Cases ***

Enter Credentials and LoginPart
    Invalid Login
    Close Browser
    Run PDF Combiner Script
addTocart
    Valid LogintEST
    AddTocart
    Close Browser
    Run PDF Combiner Script
Valid_Login
    Valid LogintEST
    Close Browser
    Run PDF Combiner Script