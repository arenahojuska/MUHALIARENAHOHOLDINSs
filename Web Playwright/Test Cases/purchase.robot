*** Settings ***

Library    Browser    enable_auto_screenshot=${False}
Library    screenshot.py
Variables    testdata.py

*** Keywords ***
Purchase
    # 1. Navigate to the Shop page
    Wait For Elements State    xpath=//*[@id="root"]/nav/div[2]/a[2]    visible    timeout=10s
    Click    xpath=//*[@id="root"]/nav/div[2]/a[2]

    # 2. Check if the Shop Header is visible
    ${Shop_visible}=    Run Keyword And Return Status
    ...    Wait For Elements State    xpath=//*[@id="root"]/div/h2    visible    timeout=10s

    # 3. Handle the logic based on page visibility
    IF    ${Shop_visible}
        Log To Console    ✅ Shop page is visible. Starting product clicks...
        screenshot.Take Screenshot

        # 4. Loop through products 1 to 23
        FOR    ${index}    IN RANGE    1    24
            Scroll To Element    xpath=//*[@id="root"]/div/div[3]/div[${index}]/div[2]/button
            Click    xpath=//*[@id="root"]/div/div[3]/div[${index}]/div[2]/button
            Log To Console    Product ${index} clicked.
            Sleep    0.2s
        END
        Log To Console    ✅ All 23 products clicked successfully.

    ELSE
        Log To Console    ❌ Shop page failed to load
        screenshot.Take Screenshot
        Fail    Could not reach the shop page - stopping test.
    END

    # --- Post-Purchase Navigation and Validation ---
    screenshot.Take Screenshot
    # Scroll back up to find the Cart icon
    Scroll By    ${None}    -10000
    Click    xpath=//*[@id="root"]/nav/div[1]/div[3]/a/span

    # Scroll down to the total section
    Scroll By    ${None}    10000

    # Get the actual count from the UI
    ${actual_count}=    Get Text    xpath=//*[@id="root"]/div/div/div[25]/div/div/div[1]/span[2]
    Log To Console    The count found on screen is: ${actual_count}

    # IF/ELSE Validation for the Final Count
    IF    "${actual_count}" == "23"
        Log To Console    ✅ Validation Passed: Cart count is exactly 23!
        screenshot.Take Screenshot
    ELSE
        Log To Console    ❌ Validation Failed: Expected 23 but found ${actual_count}
        screenshot.Take Screenshot
        Fail    Cart total mismatch! Expected: 23, Actual: ${actual_count}
    END

    Sleep   2s