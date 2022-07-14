@CREATEBooking
Feature: Create Booking

  @TC01 @deleteBooking
  Scenario: Create a booking WITH ALL REQUIRED FIELDS
    Given a booking with following details:
      | firstname | lastname | totalprice | depositpaid | additionalneeds |
      | Jim       | Brown    | 123        | true        | ice cream       |
    And following booking dates:
      | checkin    | checkout   |
      | 2018-01-01 | 2019-01-01 |

    When I create the booking
    Then the status code should be 200
    And response should contain booking details

  @TC02
  Scenario Outline: Create a booking <test_case>
    Given a booking with following details:
      | firstname   | lastname   | totalprice   | depositpaid   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <additionalneeds> |
    And following booking dates:
      | checkin   | checkout   |
      | <checkin> | <checkout> |

    When I create the booking '<test_case>'
    Then the status code should be 500

    Examples:
      | test_case             | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | WITHOUT NAME          |           | Brown    | 123        | false       | 2022-01-01 | 2022-03-01 | ice cream       |
      | WITHOUT LAST NAME     | Jim       |          | 123        | false       | 2022-01-01 | 2022-03-01 | ice cream       |
      | WITHOUT BOOKING DATES | Jim       | Brown    | 123        | false       |            |            | ice cream       |
