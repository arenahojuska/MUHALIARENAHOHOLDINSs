*** Settings ***
Library    SeleniumLibrary
Library    String
Library    BuiltIn
Library    DateTime
Library    OperatingSystem

*** Variables ***
${XPATH1}    //*[@id="inventory_container"]/div/div[1]/div[2]/div[2]/div
${XPATH2}    //*[@id="inventory_container"]/div/div[2]/div[2]/div[2]/div
${XPATH3}    //*[@id="inventory_container"]/div/div[3]/div[2]/div[2]/div
${XPATH4}    //*[@id="inventory_container"]/div/div[4]/div[2]/div[2]/div
${XPATH5}    //*[@id="inventory_container"]/div/div[5]/div[2]/div[2]/div
${XPATH6}    //*[@id="inventory_container"]/div/div[6]/div[2]/div[2]/div
${EVIDENCE ROOT}    Web_Automation/Test_Evidence

*** Keywords ***
AddTocart
    ${buttons}=    Get WebElements    xpath=//*[@id="inventory_container"]//button
    ${clicked_count}=    Set Variable    0
    ${result}=    Run Keyword And Ignore Error    Handle Alert    accept

    FOR    ${btn}    IN    @{buttons}
        Click Element    ${btn}
        ${clicked_count}=    Evaluate    ${clicked_count} + 1
    END

    ${today}=        Get Current Date    result_format=%Y-%m-%d
    ${folder}=       Set Variable    ${EVIDENCE ROOT}/${today}
    Create Directory    ${folder}
    ${clean_name}=   Replace String    ${TEST NAME}    ${SPACE}    _
    ${file}=         Set Variable    ${CURDIR}/${folder}/${clean_name}_AddTocart.png
    ${result}=    Run Keyword And Ignore Error    Handle Alert    accept

    Execute JavaScript    window.scrollBy(0, -500)
    Click Element    xpath=//*[@id="shopping_cart_container"]/a
    Capture Page Screenshot    ${file}
    ${remove_buttons}=    Get WebElements    xpath=//*[@id="cart_contents_container"]//button

    ${count}=    Get Length    ${remove_buttons}
    Log    Total 'Remove' buttons found: ${count}
    Log    Total 'clicked' buttons found: ${clicked_count}

    ${remove_buttons}=    Get WebElements    xpath=//button[text()='Remove']
    Length Should Be    ${count}    ${clicked_count}


    Capture Page Screenshot    ${file}
    Sleep    20s
