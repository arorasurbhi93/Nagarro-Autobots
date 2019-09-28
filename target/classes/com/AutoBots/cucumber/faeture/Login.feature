@Autothon
Feature: Moive Rental Service Login Functionality


Scenario Outline: Login with valid admin Credential
    Given User is home page
    When  User Click on login link
    Then 	User enter valid "<username>" 
    And 	User enter password and "<password>"
    Then  User click on login button
    
Examples: 
      | username      | password |
      | administrator | tm77dac  |
      | hitesh        | tm77da   |

  