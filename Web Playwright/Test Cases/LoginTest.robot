*** Settings ***

Library    Browser    enable_auto_screenshot=${False}
Library    Browser    timeout=60s
Library    screenshot.py
Variables    testdata.py



*** Keywords ***
Login

    New Browser    chromium    headless=False
    New Context    viewport={'width': 1920, 'height': 1300}
    New Page    ${BASE_URL}    wait_until=domcontentloaded

    screenshot.Take Screenshot

    # ------------------ TITLE VALIDATION ------------------
    ${title}=    Get Title
    Log To Console    Page title is: ${title}

    IF    '${title}' == '${TITLE}'
        Log To Console     Title matched
        screenshot.Take Screenshot
    ELSE
        Log To Console     Title mismatch
        screenshot.Take Screenshot
        Fail    Title validation failed
    END

    # ------------------ EMAIL FIELD VALIDATION ------------------
    ${email_visible}=    Run Keyword And Return Status
    ...    Wait For Elements State    id=email    visible    timeout=10s

    IF    ${email_visible}
        Log To Console     Email field is visible
        screenshot.Take Screenshot
        Fill Text    id=email    admin@example.com
    ELSE
        Log To Console    ❌ Email field not visible
        screenshot.Take Screenshot
        Fail    Email field not found
    END

    # ------------------ PASSWORD FIELD VALIDATION ------------------
    ${password_visible}=    Run Keyword And Return Status
    ...    Wait For Elements State    id=password    visible    timeout=10s

    IF    ${password_visible}
        Log To Console    ✅ Password field is visible
        screenshot.Take Screenshot
        Fill Text    id=password    Pass@123
    ELSE
        Log To Console    ❌ Password field not visible
        screenshot.Take Screenshot
        Fail    Password field not found
    END

    # ------------------ LOGIN BUTTON VALIDATION ------------------
    ${login_btn_visible}=    Run Keyword And Return Status
    ...    Wait For Elements State    xpath=//button[@type="submit"]    visible    timeout=10s

    IF    ${login_btn_visible}
        Log To Console    ✅ Login button is visible
        screenshot.Take Screenshot
        Click    xpath=//button[@type="submit"]
    ELSE
        Log To Console    ❌ Login button not visible
        screenshot.Take Screenshot
        Fail    Login button missing
    END

    # ------------------ LOGIN RESULT VALIDATION ------------------
    ${login_status}=    Run Keyword And Return Status
    ...    Wait For Elements State    text="Logout"    visible    timeout=10s

    IF    ${login_status}
        Log To Console    ✅ Login successful
        screenshot.Take Screenshot
    ELSE
        Log To Console    ❌ Login failed
        screenshot.Take Screenshot
        Fail    Login failed
    END


