package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

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

    private void setInput(By locator, String value) {
        WebElement el = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", el, value);
    }
    
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
        setInput(ageField, age);
    }

    public void selectGender(String gender) {
        String value = gender.equalsIgnoreCase("male") ? "m" : "f";
        WebElement genderRadio = driver.findElement(By.cssSelector("input[name='csex'][value='" + value + "']"));

        // Use JavaScript click to avoid "element not interactable" in headless CI
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderRadio);
    }

    public void enterHeightUS(String feet, String inches) {
        setInput(heightFeetField, feet);
        setInput(heightInchField, inches);
    }

    public void enterHeightMetric(String cm) {
        setInput(heightCmField, cm);
    }

    public void enterWeightUS(String pounds) {
        setInput(weightLbField, pounds);
    }

    public void enterWeightMetric(String kg) {
        setInput(weightKgField, kg);
    }

    public void selectActivity(String activity) {
        Select sel = new Select(driver.findElement(activityDropdown));
        String target = activity.toLowerCase();

        for (WebElement opt : sel.getOptions()) {
            String txt = opt.getText().toLowerCase();
            if (txt.startsWith(target) || txt.contains(target)) {
                opt.click();
                return;
            }
        }

        throw new RuntimeException("Could not find activity option matching: " + activity);
    }

    public void openSettings() {
        driver.findElement(settingsLink).click();
    }

    public void chooseBmrFormula(String formula) {
        WebElement formulaRadio = driver.findElement(By.xpath("//label[contains(.,'" + formula + "')]"));
        formulaRadio.click();
    }

    public void clearBodyFat() {
        setInput(bodyFatField, "");
    }

    public void enterBodyFat(String value) {
        setInput(bodyFatField, value);
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
