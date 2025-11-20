package steps;

import core.DriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.CarbCalculatorPage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarbohydrateCalculatorSteps {

    private WebDriver driver;
    private CarbCalculatorPage page;

    @Given("I am on the Carbohydrate Calculator page")
    public void i_am_on_the_carbohydrate_calculator_page() {
        driver = DriverFactory.getDriver();
        page = new CarbCalculatorPage(driver);
        page.open();
    }

    @And("I select {string}")
    public void i_select_units(String units) {
        page.selectUnits(units);
    }

    @And("I enter age {string}")
    public void i_enter_age(String age) {
        page.enterAge(age);
    }

    @And("I select gender {string}")
    public void i_select_gender(String gender) {
        page.selectGender(gender);
    }

    @And("I enter height {string} feet and {string} inches")
    public void i_enter_height_feet_and_inches(String feet, String inches) {
        page.enterHeightUS(feet, inches);
    }

    @And("I enter weight {string} pounds")
    public void i_enter_weight_pounds(String pounds) {
        page.enterWeightUS(pounds);
    }

    @And("I enter height {string} centimeters")
    public void i_enter_height_centimeters(String cm) {
        page.enterHeightMetric(cm);
    }

    @And("I enter weight {string} kilograms")
    public void i_enter_weight_kilograms(String kg) {
        page.enterWeightMetric(kg);
    }

    @And("I select activity level {string}")
    public void i_select_activity_level(String activity) {
        page.selectActivity(activity);
    }

    @When("I click the Calculate button")
    public void i_click_the_calculate_button() {
        page.clickCalculate();
    }

    @Then("I should see a carbohydrate result displayed")
    public void i_should_see_a_carbohydrate_result_displayed() {
        assertTrue("Expected a carbohydrate result, but none was found.", page.isResultDisplayed());
    }

    @And("I open the Settings panel")
    public void i_open_the_settings_panel() {
        page.openSettings();
    }

    @And("I choose the BMR formula {string}")
    public void i_choose_the_bmr_formula(String formula) {
        page.chooseBmrFormula(formula);
    }

    @And("I clear the body fat percentage field")
    public void i_clear_the_body_fat_percentage_field() {
        page.clearBodyFat();
    }

    @Then("I should see a validation message for missing body fat percentage")
    public void i_should_see_a_validation_message_for_missing_body_fat_percentage() {
        assertTrue("Expected validation for body fat percentage but did not find it.",
                page.hasBodyFatValidationMessage());
    }

    @When("I enter body fat percentage {string}")
    public void i_enter_body_fat_percentage(String bodyFat) {
        page.enterBodyFat(bodyFat);
    }

    @Then("no carbohydrate result should be displayed")
    public void no_carbohydrate_result_should_be_displayed() {
        assertFalse("Result should not be displayed when inputs are invalid.",
                page.isResultDisplayed());
    }
}
