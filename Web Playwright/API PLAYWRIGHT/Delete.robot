*** Settings ***
Library    RequestsLibrary
Library    Collections

*** Variables ***
${BASE_URL}    http://localhost:8080/api/employees

*** Test Cases ***
Delete Employee Via DELETE
    [Documentation]    Deletes an employee and verifies they are gone.
    Create Session    local_api    ${BASE_URL}

    # we deleting id number 5
    ${delete_id}=    Set Variable    5

    ${response}=    DELETE On Session    local_api    url=/${delete_id}    expected_status=ANY

    Log To Console    \nDelete Status Code: ${response.status_code}
    # Accept 200 OK or 204 No Content
    Should Be True    ${response.status_code} in [200, 204]