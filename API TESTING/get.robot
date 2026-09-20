*** Settings ***
Library    RequestsLibrary
Library    BuiltIn
Library    Collections


*** Variables ***
${base_url}          https://pokeapi.co
${endpoint}          /api/v2/pokemon/ditto
${invalid_endpoint}  /api/v2/version/9999

*** Keywords ***
Verify_Response_Is_JSON
    Create Session    mysession    ${base_url}
    ${response}=      GET On Session    mysession    ${endpoint}

    # Verify status code
    Should Be Equal As Numbers    ${response.status_code}    200

    # Get Content-Type header directly from headers dictionary
    ${content_type}=    Get From Dictionary    ${response.headers}    Content-Type
    Should Contain    ${content_type}    application/json
    #Log To Console    ✅ Response status and Content-Type verified
      # Parse response body as JSON
    ${json}=    Evaluate    json.loads("""${response.text}""")    json

    # Log parsed JSON to console
    #Log To Console    ✅ Response status and Content-Type verified
    #Log To Console    ${json}
    # Verify response time is less than 200ms
    ${elapsed}=    Evaluate    ${response.elapsed.total_seconds()} * 1000
    Should Be True    ${elapsed} < 900    Response took too long: ${elapsed}ms


Verify Invalid Pokemon Status Code
    Create Session    mysession_invalid    ${BASE_URL}
    ${invalid_response}=    GET On Session    mysession_invalid    ${INVALID_ENDPOINT}    expected_status=ANY

    Should Be Equal As Numbers    ${invalid_response.status_code}    404

    #Log To Console    ✅ Correct status code (404) received for invalid request: ${invalid_response.status_code}
Test Response Header
    Create Session    mysession    ${base_url}
    ${response}=    GET On Session    mysession    ${endpoint}

    # Access Content-Type header directly from response.headers dictionary
    ${content_type}=    Get From Dictionary    ${response.headers}    Content-Type

    Log To Console    Content-Type: ${content_type}
