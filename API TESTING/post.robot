*** Settings ***
Library           RequestsLibrary
Library           JSONLibrary
Library           OperatingSystem
Library           Collections
Library           BuiltIn
Library           String
Library           DateTime
Library           Process

*** Variables ***
${BASE4}           https://restful-booker.herokuapp.com
${BOOKING_EP}     /booking
${JSON_FILE}      C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/API TESTING/booking_data.json

${USERNAME}       admin
${PASSWORD}       password123

*** Keywords ***
Postt
    Create Session    mysession    ${BASE4}

    # Encode credentials for Basic Auth header
    ${credentials}=    Catenate    SEPARATOR=    ${USERNAME}    :    ${PASSWORD}
    ${auth}=    Evaluate    __import__('base64').b64encode("${credentials}".encode('utf-8')).decode('utf-8')
    ${auth_header}=    Set Variable    Basic ${auth}

    ${json_text}=    Get File    ${JSON_FILE}
    ${payload_list}=    Evaluate    json.loads('''${json_text}''')    json
    ${length}=    Get Length    ${payload_list}
    Log To Console    🔁 Creating and updating ${length} bookings...

    FOR    ${index}    IN RANGE    ${length}
        ${payload}=    Get From List    ${payload_list}    ${index}

        # Set headers including auth
        ${headers}=    Create Dictionary
        ...    Content-Type=application/json
        ...    Accept=application/json
        ...    Authorization=${auth_header}

        # Create booking to get a bookingid
        ${response}=    POST On Session    mysession    ${BOOKING_EP}    json=${payload}    headers=${headers}
        Should Be Equal As Numbers    ${response.status_code}    200
        ${booking}=    To JSON    ${response.content}
        ${booking_id}=    Get From Dictionary    ${booking}    bookingid

        # Send PUT request to update booking (reuse same payload)
        ${put_response}=    PUT On Session    mysession    ${BOOKING_EP}/${booking_id}    json=${payload}    headers=${headers}
        Should Be Equal As Numbers    ${put_response.status_code}    200

        Log To Console    ✅ Booking ${booking_id} created and updated successfully
    END

    Log To Console    ✅✅✅ ALL BOOKINGS CREATED, UPDATED AND VERIFIED SUCCESSFULLY ✅✅✅
