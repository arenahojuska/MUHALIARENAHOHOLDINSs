*** Settings ***
Library    DataDriver    file=ANDROID TESTING/Book11.xlsx    file_search_strategy=None
Resource    open.robot
Resource    Loginapp.robot


*** Test Cases ***
Test Open
    Open_App
    loginn