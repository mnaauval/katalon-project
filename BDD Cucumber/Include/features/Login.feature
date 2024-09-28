Feature: Login

  #Scenario: User login with valid credentials
  #Given User navigate to Login Page
  #When User input username
  #And User input password
  #And User click login button
  #Then User validate success login
  
  Scenario Outline: User login with invalid credentials
    Given User navigate to Login Page
    When User input username <username>
    And User input password <password>
    And User click login button
    Then User validate error login <error>

    Examples: 
      | username        | password                 | error                                                       |
      | standard_user   | o+tS4OuGt32nW2fw4d97xA== | Username and password do not match any user in this service |
      | locked_out_user | qcu24s4901FyWDTwXGr6XA== | Sorry, this user has been locked out.                       |
      | premium_user    | qcu24s4901FyWDTwXGr6XA== | Username and password do not match any user in this service |
      |                 | o+tS4OuGt32nW2fw4d97xA== | Username is required                                        |
      | standard_user   |                          | Password is required                                        |
