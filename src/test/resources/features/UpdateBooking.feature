@UPDATEBooking
Feature: Update Booking

  @TC05 @deleteBooking
  Scenario: Update a booking
    Given a booking with following details:
      | firstname | lastname | totalprice | depositpaid | additionalneeds |
      | Jim       | Brown    | 123        | true        | ice cream       |
    And following booking dates:
      | checkin    | checkout   |
      | 2022-07-01 | 2022-09-01 |

    When I have the booking
    And I want to update the booking with following details:
      | firstname | lastname | totalprice | depositpaid | additionalneeds |
      | John      | Doe      | 321        | false       | hot chocolate   |
    And following booking dates:
      | checkin    | checkout   |
      | 2022-07-01 | 2022-09-01 |
    And I update the booking

    Then the status code should be 200
    And response should contain new booking details

  @TC06 @deleteBooking
  Scenario: Partially Update a booking
    Given a booking with following details:
      | firstname | lastname | totalprice | depositpaid | additionalneeds |
      | Jim       | Brown    | 123        | true        | ice cream       |
    And following booking dates:
      | checkin    | checkout   |
      | 2022-07-01 | 2022-09-01 |

    When I have the booking
    And I want to update the booking with following details:
      | firstname | lastname |
      | John      | Doe      |
    And I partially update the booking

    Then the status code should be 200
    And response should contain following booking details:
      | firstname | John |
      | lastname  | Doe  |
