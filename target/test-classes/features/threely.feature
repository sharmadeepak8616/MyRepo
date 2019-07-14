@threely @web
Feature: Threely Feature

  Background:
    Given I am on Threely login page

  @validLogin
  Scenario: Verify valid login
    When I enter username as “n12345@test.com” and password as “123456”
    And I click on submit button
    Then I verify logout button is displayed
