@web @regression @Hotels
Feature: Testing Hotels.com features

  Background:
    Given I am on default locations search result screen

  @Hotels-1
  Scenario Outline: Verify user can only view the result by selected property class
    When I select property star <stars>
    Then I verify system displays only <stars> hotels on search result

    Examples:
      | stars |
      | 5 |
      | 4 |
      | 3 |

  @Hotels-2
  Scenario Outline: List of all of hotel within 10 miles radius of airport or downtown
    When I select property star <stars>
    Then I verify system displays all hotels within 10 miles radius of Newark, NJ (EWR-Liberty Intl.) airport
    And I verify Hilton Hotel is within radius

    Examples:
      | stars |
      | 5 |
      | 4 |