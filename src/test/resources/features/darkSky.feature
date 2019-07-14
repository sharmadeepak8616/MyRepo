@darkSky @web
Feature: Dark Sky Feature

  Background:
    Given I am on Dark Sky webpage

  @tempbar-increment
  Scenario: Verify timeline is displayed in correct format
    Then I verify timeline is displayed with 2 hours incremented for next 11 hours for America/Los Angeles
    And close browser

  @tempRangeInBottom
  Scenario: Verify individual day temp timeline
    When I expand todays timeline
    Then I verify lowest and highest temp is displayed correctly
    And close browser