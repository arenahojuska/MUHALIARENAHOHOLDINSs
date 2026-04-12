*** Settings ***
Library    Browser    enable_auto_screenshot=${False}
Library    screenshot.py
Variables    testdata.py
Library    Collections
Library    String

*** Keywords ***
Cart
    ${total}=    Set Variable    0.0

    FOR    ${i}    IN RANGE    2    25
        ${current_xpath}=    Set Variable    xpath=//*[@id="root"]/div/div/div[${i}]/p
        ${text}=    Get Text    ${current_xpath}
        Log To Console    Index ${i} raw text: ${text}

        # 1. Clean the string: Remove R and any non-digits except dot
        ${clean}=    Evaluate    re.sub(r'[^0-9.]', '', """${text}""")    modules=re
        Log To Console    Cleaned value: ${clean}

        # 2. Validation: Check if it's a valid number with a decimal part
        ${found_price}=    Evaluate    re.search(r"\\d+\\.\\d{2}", """${clean}""") is not None    modules=re

        IF    ${found_price}
            Log To Console     Adding price: ${clean}
            # Add and update total
            ${total}=    Evaluate    ${total} + float(${clean})
        ELSE
            Log To Console     Skipping: ${text} (Cleaned: ${clean})
        END
    END

    # Format the total to 2 decimal places
    ${formatted_total}=    Evaluate    "{:.2f}".format(${total})

    Log To Console    ==============================
    Log To Console     Final Calculated Total: R${formatted_total}
    Log To Console    ==============================


    # --- Now get the UI cart total ---
    ${ui_total_text}=    Get Text    xpath=//*[@id="root"]/div/div/div[25]/div/div/div[2]/span[2]
    Log To Console    Cart UI shows: ${ui_total_text}

    # Clean the UI cart total
    ${ui_clean}=    Evaluate    re.sub(r'[^0-9.]', '', """${ui_total_text}""")    modules=re
    ${ui_total}=    Convert To Number    ${ui_clean}

    # Format UI total to 2 decimals
    ${formatted_ui_total}=    Evaluate    "{:.2f}".format(${ui_total})

    Log To Console    Formatted Cart UI Total: R${formatted_ui_total}

    # --- Compare calculated vs shown totals ---
    Should Be Equal    ${formatted_total}    ${formatted_ui_total}

    Log To Console     Cart total matches UI (both R${formatted_total})




    # --- IF / ELSE validation with screenshots ---
    IF    '${formatted_total}' == '${formatted_ui_total}'
        Log To Console     Cart total matches UI (R${formatted_total})
                screenshot.Take Screenshot

    ELSE
        Log To Console     Cart total mismatch! Expected UI: R${formatted_ui_total}, Calculated: R${formatted_total}
        screenshot.Take Screenshot
        Fail    Cart total did not match UI
    END

    [Return]    ${formatted_total}
    Sleep    3s

