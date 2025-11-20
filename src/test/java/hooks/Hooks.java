package hooks;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void beforeScenario() {
        WebDriver driver = DriverFactory.getDriver();
    }

    @After
    public void afterScenario() {
        DriverFactory.quitDriver();
    }
}
