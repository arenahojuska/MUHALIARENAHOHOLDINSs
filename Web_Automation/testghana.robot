
*** Settings ***
Library    SeleniumLibrary

*** Variables ***
${PROXY}    --proxy-server=197.255.125.12:80
${URL}      https://www.facebook.com/

*** Test Cases ***
Open Multichoice Portal With Ghana Proxy
    ${options}=    Evaluate    sys.modules['selenium.webdriver'].ChromeOptions()    sys, selenium.webdriver
    Call Method    ${options}    add_argument    ${PROXY}
    Create WebDriver    Chrome    options=${options}
    Go To    ${URL}
    Sleep    50000s
    Close Browser
