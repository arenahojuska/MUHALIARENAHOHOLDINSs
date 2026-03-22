*** Settings ***




Resource        LoginTest.robot
Resource        purchase.robot
Resource        cart.robot

*** Test Cases ***
Login Flow

    Login
      [Teardown]    Close Browser
Purchase
    Login
    purchase
    [Teardown]    Close Browser
Cart
   Login
    purchase
    cart
    [Teardown]    Close Browser