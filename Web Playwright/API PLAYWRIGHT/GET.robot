*** Settings ***
Library    RequestsLibrary
Library    Collections
Library    DatabaseLibrary

# Using your AJM Postgres credentials
Suite Setup       Connect To Database    psycopg2    AJM    postgres    ARENAHOJUSKA    localhost    5432
Suite Teardown    Disconnect From Database

*** Variables ***
${FULL_URL}    http://localhost:8080/api/employees

*** Test Cases ***
Comprehensive Employee API and Database Validation
    [Documentation]    Validates API Response against PostgreSQL Database using modern keywords.
    Create Session    local_api    ${FULL_URL}

    # 1. GET DATA FROM API
    ${response}=    GET On Session    local_api    url=    expected_status=200
    ${json_body}=   Set Variable    ${response.json()}

    # use first employee from the API list to use for DB comparison
    ${api_emp}=     Set Variable    ${json_body[0]}
    ${api_id}=      Get From Dictionary    ${api_emp}    id
    ${api_name}=    Get From Dictionary    ${api_emp}    firstName

    # 2. DATABASE VALIDATION (The "Double Check")
    #  use ILIKE for case-insensitive matching in Postgres
    Row Count Is Greater Than X    SELECT * FROM employees WHERE id = ${api_id} AND first_name ILIKE '${api_name}'    0

    # 3. CROSS-VERIFY SPECIFIC DATA
    ${db_result}=    Query    SELECT first_name FROM employees WHERE id = ${api_id}
    # Compare API name to Database name (db_result is a list of tuples)
    Should Be Equal As Strings    ${api_name}    ${db_result[0][0]}
    Log To Console    \nVerified: API Name '${api_name}' matches Database Record for ID ${api_id}

    # 4. PERFORMANCE & TYPE CHECK
    ${speed}=    Set Variable    ${response.elapsed.total_seconds()}
    Should Be True    ${speed} < 1.0
    Log To Console    API Speed: ${speed}s | Data Integrity: Confirmed