*** Settings ***
Library    RequestsLibrary
Library    Collections

Library    DataGenerator.py

*** Variables ***
${BASE_URL}    http://localhost:8080/api/employees

*** Test Cases ***
Create New Employee Via POST
    [Documentation]    Validates JSON, Status 201, Body, Data Types, and Speed using Random Data.
    Create Session    local_api    ${BASE_URL}

    # 1. GENERATE DYNAMIC DATA

    ${body}=    Generate Random Employee Data
    Log To Console    \nGenerated Data: ${body}

    ${headers}=    Create Dictionary    Content-Type=application/json

    # 2. EXECUTE POST REQUEST
    ${response}=    POST On Session    local_api    url=    json=${body}    headers=${headers}    expected_status=201

    # 3. VERIFY STATUS CODE (201 Created)
    Should Be Equal As Numbers    ${response.status_code}    201

    # 4. VERIFY RESPONSE IS JSON
    Dictionary Should Contain Item    ${response.headers}    Content-Type    application/json
    ${json_response}=    Set Variable    ${response.json()}

    # 5. VERIFY THE BODY (Echoed Data)

    ${created_id}=    Get From Dictionary    ${json_response}    id

    # Compare response values against the randomly generated input values
    Should Be Equal    ${json_response}[firstName]    ${body}[firstName]
    Should Be Equal    ${json_response}[lastName]     ${body}[lastName]
    Should Be Equal    ${json_response}[email]        ${body}[email]

    Log To Console     \nSuccessfully created Employee ID: ${created_id}
    Log To Console     Email used: ${json_response}[email]

    # 6. VERIFY DATA TYPES
    ${id_type}=       Evaluate    type($json_response['id']).__name__
    ${email_type}=    Evaluate    type($json_response['email']).__name__

    Should Be Equal    ${id_type}       int
    Should Be Equal    ${email_type}    str

    # 7. VERIFY SPEED
    ${speed}=    Set Variable    ${response.elapsed.total_seconds()}
    Log To Console    Creation Speed: ${speed}s
    # Keeping your 20.5s threshold, though local APIs are usually < 0.5s
    Should Be True    ${speed} < 20.5    POST request was too slow: ${speed}s