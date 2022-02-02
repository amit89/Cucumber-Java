package base;

import org.ini4j.Wini;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;
import java.util.Set;

public class Driver  {
    WebDriver driver;
    String browserName;


    public Driver() {
//        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
//        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    private WebDriver createDriver()
    {

        if(browserName.equalsIgnoreCase("chrome"))
        {
            readEnvironmentFile("config/environment.ini","chromeBrowser","chrome_key", "chrome_value");
        }
        if(browserName.equalsIgnoreCase("edge"))
        {
            readEnvironmentFile("config/environment.ini","edgeBrowser","edge_key", "edge_value");
        }
        else {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
           driver = new ChromeDriver();
        }
        return driver;
    }

    public WebDriver getDriver()
    {
        if(driver == null) driver = createDriver();
        return driver;

    }

    public void readEnvironmentFile(String filePath, String propertyName, String key1, String key2 ) {
        try {
            Wini ini = new Wini(new File(filePath));
            String browserKey = ini.get(propertyName, key1, String.class);
            String browserValue = ini.get(propertyName, key2, String.class);
            System.setProperty(browserKey, browserValue);
          driver = new ChromeDriver();

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void launchBrowser(String browserName)
    {
        if(browserName.equalsIgnoreCase("chrome"))
        {
            readEnvironmentFile("config/environment.ini","chromeBrowser","chrome_key", "chrome_value");
        }
        if(browserName.equalsIgnoreCase("edge"))
        {
            readEnvironmentFile("config/environment.ini","edgeBrowser","edge_key", "edge_value");
        }
        else {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
          driver = new ChromeDriver();
        }
    }
}
