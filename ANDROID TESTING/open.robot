*** Settings ***
Library    AppiumLibrary

*** Keywords ***
Open_App
    Open Application    http://127.0.0.1:4723
    ...    platformName=Android
    ...    automationName=UiAutomator2
    ...    deviceName=emulator-5556
    ...    appPackage=fi.android.takealot
    ...    appActivity=fi.android.takealot.presentation.splash.view.impl.ViewSplashScreenActivity
    Sleep    2s

