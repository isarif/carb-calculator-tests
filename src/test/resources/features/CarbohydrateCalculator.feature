Feature: Carbohydrate Calculator – End to end flows
  As a user of calculator.net
  I want to calculate my daily carbohydrate needs
  So that I can understand how many carbs to eat per day

  Background:
    Given I am on the Carbohydrate Calculator page

  @USUnits @HappyPath
  Scenario: Calculate carbs using US units with valid inputs
    And I select "US units"
    And I enter age "30"
    And I select gender "male"
    And I enter height "5" feet and "8" inches
    And I enter weight "160" pounds
    And I select activity level "Sedentary"
    When I click the Calculate button
    Then I should see a carbohydrate result displayed

  @MetricUnits @HappyPath
  Scenario: Calculate carbs using metric units with valid inputs
    And I select "metric units"
    And I enter age "35"
    And I select gender "female"
    And I enter height "165" centimeters
    And I enter weight "60" kilograms
    And I select activity level "Light"
    When I click the Calculate button
    Then I should see a carbohydrate result displayed

  @BMRSettings @KatchMcArdle
  Scenario: BMR Katch-McArdle requires body fat percentage
    And I select "US units"
    And I enter age "30"
    And I select gender "male"
    And I enter height "5" feet and "10" inches
    And I enter weight "180" pounds
    And I select activity level "Moderate"
    And I open the Settings panel
    And I choose the BMR formula "Katch-McArdle"
    And I clear the body fat percentage field
    When I click the Calculate button
    Then I should see a validation message for missing body fat percentage
    When I enter body fat percentage "18"
    And I click the Calculate button
    Then I should see a carbohydrate result displayed

  @Negative @AgeValidation
  Scenario Outline: Invalid age values are rejected
    And I select "US units"
    And I enter age "<age>"
    And I select gender "male"
    And I enter height "5" feet and "8" inches
    And I enter weight "160" pounds
    And I select activity level "Sedentary"
    When I click the Calculate button
    Then no carbohydrate result should be displayed

    Examples:
      | age |
      | 17  |
      | 81  |
      | abc |

  @Negative @RequiredField
  Scenario: Missing weight prevents calculation
    And I select "US units"
    And I enter age "30"
    And I select gender "male"
    And I enter height "5" feet and "8" inches
    And I select activity level "Sedentary"
    When I click the Calculate button
    Then no carbohydrate result should be displayed

  @Regression @Recalculation
  Scenario: Recalculate after changing only activity level
    And I select "metric units"
    And I enter age "32"
    And I select gender "female"
    And I enter height "170" centimeters
    And I enter weight "65" kilograms
    And I select activity level "Sedentary"
    When I click the Calculate button
    Then I should see a carbohydrate result displayed
    And I select activity level "Very active"
    When I click the Calculate button
    Then I should see a carbohydrate result displayed
