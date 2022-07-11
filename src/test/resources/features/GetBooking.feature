@GETBooking
Feature: Get Booking

  @TC03
  Scenario: Get all booking IDs
    When I make request to get all booking IDs
    Then the status code should be 200
    And response should contain all booking IDs

  @TC04
  Scenario: Get all booking IDs by filtering them
    When I make request to get all booking IDs by following filters:
      | firstname | Jim   |
      | lastname  | Brown |

    Then the status code should be 200
    And all IDs returned should belong to bookings with following attributes:
      | firstname | Jim   |
      | lastname  | Brown |
