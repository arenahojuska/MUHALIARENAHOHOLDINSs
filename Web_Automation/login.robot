*** Settings ***

Library    screenshot.py
Variables  C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/Web Playwright/Test Cases/testdata.py
Library    Browser    enable_auto_screenshot=${False}

*** Test Cases ***
Login Validation With IF ELSE And Screenshots
    # ------------------ BROWSER SETUP ------------------
    New Browser    chromium    headless=False
    New Context    viewport={'width': 1920, 'height': 1300}
    New Page    ${BASE_URL}    wait_until=domcontentloaded

    # Use the prefix 'screenshot.' to call YOUR Python function
    screenshot.Take Screenshot

    # ------------------ TITLE VALIDATION ------------------
    ${title}=    Get Title
    Log To Console    Page title is: ${title}

    IF    '${title}' == '${EXPECTED_TITLE}'
        Log To Console    ✅ Title matched
        screenshot.Take Screenshot
    ELSE
        Log To Console    ❌ Title mismatch
        screenshot.Take Screenshot
        Fail    Title validation failed
    END

    # ------------------ EMAIL FIELD VALIDATION ------------------
    ${email_visible}=    Run Keyword And Return Status
    ...    Wait For Elements State    ${EMAIL_FIELD}    visible    timeout=10s

    IF    ${email_visible}
        Log To Console    ✅ Email field is visible
        screenshot.Take Screenshot
        Fill Text    ${EMAIL_FIELD}    ${ADMIN_USER}
    ELSE
        Log To Console    ❌ Email field not visible
        screenshot.Take Screenshot
        Fail    Email field not found
    END

    # [Repeat this 'screenshot.' prefix for the rest of your IF/ELSE blocks]

    [Teardown]    Close Browser

*** Test Cases ***
Login Validation With IF ELSE And Screenshots
    # ------------------ BROWSER SETUP ------------------
    New Browser    chromium    headless=False
    New Context    viewport={'width': 1920, 'height': 1300}
    New Page    ${BASE_URL}    wait_until=domcontentloaded

    # Use the prefix 'screenshot.' to call YOUR Python function
    screenshot.Take Screenshot

    # ------------------ TITLE VALIDATION ------------------
    ${title}=    Get Title
    Log To Console    Page title is: ${title}

    IF    '${title}' == '${EXPECTED_TITLE}'
        Log To Console    ✅ Title matched
        screenshot.Take Screenshot
    ELSE
        Log To Console    ❌ Title mismatch
        screenshot.Take Screenshot
        Fail    Title validation failed
    END

    # ------------------ EMAIL FIELD VALIDATION ------------------
    ${email_visible}=    Run Keyword And Return Status
    ...    Wait For Elements State    ${EMAIL_FIELD}    visible    timeout=10s

    IF    ${email_visible}
        Log To Console    ✅ Email field is visible
        screenshot.Take Screenshot
        Fill Text    ${EMAIL_FIELD}    ${ADMIN_USER}
    ELSE
        Log To Console    ❌ Email field not visible
        screenshot.Take Screenshot
        Fail    Email field not found
    END

    # [Repeat this 'screenshot.' prefix for the rest of your IF/ELSE blocks]

    [Teardown]    Close Browser