*** Settings ***
Library           RequestsLibrary
Library           Collections
Library           BuiltIn

*** Variables ***
${BASE}           https://restful-booker.herokuapp.com
${BOOKING_EP}     /booking
${USERNAME}       admin
${PASSWORD}       password123

*** Test Cases ***
Delete Booking By ID
    Create Session    mysession    ${BASE}    verify=False

    # Encode credentials for Basic Auth
    ${credentials}=    Catenate    SEPARATOR=    ${USERNAME}    :    ${PASSWORD}
    ${auth}=    Evaluate    __import__('base64').b64encode("${credentials}".encode('utf-8')).decode('utf-8')
    ${headers}=    Create Dictionary    Authorization=Basic ${auth}    Content-Type=application/json

    # Replace 1 with a valid booking id
    ${response}=    DELETE On Session    mysession    ${BOOKING_EP}/1    headers=${headers}
    Should Be Equal As Numbers    ${response.status_code}    201
    Log To Console    ✅ Booking 1 deleted successfully
