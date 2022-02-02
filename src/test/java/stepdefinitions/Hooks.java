package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.WebDriverManager;

public class Hooks {

    @Before(order = 0)
    public void beforeScenario(){
        System.out.println("Running before scernario step");
    }

    @After(order = 0)
    public void afterScenario()
    {
        WebDriverManager driverManager = new WebDriverManager();
        driverManager.getDriver();
        driverManager.closeDriver();
        System.out.println("Running after scenario step");
    }
}
