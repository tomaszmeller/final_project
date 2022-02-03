@addressAdd
Feature: Add address to account

  Scenario Outline: I can add address to already created user account
    Given  Active home page and existing account credentials
    When  I click SignIn button
    And  I log in with email <email> and psswd <psswd>
    And  I click ADD FIRST ADDRESS
    And  I fill fields: alias <alias> address <address> city <city> zipcode <zipcode> phone <phone> and choose country
    And  I submit changes by clicking SAVE
    Then  Success alert appears, comparing input and output data <alias> <address> <city> <zipcode> <phone>
    And I delete address
    Then I check if it's deleted
    And  I log out
    And  Close browser

    Examples:
      | email               | psswd      | alias | address          | city    | zipcode | phone     |
      | thomasbaye@baye.com | Qwerty1234 | Tom   | ul.Przykładowa24 | Gniezno | 62-200  | 987654321 |

