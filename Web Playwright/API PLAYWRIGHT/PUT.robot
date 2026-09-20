*** Settings ***
Library    RequestsLibrary
Library    Collections
Library    DataGenerator.py

*** Variables ***
${BASE_URL}    http://localhost:8080/api/employees

*** Test Cases ***
Update Existing Employee Via PUT
    [Documentation]    Updates an employee and verifies the change.
    Create Session    local_api    ${BASE_URL}

    # 1. we need an ID to update.
    ${target_id}=    Set Variable    1

    # 2. Generate new random data for the update
    ${new_data}=    Generate Random Employee Data
    Log To Console    \nUpdating ID ${target_id} with: ${new_data}

    # 3. Execute PUT Request to /api/employees/1
    ${headers}=    Create Dictionary    Content-Type=application/json
    ${response}=    PUT On Session    local_api    url=/${target_id}    json=${new_data}    headers=${headers}

    # 4. Verifications
    Should Be Equal As Numbers    ${response.status_code}    200
    ${json}=    Set Variable    ${response.json()}

    # Verify the name actually changed to the random one
    Should Be Equal    ${json}[firstName]    ${new_data}[firstName]
    Log To Console     Update Successful for ID: ${target_id}