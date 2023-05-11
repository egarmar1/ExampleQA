@InventorySuite
Feature: Inventory test suite

  Background: Log in succesfully
    Given the user is on the home page
    And the user provide the username standard_user and password secret_sauce
    When the user clicks the login button
  @SixProducts
  Scenario: Verify there are 6 products
    Then the user is in the inventory page where it is shown 6 products

  @ProductExists
  Scenario: Verify that exists the product Sauce labs Bolt T-shirt in the inventory
    Then the user is in the inventory page where it is shown the product Sauce labs Bolt T-shirt

  @AddProduct
  Scenario: Add a product to the cart
    And the user adds to cart the product Sauce Labs Bolt T-shirt
    Then the icon of the cart shows the number 1

  @DeleteProduct
  Scenario: Delete a product from the inventory
    And the user adds to cart the product Sauce Labs Bolt T-shirt
    And the user clicks the button remove from the product Sauce Labs Bolt T-shirt
    Then the icon of the cart doesnt show a number

  @Add3products
  Scenario: Add 3 random products
    And the user adds to cart 3 random products
    And the user clicks the button remove from the product Sauce Labs Bolt T-shirt
    Then the icon of the cart shows the number 3

  @Order
  Scenario Outline: order products by filter
    And the user chooses the option "filter"
    Then the user views the products sorted by "filter"

  Examples:
  | filter              |
  | NAME (Z TO A)       |
  | PRICE (low to high) |
  | PRICE (high to low) |