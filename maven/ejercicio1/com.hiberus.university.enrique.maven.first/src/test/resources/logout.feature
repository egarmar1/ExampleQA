@Logout
Feature: Logout test suite

  Scenario: Logout successfully
    Given the user is on the home page
    And the user provide the username standard_user and password secret_sauce
    When the user clicks the login button
    And the user clicks the dropdown menu
    And the user clicks the logout link
    Then the user is on the login page
