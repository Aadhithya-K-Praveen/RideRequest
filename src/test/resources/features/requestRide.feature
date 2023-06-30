Feature: Request Ride
  As a user
  I want to be able to request new rides

  Scenario: If the details are given properly
    Given the current API "/" is available
    When I make a POST request to the "/rides/request" API endpoint
    Then I should receive a successful response with "Request successful"

