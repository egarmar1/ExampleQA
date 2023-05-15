@Checkout
@order
Feature: Checkout test suite

  Background: Log in successfully
    Given the user is on the home page
    And the user provide the username "standard_user" and password "secret_sauce"
    When the user clicks the login button

  @testcase12
  @CheckPrice
  Scenario: Check that the final price is the sum of the products from the cart
    And  the user adds to cart 3 random products
    And the user clicks the cart icon
    And the user clicks the button checkout
    And the user fills out all the fields of the form
    And the user clicks the button continue
    Then the order price(Item Total) is correctly displayed which is the sum of the products

  @testcase13
  @smoke
  @PlaceOrder
  Scenario: Complete an order
    And the user adds to cart 1 random products
    And the user clicks the cart icon
    And the user clicks the button checkout
    And the user fills out all the fields of the form
    And the user clicks the button continue
    And the user clicks the button finish
    Then the user is on a web page displaying the message: "Your order has been dispatched, and will arrive just as fast as the pony can get there!"