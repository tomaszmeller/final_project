@itemBuy
Feature: Buy some items from shop

  Scenario Outline: I can buy some item from site
    Given Active home page with log in user login <email> psswd <psswd>
    When I choose Hummingbird printed sweater
    And I choose size and quantity
    And I add to cart
    And I go to check out
    And I confirm address
    And I choose Shipping Method
    And I choose Payment Method
    Then Item is bought, I take a screenshot of confirmation
    And I log out my account
    And I close browser

    Examples:
      | email               | psswd      |
      | thomasbaye1@baye.com | Qwerty1234 |
