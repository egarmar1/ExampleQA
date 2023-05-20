@Cart
Feature: Cart test suite

  @testcase11
  @smoke
  Scenario: Delete product from the cart
    Given the user is on the home page
    And the user provide the username "standard_users" and password "secret_sauce"
    When the user clicks the login button
    And  the user adds to cart 2 random products
    And the user clicks the cart icon
    And the user clicks remove to a random product
    Then the product removed does not appear in the cart
