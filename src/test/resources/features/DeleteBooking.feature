@DELETEBooking
Feature: Delete Booking

  @TC07
  Scenario: Delete booking
    Given a booking with following details:
      | firstname | lastname | totalprice | depositpaid | additionalneeds |
      | Jim       | Brown    | 123        | true        | ice cream       |
    And following booking dates:
      | checkin    | checkout   |
      | 2018-01-01 | 2019-01-01 |

    When I have the booking
    And I delete the booking
    Then the status code should be 201
