package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class CarbCalculatorPage {

    private WebDriver driver;

    public CarbCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    private By ageField = By.name("cage");
    private By weightLbField = By.name("cpound");
    private By weightKgField = By.name("ckg");
    private By heightFeetField = By.name("cheightfeet");
    private By heightInchField = By.name("cheightinch");
    private By heightCmField = By.name("cheightmeter");
    private By activityDropdown = By.name("cactivity");
    private By bodyFatField = By.name("cbodyfat");

    private By calculateBtn = By.xpath("//input[@type='submit' and @value='Calculate']");
    private By settingsLink = By.xpath("//*[contains(text(),'Settings')]");

    public void open() {
        driver.get("https://www.calculator.net/carbohydrate-calculator.html");
    }

    public void selectUnits(String units) {
        String normalized = units.toLowerCase();

        if (normalized.contains("us")) {
            // US units is the default – just make sure we’re on the base page
            driver.get("https://www.calculator.net/carbohydrate-calculator.html");
        } else if (normalized.contains("metric")) {
            // Metric units view
            driver.get("https://www.calculator.net/carbohydrate-calculator.html?ctype=metric");
        } else if (normalized.contains("other")) {
            // Other units view (not currently used, but here for completeness)
            driver.get("https://www.calculator.net/carbohydrate-calculator.html?ctype=other");
        }
    }

    public void enterAge(String age) {
        WebElement ageInput = driver.findElement(ageField);
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void selectGender(String gender) {
        String value = gender.equalsIgnoreCase("male") ? "m" : "f";
        WebElement genderRadio = driver.findElement(By.cssSelector("input[name='csex'][value='" + value + "']"));

        // Use JavaScript click to avoid "element not interactable" in headless CI
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderRadio);
    }

    public void enterHeightUS(String feet, String inches) {
        WebElement feetInput = driver.findElement(heightFeetField);
        WebElement inchInput = driver.findElement(heightInchField);
        feetInput.clear();
        feetInput.sendKeys(feet);
        inchInput.clear();
        inchInput.sendKeys(inches);
    }

    public void enterHeightMetric(String cm) {
        WebElement heightCmInput = driver.findElement(heightCmField);
        heightCmInput.clear();
        heightCmInput.sendKeys(cm);
    }

    public void enterWeightUS(String pounds) {
        WebElement weightInput = driver.findElement(weightLbField);
        weightInput.clear();
        weightInput.sendKeys(pounds);
    }

    public void enterWeightMetric(String kg) {
        WebElement weightKgInput = driver.findElement(weightKgField);
        weightKgInput.clear();
        weightKgInput.sendKeys(kg);
    }

    public void selectActivity(String activity) {
        Select sel = new Select(driver.findElement(activityDropdown));
        sel.selectByVisibleText(activity);
    }

    public void openSettings() {
        driver.findElement(settingsLink).click();
    }

    public void chooseBmrFormula(String formula) {
        WebElement formulaRadio = driver.findElement(By.xpath("//label[contains(.,'" + formula + "')]"));
        formulaRadio.click();
    }

    public void clearBodyFat() {
        driver.findElement(bodyFatField).clear();
    }

    public void enterBodyFat(String value) {
        WebElement bodyFatInput = driver.findElement(bodyFatField);
        bodyFatInput.clear();
        bodyFatInput.sendKeys(value);
    }

    public void clickCalculate() {
        driver.findElement(calculateBtn).click();
    }

    public boolean isResultDisplayed() {
        String pageSource = driver.getPageSource().toLowerCase();
        return pageSource.contains("grams") || pageSource.contains("carbohydrate");
    }

    public boolean hasBodyFatValidationMessage() {
        String pageSource = driver.getPageSource().toLowerCase();
        return pageSource.contains("body fat");
    }
}
