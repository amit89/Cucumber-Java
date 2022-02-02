package stepdefinitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverManager;

public class Login  {
	private final WebDriverManager drivermanager = new WebDriverManager();

	public Login()
	{
		PageFactory.initElements(drivermanager.getDriver(), this);
	}

	@Given("^User open the gamil login page$")
	public void user_open_the_gamil_login_page() throws Throwable {
		drivermanager.openPage("https://en.wikipedia.org/wiki/Main_Page");
	}

	@Given("^Enter the usernam and password$")
	public void enter_the_usernam_and_password() throws Throwable {
		drivermanager.enterData(By.xpath("//input[@id='searchInput']"),"USA");
		drivermanager.keyBoardAction(By.xpath("//input[@id='searchInput']"));
		Thread.sleep(5000);
	}

	@Then("^Validate that user has loggedin successsfuly$")
	public void validate_that_user_has_loggedin_successsfuly() throws Throwable {
		String actual_result = drivermanager.getText(By.cssSelector("h1[id = 'firstHeading']"));
		org.junit.Assert.assertEquals("There is mismatch in the values", "United States", actual_result);
	}
}

