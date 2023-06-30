Feature: Find All Rides
  As a user
  I want to be able to find all the rides available

  Scenario: If the details are properly given to the request ride endpoint
    Given the current API "/" is available
    When I make a GET request to the API endpoint
    Then I should receive a successful response with status code 200

