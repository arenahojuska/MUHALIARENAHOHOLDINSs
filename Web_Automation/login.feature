Feature: Login Feature

  Scenario: Successful login
    Given the user opens the login page
    When the user enters valid credentials
    Then the user should be redirected to the homepage
