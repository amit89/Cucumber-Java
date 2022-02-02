package utils;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import enums.DriverType;
import enums.EnvironmentType;

public class WebDriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case SIT : driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        String preferredDriver = System.getProperty("browser", "chrome");
        switch (preferredDriver.toLowerCase()) {
            case "firefox" : driver = new FirefoxDriver();
                break;
            case "chrome" :
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
                driver = new ChromeDriver();
                break;
            case "EDGE" : driver = new EdgeDriver();
                break;
        }
        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }

    public void clickonelement(By by)
    {
        driver.findElement(by).click();
    }

    public void openPage(String url)
    {
        driver.get(url);
    }

    public void enterData(By by, String value)
    {
        driver.findElement(by).sendKeys(value);
    }

    public void keyBoardAction(By by)
    {
        driver.findElement(by).sendKeys(Keys.ENTER);
    }

    public String getText(By by)
    {
        String textValue = driver.findElement(by).getText();
        return textValue;
    }
}