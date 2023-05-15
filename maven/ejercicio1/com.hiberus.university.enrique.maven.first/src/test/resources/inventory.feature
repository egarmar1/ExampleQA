@Inventory
Feature: Inventory test suite

  Background: Log in succesfully
    Given the user is on the home page
    And the user provide the username "standard_user" and password "secret_sauce"
    When the user clicks the login button

  @SixProducts
  @testcase03
  Scenario: Verify there are 6 products
    Then the user is in the inventory page where it is shown 6 products

  @ProductExists
  @testcase04
  Scenario: Verify that exists the product "Sauce labs Bolt T-shirt" in the inventory
    Then the user is in the inventory page where it is shown the product "Sauce Labs Bolt T-Shirt"

  @AddProduct
  @testcase05
  Scenario: Add a product to the cart
    And the user adds to the cart the product "Sauce Labs Bolt T-shirt"
    Then the icon of the cart shows the value "1"

  @DeleteProduct
  @testcase06
  Scenario: Delete a product from the inventory
    And the user adds to the cart the product "Sauce Labs Bolt T-shirt"
    And the user clicks the button remove from the product "Sauce Labs Bolt T-shirt"
    Then the icon of the cart shows the value ""

  @Add3products
  @testcase07
  Scenario: Add 3 random products
    And the user adds to the cart 3 random products
    Then the icon of the cart shows the value "3"

  @sort
  Scenario Outline: order products by filter
    And the user chooses the option "<filter>"
    Then the user views the products sorted by "<filter>"

  @testcase08
  Examples:
  | filter              |
  | NAME (Z TO A)       |

  @testcase09
  Examples:
  | filter              |
  | PRICE (low to high) |

  @testcase10
  Examples:
  | filter              |
  | PRICE (high to low) |